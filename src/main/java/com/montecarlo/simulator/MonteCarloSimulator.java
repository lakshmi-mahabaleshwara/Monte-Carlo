package com.montecarlo.simulator;

import com.montecarlo.model.IterationParameters;
import com.montecarlo.model.Portfolio;
import com.montecarlo.simulation.ISimulation;
import com.montecarlo.simulation.impl.SimulationRunnerImpl;
import com.montecarlo.util.Constants;
import com.montecarlo.util.PortfolioType;

import java.util.*;
import java.util.concurrent.*;


/**
 * This class runs Monte-Carlo simulations on a collection of portfolios and generates the comparable results of projected values.
 * Created by lmahabaleshwara on 2/4/17.
 */
public class MonteCarloSimulator {

    private List<Portfolio> portfolios;
    private IterationParameters iterationParameters;

    MonteCarloSimulator(){
        initializePortfolios();
        initializeSimulationIterations();
    }

    private void initializePortfolios() {
        this.portfolios = new ArrayList<Portfolio>();
        Portfolio conservativePortfolio = new Portfolio(
                PortfolioType.CONSERVATIVE,
                Constants.CONSERVATIVE_RETURNS,
                Constants.CONSERVATIVE_RISK);
        Portfolio aggressivePortfolio = new Portfolio(
                PortfolioType.AGGRESSIVE,
                Constants.AGGRESSIVE_RETURNS,
                Constants.AGGRESSIVE_RISK);
        portfolios.add(conservativePortfolio);
        portfolios.add(aggressivePortfolio);
    }

    private void initializeSimulationIterations() {
        this.iterationParameters = new IterationParameters(
                Constants.PORTFOLIO_INITIAL_VALUE,
                Constants.INFLATION_RATE,
                Constants.YEARS_PER_ITERATION,
                Constants.NUM_OF_ITERATIONS,
                Constants.NUM_OF_ITERATION_THREADS,
                Constants.FETCH_ITERATION_RESULT_TIMEOUT_MS);
    }

    public void computeSimulations() {
        ExecutorService execService =
                Executors.newFixedThreadPool(Constants.NUM_OF_SIMULATION_THREADS);
        CompletionService<ISimulation> completionService =
                new ExecutorCompletionService<ISimulation>(execService);
        Map<Future<ISimulation>, Portfolio> portfolioForSimulationTask = new HashMap<>();

        for (Portfolio portfolio: portfolios) {
            Callable<ISimulation> simulationRunner = new SimulationRunnerImpl(
                    portfolio,
                    iterationParameters);
            Future<ISimulation> future = completionService.submit(simulationRunner);
            portfolioForSimulationTask.put(future, portfolio);
        }

        printHeaders();

        for (int i=0; i<portfolios.size(); i++) {
            Portfolio portfolioForFuture = null;
            try {
                Future<ISimulation> future = completionService.take();
                portfolioForFuture = portfolioForSimulationTask.get(future);
                printResults(future, portfolioForFuture);
            } catch (Exception e) {
                if (portfolioForFuture == null) {
                    System.out.println("An exception was encountered running the simulation.\n" + e.getMessage());
                } else {
                    System.out.println("An exception was encountered running the simulation for portfolio <"
                            + portfolioForFuture.getPortfolioType().name() + ">.\n" + e.getMessage());
                }
            }
        }
        execService.shutdown();

    }

    private void printHeaders() {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.US);
        formatter.format("%15s", "Portfolio Type");
        formatter.format("%25s", "Median 20th Year ($)");
        formatter.format("%25s", "10% Best Case ($)");
        formatter.format("%25s", "10% Worst Case ($)");
        System.out.println(sb.toString());
        formatter.close();
    }

    private void printResults(Future<ISimulation> future, Portfolio portfolio)
            throws InterruptedException, ExecutionException {
        ISimulation simulationResults = future.get();
        if (simulationResults == null) {
            System.out.println("A problem was encountered running the simulation for portfolio <"
                    + portfolio.getPortfolioType().name() + ">.");
            return;
        }
        if (simulationResults.hasExceptions()) {
            System.out.println("Simulation did not complete successfully. The following <"
                    + simulationResults.getExceptions().size()
                    + "> exceptions occurred ...");
            for (Throwable t : simulationResults.getExceptions())
                System.out.println(t.getMessage());
        } else {
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb, Locale.US);
            formatter.format("%15s", portfolio.getPortfolioType().getType());
            formatter.format("%,25.2f", simulationResults.getMedian());
            formatter.format("%,25.2f", simulationResults.getPercentile(90));
            formatter.format("%,25.2f", simulationResults.getPercentile(10));
            System.out.println(sb.toString());
            formatter.close();
        }
    }

    public static void main(String[] args) {
        MonteCarloSimulator portfolioComparator = new MonteCarloSimulator();
        portfolioComparator.computeSimulations();
    }

}
