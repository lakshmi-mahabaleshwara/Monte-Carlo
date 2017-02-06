package com.montecarlo.util;

/**
 * This class handles all the Exceptions occurred during the simulation process
 * Created by lmahabaleshwara on 2/4/17.
 */
public class MonteCarloSimulationException extends Exception{

    public MonteCarloSimulationException(String string, Exception e) {
        super(string, e);
    }

    public MonteCarloSimulationException(String message) {
        super(message);
    }
}
