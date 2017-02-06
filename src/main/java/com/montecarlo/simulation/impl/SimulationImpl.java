package com.montecarlo.simulation.impl;

import com.montecarlo.simulation.ISimulation;
import com.google.common.math.Quantiles;
import java.util.Collection;

/**
 * This class is used to get Simulation results. If the execution is successful it returns the value.
 * If simulation failed due some exception, it makes hasExceptions true and exceptions are stored in collections.
 * Created by lmahabaleshwara on 2/4/17.
 */
public class SimulationImpl implements ISimulation {

    private double[] dataSet;
    private boolean hasExceptions;
    private Collection<Throwable> exceptions;

    public SimulationImpl(double[] dataSet, boolean hasExceptions, Collection<Throwable> exceptions){
        this.dataSet=dataSet;
        this.hasExceptions=hasExceptions;
        this.exceptions=exceptions;
    }

    @Override
    public double getMedian() {
        if (hasExceptions) return Double.NaN;
        if (dataSet == null || dataSet.length == 0) return Double.NaN;
        double myMedian = Quantiles.median().compute(dataSet);
        return myMedian;
    }

    @Override
    public double getPercentile(int percentileValue) {
        if (hasExceptions) return Double.NaN;
        if (percentileValue < 0 || percentileValue > 100) return Double.NaN;

        if (dataSet == null || dataSet.length == 0) return Double.NaN;
        if (percentileValue == 0) return dataSet[0];

        double myPercentile = Quantiles.percentiles().index(percentileValue).compute(dataSet);
        return myPercentile;
    }

    @Override
    public boolean hasExceptions() {
        return false;
    }

    @Override
    public Collection<Throwable> getExceptions() {
        return null;
    }
}
