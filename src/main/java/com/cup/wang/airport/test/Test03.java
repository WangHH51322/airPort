package com.cup.wang.airport.test;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import com.cup.wang.airport.simulator.others.MatrixSolver;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/16 9:41
 */
public class Test03 {

    public static void main(String[] args) {

        double[][] A ={{1,2,3},{4,5,6},{7,8,9}};
        double[][] B = {{1},{2},{3}};
        Matrix matrix = new Matrix(A);
        Matrix b = new Matrix(B);
        matrix.print(9, 6);

        //奇异值分解
        SingularValueDecomposition s = matrix.svd();
        System.out.print("U = ");
        Matrix U = s.getU();
        U.print(9, 6);
        System.out.print("Sigma = ");
        Matrix S = s.getS();
        S.print(9, 6);
        System.out.print("V = ");
        Matrix V = s.getV();
        V.print(9, 6);

        Matrix x = V.times(S.inverse()).times(U.transpose()).times(b);
        x.print(9, 6);

        double[] result = new double[A[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = x.get(i,0);
        }

        for (double v : result) {
            System.out.println(v);
        }

//        //直接法
//        Matrix xx = ((matrix.transpose()).times(matrix)).inverse().times(matrix.transpose());
//        Matrix xxx = xx.times(b);
//        xxx.print(9,6);

    }
}
