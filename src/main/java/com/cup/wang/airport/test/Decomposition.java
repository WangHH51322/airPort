package com.cup.wang.airport.test;
import java.util.Arrays;
import java.util.List;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/15 9:53
 */

public class Decomposition {

    public static void main(String[] args) {
        double [][]A ={{1,1,1,1},{-1,1,-1,1},{0,1,-1,2},{0,0,2,-1}};
        double []b = {10,2,7,2};
        double[]x = solve(A, b);
        for(int i = 0;i<x.length;i++){
            System.out.println(x[i]);
        }

    }
    public static double[] solve(double[][] a, double[] b) {
        List<double[][]> LAndU = decomposition(a);  //LU decomposition
        double[][] L = LAndU.get(0);
        double[][] U = LAndU.get(1);
        double[] UMultiX = getUMultiX(a, b, L);   //前代
        return getSolution(a, U, UMultiX);        //回代
    }

    /**
     * Get solution of the equations
     * @param a - Coefficient matrix of the equations
     * @param U - U of LU Decomposition
     * @param UMultiX - U multiply X
     * @return Equations solution
     */
    private static double[] getSolution(double[][] a, double[][] U,
                                        double[] UMultiX) {
        double[] solutions = new double[a[0].length];
        for(int i=U.length-1; i>=0; i--) {
            double right_hand = UMultiX[i];
            for(int j=U.length-1; j>i; j--) {
                right_hand -= U[i][j] * solutions[j];
            }
            solutions[i] = right_hand / U[i][i];
        }
        return solutions;
    }

    /**
     * Get U multiply X
     * @param a - Coefficient matrix of the equations
     * @param b - right-hand side of the equations
     * @param L - L of LU Decomposition
     * @return U multiply X
     */
    private static double[] getUMultiX(double[][] a, double[] b, double[][] L) {
        double[] UMultiX = new double[a.length];
        for(int i=0; i<a.length; i++) {
            double right_hand = b[i];
            for(int j=0; j<i; j++) {
                right_hand -= L[i][j] * UMultiX[j];  //
            }
            UMultiX[i] = right_hand / L[i][i];
        }
        return UMultiX;
    }

    private static List<double[][]> decomposition(double[][]a){
        double[][] U = a;  //a是要分解的矩阵
        double[][] L = createIndentityMatrix(a.length);

        for(int j=0; j<a[0].length - 1; j++) {
            if(a[j][j] == 0) {
                throw new IllegalArgumentException("zero pivot encountered.");
            }

            for(int i=j+1; i<a.length; i++) {
                double mult = a[i][j] / a[j][j];
                for(int k=j; k<a[i].length; k++) {
                    U[i][k] = a[i][k] - a[j][k] * mult;
                    //得出上三角矩阵U,通过减去矩阵的第一行,第二行,第一行(第二行)得到上三角矩阵
                }
                L[i][j] = mult;  //得到下三角矩阵是得出上三角矩阵的乘积因子
            }
        }
        return Arrays.asList(L, U);

    }
    private static double[][]createIndentityMatrix(int row){
        double[][]identityMatrix = new double[row][row];
        for(int i=0;i<identityMatrix.length;i++){
            for(int j=i;j<identityMatrix[i].length;j++){
                if(j==i){
                    if (j==i) {
                        identityMatrix[i][j]= 1;
                    }else {
                        identityMatrix[i][j] = 0;
                    }
                }
            }
        }
        return identityMatrix;
    }
}
