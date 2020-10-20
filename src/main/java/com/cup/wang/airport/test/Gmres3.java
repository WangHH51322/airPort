package com.cup.wang.airport.test;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import com.cup.wang.airport.simulator.others.MatrixSolver;
import org.ejml.simple.SimpleMatrix;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/18 21:41
 */
public class Gmres3 {

    public static void main(String[] args) {
        double[][] A = {{1,0,0,1},{0,1,-1,0},{0,0,1,0},{1,0,1,0}};
        double[] B = {0,-1,2,3};
        double[] X = {0,0,0,0};

        solve(A,B,X);
        for (int i = 0; i < X.length; i++) {
            System.out.println(X[i]);
        }
    }

    public static void solve(double[][] A,double[] B,double[] X){

        int ALength=A.length;
        int iteration = 2000;//迭代次数
        int restart = 2000;
        double tol = Math.pow(10,-6);

        //B的2范数
        double bNrm2 = 0;
        for (int i = 0; i < B.length; i++) {
            bNrm2 += B[i]*B[i];
        }
        bNrm2 = Math.sqrt(bNrm2);
        if (bNrm2 == 0.0){
            bNrm2 = 1.0;
        }

        //参数 r
        double[] r = new double[ALength];
        for (int i = 0; i < ALength; i++) {
            double sum = 0;
            for (int j = 0; j < ALength; j++) {
                sum += A[i][j]*X[j];
            }
            r[i] = B[i] - sum;
        }

        //r 的2范数
        double rNrm2 = 0;
        for (int i = 0; i < r.length; i++) {
            rNrm2 += r[i]*r[i];
        }
        rNrm2 = Math.sqrt(rNrm2);

        //参数 error
        double error = rNrm2/bNrm2;
        if (error < tol){
            System.out.println("无需迭代,结果精准");
        }

        //计算参数预设
        MatrixSolver matrixSolver = new MatrixSolver();
        Decomposition D = new Decomposition();
        double[][] V = new double[ALength][restart + 1];
        for (int i = 0; i < V.length; i++) {
            for (int j = 0; j < V[i].length; j++) {
                V[i][j] = 0;
            }
        }
        double[][] H = new double[restart + 1][restart];
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[i].length; j++) {
                H[i][j] = 0;
            }
        }
        double[] cs = new double[restart];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = 0;
        }
        double[] sn = new double[restart];
        for (int i = 0; i < sn.length; i++) {
            sn[i] = 0;
        }
        double[][] el = new double[ALength][1];
        for (int i = 0; i < el.length; i++) {
            el[i][0] = 0;
        }
        el[0][0] = 1.0;

        //开始迭代计算
        for (int i = 0; i < iteration; i++) {

            //参数rIt计算
            double[] rIt = new double[ALength];
            for (int k = 0; k < ALength; k++) {
                double sum = 0;
                for (int j = 0; j < ALength; j++) {
                    sum += A[k][j]*X[j];
                }
                rIt[k] = B[k] - sum;
            }

            //rIt 的2范数
            double rItNrm2 = 0;
            for (int j = 0; j < r.length; j++) {
                rItNrm2 += rIt[j]*rIt[j];
            }
            rItNrm2 = Math.sqrt(rItNrm2);

            //V的第一列值
            for (int j = 0; j < V.length; j++) {
                V[j][0] = rIt[j]/rItNrm2;
            }

            //参数 s
            double[] s = new double[restart];
            for (int j = 0; j < s.length; j++) {
                s[i] = rItNrm2 * el[i][0];
            }

            //内循环
            for (int j = 0; j < restart; j++) {

                double[] w = new double[ALength];
                for (int k = 0; k < A.length; k++) {
                    double sum = 0;
                    for (int l = 0; l < A[i].length; l++) {
                        sum += A[k][l] * V[l][j];
                    }
                    w[k] = sum;
                }

                //三层内循环
                for (int k = 0; k < j; k++) {
                    double sum = 0;
                    for (int l = 0; l < w.length; l++) {
                        sum += w[l]*V[l][k];
                    }
                    H[k][j] = sum;
                    for (int l = 0; l < w.length; l++) {
                        w[l] = w[l] - H[k][j] * V[l][k];
                    }
                }

                //w 的2范数
                double wNrm2 = 0;
                for (int k = 0; k < w.length; k++) {
                    wNrm2 += w[k]*w[k];
                }
                wNrm2 = Math.sqrt(wNrm2);
                H[j+1][j] = wNrm2;

                //求V的j+1列值
                for (int k = 0; k < V.length; k++) {
                    V[k][j+1] = w[k]/H[j+1][j];
                }

                double temp = 0.0;
                for (int k = 0; k < j - 1; k++) {
                    temp = cs[k]*H[k][j] + sn[k]*H[k+1][j];
                    H[k+1][j] = -sn[k]*H[k][j] + cs[k]*H[k+1][j];
                    H[k][j] = temp;
                }
                double[] doubles = rotMat(H[j][j], H[j + 1][j]);
                cs[j] = doubles[0];
                sn[j] = doubles[1];
                temp = cs[j] * s[j];
                s[j+1] = -sn[j] * s[j];
                s[j] = temp;
                H[j][j] = cs[j]*H[j][j] + sn[j]*H[j+1][j];
                H[j+1][j] = 0;

                error = Math.abs(s[j+1])/bNrm2;
                if (error <= tol){
                    double[][] AI = new double[j+1][j+1];
                    for (int k = 0; k < AI.length; k++) {
                        for (int l = 0; l < AI[k].length; l++) {
                            AI[k][l] = H[k][l];
                        }
                    }
                    double[] bI = new double[j+1];
                    for (int k = 0; k < bI.length; k++) {
                        bI[k] = s[k];
                    }
                    double[] yI = new double[j+1];
                    for (int k = 0; k < yI.length; k++) {
                        yI[k] = 0.0;
                    }
                    double[] xI = D.solve(AI, bI);
                    for (int k = 0; k < X.length; k++) {
                        double sum = 0;
                        for (int l = 0; l < yI.length; l++) {
                            sum += V[k][l]*xI[l];
                        }
                        X[k] += sum;
                    }
                    break;
                }
            }

            if (error <= tol){
                break;
            }
            double[][] AM = new double[restart+1][restart+1];
            for (int k = 0; k < AM.length; k++) {
                for (int l = 0; l < AM[k].length; l++) {
                    AM[k][l] = H[k][l];
                }
            }
            double[] bM = new double[restart+1];
            for (int k = 0; k < bM.length; k++) {
                bM[k] = s[k];
            }
            double[] yM = new double[restart+1];
            for (int k = 0; k < yM.length; k++) {
                yM[k] = 0.0;
            }
            double[] xM = D.solve(AM, bM);
            for (int k = 0; k < X.length; k++) {
                double sum = 0;
                for (int l = 0; l < yM.length; l++) {
                    sum += V[k][l]*xM[l];
                }
                X[k] += sum;
            }

            for (int j = 0; j < A.length; j++) {
                double sum = 0.0;
                for (int k = 0; k < A[j].length; k++) {
                    sum += A[j][k] * X[k];
                }
                r[j] = B[j] - sum;
            }

            double sum = 0.0;
            for (int j = 0; j < r.length; j++) {
                sum += r[j]*r[j];
            }
            s[i+1] = Math.sqrt(sum);
            error = s[i+1]/bNrm2;
            if (error <= tol){
                break;
            }
        }

        if (error > tol){
            System.out.println("迭代计算失败!");
        }

    }

    public static double[] rotMat(double H1,double H2){
        double c = 0.0;
        double s = 0.0;
        double temp = 0.0;
        double[] result = new double[2];
        if (H2 == 0){
            c = 1.0;
            s = 0.0;
        }else if (Math.abs(H2) > Math.abs(H1)){
            temp = H1/H2;
            s = 1.0/Math.abs(1.0+temp*temp);
            c = temp * s;
        }else {
            temp = H2/H1;
            c = 1.0/Math.abs(1.0+temp*temp);
            s = temp * c;
        }
        result[0] = c;
        result[1] = s;
        return result;
    }

}

