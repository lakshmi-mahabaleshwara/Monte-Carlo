package com.montecarlo.model;

/**
 * This class holds all iteration data
 * Created by lmahabaleshwara on 2/4/17.
 */
public class IterationParameters {

    private final double initialValue;
    private final double rateOfInflation;
    private final int numberOfYears;
    private final int numberOfIterations;
    private final int numberOfIterationThreads;
    private final long fetchIterationResultTimeout;

    public IterationParameters(double initialValue, double interestRate, int numberOfYears,
                               int numberOfIterations, int numberOfIterationThreads, long fetchIterationResultTimeout) {
        this.initialValue = initialValue;
        this.rateOfInflation = interestRate;
        this.numberOfYears = numberOfYears;
        this.numberOfIterations=numberOfIterations;
        this.numberOfIterationThreads=numberOfIterationThreads;
        this.fetchIterationResultTimeout=fetchIterationResultTimeout;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public double getRateOfInflation() {
        return rateOfInflation;
    }

    public long getFetchIterationResultTimeout() {
        return fetchIterationResultTimeout;
    }

    public int getNumberOfIterationThreads() {
        return numberOfIterationThreads;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }
}
