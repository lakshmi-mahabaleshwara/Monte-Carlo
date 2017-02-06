package com.montecarlo.util;

/**
 * This class holds all constants that are used in the application
 * Created by lmahabaleshwara on 2/4/17.
 */
public class Constants {

    //Given Data in the problem statements

    public static final double CONSERVATIVE_RETURNS = 6.189;
    public static final double CONSERVATIVE_RISK = 6.3438;
    public static final double AGGRESSIVE_RETURNS = 9.4324;
    public static final double AGGRESSIVE_RISK = 15.675;
    public static final double PORTFOLIO_INITIAL_VALUE = 100000.00;
    public static final double INFLATION_RATE = 3.5;
    public static final int NUM_OF_ITERATIONS = 10000;
    public static final int YEARS_PER_ITERATION = 20;

    // Assumptions to handle multiple threads.
    public static final long FETCH_ITERATION_RESULT_TIMEOUT_MS = 1000;
    public static final int NUM_OF_ITERATION_THREADS = 4;
    public static final int NUM_OF_SIMULATION_THREADS=2;
}
