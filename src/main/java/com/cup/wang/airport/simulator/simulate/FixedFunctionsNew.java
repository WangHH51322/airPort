package com.cup.wang.airport.simulator.simulate;

import com.cup.wang.airport.model.Node;
import com.cup.wang.airport.model.Pipe;
import com.cup.wang.airport.model.Valve;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.sparse.FlexCompRowMatrix;
import no.uib.cipr.matrix.sparse.GMRES;
import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;
import org.ejml.simple.SimpleMatrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/30 21:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedFunctionsNew implements Serializable {

    private Double dx;
    private Double dt;
    private Double a;
    private Integer t;
    //管段长度数组
    private double[] pipeLength;
    //各管段分段数
    private int[] pipeSegments;
    //各管段分段长度
    private double[][] pipeSegmentsLength;
    //系数矩阵(二维数组)
    private double[][] matrix;
    //系数矩阵
    private SimpleMatrix simpleMatrixA;
    //连接关系
    private List<Map<String,Object>> connections;
    //方程组右端向量
    private double[][] b;
    //各节点以及分段点处流量
    private double[][] Qn;
    //节点流量
    private double[] Qin;
    //各节点以及分段点处压力
    private double[][] Hn;
    //节点压力
    private double[] Hin;
    //迭代计算的初值
    private double[][] Xout;
    //优化后矩阵
    private double[][] matrixNewBee;
    //阀门流量系数

    public void setCoefficientMatrix (NetWork netWork) throws IterativeSolverNotConvergedException {
        //步长
        double dx = this.dx;
        //时长
        double dt = this.dt;
        //波速
        double a = this.a;
        //计算时间
        int time = this.t;
        //计算次数
        int times = (int) (time/dt);
        //常量
        double g = 9.81;
        double f = 0.0118520;//摩阻系数
        //double m = 0.125;
        double cv = 6000;
        double viscosity = 1.019 * Math.pow(10,-6);
        double rou = 794.121;
        //管段数
        int pipeCount = netWork.getPipes().size();
        //阀门数量
        int valveCount = netWork.getValves().size();
        //各管段的内径
        double[] outsideDiameter = new double[pipeCount];
        double[] thickness = new double[pipeCount];
        double[] insideDiameter = new double[pipeCount];
        //各管段的流通面积
        double[] area = new double[pipeCount];
        /*运动方差中H的系数*/
        double[] gwdt = new double[pipeCount];
        /*动量方程中Q的系数*/
        double[] Qcoef2 = new double[pipeCount];
        //管段长度数组
        double[] pipeLength = new double[pipeCount];
        //各管段分段数
        int[] pipeSegments = new int[pipeCount];
        //所有管段分段数总和
        int sum = 0;
        //各管段分段长度
        double[][] pipeSegmentsLength = new double[pipeCount][];
        //每根管段最右一段长度
        double[] dxn = new double[pipeCount];
        //系数矩阵


        long startTime0 = System.currentTimeMillis();

        for (int i = 0; i < pipeLength.length; i++) {
            //每根管段的长度
            pipeLength[i] = netWork.getPipes().get(i).getLength();
            //各管段的内径
            outsideDiameter[i] = netWork.getPipes().get(i).getOutsideDiameter();
            thickness[i] = netWork.getPipes().get(i).getThickness();
            insideDiameter[i] = outsideDiameter[i] - thickness[i]*2;
            //各管段的流通面积
            area[i] = Math.PI * insideDiameter[i] * insideDiameter[i] / 4;
            //各管段运动方差中H的系数
            gwdt[i] = g * area[i] * dt / dx;
            //各管段动量方程中Q的系数
            Qcoef2[i] = a * a * dt / (g * area[i] * dx);
            //每根管段的分段数
            int n = (int) Math.floor(pipeLength[i] / dx);
            dxn[i] = pipeLength[i] - n * dx;
            if (dxn[i] < dx/2){
                pipeSegments[i] = n;
                pipeSegmentsLength[i] = new double[pipeSegments[i]];
                //每根管段的分段的长度
                for (int j = 0; j < pipeSegmentsLength[i].length; j++) {
                    if (j == pipeSegmentsLength[i].length - 1){
                        pipeSegmentsLength[i][j] = dx + dxn[i];
                    } else {
                        pipeSegmentsLength[i][j] = dx;
                    }
                }
            } else {
                pipeSegments[i] = n+1;
                pipeSegmentsLength[i] = new double[pipeSegments[i]];
                for (int j = 0; j < pipeSegmentsLength[i].length; j++) {
                    if (j == pipeSegmentsLength[i].length - 1){
                        pipeSegmentsLength[i][j] = dxn[i];
                    } else {
                        pipeSegmentsLength[i][j] = dx;
                    }
                }
            }
            sum += pipeSegments[i];
        }

        //系数矩阵
        double[][] matrix = new double[sum*2 + pipeCount + 4*valveCount][sum*2 + pipeCount + 4*valveCount];

        /*运动方差中H的系数*/
        int rowNumb1 = 0;
        int colNumb1 = sum + pipeCount;
        for (int i = 0; i < pipeCount; i++) {
            for (int j = 0; j < pipeSegments[i]-1; j++) {
                if (j == pipeSegments[i] - 2) {
                    matrix[rowNumb1 + j][colNumb1 + j] = -gwdt[i] * dx / (dxn[i] / 2 + dx / 2);
                    matrix[rowNumb1 + j][colNumb1 + j + 1] = gwdt[i] * dx / (dxn[i] / 2 + dx / 2);
                } else {
                    matrix[rowNumb1 + j][colNumb1 + j] = -gwdt[i];
                    matrix[rowNumb1 + j][colNumb1 + j + 1] = gwdt[i];
                }
            }
            rowNumb1 += 2*pipeSegments[i] - 1;
            colNumb1 += pipeSegments[i];
        }
        /*动量方程中Q和H的系数*/
        int rowNumb2 = pipeSegments[0] - 1;
        int colNumb21 = 0;
        int colNumb22 = sum + pipeCount;
        for (int i = 0; i < pipeCount; i++) {
            for (int j = 0; j < pipeSegments[i]; j++) {
                if (j == pipeSegments[i] - 1) {
                    matrix[rowNumb2 + j][colNumb21 + j] = -Qcoef2[i] * dx / (dxn[i] / 2 + dx / 2);
                    matrix[rowNumb2 + j][colNumb21 + j + 1] = Qcoef2[i] * dx / (dxn[i] / 2 + dx / 2);
                } else {
                    matrix[rowNumb2 + j][colNumb21 + j] = -Qcoef2[i];
                    matrix[rowNumb2 + j][colNumb21 + j + 1] = Qcoef2[i];
                }
                matrix[rowNumb2 + j][colNumb22 + j] = 1;
            }
            if (i != pipeCount - 1){
                rowNumb2 = rowNumb2 + pipeSegments[i] + pipeSegments[i+1] - 1;
            }
            colNumb21 += pipeSegments[i] + 1;
            colNumb22 += pipeSegments[i];
        }

        /*管段的外边界点和节点关联*/
        List<Map<String,Object>> connections = new ArrayList<Map<String,Object>>();
        //管段列表
        List<Pipe> pipes = netWork.getPipes();
        int rowNumb31 = 0;
        int rowNumb32 = sum + pipeCount;
        int rowNumb321 = sum + pipeCount + 1;
        int rowNumb33 = -1;
        int rowNumb34 = sum + pipeCount - 2;
        int rowNumb35 = sum + pipeCount - 1;
        //记录各个管段入口节点在系数矩阵中的编号
        int[] inPipeNumb = new int[pipeCount];
        //管段粗糙度
        double[] pipeRoughness = new double[pipeCount];
        //管径
        double[] diameter = new double[pipeCount];
        for (int i = 0; i < pipeCount; i++) {
            rowNumb33 += pipeSegments[i] + 1;
            rowNumb34 += pipeSegments[i];
            rowNumb35 += pipeSegments[i];

            //管段粗糙度\管径
            pipeRoughness[i] = pipes.get(i).getRoughness();
            diameter[i] = pipes.get(i).getOutsideDiameter() - pipes.get(i).getThickness();

            Map<String,Object> connection = new HashMap<String, Object>();
            String start = String.valueOf(0 + pipes.get(i).getStartId());//管段起点编号
            String end = String.valueOf(0 - pipes.get(i).getEndId());//管段终点编号
            int[] qAndHStart = {rowNumb31 , rowNumb32 , rowNumb321};//管段入口处Q2,H2,H3
            inPipeNumb[i] = rowNumb31;//管段入口处Q2,用于后面计算管段摩阻
            int[] qAndHEnd = {rowNumb33 , rowNumb34 , rowNumb35};//管段出口处Qn+2,Hn,Hn+1
            //在三通处,某个节点可能同时是两根管段的起点或终点,则用 & 符号进行区分
            for (int j = 0; j < i; j++) {
                if (connections.get(j).containsKey(start)){
                    start += "&";
                }
                if (connections.get(j).containsKey(end)){
                    end += "&";
                }
            }
//            System.out.println("start:" + start);
//            System.out.println("起点Q" + rowNumb31);
//            System.out.println("起点H1" + rowNumb32);
//            System.out.println("起点H2" + rowNumb321);
//            System.out.println("end:" + end);
//            System.out.println("终点Q" + rowNumb33);
//            System.out.println("终点H1" + rowNumb34);
//            System.out.println("终点H2" + rowNumb35);
            connection.put(start,qAndHStart);
            connection.put(end,qAndHEnd);
            connections.add(connection);

            rowNumb31 += pipeSegments[i] + 1;
            rowNumb32 += pipeSegments[i];
            rowNumb321 += pipeSegments[i];
        }
        //阀门列表
        List<Valve> valves = netWork.getValves();
        int colNumb31 = 2*sum + pipeCount;
        for (int i = 0; i < valveCount; i++) {
            Map<String,Object> connection = new HashMap<String, Object>();
            String start = String.valueOf(0 + valves.get(i).getStartId());//管段起点编号
            start += "$";
            String end = String.valueOf(0 - valves.get(i).getEndId());//管段终点编号
            end += "$";
            //在三通处,某个节点可能同时是两根管段的起点或终点,则用 & 符号进行区分
            for (int j = 0; j < pipeCount; j++) {
                if (connections.get(j).containsKey(String.valueOf(0 + valves.get(i).getStartId()))){
                    start += "&";
                }
                if (connections.get(j).containsKey(String.valueOf(0 - valves.get(i).getEndId()))){
                    end += "&";
                }
            }
            int[] qAndHStart = {colNumb31 , colNumb31+1};//Q1,H1
            int[] qAndHEnd = {colNumb31+2 , colNumb31+3};//Q2,H2
//            System.out.println("start:" + start);
//            System.out.println("起点Q" + colNumb31);
//            System.out.println("起点H" + (colNumb31+1));
//            System.out.println("end:" + end);
//            System.out.println("终点Q" + (colNumb31+2));
//            System.out.println("终点H" + (colNumb31+3));
            connection.put(start,qAndHStart);
            connection.put(end,qAndHEnd);
            connections.add(connection);

            colNumb31 += 4;//每个阀有进出两个节点,即4个未知数
        }
//        System.out.println("元件个数:" + connections.size());


        /**
         * 获取全部节点
         */
        List<Node> nodes = netWork.getNodes();

        /*瞬态压力流量初始化*/
        double[][] Qn = new double[times + 1][sum + pipeCount + 2*valveCount];
        double[][] Hn = new double[times + 1][sum + 2 * (pipeCount + valveCount)];
        //方程右端向量
        double[][] b = new double[sum*2 + pipeCount + 4*valveCount][1];

        for (int i = 0; i < Qn[0].length; i++) {
            Qn[0][i] = 80;
        }
        for (int i = 0; i < Hn[0].length; i++) {
            Hn[0][i] = 80;
        }

        //摩阻
        double[] lambda = new double[pipeCount];
        double[] m = new double[pipeCount];
        CalculateLambda calculateLambda = new CalculateLambda();

        //迭代求解
        double[] X = new double[sum*2 + pipeCount + 4*valveCount];
        double[][] Xout = new double[times][sum*2 + pipeCount + 4*valveCount];
        //优化：优化矩阵newMatrix转数组
        double[][] matrixNew = new double[sum*2 + pipeCount + 4*valveCount][sum*2 + pipeCount + 4*valveCount];
        //优化：优化右端向量
        double[] B = new double[sum*2 + pipeCount + 4*valveCount];
        SimpleMatrix result = null;
        /*优化矩阵结构*/
        SimpleMatrix oldResult = new SimpleMatrix(sum*2 + pipeCount + 4*valveCount,1);

        long endTime0 = System.currentTimeMillis();
        System.out.println("生成系数矩阵和初始化求解计算时间:" + (endTime0 - startTime0));


        /*开始计算*/

        double[] it1 = new double[times];
        double[] it2 = new double[times];
        for (int mm = 0; mm < times; mm ++) {

            /*管段摩阻计算*/
            long startTime1 = System.currentTimeMillis();
            for (int i = 0; i < inPipeNumb.length; i++) {
                calculateLambda.calculate(Qn[mm][inPipeNumb[i]],diameter[i],viscosity,pipeRoughness[i]);
                lambda[i] = calculateLambda.getLambda();
                m[i] = calculateLambda.getM();
                System.out.println("管段入口"+ i + "处流量:" + Qn[mm][inPipeNumb[i]]);
                System.out.println("管段"+ i + "处摩阻:" + lambda[i]);
                System.out.println("管段"+ i + "处m:" + m[i]);
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("管段摩阻求解计算时间:" + (endTime1 - startTime1));

            long startTime2 = System.currentTimeMillis();
            /*运动方程Q的系数*/
            int rowNumb51 = 0;
            int colNumb51 = 1;
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < pipeSegments[i] - 1; j++) {
                    matrix[rowNumb51 + j][colNumb51 + j] = (1 + lambda[i] * g * area[i] * dt * Math.pow(Math.abs(Qn[mm][colNumb51 + j]), (1 -m[i])));
                }
                rowNumb51 += 2*pipeSegments[i] - 1;
                colNumb51 += pipeSegments[i] + 1;
            }

            /**
             * 水击条件
             */
//            if (mm > 200){
//                nodes.get(4).setFlow(0.0067);
//            }

            /*左边界条件*/
            //系数矩阵--边界条件
            int colNumb7 = 2*sum - pipeCount + 2*valveCount;
            for (int i = 0; i < nodes.size(); i++) {
                //节点类型
                Integer nodeType = nodes.get(i).getNodeType();
                //节点编号
                Integer numb = nodes.get(i).getNumb();
                if (nodeType == 1){//入口--定压
                    String s1 = String.valueOf(0 + numb);
                    for (int j = 0; j < connections.size(); j++) {
                        if (connections.get(j).get(s1) != null){
                            int[] x = (int[]) connections.get(j).get(s1);
                            //入口边界条件
                            matrix[colNumb7][x[1]] = 1.5;
                            matrix[colNumb7][x[2]] = -0.5;
                            b[colNumb7][0] = nodes.get(i).getPressure()/(rou * g);
//                            System.out.println("入口坐标");
//                            for (int k = 0; k < x.length; k++) {
//                                System.out.println(x[k]);
//                            }
                        }
                    }
                    colNumb7 += 1;
                } else if (nodeType == 3){//普通中间节点
                    //中间节点起点
                    String s31 = String.valueOf(0 + numb);
                    String s311 = s31 + "&";
                    //阀门进入口
                    String s312 = s31 + "$";
                    String s313 = s312 + "&";
                    //中间节点终点
                    String s32 = String.valueOf(0 - numb);
                    String s321 = s32 + "&";
                    //阀门进入口
                    String s322 = s32 + "$";
                    String s323 = s322 + "$";
                    //判断是否为三通处(与弯头处不同,多一个内边界点条件)
                    int panduan = 0;

                    for (int j = 0; j < connections.size(); j++) {
                        if (connections.get(j).get(s31) != null){
                            int panduan1 = 0;
                            int[] x = (int[]) connections.get(j).get(s31);

                            for (int jj = 0; jj < connections.size(); jj++) {
                                if (connections.get(jj).get(s311) != null ){
                                    int[] xx = (int[]) connections.get(jj).get(s311);
                                    //内节点边界条件()
                                    //压力平衡1
                                    matrix[colNumb7][xx[1]] = -1.5;
                                    matrix[colNumb7][xx[2]] = 0.5;
                                    b[colNumb7][0] = 0;
                                    //压力平衡2
                                    matrix[colNumb7 + 2][x[1]] = -1.5;
                                    matrix[colNumb7 + 2][x[2]] = 0.5;
                                    matrix[colNumb7 + 2][xx[1]] = 1.5;
                                    matrix[colNumb7 + 2][xx[2]] = -0.5;
                                    b[colNumb7 + 2][0] = 0;
                                    //流量平衡
                                    matrix[colNumb7 + 1][x[0]] = -1;
                                    matrix[colNumb7 + 1][xx[0]] = -1;
                                    b[colNumb7 + 1][0] = 0;
                                    panduan1 = 1;
                                    panduan = 1;
                                }
                                if (connections.get(jj).get(s313) != null ){
                                    int[] xx = (int[]) connections.get(jj).get(s313);
                                    //内节点边界条件()
                                    //压力平衡1
                                    matrix[colNumb7][xx[1]] = -1;
                                    b[colNumb7][0] = 0;
                                    //压力平衡2
                                    matrix[colNumb7 + 2][x[1]] = -1.5;
                                    matrix[colNumb7 + 2][x[2]] = 0.5;
                                    matrix[colNumb7 + 2][xx[1]] = 1;
                                    b[colNumb7 + 2][0] = 0;
                                    //流量平衡
                                    matrix[colNumb7 + 1][x[0]] = -1;
                                    matrix[colNumb7 + 1][xx[0]] = -1;
                                    b[colNumb7 + 1][0] = 0;
                                    panduan1 = 1;
                                    panduan = 1;
                                }
                            }
                            if (panduan1 == 0){
                                //内节点边界条件()
                                //压力平衡
                                matrix[colNumb7][x[1]] = -1.5;
                                matrix[colNumb7][x[2]] = 0.5;
                                b[colNumb7][0] = 0;
                                //流量平衡
                                matrix[colNumb7 + 1][x[0]] = -1;
                                b[colNumb7 + 1][0] = 0;
//                                System.out.println("中间点起点坐标");
//                                for (int k = 0; k < x.length; k++) {
//                                    System.out.println(x[k]);
//                                }
                            }
                        } else if (connections.get(j).get(s312) != null){
                            int[] x = (int[]) connections.get(j).get(s312);
                            //压力平衡
                            matrix[colNumb7][x[1]] = -1;
                            b[colNumb7][0] = 0;
                            //流量平衡
                            matrix[colNumb7 + 1][x[0]] = -1;
                            b[colNumb7 + 1][0] = 0;
                        }

                        if (connections.get(j).get(s32) != null){
                            int panduan2 = 0;
                            int[] x = (int[]) connections.get(j).get(s32);

                            for (int jj = 0; jj < connections.size(); jj++) {
                                if (connections.get(jj).get(s321) != null ){
                                    int[] xx = (int[]) connections.get(jj).get(s321);
                                    //内节点边界条件()
                                    //压力平衡1
                                    matrix[colNumb7][xx[1]] = -0.5;
                                    matrix[colNumb7][xx[2]] = 1.5;
                                    b[colNumb7][0] = 0;
                                    //压力平衡2
                                    matrix[colNumb7 + 2][x[1]] = 0.5;
                                    matrix[colNumb7 + 2][x[2]] = -1.5;
                                    matrix[colNumb7 + 2][xx[1]] = -0.5;
                                    matrix[colNumb7 + 2][xx[2]] = 1.5;
                                    b[colNumb7 + 2][0] = 0;
                                    //流量平衡
                                    matrix[colNumb7 + 1][x[0]] = 1;
                                    matrix[colNumb7 + 1][xx[0]] = 1;
                                    b[colNumb7 + 1][0] = 0;
                                    panduan2 = 1;
                                    panduan = 1;
                                }
                                if (connections.get(jj).get(s323) != null ){
                                    int[] xx = (int[]) connections.get(jj).get(s323);
                                    //内节点边界条件()
                                    //压力平衡1
                                    matrix[colNumb7][xx[1]] = 1;
                                    b[colNumb7][0] = 0;
                                    //压力平衡2
                                    matrix[colNumb7 + 2][x[1]] = 0.5;
                                    matrix[colNumb7 + 2][x[2]] = -1.5;
                                    matrix[colNumb7 + 2][xx[1]] = 1;
                                    b[colNumb7 + 2][0] = 0;
                                    //流量平衡
                                    matrix[colNumb7 + 1][x[0]] = 1;
                                    matrix[colNumb7 + 1][xx[0]] = 1;
                                    b[colNumb7 + 1][0] = 0;
//                                    System.out.println("双入口");
                                    panduan2 = 1;
                                    panduan = 1;
                                }
                            }
                            if (panduan2 == 0){
                                //内节点边界条件()
                                //压力平衡
                                matrix[colNumb7][x[1]] = -0.5;
                                matrix[colNumb7][x[2]] = 1.5;
                                b[colNumb7][0] = 0;
                                //流量平衡
                                matrix[colNumb7 + 1][x[0]] = 1;
                                b[colNumb7 + 1][0] = 0;
//                                System.out.println("中间点终点坐标");
//                                for (int k = 0; k < x.length; k++) {
//                                    System.out.println(x[k]);
//                                }

                            }
                        } else if (connections.get(j).get(s322) != null){
                            int[] x = (int[]) connections.get(j).get(s322);
                            //压力平衡
                            matrix[colNumb7][x[1]] = 1;
                            b[colNumb7][0] = 0;
                            //流量平衡
                            matrix[colNumb7 + 1][x[0]] = 1;
                            b[colNumb7 + 1][0] = 0;
                        }
                    }
                    if (panduan == 1){
                        colNumb7 += 3;
                    } else {
                        colNumb7 += 2;
                    }

                } else if (nodeType == 2){//出口
                    int panduan = 0;
                    String s2 = String.valueOf(0 - numb);
                    String s22 = String.valueOf(0 + numb);
                    String s23 = s2 + "&";
                    String s21 = s2 + "$";
                    for (int j = 0; j < connections.size(); j++) {
                        if (connections.get(j).get(s2) != null){
                            int[] x = (int[]) connections.get(j).get(s2);
                            for (int jj = 0; jj < connections.size(); jj++) {
                                if (connections.get(jj).get(s22) != null){
                                    int[] xx = (int[]) connections.get(jj).get(s22);
                                    //出口边界条件(定流)
                                    matrix[colNumb7][x[0]] = 1;
                                    matrix[colNumb7][xx[0]] = -1;
                                    b[colNumb7][0] = nodes.get(i).getFlow();
                                    //压力平衡
                                    matrix[colNumb7 + 1][x[1]] = 1.5;
                                    matrix[colNumb7 + 1][x[2]] = -0.5;
                                    matrix[colNumb7 + 1][xx[1]] = -1.5;
                                    matrix[colNumb7 + 1][xx[2]] = 0.5;
                                    panduan = 1;
                                } else if (connections.get(jj).get(s23) != null){
                                    int[] xx = (int[]) connections.get(jj).get(s22);
                                    //出口边界条件(定流)
                                    matrix[colNumb7][x[0]] = 1;
                                    matrix[colNumb7][xx[0]] = 1;
                                    b[colNumb7][0] = nodes.get(i).getFlow();
                                    //压力平衡
                                    matrix[colNumb7 + 1][x[1]] = 1.5;
                                    matrix[colNumb7 + 1][x[2]] = -0.5;
                                    matrix[colNumb7 + 1][xx[1]] = -1.5;
                                    matrix[colNumb7 + 1][xx[2]] = 0.5;
                                    panduan = 1;
                                }
                            }
                            //出口边界条件(定流)
                            matrix[colNumb7][x[0]] = 1;
                            //if (mm >= 10 && i == 4){
                            //b[colNumb7][0] = 0;
                            //}else{
                            b[colNumb7][0] = nodes.get(i).getFlow();
                            //}
//                            System.out.println("出口坐标");
//                            for (int k = 0; k < x.length; k++) {
//                                System.out.println(x[k]);
//                            }
                        } else if (connections.get(j).get(s21) != null){
                            int[] x = (int[]) connections.get(j).get(s21);
                            //出口边界条件(定流)
                            matrix[colNumb7][x[0]] = 1;
                            b[colNumb7][0] = nodes.get(i).getFlow();
                        }
                    }
                    if (panduan == 1){
                        colNumb7 += 2;
                    }else {
                        colNumb7 += 1;
                    }
                }
            }


            /*阀门进出口压力流量关系系数*/
            int rowNumb4 = 2*sum - pipeCount;
            int rowNumb44 = sum + pipeCount;
            for (int i = 0; i < valveCount; i++) {
                String start = String.valueOf(0 + valves.get(i).getStartId());//管段起点编号
                start += "$";
                if ( !connections.get(pipeCount + i).containsKey(start) ){
                    start += "&";
                }
                int[] x1 = (int[]) connections.get(pipeCount + i).get(start);//阀门入口参数:Q1,H1

                String end = String.valueOf(0 - valves.get(i).getEndId());//管段终点编号
                end += "$";
                if ( !connections.get(pipeCount + i).containsKey(end) ){
                    end += "&";
                }
                int[] x2 = (int[]) connections.get(pipeCount + i).get(end);//阀门出口参数:Q2,H2

                //阀门进出口流量平衡
                matrix[rowNumb4][x1[0]] = 1;
                matrix[rowNumb4][x2[0]] = -1;
                b[rowNumb4][0] = 0;
                rowNumb4 ++;
                //阀门进出口压力平衡
                matrix[rowNumb4][x1[1]] = 1;
                matrix[rowNumb4][x2[1]] = -1;
                matrix[rowNumb4][x1[0]] = -1 * Qn[mm][rowNumb44]/ (2*g*cv*cv);
                b[rowNumb4][0] = 0;
                rowNumb44 += 2;//阀门入口流量在Qn中隔一个排列
                rowNumb4 ++;
            }

            /*方程组右端向量*/
            int rowNumb61 = 0;
            int rowNumb62 = 1;
            int rowNumb63 = pipeSegments[0] - 1;
            int rowNumb64 = 1;
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < pipeSegments[i] - 1; j++) {
                    b[rowNumb61 + j][0] = Qn[mm][rowNumb62 + j];
                    b[rowNumb63 + j][0] = Hn[mm][rowNumb64 + j];
                }
                b[rowNumb63 + pipeSegments[i] - 1][0] = Hn[mm][rowNumb64 + pipeSegments[i] - 1];

                rowNumb61 += 2*pipeSegments[i] - 1;
                rowNumb62 += pipeSegments[i] + 1;
                if (i != pipeCount - 1){
                    rowNumb63 = rowNumb63 + pipeSegments[i] + pipeSegments[i+1] -1;
                }
                rowNumb64 = rowNumb64 + pipeSegments[i] + 2;
            }

            //未优化前的矩阵
            SimpleMatrix matrixA = new SimpleMatrix(matrix);
            SimpleMatrix matrixB = new SimpleMatrix(b);

            long endTime2 = System.currentTimeMillis();
            System.out.println("边界条件求解计算时间:" + (endTime2 - startTime2));
//            System.out.println("matrixB" + matrixB);

            /*优化矩阵结构*/
            long startTime6 = System.currentTimeMillis();

            SimpleMatrix newMatrix = new SimpleMatrix(sum*2 + pipeCount + 4*valveCount,sum*2 + pipeCount + 4*valveCount);
            SimpleMatrix newB = new SimpleMatrix(sum*2 + pipeCount + 4*valveCount,1);

            //管段矩阵优化
            int numbRow1 = pipeSegments[0] - 1;
            int numbRow2 = 0;
            int numbCol1 = 0;
            int numbCol2 = sum + pipeCount;
            int numbNewRow = 0;
            int numbNewCol = 0;
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < 2*pipeSegments[i] - 1; j++) {
                    int numbCol11 = numbCol1;
                    int numbCol22 = numbCol2;
                    if (j % 2 == 0){
                        //管段节点矩阵
                        for (int k = 0; k < 2 * pipeSegments[i] + 1; k++) {
                            if (k % 2 == 0){
                                newMatrix.set(numbNewRow+j,numbNewCol+k,matrixA.get(numbRow1,numbCol11));
                                numbCol11 ++;
                            }else {
                                newMatrix.set(numbNewRow+j,numbNewCol+k,matrixA.get(numbRow1,numbCol22));
                                numbCol22 ++;
                            }
                        }
                        //方程右端向量
                        newB.set(numbNewRow+j,0,matrixB.get(numbRow1,0));
                        numbRow1++;
                    }else {
                        for (int k = 0; k < 2 * pipeSegments[i] + 1; k++) {
                            if (k % 2 == 0){
                                newMatrix.set(numbNewRow+j,numbNewCol+k,matrixA.get(numbRow2,numbCol11));
                                numbCol11 ++;
                            }else {
                                newMatrix.set(numbNewRow+j,numbNewCol+k,matrixA.get(numbRow2,numbCol22));
                                numbCol22 ++;
                            }
                        }
                        newB.set(numbNewRow+j,0,matrixB.get(numbRow2,0));
                        numbRow2++;
                    }
                }
                if(i < pipeCount-1){
                    numbRow1 = numbRow1 + pipeSegments[i+1] - 1;
                }
                numbRow2 = numbRow2 + pipeSegments[i];

                numbCol1 = numbCol1 + pipeSegments[i] + 1;
                numbCol2 = numbCol2 + pipeSegments[i];

                numbNewRow = numbNewRow + 2*pipeSegments[i] - 1;
                numbNewCol = numbNewCol + 2*pipeSegments[i] + 1;
            }
            //边界条件与元件矩阵优化
            //侧边元件矩阵
            for (int j = 0; j < 2*sum + pipeCount + 4*valveCount; j++) {
                for (int i = 2*sum + pipeCount; i < 2*sum + pipeCount + 4*valveCount; i++) {
                    newMatrix.set(j,i,matrixA.get(j,i));
                }
            }
            //底部边界条件
            for (int j = 2*sum - pipeCount; j < sum*2 + pipeCount + 4*valveCount; j++) {
                int numbNewCol2 = 0;
                int numbCol3 = 0;
                int numbCol4 = sum + pipeCount;
                for (int i = 0; i < pipeCount; i++) {
                    for (int k = 0; k < 2 * pipeSegments[i] + 1; k++) {
                        if (k % 2 == 0){
                            newMatrix.set(j,numbNewCol2+k,matrixA.get(j,numbCol3));
                            numbCol3 ++;
                        }else {
                            newMatrix.set(j,numbNewCol2+k,matrixA.get(j,numbCol4));
                            numbCol4 ++;
                        }
                    }
                    numbNewCol2 = numbNewCol2 + 2 * pipeSegments[i] + 1;
                }
            }
            //方程右端向量优化
            for (int i = 2*sum - pipeCount; i < sum*2 + pipeCount + 4*valveCount; i++) {
                newB.set(i,0,matrixB.get(i,0));
            }
            long endTime6 = System.currentTimeMillis();
            System.out.println("系数矩阵优化计算时间:" + (endTime6 - startTime6));

            //优化：优化矩阵newMatrix转数组
            for (int i = 0; i < matrixNew.length; i++) {
                for (int j = 0; j < matrixNew[i].length; j++) {
                    matrixNew[i][j] = newMatrix.get(i,j);
                }
            }


            if (mm < 21){

                /*未优化直接求解*/
//                long startTime = System.currentTimeMillis();
//                result = matrixA.solve(matrixB);
//                for(int i = 0;i < X.length;i++){
//                    X[i] = result.get(i,0);
//                }
//                long endTime = System.currentTimeMillis();
//                System.out.println("直接求解计算时间:" + (endTime - startTime));

                /*优化求解*/
                long startTime = System.currentTimeMillis();
                result = newMatrix.solve(newB);
                long endTime = System.currentTimeMillis();
                System.out.println("优化直接求解计算时间:" + (endTime - startTime));
                it1[mm] = (endTime - startTime);

                for(int i = 0;i < X.length;i++){
                    X[i] = result.get(i,0);
                }

                //输出结果优化
                long startTime7 = System.currentTimeMillis();
                int numbRow4 = sum + pipeCount;
                int numbRow5 = 0;
                int numbNewRow3 = 0;
                for (int i = 0; i < pipeCount; i++) {
                    for (int j = 0; j < 2*pipeSegments[i] + 1; j++) {
                        if (j % 2 == 0){
                            oldResult.set(numbRow5,0,result.get(numbNewRow3+j,0));
                            numbRow5++;
                        }else {
                            oldResult.set(numbRow4,0,result.get(numbNewRow3+j,0));
                            numbRow4++;
                        }
                    }
                    numbNewRow3 = numbNewRow3 + 2*pipeSegments[i] + 1;
                }

                long endTime7 = System.currentTimeMillis();
                System.out.println("输出结果优化计算时间:" + (endTime7 - startTime7));
            }
            else {
                System.out.println("迭代计算");

                /*迭代计算的初值输出*/
                for(int i = 0;i < X.length;i++){
                    Xout[mm][i] = X[i];
                }

//                for (int i = 0; i < B.length; i++) {
//                    B[i] = matrixB.get(i,0);
//                }
                //优化：右端向量B转数组
                for (int i = 0; i < B.length; i++) {
                    B[i] = newB.get(i,0);
                }
                //优化：优化矩阵newMatrix转数组
                for (int i = 0; i < matrixNew.length; i++) {
                    for (int j = 0; j < matrixNew[i].length; j++) {
                        matrixNew[i][j] = newMatrix.get(i,j);
                    }
                }
                double shift = 1e-7;
                //补齐主对角线上的零元素
//                for (int i = 0; i < matrixNew.length; i++) {
//                    if (matrixNew[i][i] == 0.0){
//                        matrixNew[i][i] = shift;
//                    }
//                }

                /*MTJ自带的迭代算法*/
                DenseMatrix Aold = new DenseMatrix(matrixNew);
                addDiagonal(Aold,shift);

                DenseVector Bnew = new DenseVector(B);
                DenseVector Xnew = new DenseVector(X);

//                SparseVector Bnew = new SparseVector(Bold);

                //行压缩格式
//                CompRowMatrix Anew = new CompRowMatrix(Aold);
                //列压缩格式
//                CompColMatrix Anew = new CompColMatrix(Aold);
                //弹性压缩矩阵
                FlexCompRowMatrix Anew = new FlexCompRowMatrix(Aold);
                //弹性压缩矩阵
//                FlexCompColMatrix Anew = new FlexCompColMatrix(Aold);
                //对角压缩格式
//                CompDiagMatrix Anew = new CompDiagMatrix(Aold);
                //不压缩格式
//                LinkedSparseMatrix Anew = new LinkedSparseMatrix(Aold);

                /*MTJ迭代求解*/
                long startTime5 = System.currentTimeMillis();

//                DefaultIterationMonitor defaultIterationMonitor = new DefaultIterationMonitor();
//                defaultIterationMonitor.setAbsoluteTolerance(1e-4);
//                defaultIterationMonitor.setRelativeTolerance(1e-3);
//                defaultIterationMonitor.setDivergenceTolerance(1e+5);

//                ILU ilu = new ILU(new CompRowMatrix(Aold));
//                ilu.setMatrix(new FlexCompRowMatrix(Aold));

                /*22222222222*/
//                ILU ilu = new ILU(Anew);
//                ilu.setMatrix(Aold);
//                Vector apply = ilu.apply(Bnew, Xnew);
//                Vector vectorEntries = ilu.transApply(Bnew, Xnew);
//                System.out.println("apply" + apply);
//                System.out.println("vectorEntries" + vectorEntries);

//                ILUT ilu = new ILUT(Anew);
//                ilu.setMatrix(Anew);

//                ICC ilu = new ICC(Anew);
//                ilu.setMatrix(Anew);

//                AMG ilu = new AMG();
//                ilu.setMatrix(Anew);


                GMRES gmres = new GMRES(Xnew,200);
//                gmres.setIterationMonitor(defaultIterationMonitor);
//                gmres.setPreconditioner(ilu);
                gmres.solve(Anew,Bnew,Xnew);

//                QMR qmr = new QMR(Bnew);
//                qmr.setPreconditioner(ilu);
//                qmr.solve(Aold,Bnew,Xnew);

//                BiCGstab biCGstab = new BiCGstab(Xnew);
//                biCGstab.setPreconditioner(ilu);
//                biCGstab.solve(Anew,Bnew,Xnew);

                for (int i = 0; i < X.length; i++) {
                    result.set(i,0,Xnew.get(i));
                }

                /*师兄的迭代算法*/
//                MatrixSolver matrixSolver = new MatrixSolver();
//                matrixSolver.BiCGSTAB(matrixNew,B,X);
//                for (int i = 0; i < X.length; i++) {
//                    result.set(i,0,X[i]);
//                }


                long endTime5 = System.currentTimeMillis();
                System.out.println("迭代求解计算时间:" + (endTime5 - startTime5));
                it2[mm] = (endTime5 - startTime5);

                /*输出结果优化*/
                long startTime8 = System.currentTimeMillis();
                int numbRow4 = sum + pipeCount;
                int numbRow5 = 0;
                int numbNewRow3 = 0;
                for (int i = 0; i < pipeCount; i++) {
                    for (int j = 0; j < 2*pipeSegments[i] + 1; j++) {
                        if (j % 2 == 0){
                            oldResult.set(numbRow5,0,result.get(numbNewRow3+j,0));
                            numbRow5++;
                        }else {
                            oldResult.set(numbRow4,0,result.get(numbNewRow3+j,0));
                            numbRow4++;
                        }
                    }
                    numbNewRow3 = numbNewRow3 + 2*pipeSegments[i] + 1;
                }
                long endTime8 = System.currentTimeMillis();
                System.out.println("输出结果优化2计算时间:" + (endTime8 - startTime8));

            }

            long startTime3 = System.currentTimeMillis();
            int rowNumb81 = 0;
            int rowNumb82 = 1;
            int rowNumb83 = sum + pipeCount;

//            for (int i = 0; i < pipeCount; i++) {
//                for (int j = 0; j < pipeSegments[i] + 1; j++) {
//                    Qn[mm + 1][rowNumb81 + j] = X[rowNumb81 + j];
//                }
//                rowNumb81 += pipeSegments[i] + 1;
//                for (int j = 0; j < pipeSegments[i]; j++) {
//                    Hn[mm + 1][rowNumb82 + j] = X[rowNumb83 + j];
//                }
//                Hn[mm + 1][rowNumb82 - 1] = 1.5 * Hn[mm + 1][rowNumb82] - 0.5 * Hn[mm + 1][rowNumb82 + 1];
//                Hn[mm + 1][rowNumb82 + pipeSegments[i]] = Hn[mm + 1][rowNumb82 + pipeSegments[i] - 1] * (dx + 2 * dxn[i]) / (dx + dxn[i]) - Hn[mm + 1][rowNumb82 + pipeSegments[i] - 2] * dxn[i] / (dx + dxn[i]);
//
//                rowNumb82 += pipeSegments[i] + 2;
//                rowNumb83 += pipeSegments[i];
//            }
//            int rowNumb84 = sum + pipeCount;
//            int rowNumb85 = 2*sum + pipeCount;
//            int rowNumb86 = sum + 2*pipeCount;
//            for (int i = 0; i < valveCount; i++) {
//                Qn[mm + 1][rowNumb84] = X[rowNumb85];
//                Qn[mm + 1][rowNumb84 + 1] = X[rowNumb85 + 2];
//                Hn[mm + 1][rowNumb86] = X[rowNumb85 + 1];
//                Hn[mm + 1][rowNumb86 + 1] = X[rowNumb85 + 3];
//                rowNumb84 += 2;
//                rowNumb86 += 2;
//                rowNumb85 += 4;
//            }

            /*给管段所有分段节点的Qn与Hn赋值*/
//            for (int i = 0; i < pipeCount; i++) {
//                for (int j = 0; j < pipeSegments[i] + 1; j++) {
//                    Qn[mm + 1][rowNumb81 + j] = result.get(rowNumb81 + j,0);
//                }
//                rowNumb81 += pipeSegments[i] + 1;
//                for (int j = 0; j < pipeSegments[i]; j++) {
//                    Hn[mm + 1][rowNumb82 + j] = result.get(rowNumb83 + j,0);
//                }
//                Hn[mm + 1][rowNumb82 - 1] = 1.5 * Hn[mm + 1][rowNumb82] - 0.5 * Hn[mm + 1][rowNumb82 + 1];
//                Hn[mm + 1][rowNumb82 + pipeSegments[i]] = Hn[mm + 1][rowNumb82 + pipeSegments[i] - 1] * (dx + 2 * dxn[i]) / (dx + dxn[i]) - Hn[mm + 1][rowNumb82 + pipeSegments[i] - 2] * dxn[i] / (dx + dxn[i]);
//
//                rowNumb82 += pipeSegments[i] + 2;
//                rowNumb83 += pipeSegments[i];
//            }
//
//            //给阀门两端节点的Qn与Hn赋值
//            int rowNumb84 = sum + pipeCount;
//            int rowNumb85 = 2*sum + pipeCount;
//            int rowNumb86 = sum + 2*pipeCount;
//            for (int i = 0; i < valveCount; i++) {
//                Qn[mm + 1][rowNumb84] = result.get(rowNumb85,0);
//                Qn[mm + 1][rowNumb84 + 1] = result.get(rowNumb85 + 2,0);
//                Hn[mm + 1][rowNumb86] = result.get(rowNumb85 + 1,0);
//                Hn[mm + 1][rowNumb86 + 1] = result.get(rowNumb85 + 3,0);
//                rowNumb84 += 2;
//                rowNumb86 += 2;
//                rowNumb85 += 4;
//            }

            /*结果优化输出*/
            /*给管段所有分段节点的Qn与Hn赋值*/
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < pipeSegments[i] + 1; j++) {
                    Qn[mm + 1][rowNumb81 + j] = oldResult.get(rowNumb81 + j,0);
                }
                rowNumb81 += pipeSegments[i] + 1;
                for (int j = 0; j < pipeSegments[i]; j++) {
                    Hn[mm + 1][rowNumb82 + j] = oldResult.get(rowNumb83 + j,0);
                }
                Hn[mm + 1][rowNumb82 - 1] = 1.5 * Hn[mm + 1][rowNumb82] - 0.5 * Hn[mm + 1][rowNumb82 + 1];
                Hn[mm + 1][rowNumb82 + pipeSegments[i]] = Hn[mm + 1][rowNumb82 + pipeSegments[i] - 1] * (dx + 2 * dxn[i]) / (dx + dxn[i]) - Hn[mm + 1][rowNumb82 + pipeSegments[i] - 2] * dxn[i] / (dx + dxn[i]);

                rowNumb82 += pipeSegments[i] + 2;
                rowNumb83 += pipeSegments[i];
            }

            //给阀门两端节点的Qn与Hn赋值
            int rowNumb84 = sum + pipeCount;
            int rowNumb85 = 2*sum + pipeCount;
            int rowNumb86 = sum + 2*pipeCount;
            for (int i = 0; i < valveCount; i++) {
                Qn[mm + 1][rowNumb84] = oldResult.get(rowNumb85,0);
                Qn[mm + 1][rowNumb84 + 1] = oldResult.get(rowNumb85 + 2,0);
                Hn[mm + 1][rowNumb86] = oldResult.get(rowNumb85 + 1,0);
                Hn[mm + 1][rowNumb86 + 1] = oldResult.get(rowNumb85 + 3,0);
                rowNumb84 += 2;
                rowNumb86 += 2;
                rowNumb85 += 4;
            }

            long endTime3 = System.currentTimeMillis();
            System.out.println("给下一时步赋值计算时间:" + (endTime3 - startTime3));
            /*判断是否需要结束*/
            int panduan3 = 0;
            for (int i = 0; i < Qn[0].length; i++) {
                if (Math.abs(Qn[mm + 1][i] - Qn[mm][i]) > 0.0001){
                    panduan3 = 1;
                }
            }
            for (int i = 0; i < Hn[0].length; i++) {
                if (Math.abs(Hn[mm + 1][i] - Hn[mm][i]) > 0.000001){
                    panduan3 = 1;
                }
            }
            if (panduan3 == 0){
                System.out.println("提前结束!");
                //break;
            }
            if (mm == times - 1){
                System.out.println("计算时间不够!");
            }
        }

//        int numb = 1;
//        Map<String,Double> QQ = new HashMap<>();
//        for (int i = 0; i < pipeCount; i++) {
//            System.out.println("第"+(i+1)+"段管段的管段数为:" + pipeSegments[i]);
//            System.out.println("第"+(i+1)+"段管段的入口分段编号为:" + numb);
//            //管段入口节点编号
//            String pipeInNumb = String.valueOf(pipes.get(i).getStartId());
//            double Q = Qn[time][numb-1];
//
//            if ( !QQ.containsKey(pipeInNumb) ){
//                QQ.put(pipeInNumb,Q);
//            }
//
//            numb = numb + pipeSegments[i] + 1;
//        }
//        System.out.println(QQ);
//
//        //节点流量(各管段入口)
//        double[] Qin = new double[nodes.size()];
//        for (int i = 0; i < nodes.size(); i++) {
//            String key = String.valueOf(i+1);
//            Qin[i] = QQ.get(key);
//        }

        long startTime4 = System.currentTimeMillis();
        /*获取每个节点的压力*/
        int numb1 = 1;
        int numb2 = -1;
        Map<String,Double> HH = new HashMap<>();
        for (int i = 0; i < pipeCount; i++) {
            numb2 = numb2 + pipeSegments[i] + 2;

            //管段入口节点编号
            String pipeInNumb = String.valueOf(pipes.get(i).getStartId());
            //管段出口节点编号
            String pipeOutNumb = String.valueOf(pipes.get(i).getEndId());

            double H1 = Hn[times][numb1-1];
            double H2 = Hn[times][numb2];

            if ( !HH.containsKey(pipeInNumb) ){
                HH.put(pipeInNumb,H1);
            }
            if ( !HH.containsKey(pipeOutNumb) ){
                HH.put(pipeOutNumb,H2);
            }

            numb1 = numb1 + pipeSegments[i] + 2;
        }

        //节点压力(各管段入口)
        double[] Hin = new double[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            String key = String.valueOf(i+1);
            if ( HH.containsKey(key) ){
                Hin[i] = HH.get(key);
            }
        }

        long endTime4 = System.currentTimeMillis();
        System.out.println("计算结束获取结果时间:" + (endTime4 - startTime4));

        for (int i = 0; i < times; i++) {
            System.out.println("直接求解法第" + i + "次的计算时间" + it1[i]);
        }
        for (int i = 0; i < times; i++) {
            System.out.println("迭代法第" + i + "次的计算时间" + it2[i]);
        }

        SimpleMatrix matrixA = new SimpleMatrix(matrix);

        //将优化过后的B输出
        double[][] BB = new double[sum*2 + pipeCount + 4*valveCount][1];
        for (int i = 0; i < BB.length; i++) {
            BB[i][0] = B[i];
        }

        this.matrixNewBee = matrixNew;
        this.Xout = Xout;
        this.Hin = Hin;
        this.Qn = Qn;
        this.Hn = Hn;
        this.b = b;
        this.connections = connections;
        this.matrix = matrix;
        this.simpleMatrixA = matrixA;
        this.pipeLength = pipeLength;
        this.pipeSegments = pipeSegments;
        this.pipeSegmentsLength = pipeSegmentsLength;
    }

    public void nodeType(NetWork netWork){


    }

    protected void addDiagonal(Matrix A, double shift) {
        int n = A.numRows(), m = A.numColumns();
        for (int i = 0; i < Math.min(n, m); ++i){
            if (A.get(i,i) == 0){
                A.set(i, i, shift);
            }
        }
    }
}
