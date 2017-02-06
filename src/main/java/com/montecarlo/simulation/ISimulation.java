package com.montecarlo.simulation;

import java.util.Collection;

/**
 * Interface to define the API contract
 * Created by lmahabaleshwara on 2/4/17.
 */
public interface ISimulation {
    public double getMedian();
    public double getPercentile(int percentileValue);
    public boolean hasExceptions();
    public Collection<Throwable> getExceptions();
}
