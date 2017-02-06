package com.montecarlo.simulation;

import com.montecarlo.simulation.impl.SimulationImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * Created by lmahabaleshwara on 2/4/17.
 */
public class SimulationTest{

    //Positive test case

    @Test
    public void testValidDataSets(){
        ISimulation simulation=new SimulationImpl(new double[]{10.0, 20.0, 30.0,40.0,50.0}, false, null);
        double median=simulation.getMedian();
        Assert.assertEquals(30.0,median);

        simulation=new SimulationImpl(new double[]{10.0, 20.0, 30.0,40.0,50.0,60.0}, false, null);
        median=simulation.getMedian();
        Assert.assertEquals(35.0,median);

        simulation=new SimulationImpl(new double[]{10.0,20.0,30.0,40.0,50.0,60.0}, false, null);
        double percentile90=simulation.getPercentile(90);
        Assert.assertEquals(55.0,percentile90);

        simulation=new SimulationImpl(new double[]{10.0,20.0,30.0,40.0,50.0,60.0}, false, null);
        double percentile10=simulation.getPercentile(10);
        Assert.assertEquals(15.0,percentile10);
    }

    //Negative test cases

    @Test
    public void testNullDataSets() {
        ISimulation simulationResults = new SimulationImpl(null, false, null);
        double result = simulationResults.getMedian();
        Assert.assertEquals(Double.NaN,result);

        simulationResults = new SimulationImpl(new double[]{}, false, null);
        result = simulationResults.getMedian();
        Assert.assertEquals(Double.NaN,result);

    }

    @Test
    public void testDataSetsWithExceptions(){
        ISimulation simulationResults = new SimulationImpl(new double[]{10.0, 20.0, 30.0,40.0,50.0}, true, null);
        double result = simulationResults.getMedian();
        Assert.assertEquals(Double.NaN,result);

    }

    @Test
    public void testDataSetsWithIncorrectPercentile(){
        ISimulation simulationResults = new SimulationImpl(new double[]{10.0, 20.0, 30.0,40.0,50.0}, true, null);
        double result = simulationResults.getPercentile(101);
        Assert.assertEquals(Double.NaN,result);

        simulationResults = new SimulationImpl(new double[]{}, false, null);
        result = simulationResults.getPercentile(-1);
        Assert.assertEquals(Double.NaN,result);

    }
}
