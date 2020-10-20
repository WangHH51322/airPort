package com.cup.wang.airport.test;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/12 15:10
 */
/*使用高斯赛德尔迭代法求解线性方程组*/
public class Gauss_Seidel_Iterate {
    /*求下三角*/
    private static double[][] find_lower(double data[][],int k){
        int length=data.length;
        double data2[][]=new double[length][length];
        if(k>=0){
            for(int i=0;i<=length-k-1;i++){
                for(int j=0;j<=i+k;j++){
                    data2[i][j]=data[i][j];
                }
            }
            for(int i=length-k;i<length;i++){
                for(int j=0;j<length;j++){
                    data2[i][j]=data[i][j];
                }
            }
        }
        else{
            for(int i=-k;i<length;i++){
                for(int j=0;j<=i+k;j++){
                    data2[i][j]=data[i][j];
                }
            }
        }
        return data2;
    }
    /*求原矩阵的负*/
    private static double[][] opposite_matrix(double[][] data){
        int M=data.length;
        int N=data[0].length;
        double data_temp[][]=new double[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                data_temp[i][j]=-data[i][j];
            }
        }
        return data_temp;
    }
    /*原矩阵去掉第i+1行第j+1列后的剩余矩阵*/
    private static double[][] get_complement(double[][] data, int i, int j) {

        /* x和y为矩阵data的行数和列数 */
        int x = data.length;
        int y = data[0].length;

        /* data2为所求剩余矩阵 */
        double data2[][] = new double[x - 1][y - 1];
        for (int k = 0; k < x - 1; k++) {
            if (k < i) {
                for (int kk = 0; kk < y - 1; kk++) {
                    if (kk < j) {
                        data2[k][kk] = data[k][kk];
                    } else {
                        data2[k][kk] = data[k][kk + 1];
                    }
                }

            } else {
                for (int kk = 0; kk < y - 1; kk++) {
                    if (kk < j) {
                        data2[k][kk] = data[k + 1][kk];
                    } else {
                        data2[k][kk] = data[k + 1][kk + 1];
                    }
                }
            }
        }
        return data2;

    }
    /* 计算矩阵行列式 */
    private static double cal_det(double[][] data) {
        double ans=0;
        /*若为2*2的矩阵可直接求值并返回*/
        if(data[0].length==2){
            ans=data[0][0]*data[1][1]-data[0][1]*data[1][0];
        }
        else{
            for(int i=0;i<data[0].length;i++){
                /*若矩阵不为2*2那么需求出矩阵第一行代数余子式的和*/
                double[][] data_temp=get_complement(data, 0, i);
                if(i%2==0){
                    /*递归*/
                    ans=ans+data[0][i]*cal_det(data_temp);
                }
                else{
                    ans=ans-data[0][i]*cal_det(data_temp);
                }
            }
        }
        return ans;

    }

    /*计算矩阵的伴随矩阵*/
    private static double[][] ajoint(double[][] data) {
        int M=data.length;
        int N=data[0].length;
        double data2[][]=new double[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if((i+j)%2==0){
                    data2[i][j]=cal_det(get_complement(data, i, j));
                }
                else{
                    data2[i][j]=-cal_det(get_complement(data, i, j));
                }
            }
        }

        return trans(data2);


    }

    /*转置矩阵*/
    private static double [][]trans(double[][] data){
        int i=data.length;
        int j=data[0].length;
        double[][] data2=new double[j][i];
        for(int k2=0;k2<j;k2++){
            for(int k1=0;k1<i;k1++){
                data2[k2][k1]=data[k1][k2];
            }
        }

        /*将矩阵转置便可得到伴随矩阵*/
        return data2;

    }



    /*求矩阵的逆，输入参数为原矩阵*/
    private static double[][] inv(double [][] data){
        int M=data.length;
        int N=data[0].length;
        double data2[][]=new double[M][N];
        double det_val=cal_det(data);
        data2=ajoint(data);
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                data2[i][j]=data2[i][j]/det_val;
            }
        }

        return data2;
    }
    /*矩阵加法*/
    private static double[][] matrix_add(double[][] data1,double[][] data2){
        int M=data1.length;
        int N=data1[0].length;
        double data[][]=new double[M][N];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                data[i][j]=data1[i][j]+data2[i][j];
            }
        }
        return data;
    }
    /*矩阵相乘*/
    private static double[][] multiply(double[][] data1,double[][] data2){
        int M=data1.length;
        int N=data1[0].length;
        int K=data2[0].length;
        double[][] data3=new double[M][K];
        for(int i=0;i<M;i++){
            for(int j=0;j<K;j++){
                for(int k=0;k<N;k++){
                    data3[i][j]+=data1[i][k]*data2[k][j];
                }
            }
        }
        return data3;
    }
    /*输入参数为原矩阵和一个整数,该整数代表从对角线往上或往下平移的元素个数*/
    private static double[][] find_upper(double[][] data,int k){
        int length=data.length;
        int M=length-k;
        double[][] data2=new double[length][length];
        if(k>=0){
            for(int i=0;i<M;i++){
                for(int j=k;j<length;j++){
                    data2[i][j]=data[i][j];
                }
                k+=1;
            }
        }
        else {
            for(int i=0;i<-k;i++){
                for(int j=0;j<length;j++){
                    data2[i][j]=data[i][j];
                }
            }
            for(int i=-k;i<length;i++){
                for(int j=i+k;j<length;j++){
                    data2[i][j]=data[i][j];
                }
            }
        }
        return data2;
    }
    /*m*n矩阵与n维向量的乘法*/
    private static double[] multiply2(double[][] data1,double[] data2){
        int M=data1.length;
        int N=data1[0].length;
        double[] data3=new double[M];
        for(int k=0;k<M;k++){
            for(int j=0;j<N;j++){
                data3[k]+=data1[k][j]*data2[j];
            }
        }
        return data3;
    }
    /*向量加法*/
    private static double[] matrix_add2(double[] data1,double[] data2){
        int M=data1.length;
        double data[]=new double[M];
        for(int i=0;i<M;i++){
            data[i]=data1[i]+data2[i];
        }
        return data;
    }
    /*求两向量之差的二范数（用于检验误差）*/
    public static double cal_error(double[] X1, double[] X2){
        int M=X1.length;
        double temp=0;
        for(int i=0;i<M;i++){
            temp+=Math.pow((X1[i]-X2[i]),2);
        }
        temp=Math.sqrt(temp);
        return temp;
    }
    /*求矩阵的对角矩阵*/
    private static double[][] find_diagnal(double A[][]) {
        int m = A.length;
        int n = A[0].length;
        double B[][] = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    B[i][j] = A[i][j];
                }
            }
        }
        return B;

    }
    /*高斯赛德尔迭代法*/
    public static double[] Gauss_Seidel_method(double[][] A, double[] B, double[] X){
        double D[][]=find_diagnal(A);
        double L[][]=find_lower(A, -1);
        double U[][]=find_upper(A, 1);
        double temp1[][]=inv(matrix_add(D, L));
        double temp2[][]=opposite_matrix(temp1);
        double B0[][]=multiply(temp2, U);
        double F[]=multiply2(temp1, B);

        return matrix_add2(multiply2(B0, X), F);


    }

    public static void main(String[] args) {

        System.out.println("输入误差限：");
//        double er= (double) 0.0001;
//        double temp[]=new double[1];
//        while(cal_error((temp=Gauss_Seidel_method(A, B, X)), X)>=er){
//            X=temp;
//        }
//        X=temp;
//        System.out.println("高斯赛德尔计算得到的解向量为：");
//        for(int i=0;i<M;i++){
//            System.out.println(X[i]+" ");
//        }
//        System.out.println();

    }

}