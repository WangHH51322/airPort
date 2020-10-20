package com.cup.wang.airport.test;

import com.cup.wang.airport.simulator.simulate.CalculateLambda;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/9 19:30
 */
public class Test02 {
    public static void main(String[] args) {
        //double flow,double diameter, double viscosity, double roughness
        double flow = 0.01;
        double diameter = 0.6;
        double viscosity = 1.019 * Math.pow(10,-6);
        double roughness = 0.00003;
        CalculateLambda calculateLambda = new CalculateLambda();
        calculateLambda.calculate(flow,diameter,viscosity,roughness);
        double lambda = calculateLambda.getLambda();
        System.out.println(lambda);
    }
}
