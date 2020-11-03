package com.cup.wang.airport.simulator.others;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/8 14:34
 */
public class MatrixSolver {

    public static void gaussSeidelIteration(double[][] A, double[] C, double[] X){
        int ALength=A.length;

        //预处理
        double[][] solvingA=new double[ALength][ALength];//A的转置矩阵与A的乘积
        for (int i = 0; i < ALength; i++) {
            for (int j = 0; j < ALength; j++) {
                for (int k = 0; k < ALength; k++) {
                    solvingA[i][j]+=A[k][i]*A[k][j];
                }
            }
        }

        double[] solvingC=new double[ALength];//A的转置矩阵与C的乘积
        for (int i = 0; i < ALength; i++) {
            for (int j = 0; j < ALength; j++) {
                solvingC[i]+=A[j][i]*C[j];
            }
        }



        int maxIterations=2000;//最大迭代次数
        int iteration=0;
        while (true){

            iteration++;
            if (iteration>maxIterations){
                System.out.println("GaussSeidel迭代不收敛");
                break;
            }

            double[] tempX=new double[ALength];
            for (int i = 0; i < ALength; i++) {
                tempX[i] = X[i];
                double sum = 0;
                for (int j = 0; j < ALength; j++) {
                    if (i != j) {
                        sum+=solvingA[i][j]*X[j];
                    }
                }
                X[i]=(solvingC[i]-sum)/solvingA[i][i];
            }

            boolean flag = true;
            for (int i = 0; i < ALength; i++) {
                if (Math.abs(X[i] - tempX[i]) > 0.00001) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                break;
            }

        }

    }

    public static void BiCGSTAB(double[][] A, double[] C, double[] X){
        int ALength=A.length;
        double sumNorm=0;
        for (int i = 0; i < ALength; i++) {
            sumNorm+=C[i]*C[i];
        }
        sumNorm=Math.sqrt(sumNorm);

        double[] rStarZero=new double[ALength];
        double[] r=new double[ALength];
        for (int i = 0; i < ALength; i++) {
            double sum=0;
            for (int j = 0; j < ALength; j++) {
                sum+=A[i][j]*X[j];
            }
            rStarZero[i]=C[i]-sum;
            r[i]=rStarZero[i];
        }
        double[] p=new double[ALength];
        double[] v=new double[ALength];
        double rou=-1;
        double omega=-1;
        double alpha=-1;

        int maxIterations=1000;//最大迭代次数
        int iteration=0;
        double[][] result = new double[maxIterations][ALength];
        while (true){
            iteration++;
            if (iteration>maxIterations){
                System.out.println("BiCGSTAB计算不收敛！");
                break;
            }

            double rouPre=rou;
            double sum0=0;
            for (int i = 0; i < ALength; i++) {
                sum0+=r[i]*rStarZero[i];

            }
            rou=sum0;

            if (rou==0){
                System.out.println("方法失败！");
                System.out.println("迭代次数:" + iteration);
                break;
            }

            if (iteration==1){
                for (int i = 0; i < ALength; i++) {
                    p[i]=r[i];
                }
            }else{
                double betaPre=(rou/rouPre)*(alpha/omega);
                for (int i = 0; i < ALength; i++) {
                    p[i]=r[i]+betaPre*(p[i]-omega*v[i]);
                }
            }

            for (int i = 0; i < ALength; i++) {
                double sum1=0;
                for (int j = 0; j < ALength; j++) {
                    sum1+=A[i][j]*p[j];
                }
                v[i]=sum1;
            }

            double sum2=0;
            for (int i = 0; i < ALength; i++) {
                sum2+=v[i]*rStarZero[i];
            }
            alpha=rou/sum2;

            double norm=0;
            double[] s=new double[ALength];
            for (int i = 0; i < ALength; i++) {
                s[i]=r[i]-alpha*v[i];
                norm+=s[i]*s[i];
            }


            norm=Math.sqrt(norm);
            if (norm<1e-3){
                for (int i = 0; i < ALength; i++) {
                    X[i]=X[i]+alpha*p[i];
                    result[iteration-1][i] = X[i];
                }
                System.out.println("迭代次数:" + iteration);
                break;
            }

            double[] t=new double[ALength];
            for (int i = 0; i < ALength; i++) {
                for (int j = 0; j < ALength; j++) {
                    t[i]+=A[i][j]*s[j];
                }
            }

            double sum3=0;
            double sum4=0;
            for (int i = 0; i < ALength; i++) {
                sum3+=s[i]*t[i];
                sum4+=t[i]*t[i];
            }
            omega=sum3/sum4;
//            System.out.println("omega:"+omega);

            double[] preX=new double[ALength];
            for (int i = 0; i < ALength; i++) {
                preX[i]=X[i];
            }
            for (int i = 0; i < ALength; i++) {
                X[i]=X[i]+alpha*p[i]+omega*s[i];
                for (int j = 0; j < ALength; j++) {
                    result[iteration-1][j] = X[j];
                }
            }

            for (int i = 0; i < ALength; i++) {
                r[i]=s[i]-omega*t[i];
            }

            if (omega==0){
                System.out.println("omega为0");
                System.out.println("迭代次数:" + iteration);
                break;
            }else{//检验收敛性
                double sumR=0;
                for (int i = 0; i < ALength; i++) {
                    sumR+=r[i]*r[i];
                }
                sumR=Math.sqrt(sumR);
                if (sumR/sumNorm<1e-3){
                    System.out.println("迭代次数:" + iteration);
                    break;
                }

            }

        }

        /*excel*/
        String filePath = "D:\\zexcel\\bicgstab.xlsx";
        SXSSFWorkbook sxssfWorkbook = null;
        BufferedOutputStream outputStream = null;

        try {
            //这样表示SXSSFWorkbook只会保留100条数据在内存中，其它的数据都会写到磁盘里，这样的话占用的内存就会很少
            sxssfWorkbook = new SXSSFWorkbook(getXSSFWorkbook(filePath),100);
            //获取第一个Sheet页
            SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);
            for (int z = 0; z < result.length; z++) {
                SXSSFRow row = sheet.createRow(z);
                for (int j = 0; j < result[z].length; j++) {
                    row.createCell(j).setCellValue(result[z][j]);
                }
            }
            outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            sxssfWorkbook.write(outputStream);
            outputStream.flush();
            sxssfWorkbook.dispose();// 释放workbook所占用的所有windows资源
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 先创建一个XSSFWorkbook对象
     * @param filePath
     * @return
     */
    public static XSSFWorkbook getXSSFWorkbook(String filePath) {
        XSSFWorkbook workbook =  null;
        BufferedOutputStream outputStream = null;
        try {
            File fileXlsxPath = new File(filePath);
            outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new XSSFWorkbook();
            workbook.createSheet("bicstab迭代");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return workbook;
    }

    /**
     *Jacobi迭代法Ax = b,若在给定步数能求出解，返回true，否则返回false
     * @param
     * @param
     * @param
     * @param A
     * @param b
     * @return
     */
    //n未知数个数,x解,esp误差
    public static double[] Jacobi(double[][] A, double[] b, double[]x)
    {
        double esp = 0.0001;
        int step = 1000;
        int n = b.length;
        double[] y = new double[n];
        for(int i = 0 ; i < n ; ++i ) {
            x[i] = 0;
        }
        int k = 0;
        boolean flag = false;
        while( k < step)
        {
            for(int i = 0 ; i < n ; ++i)
            {
                double sum = b[i];
                for(int j = 0 ; j < n ; ++j)
                {
                    if(i!=j)
                    {
                        sum-=A[i][j]*x[j];
                    }
                }
                y[i] = sum/A[i][i];
            }

            //比较误差
            double max = Math.abs(y[0]-x[0]);
            for(int i = 1 ; i < n ; ++i)
                if(max>esp)
                {
                    break;
                }else{
                    max = Math.max(max, Math.abs(y[i]-x[i]));
                }
            for(int i = 0; i < n ; ++i){
                x[i] = y[i];
            }
            if(max<esp){
                flag = true;
                break;
            }
            k++;
        }
        if (flag){
            System.out.println("迭代结束");
        }else {
            System.out.println("迭代次数不够");
        }
        return y;
    }
}
