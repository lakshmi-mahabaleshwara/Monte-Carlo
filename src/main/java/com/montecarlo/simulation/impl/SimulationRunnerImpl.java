package com.montecarlo.simulation.impl;

import com.montecarlo.model.IterationParameters;
import com.montecarlo.model.Portfolio;
import com.montecarlo.simulation.ISimulation;
import com.montecarlo.util.MonteCarloSimulationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

/** This class runs the monte carlo simulation for given portfolio.
 * Created by lmahabaleshwara on 2/4/17.
 */
public class SimulationRunnerImpl implements Callable<ISimulation> {

    private final Portfolio portfolio;
    private final IterationParameters simulationIteration;

    public SimulationRunnerImpl(Portfolio portfolio, IterationParameters simulationIteration){
        this.portfolio=portfolio;
        this.simulationIteration=simulationIteration;
    }


    /**
     * Calculates the entire set of projected values for portfolio.
     * Each iteration is run as a separate thread.
     */
    @Override
    public ISimulation call() throws Exception {
        ExecutorService execService = Executors.newFixedThreadPool(simulationIteration.getNumberOfIterationThreads());
        CompletionService<Double> completionService = new ExecutorCompletionService<Double>(execService);
        double[] projectedPortfolioValues = new double[simulationIteration.getNumberOfIterations()];
        Collection<Throwable> exceptions = new ArrayList<Throwable>();

        for (int i=0; i<simulationIteration.getNumberOfIterations(); i++) {
            Callable<Double> iteration = new SimulationIteratorImpl(portfolio, simulationIteration);
            completionService.submit(iteration);
        }

        for (int i=0; i<simulationIteration.getNumberOfIterations(); i++) {
            try {
                Future<Double> nextIterationResult = completionService.poll(
                        simulationIteration.getFetchIterationResultTimeout(),
                        TimeUnit.MILLISECONDS);
                if (nextIterationResult != null)
                    projectedPortfolioValues[i] = nextIterationResult.get();
                else {
                    projectedPortfolioValues[i] = Double.NaN;
                    exceptions.add(
                            new MonteCarloSimulationException("Timed out occurred while waiting"));
                }
            } catch (InterruptedException e) {
                projectedPortfolioValues[i] = Double.NaN;
                exceptions.add(new MonteCarloSimulationException("Simulation was interrupted.", e));
            } catch (ExecutionException e) {
                projectedPortfolioValues[i] = Double.NaN;
                exceptions.add(new MonteCarloSimulationException("Failed to get result of iteration.", e));
            }
        }

        execService.shutdown();
        return new SimulationImpl(projectedPortfolioValues, exceptions.size() != 0, exceptions);
    }
}
