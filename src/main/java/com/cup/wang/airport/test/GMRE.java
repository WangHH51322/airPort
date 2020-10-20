package com.cup.wang.airport.test;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/16 8:02
 */
public class GMRE {

    public static void main(String[] args) {
        double[][] A = {{1,0,0,1},{0,1,-1,0},{0,0,1,0},{1,0,1,0}};
        double[] B = {0,-1,2,3};
        double[] X = {0,0,0,0};

        gmres(A,B,X);
    }

    public static void gmres(double[][] A,double[] B,double[] X){

        int ALength=A.length;
        int iteration = 2000;//迭代次数

        double[] rStarZero = new double[ALength];
        for (int i = 0; i < ALength; i++) {
            double sum = 0;
            for (int j = 0; j < ALength; j++) {
                sum += A[i][j]*X[j];
            }
            rStarZero[i] = B[i] - sum;
            System.out.println("rStarZero" + i + "的值为:" + rStarZero[i]);
        }

        double beta;
        double sum1 = 0;
        for (int i = 0; i < ALength; i++) {
            sum1 += rStarZero[i]*rStarZero[i];
        }
        beta = Math.sqrt(sum1);
        System.out.println("beta的值为:" + beta);

        double[][] v = new double[iteration + 1][ALength];
        for (int i = 0; i < ALength; i++) {
            v[0][i] = rStarZero[i]/beta;
            System.out.println("v" + i + "初值:" + v[0][i]);
        }

        /*迭代计算*/
        double[][] h = new double[iteration+1][iteration];
        int iterationBreak = 0;
        for (int i = 0; i < iteration; i++) {

            double[] omega = new double[ALength];
            for (int j = 0; j < ALength; j++) {
                double sum = 0;
                for (int k = 0; k < ALength; k++) {
                    sum += A[j][k] * v[i][k];
                }
                omega[j] = sum;
                System.out.println("omegaPre" + j + "的值为:" + omega[j]);
            }

            for (int j = 0; j < i+1; j++) {
                for (int k = 0; k < ALength; k++) {
                    h[j][i] += omega[k] * v[j][k];
                    System.out.println("h[j][i]:" + h[j][i]);
                }

                for (int k = 0; k < ALength; k++) {
                    omega[k] = omega[k] - h[j][i]*v[j][k];
                    System.out.println("omega[k]:" + omega[k]);
                }
            }

            for (int j = 0; j < omega.length; j++) {
                System.out.println("omega[j]:" + omega[j]);
            }

            double sum2 = 0;
            for (int j = 0; j < ALength; j++) {
                sum2 = sum2 + omega[j]*omega[j];
            }
            System.out.println("sum2值为:" + sum2);


            h[i+1][i] = Math.sqrt(sum2);
            System.out.println("h[i+1][i]的值为:" + h[i+1][i]);

            for (int j = 0; j < ALength; j++) {
                v[i+1][j] = omega[j]/h[i+1][i];
            }

            if (Math.abs(h[i+1][i]) < Math.pow(10,-100)){
                iterationBreak = i+1;
                System.out.println("迭代结束," + iterationBreak);
                break;
            }
            if (i == iteration - 1){
                System.out.println("计算不收敛！");
                break;
            }

        }

        double[][] Hm = new double[iterationBreak+1][iterationBreak];
        for (int i = 0; i < Hm.length; i++) {
            for (int j = 0; j < Hm[i].length; j++) {
                Hm[i][j] = h[i][j];
            }
        }

        double[] b = new double[iterationBreak+1];
        for (int i = 0; i < b.length; i++) {
            b[i] = beta;
        }

        //求解最小二乘问题
        double[] svd = svd(Hm, b);

        //构造近似解
        for (int i = 0; i < X.length; i++) {
            X[i] = X[i] + v[iterationBreak][i]*svd[i];
            System.out.println("构造近似解:" + X[i]);
        }
    }

    public static double[] svd(double[][] A, double[] b){

        double[][] bb = new double[b.length][1];
        for (int i = 0; i < bb.length; i++) {
            bb[i][0] = b[i];
        }

        Matrix matrix = new Matrix(A);
        Matrix B = new Matrix(bb);
        System.out.println("海森伯格矩阵:");
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

        Matrix x = V.times(S.inverse()).times(U.transpose()).times(B);
        System.out.println("新值:");
        x.print(9, 6);

        double[] result = new double[A[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = x.get(i,0);
        }
        return result;
    }
}















