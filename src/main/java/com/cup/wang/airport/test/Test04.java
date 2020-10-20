package com.cup.wang.airport.test;

import no.uib.cipr.matrix.*;
import no.uib.cipr.matrix.sparse.GMRES;
import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;

import java.util.Iterator;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/16 17:19
 */
public class Test04 {

    public static void main(String[] args) throws IterativeSolverNotConvergedException {

        double[][] A = {{1,0,0,1},{0,1,-1,0},{0,0,1,0},{1,0,1,0}};
        double[] B = {0,-1,2,3};
        double[] X = {0,0,0,0};

        //GMRES gmres;
        DenseMatrix matrix = new DenseMatrix(A);
        DenseVector b = new DenseVector(B);
        DenseVector x = new DenseVector(X);
        GMRES gmres = new GMRES(b);
        gmres.solve(matrix,b,x);
        for (int i = 0; i < X.length; i++) {
            System.out.println(x.get(i));
        }
    }
}
