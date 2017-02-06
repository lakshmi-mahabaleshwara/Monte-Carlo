package com.montecarlo.simulation.impl;

import com.montecarlo.model.IterationParameters;
import com.montecarlo.model.Portfolio;

import java.util.Random;
import java.util.concurrent.Callable;

/** This class computes the monte carlo simulations for given num of years for single iteration.
 * Created by lmahabaleshwara on 2/4/17.
 */
public class SimulationIteratorImpl implements Callable<Double> {

    private final Portfolio portfolio;
    private final IterationParameters iterationParameters;

    public SimulationIteratorImpl(Portfolio portfolio, IterationParameters iterationParameters) {
        this.portfolio = portfolio;
        this.iterationParameters = iterationParameters;
    }

    @Override
    public Double call() throws Exception {
        double portfolioValue = iterationParameters.getInitialValue();
        Random random = new Random();

        for (int i = 0; i < iterationParameters.getNumberOfYears(); i++) {
            double yearEndReturn =portfolio.getReturns() + portfolio.getRisk() * random.nextGaussian();
            double inflationAdjustedPercentageReturn =
                    (1.0 + yearEndReturn/100.0) / (1.0 + iterationParameters.getRateOfInflation()/100.0) - 1.0;
            portfolioValue += (portfolioValue * inflationAdjustedPercentageReturn);
        }
        return portfolioValue;
    }
}
