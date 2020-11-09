package com.cup.wang.airport.simulator.simulate;

import com.cup.wang.airport.model.Node;
import com.cup.wang.airport.model.Pipe;
import com.cup.wang.airport.model.Valve;
import com.cup.wang.airport.simulator.others.MatrixSolver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.uib.cipr.matrix.DenseMatrix;
import no.uib.cipr.matrix.DenseVector;
import no.uib.cipr.matrix.Matrix;
import no.uib.cipr.matrix.Vector;
import no.uib.cipr.matrix.sparse.*;
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
    /*初始参数值*/
    private Double Q0;
    private Double H0;
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



        long startTime0 = System.currentTimeMillis();

        /*管网的基本信息*/
        double[] pipeRoughness = new double[pipeCount];//管段粗糙度
        double[] diameter = new double[pipeCount];//管径
        List<Pipe> pipes = netWork.getPipes();//管段列表
        for (int i = 0; i < pipeCount; i++) {
            /*管段粗糙度\管径*/
            pipeRoughness[i] = pipes.get(i).getRoughness();
            diameter[i] = pipes.get(i).getOutsideDiameter() - pipes.get(i).getThickness();
            /*每根管段的长度*/
            pipeLength[i] = netWork.getPipes().get(i).getLength();
            /*各管段的内径*/
            outsideDiameter[i] = netWork.getPipes().get(i).getOutsideDiameter();
            thickness[i] = netWork.getPipes().get(i).getThickness();
            insideDiameter[i] = outsideDiameter[i] - thickness[i]*2;
            /*各管段的流通面积*/
            area[i] = Math.PI * insideDiameter[i] * insideDiameter[i] / 4;
            /*各管段运动方差中H的系数*/
            gwdt[i] = g * area[i] * dt / dx;
            /*各管段动量方程中Q的系数*/
            Qcoef2[i] = a * a * dt / (g * area[i] * dx) / 50000;
            /*每根管段的分段数*/
            int n = (int) Math.floor(pipeLength[i] / dx);
            dxn[i] = pipeLength[i] - n * dx;
            if (dxn[i] < dx/2){
                pipeSegments[i] = n;
                dxn[i] = dx + dxn[i];
            } else {
                pipeSegments[i] = n+1;
            }
            sum += pipeSegments[i];
        }
        for (int i = 0; i < dxn.length; i++) {
            System.out.println("dxn[i]的长度" + dxn[i]);
        }

        /*系数矩阵*/
        double[][] matrix = new double[sum*2 + pipeCount + 4*valveCount][sum*2 + pipeCount + 4*valveCount];

        /*对节点在管网中的特点进行分类*/
        Map<Integer,Map<String,List<Integer>>> nodeConnections = new HashMap<>();
        Map<Integer,Map<String,List<Integer>>> pipeAndValveConnections = new HashMap<>();
        /*管段起终点Q和H的列编号*/
        int colNumbForPipeQ = 0;  //Q列计数
        int colNumbForPipeH = (sum+pipeCount) + 2*valveCount;  //H列计数
        /*首先给节点关联管段起终点*/
        for (int i = 0; i < pipeCount; i++) {
            /*将管段起点节点的信息和与之相连的管段编号存储到Map中*/
            Integer startId = netWork.getPipes().get(i).getStartId();
            if (nodeConnections.containsKey(startId)){
                if (nodeConnections.get(startId).containsKey("in")){
                    nodeConnections.get(startId).get("in").add(i);
                }else {
                    List<Integer> pipeNumb = new ArrayList<>();
                    pipeNumb.add(i);
                    nodeConnections.get(startId).put("in",pipeNumb);
                }
            }else {
                Map<String,List<Integer>> inAndOut = new HashMap<>();
                List<Integer> pipeNumb = new ArrayList<>();
                pipeNumb.add(i);
                inAndOut.put("in",pipeNumb);
                nodeConnections.put(startId,inAndOut);
            }
            /*将管段终点节点的信息和与之相连的管段编号存储到Map中*/
            Integer endId = netWork.getPipes().get(i).getEndId();
            if (nodeConnections.containsKey(endId)){
                if (nodeConnections.get(endId).containsKey("out")){
                    nodeConnections.get(endId).get("out").add(i);
                }else {
                    List<Integer> pipeNumb = new ArrayList<>();
                    pipeNumb.add(i);
                    nodeConnections.get(endId).put("out",pipeNumb);
                }
            }else {
                Map<String,List<Integer>> inAndOut = new HashMap<>();
                List<Integer> pipeNumb = new ArrayList<>();
                pipeNumb.add(i);
                inAndOut.put("out",pipeNumb);
                nodeConnections.put(endId,inAndOut);
            }
            /*将管段起终点节点对应的系数矩阵节点存储到Map中*/
            /*Q*/
            Map<String,List<Integer>> inAndOutQ = new HashMap<>();
            List<Integer> startAndEndQ = new ArrayList<>();  //用来存放Q部分的列编号
            startAndEndQ.add(colNumbForPipeQ);    //管段起点Q2对应的列编号
            startAndEndQ.add(colNumbForPipeQ + pipeSegments[i]);    //管段终点Qn+2对应的列编号
            inAndOutQ.put("Q",startAndEndQ);    //一开始Map内还没有管段i
            pipeAndValveConnections.put(i,inAndOutQ);   //将Q部分的列编号存入Map
            /*H*/
            List<Integer> startAndEndH = new ArrayList<>();  //用来存放H部分的列编号
            startAndEndH.add(colNumbForPipeH);    //管段起点H2对应的列编号1
            startAndEndH.add(colNumbForPipeH + 1);    //管段起点H3对应的列编号2
            startAndEndH.add(colNumbForPipeH + pipeSegments[i] - 2);    //管段终点Hn对应的列编号1
            startAndEndH.add(colNumbForPipeH + pipeSegments[i] - 1);    //管段终点Hn+1对应的列编号2
            pipeAndValveConnections.get(i).put("H",startAndEndH);   //将H部分的列编号存入Map
            /*更新colNumbForQ和colNumbForH*/
            colNumbForPipeQ += (pipeSegments[i] + 1);
            colNumbForPipeH += pipeSegments[i];
        }

        /*阀门起终点Q和H的列编号*/
        int colNumbForValveQ = sum + pipeCount;  //Q列计数
        int colNumbForValveH = 2*sum + pipeCount + 2*valveCount;  //H列计数
        /*再给节点关联阀门起终点*/
        for (int i = 0; i < valveCount; i++) {
            /*将管段起点节点的信息和与之相连的管段编号存储到Map中*/
            Integer startId = netWork.getValves().get(i).getStartId();
            if (nodeConnections.containsKey(startId)){
                if (nodeConnections.get(startId).containsKey("in")){
                    nodeConnections.get(startId).get("in").add(pipeCount+i);
                }else {
                    List<Integer> valveNumb = new ArrayList<>();
                    valveNumb.add(pipeCount+i);
                    nodeConnections.get(startId).put("in",valveNumb);
                }
            }else {
                Map<String,List<Integer>> inAndOut = new HashMap<>();
                List<Integer> valveNumb = new ArrayList<>();
                valveNumb.add(pipeCount+i);
                inAndOut.put("in",valveNumb);
                nodeConnections.put(startId,inAndOut);
            }
            /*将管段终点节点的信息和与之相连的管段编号存储到Map中*/
            Integer endId = netWork.getValves().get(i).getEndId();
            if (nodeConnections.containsKey(endId)){
                if (nodeConnections.get(endId).containsKey("out")){
                    nodeConnections.get(endId).get("out").add(pipeCount+i);
                }else {
                    List<Integer> valveNumb = new ArrayList<>();
                    valveNumb.add(pipeCount+i);
                    nodeConnections.get(endId).put("out",valveNumb);
                }
            }else {
                Map<String,List<Integer>> inAndOut = new HashMap<>();
                List<Integer> valveNumb = new ArrayList<>();
                valveNumb.add(pipeCount+i);
                inAndOut.put("out",valveNumb);
                nodeConnections.put(endId,inAndOut);
            }
            /*将阀门起终点节点对应的系数矩阵节点存储到Map中*/
            /*Q*/
            Map<String,List<Integer>> inAndOutQ = new HashMap<>();
            List<Integer> startAndEndQ = new ArrayList<>();  //用来存放Q部分的列编号
            startAndEndQ.add(colNumbForValveQ);    //阀门起点Q对应的列编号
            startAndEndQ.add(colNumbForValveQ + 1);    //管段终点Q对应的列编号
            inAndOutQ.put("Q",startAndEndQ);    //一开始Map内还没有管段i
            pipeAndValveConnections.put((i + pipeCount),inAndOutQ);   //将Q部分的列编号存入Map
            /*H*/
            List<Integer> startAndEndH = new ArrayList<>();  //用来存放H部分的列编号
            startAndEndH.add(colNumbForValveH);    //管段起点H对应的列编号
            startAndEndH.add(colNumbForValveH + 1);    //管段终点H对应的列编号
            pipeAndValveConnections.get(i + pipeCount).put("H",startAndEndH);   //将H部分的列编号存入Map
            /*更新colNumbForQ和colNumbForH*/
            colNumbForValveQ += 2;
            colNumbForValveH += 2;
        }
//        for (Integer in : nodeConnections.get(2).get("in")) {
//            System.out.println("以节点2作为入口的元件有:" + in);
//        }
//        for (Integer q : pipeAndValveConnections.get(2).get("H")) {
//            System.out.println("管段3的起终点H列坐标" + q);
//        }
//        for (Integer h : pipeAndValveConnections.get(3).get("Q")) {
//            System.out.println("阀门的起终点Q列坐标" + h);
//        }


        /*系数矩阵填充系数*/
        /*管段部分填充*/
        /*动量方程中Q和H的系数*/
        int rowNumbForDong = 0;    //第一根管段动量方程的行编号
        int colNumbForDongQ = 0;    //第一根管段动量方程Q2的列编号
        int colNumbForDongH = sum + pipeCount + 2*valveCount;   //第一根管段动量方程H2的列编号
        /*运动方程中H的系数*/
        int rowNumbForYun = sum + pipeCount + 2*valveCount + 1;   //第一根管段运动方程的行编号
        int colNumbForYunH = sum + pipeCount + 2*valveCount;   //第一根管段运动方程H2的列编号
        for (int i = 0; i < pipeCount; i++) {
            /*动量、运动方程部分系数补齐*/
            for (int j = 0; j < pipeSegments[i]; j++) {
                if (j < pipeSegments[i] - 2){
                    matrix[rowNumbForDong + j][colNumbForDongQ + j] = -Qcoef2[i];
                    matrix[rowNumbForDong + j][colNumbForDongQ + j + 1] = Qcoef2[i];
                    matrix[rowNumbForYun + j][colNumbForYunH + j] = -gwdt[i];
                    matrix[rowNumbForYun + j][colNumbForYunH + j + 1] = gwdt[i];
                }else if (j == pipeSegments[i] - 2){
                    matrix[rowNumbForDong + j][colNumbForDongQ + j] = -Qcoef2[i];
                    matrix[rowNumbForDong + j][colNumbForDongQ + j + 1] = Qcoef2[i];
                    matrix[rowNumbForYun + j][colNumbForYunH + j] = -gwdt[i] * dx / (dxn[i] / 2 + dx / 2);
                    matrix[rowNumbForYun + j][colNumbForYunH + j + 1] = gwdt[i] * dx / (dxn[i] / 2 + dx / 2);
                }else if(j == pipeSegments[i] - 1){
                    matrix[rowNumbForDong + j][colNumbForDongQ + j] = -Qcoef2[i] * dx / (dxn[i] / 2 + dx / 2);
                    matrix[rowNumbForDong + j][colNumbForDongQ + j + 1] = Qcoef2[i] * dx / (dxn[i] / 2 + dx / 2);
                }
//                if (j == pipeSegments[i] - 1) {
//                    matrix[rowNumbForDong + j][colNumbForDongQ + j] = -Qcoef2[i] * dx / (dxn[i] / 2 + dx / 2);
//                    matrix[rowNumbForDong + j][colNumbForDongQ + j + 1] = Qcoef2[i] * dx / (dxn[i] / 2 + dx / 2);
//                } else {
//                    matrix[rowNumbForDong + j][colNumbForDongQ + j] = -Qcoef2[i];
//                    matrix[rowNumbForDong + j][colNumbForDongQ + j + 1] = Qcoef2[i];
//                }
                matrix[rowNumbForDong + j][colNumbForDongH + j] = 1;
            }
            /*动量方程行列编号自增*/
            rowNumbForDong += (pipeSegments[i] + 1);
            colNumbForDongQ += (pipeSegments[i] + 1);
            colNumbForDongH += pipeSegments[i];
            /*运动方程行列编号自增*/
            rowNumbForYun += pipeSegments[i];
            colNumbForYunH += pipeSegments[i];
            /*运动方程部分系数补齐*/
//            for (int j = 0; j < pipeSegments[i]-1; j++) {
//                if (j == pipeSegments[i] - 2) {
//                    matrix[rowNumbForYun + j][colNumbForYunH + j] = -gwdt[i] * dx / (dxn[i] / 2 + dx / 2);
//                    matrix[rowNumbForYun + j][colNumbForYunH + j + 1] = gwdt[i] * dx / (dxn[i] / 2 + dx / 2);
//                } else {
//                    matrix[rowNumbForYun + j][colNumbForYunH + j] = -gwdt[i];
//                    matrix[rowNumbForYun + j][colNumbForYunH + j + 1] = gwdt[i];
//                }
//            }
//            rowNumbForYun += pipeSegments[i];
//            colNumbForYunH += pipeSegments[i];
        }

        /**
         * 获取全部节点
         */
        List<Node> nodes = netWork.getNodes();

        /*瞬态压力流量初始化*/
        double[][] Qn = new double[times + 1][sum + pipeCount + 2*valveCount];
        double[][] Hn = new double[times + 1][sum + 2 * (pipeCount + valveCount)];
        /*方程右端向量*/
        double[][] b = new double[sum*2 + pipeCount + 4*valveCount][1];

        for (int i = 0; i < Qn[0].length; i++) {
            Qn[0][i] = this.Q0;
        }
        for (int i = 0; i < Hn[0].length; i++) {
            Hn[0][i] = this.H0;
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
            for (int i = 0; i < pipeCount; i++) {
                int pipeInNumb = pipeAndValveConnections.get(i).get("Q").get(0);    //管段入口节点在系数矩阵中的编号
                calculateLambda.calculate(Qn[mm][pipeInNumb] / 50000,diameter[i],viscosity,pipeRoughness[i]);
                lambda[i] = calculateLambda.getLambda();
                m[i] = calculateLambda.getM();
                System.out.println("管段入口"+ i + "处流量:" + Qn[mm][pipeInNumb]);
                System.out.println("管段"+ i + "处摩阻:" + lambda[i]);
                System.out.println("管段"+ i + "处m:" + m[i]);
            }
            long endTime1 = System.currentTimeMillis();
            System.out.println("管段摩阻求解计算时间:" + (endTime1 - startTime1));

            long startTime2 = System.currentTimeMillis();
            /*运动方程Q的系数*/
            int rowNumbForYun2 = sum + pipeCount + 2*valveCount + 1;   //第一根管段运动方程的行编号
            int colNumbForYunQ = 1;   //第一根管段运动方程Q3的列编号
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < pipeSegments[i] - 1; j++) {
                    matrix[rowNumbForYun2 + j][colNumbForYunQ + j] = (1 + lambda[i] * g * area[i] * dt * Math.pow(Math.abs(Qn[mm][colNumbForYunQ + j] / 50000), (1 -m[i]))) / 50000;
                }
                /*系数自增*/
                rowNumbForYun2 += pipeSegments[i];
                colNumbForYunQ += pipeSegments[i] + 1;
            }

            /**
             * 水击条件
             */
//            if (mm > 200){
//                nodes.get(4).setFlow(0.0067);
//            }


            /*方程组右端向量*/
            int rowNumbRigntB1 = 0;     //右端向量b的行编号
            int rowNumbRigntB2 = sum + pipeCount + 2*valveCount + 1;    //右端向量b的行编号
            int rowNumbQn = 1;
            int rowNumbHn = 1;
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < pipeSegments[i] - 1; j++) {
                    b[rowNumbRigntB1 + j][0] = Hn[mm][rowNumbHn + j];
                    b[rowNumbRigntB2 + j][0] = Qn[mm][rowNumbQn + j] / 50000;
                }
                b[rowNumbRigntB1 + pipeSegments[i] - 1][0] = Hn[mm][rowNumbHn + pipeSegments[i] - 1];

                rowNumbRigntB1 += pipeSegments[i] + 1;
                rowNumbRigntB2 += pipeSegments[i];
                rowNumbQn += pipeSegments[i] + 1;
                rowNumbHn += pipeSegments[i] + 2;
            }

            /*阀门进出口边界条件*/
            int rowNumbValveBoundaryQ = sum + pipeCount + 1;     //流量边界条件行编号
            int rowNumbValveBoundaryH = 2*sum + pipeCount + 2*valveCount;    //压力边界条件行编号
            for (int i = 0; i < valveCount; i++) {
                /*阀门出口流量平衡*/
                Integer valveEndId = netWork.getValves().get(i).getEndId();   //阀门出口节点编号
                int elementStartNumb = 0;   //此节点连接的元件数
                /*中间节点:有进有出*/
                if (nodeConnections.get(valveEndId).containsKey("in") && nodeConnections.get(valveEndId).containsKey("out")){
                    elementStartNumb = nodeConnections.get(valveEndId).get("in").size()
                            + nodeConnections.get(valveEndId).get("out").size();
                }
                /*中间节点:弯头*/
                if (elementStartNumb == 2){
                    System.out.println("阀门" + (i+1)+ "出口节点是弯头");
                    Integer in = nodeConnections.get(valveEndId).get("in").get(0);     //元件编号(元件入口)
                    Integer out = nodeConnections.get(valveEndId).get("out").get(0);   //元件编号(元件出口)
                    /*流量平衡*/
                    Integer inQ = pipeAndValveConnections.get(in).get("Q").get(0);      //入口元件起点Q2的列编号
                    Integer outQ = pipeAndValveConnections.get(out).get("Q").get(1);      //出口元件终点Qn+2的列编号
                    matrix[rowNumbValveBoundaryQ][inQ] = -1;
                    matrix[rowNumbValveBoundaryQ][outQ] = 1;
                    b[rowNumbValveBoundaryQ][0] = 0;
                }
                /*中间节点:三通*/
                if (elementStartNumb == 3){
                    System.out.println("阀门" + (i+1)+ "出口节点是三通");
                    /*节点是一根管段的入口,两根管段的出口,此为特殊情况,需额外讨论*/
                    if (nodeConnections.get(valveEndId).get("in").size() == 1){

                        Integer in = nodeConnections.get(valveEndId).get("in").get(0);     //元件编号(元件入口)
                        Integer out1 = nodeConnections.get(valveEndId).get("out").get(0);   //元件编号(元件出口)
                        Integer out2 = nodeConnections.get(valveEndId).get("out").get(1);   //元件编号(元件出口)
                        if (!nodeConnections.get(valveEndId).containsKey("used")){
                            /*流量平衡*/
                            Integer inQ = pipeAndValveConnections.get(in).get("Q").get(0);      //入口元件起点Q2的列编号
                            Integer outQ1 = pipeAndValveConnections.get(out1).get("Q").get(1);      //出口元件1终点Qn+2的列编号
                            Integer outQ2 = pipeAndValveConnections.get(out2).get("Q").get(1);      //出口元件2终点Qn+2的列编号
                            matrix[rowNumbValveBoundaryQ][inQ] = -1;
                            matrix[rowNumbValveBoundaryQ][outQ1] = 1;
                            matrix[rowNumbValveBoundaryQ][outQ2] = 1;
                            b[rowNumbValveBoundaryQ][0] = 0;
                            /*特殊情况,此节点已经使用过流量平衡条件,不可再用,添加key:"used"表示*/
                            List<Integer> used = new ArrayList<>();
                            nodeConnections.get(valveEndId).put("used",used);
                        }else {
                            /*特殊情况,此节点已经使用过流量平衡条件,不可再用,只能使用两个out的压力平衡代替流量平衡*/
                            /*两个出口均为管段出口*/
                            /*此时还需要判断哪个出口跟此时的管段重合*/
                            if (out1 < pipeCount && out2 < pipeCount){
                                /*中间系数*/
                                double a1 = - dxn[out1]/(dx+dxn[out1]);
                                double a2 = (dx+2*dxn[out1])/(dx+dxn[out1]);
                                double b1 = - dxn[out2]/(dx+dxn[out2]);
                                double b2 = (dx+2*dxn[out2])/(dx+dxn[out2]);
                                /*出口元件1终点Hn在Hn(mm)中的编号*/
                                int colNumbForOut1 = 0;
                                for (int j = 0; j < out1 + 1; j++) {
                                    colNumbForOut1 += pipeSegments[out1];
                                }
                                colNumbForOut1 = 2*out1 - 1;   //出口元件1终点Hn在Hn(mm)中的编号
                                Integer out1H1 = pipeAndValveConnections.get(out1).get("H").get(2);      //出口元件1终点Hn的列编号
                                Integer out1Q2 = pipeAndValveConnections.get(out1).get("Q").get(1);      //出口元件1终点Qn+2的列编号
                                Integer out1Q1 = out1Q2 - 1;      //出口元件2终点Qn+1的列编号
                                Integer out2H1 = pipeAndValveConnections.get(out2).get("H").get(2);      //出口元件2终点Hn的列编号
                                Integer out2H2 = pipeAndValveConnections.get(out2).get("H").get(3);      //出口元件2终点Hn+1的列编号
                                matrix[rowNumbValveBoundaryQ][out1H1] = a1;
                                matrix[rowNumbValveBoundaryQ][out1Q2] = -a2*Qcoef2[out1];
                                matrix[rowNumbValveBoundaryQ][out1Q1] = a2*Qcoef2[out1];
                                matrix[rowNumbValveBoundaryQ][out2H1] = -b1;
                                matrix[rowNumbValveBoundaryQ][out2H2] = -b2;
                                b[rowNumbValveBoundaryQ][0] = -a2*Hn[mm][colNumbForOut1];
                            }
                            /*验证是第二次使用此节点后即可删去这个key*/
                            nodeConnections.get(valveEndId).remove("used");
                        }

                    }
                    /*节点是一根管段的出口,两根管段的入口*/
                    if (nodeConnections.get(valveEndId).get("in").size() == 2){
                        Integer in1 = nodeConnections.get(valveEndId).get("in").get(0);     //元件编号(元件入口)
                        Integer in2 = nodeConnections.get(valveEndId).get("in").get(1);     //元件编号(元件入口)
                        Integer out = nodeConnections.get(valveEndId).get("out").get(0);   //元件编号(元件出口)
                        /*流量平衡*/
                        Integer inQ1 = pipeAndValveConnections.get(in1).get("Q").get(0);      //入口元件1起点Q2的列编号
                        Integer inQ2 = pipeAndValveConnections.get(in2).get("Q").get(0);      //入口元件2起点Q2的列编号
                        Integer outQ = pipeAndValveConnections.get(out).get("Q").get(1);      //出口元件终点Qn+2的列编号
                        matrix[rowNumbValveBoundaryQ][inQ1] = -1;
                        matrix[rowNumbValveBoundaryQ][inQ2] = -1;
                        matrix[rowNumbValveBoundaryQ][outQ] = 1;
                        b[rowNumbValveBoundaryQ][0] = 0;
                    }
                }
                /*特殊节点:出口*/
                if (elementStartNumb == 0 && nodeConnections.get(valveEndId).containsKey("out")){
                    /*特殊节点:管网出口*/
                    if (nodeConnections.get(valveEndId).get("out").size() == 1){
                        System.out.println("阀门" + (i+1)+ "出口节点是管网出口");
                        /*流量平衡*/
                        Integer out = nodeConnections.get(valveEndId).get("out").get(0);   //元件编号(元件出口)
                        Integer outQ = pipeAndValveConnections.get(out).get("Q").get(1);      //出口元件终点Qn+2的列编号
                        matrix[rowNumbValveBoundaryQ][outQ] = 1;
                        b[rowNumbValveBoundaryQ][0] = nodes.get(valveEndId - 1).getFlow();    //nodes是一个List,在list内的编号比实际编号小一
                    }
                    /*特殊节点:管段双出口*/
                    if (nodeConnections.get(valveEndId).get("out").size() == 2){
                        System.out.println("阀门" + (i+1)+ "入口节点是管段双出口");
                    }
                    /*特殊节点:管段三出口*/
                    if (nodeConnections.get(valveEndId).get("out").size() == 3){
                        System.out.println("阀门" + (i+1)+ "入口节点是管段三出口");
                    }
                }

                /*阀门入口压力平衡*/
                Integer valveStartId = netWork.getValves().get(i).getStartId();   //阀门入口节点编号
                elementStartNumb = 0;
                /*中间节点:有进有出*/
                if (nodeConnections.get(valveStartId).containsKey("in") && nodeConnections.get(valveStartId).containsKey("out")){
                    elementStartNumb = nodeConnections.get(valveStartId).get("in").size()
                            + nodeConnections.get(valveStartId).get("out").size();
                }
                /*中间节点:弯头*/
                if (elementStartNumb == 2) {
                    System.out.println("阀门" + (i+1)+ "入口节点是弯头");
                    Integer in = nodeConnections.get(valveStartId).get("in").get(0);     //元件编号(元件入口)
                    Integer out = nodeConnections.get(valveStartId).get("out").get(0);   //元件编号(元件出口)
                    /*出口元件是阀*/
                    if(out >= pipeCount){
                        /*压力平衡*/
                        Integer inH = pipeAndValveConnections.get(in).get("H").get(0);      //入口元件起点H2的列编号
                        Integer outH = pipeAndValveConnections.get(out).get("H").get(1);      //出口元件终点Hn的列编号
                        matrix[rowNumbValveBoundaryH][inH] = -1;
                        matrix[rowNumbValveBoundaryH][outH] = 1;
                        b[rowNumbValveBoundaryH][0] = 0;
                    }
                    /*出口元件是管段*/
                    if (out < pipeCount){
                        /*压力平衡*/
                        Integer inH = pipeAndValveConnections.get(in).get("H").get(0);      //入口元件起点H2的列编号
                        Integer outH1 = pipeAndValveConnections.get(out).get("H").get(2);      //出口元件终点Hn的列编号
                        Integer outH2 = pipeAndValveConnections.get(out).get("H").get(3);      //出口元件终点Hn+1的列编号
                        matrix[rowNumbValveBoundaryH][inH] = -1;
                        matrix[rowNumbValveBoundaryH][outH1] = - dxn[i]/(dx+dxn[i]);
                        matrix[rowNumbValveBoundaryH][outH2] = (dx+2*dxn[i])/(dx+dxn[i]);
                        b[rowNumbValveBoundaryH][0] = 0;
                    }
                }
                /*中间节点:三通*/
                if (elementStartNumb == 3){
                    System.out.println("阀门" + (i+1)+ "入口节点是三通");
                    Integer out = nodeConnections.get(valveStartId).get("out").get(0);   //元件编号(元件出口)
                    Integer inH = pipeAndValveConnections.get(i+pipeCount).get("H").get(0);      //入口元件起点H1的列编号
                    matrix[rowNumbValveBoundaryH][inH] = -1;
                    b[rowNumbValveBoundaryH][0] = 0;
                    /*出口元件是阀*/
                    if (out >= pipeCount){
                        /*压力平衡*/
                        Integer outH = pipeAndValveConnections.get(out).get("H").get(1);      //出口元件终点Hn的列编号
                        matrix[rowNumbValveBoundaryH][outH] = 1;
                    }
                    /*出口元件是管段*/
                    if(out < pipeCount){
                        /*压力平衡*/
                        Integer outH1 = pipeAndValveConnections.get(out).get("H").get(2);      //出口元件终点Hn的列编号
                        Integer outH2 = pipeAndValveConnections.get(out).get("H").get(3);      //出口元件终点Hn+1的列编号
                        matrix[rowNumbValveBoundaryH][outH1] = - dxn[i]/(dx+dxn[i]);
                        matrix[rowNumbValveBoundaryH][outH2] = (dx+2*dxn[i])/(dx+dxn[i]);
                    }
                }
                /*特殊节点:入口*/
                if (elementStartNumb == 0 && nodeConnections.get(valveStartId).containsKey("in")){
                    /*特殊节点:管网入口*/
                    if (nodeConnections.get(valveStartId).get("in").size() == 1){
                        System.out.println("阀门" + (i+1)+ "入口节点是管网入口");
                        Integer inH = pipeAndValveConnections.get(i+pipeCount).get("H").get(0);      //入口元件起点H1的列编号
                        matrix[rowNumbValveBoundaryH][inH] = 1;
                        b[rowNumbValveBoundaryH][0] = nodes.get(valveStartId - 1).getPressure() / rou / g;
                    }
                    /*特殊节点:管段双入口*/
                    if (nodeConnections.get(valveStartId).get("in").size() == 2){
                        System.out.println("管段" + (i+1)+ "入口节点是管段双入口");
                    }
                    /*特殊节点:管段三入口*/
                    if (nodeConnections.get(valveStartId).get("in").size() == 3){
                        System.out.println("管段" + (i+1)+ "入口节点是管段三入口");
                    }
                }
                System.out.println("管段" + (i+1) + "入口节点连接的元件数" + elementStartNumb);
                /*压力边界条件行编号自增*/
                rowNumbValveBoundaryQ += 2;     //流量边界条件行编号
                rowNumbValveBoundaryH += 2;    //压力边界条件行编号
            }

            /*阀门部分填充*/
            /*Q的系数*/
            int rowNumbForValveEquationQ = sum + pipeCount;    //Q的行编号
            int colNumbForValveEquationQ = sum + pipeCount;    //Q的列编号
            /*H的系数*/
            int rowNumbForValveEquationH = 2*sum + pipeCount + 2*valveCount + 1;    //H的行编号
            int colNumbForValveEquationH = 2*sum + pipeCount + 2*valveCount;    //H的列编号
            for (int i = 0; i < valveCount; i++) {
                matrix[rowNumbForValveEquationQ][colNumbForValveEquationQ] = 1;
                matrix[rowNumbForValveEquationQ][colNumbForValveEquationQ + 1] = -1;
                b[rowNumbForValveEquationQ][0] = 0;
                matrix[rowNumbForValveEquationH][colNumbForValveEquationH] = 1;
                matrix[rowNumbForValveEquationH][colNumbForValveEquationH + 1] = -1;
                matrix[rowNumbForValveEquationH][colNumbForValveEquationQ] = -1 * Qn[mm][colNumbForValveEquationQ]/ (2*g*cv*cv); //随时间变化
                b[rowNumbForValveEquationH][0] = 0;

                /*编号更新*/
                rowNumbForValveEquationQ += 2;
                colNumbForValveEquationQ += 2;
                rowNumbForValveEquationH += 2;
                colNumbForValveEquationH += 2;
            }

            /*系数矩阵中边界条件的系数*/
            /*管段进出口边界条件*/
            int rowNumbPipeBoundaryQ = -1;     //流量边界条件行编号
            int rowNumbPipeBoundaryH = sum + pipeCount + 2*valveCount;    //压力边界条件行编号
            for (int i = 0; i < pipeCount; i++) {
                /*流量边界条件行编号自增*/
                rowNumbPipeBoundaryQ += pipeSegments[i] + 1;

                /*管段出口流量平衡*/
                Integer pipeEndId = netWork.getPipes().get(i).getEndId();   //管段出口节点编号
                int elementStartNumb = 0;   //此节点连接的元件数
                /*中间节点:有进有出*/
                if (nodeConnections.get(pipeEndId).containsKey("in") && nodeConnections.get(pipeEndId).containsKey("out")){
                    elementStartNumb = nodeConnections.get(pipeEndId).get("in").size()
                            + nodeConnections.get(pipeEndId).get("out").size();
                }
                /*中间节点:弯头*/
                if (elementStartNumb == 2){
                    System.out.println("管段" + (i+1)+ "出口节点是弯头");
                    Integer in = nodeConnections.get(pipeEndId).get("in").get(0);     //元件编号(元件入口)
                    Integer out = nodeConnections.get(pipeEndId).get("out").get(0);   //元件编号(元件出口)
                    /*流量平衡*/
                    /*节点类型*/
                    Integer nodeType = netWork.getNodes().get(pipeEndId - 1).getNodeType();     //管段起终点的实际编号比nodes在数组里面的序号大1
                    Integer inQ = pipeAndValveConnections.get(in).get("Q").get(0);      //入口元件起点Q2的列编号
                    Integer outQ = pipeAndValveConnections.get(out).get("Q").get(1);      //出口元件终点Qn+2的列编号
                    matrix[rowNumbPipeBoundaryQ][inQ] = -1;
                    matrix[rowNumbPipeBoundaryQ][outQ] = 1;
                    /*普通节点*/
                    if (nodeType == 3){
                        b[rowNumbPipeBoundaryQ][0] = 0;
                    }
                    /*出口节点*/
                    else if(nodeType == 2){
                        b[rowNumbPipeBoundaryQ][0] = netWork.getNodes().get(pipeEndId - 1).getFlow();
                    }

                }
                /*中间节点:三通*/
                if (elementStartNumb == 3){
                    System.out.println("管段" + (i+1)+ "出口节点是三通");
                    /*节点是一根管段的入口,两根管段的出口,此为特殊情况,需额外讨论*/
                    if (nodeConnections.get(pipeEndId).get("in").size() == 1){
                        Integer in = nodeConnections.get(pipeEndId).get("in").get(0);     //元件编号(元件入口)
                        System.out.println("管段" + (i+1) + "出口节点对应管段" + in + "入口");
                        Integer out1 = nodeConnections.get(pipeEndId).get("out").get(0);   //元件编号(元件出口)
                        System.out.println("管段" + (i+1) + "出口节点对应管段" + out1 + "出口");
                        Integer out2 = nodeConnections.get(pipeEndId).get("out").get(1);   //元件编号(元件出口)
                        System.out.println("管段" + (i+1) + "出口节点对应管段" + out2 + "出口");
                        if (!nodeConnections.get(pipeEndId).containsKey("used")){
                            /*流量平衡*/
                            Integer inQ = pipeAndValveConnections.get(in).get("Q").get(0);      //入口元件起点Q2的列编号
                            Integer outQ1 = pipeAndValveConnections.get(out1).get("Q").get(1);      //出口元件1终点Qn+2的列编号
                            Integer outQ2 = pipeAndValveConnections.get(out2).get("Q").get(1);      //出口元件2终点Qn+2的列编号
                            matrix[rowNumbPipeBoundaryQ][inQ] = -1;
                            matrix[rowNumbPipeBoundaryQ][outQ1] = 1;
                            matrix[rowNumbPipeBoundaryQ][outQ2] = 1;
                            b[rowNumbPipeBoundaryQ][0] = 0;
                            /*特殊情况,此节点已经使用过流量平衡条件,不可再用,添加key:"used"表示*/
                            List<Integer> used = new ArrayList<>();
                            nodeConnections.get(pipeEndId).put("used",used);
                        }else {
                            /*特殊情况,此节点已经使用过流量平衡条件,不可再用,只能使用两个out的压力平衡代替流量平衡*/
                            if(out2 == i){      //这一步的目的是令此时参与计算的管段编号=out1，因为这时out2对应的管段出口已经是使用过流量平衡边界条件
                                                //所以只能对out1使用压力平衡条件，并且把out1对应的出口条件进行分解
                                out2 = out1;
                                out1 = i;
                            }
                            /*两个出口均为管段出口*/
                            if (out1 < pipeCount && out2 < pipeCount) {
                                /*中间系数*/
                                double a1 = -dxn[out1] / (dx + dxn[out1]);
                                double a2 = (dx + 2 * dxn[out1]) / (dx + dxn[out1]);
                                double b1 = -dxn[out2] / (dx + dxn[out2]);
                                double b2 = (dx + 2 * dxn[out2]) / (dx + dxn[out2]);
                                /*出口元件1终点Hn在Hn(mm)中的编号*/
                                int colNumbForOut1 = 0;
                                for (int j = 0; j < out1 + 1; j++) {
                                    colNumbForOut1 += pipeSegments[j];
                                }
                                colNumbForOut1 += 2*out1;   //出口元件1终点Hn+1在Hn(mm)中的编号
                                Integer out1H1 = pipeAndValveConnections.get(out1).get("H").get(2);      //出口元件1终点Hn的列编号
                                Integer out1Q2 = pipeAndValveConnections.get(out1).get("Q").get(1);      //出口元件1终点Qn+2的列编号
                                Integer out1Q1 = out1Q2 - 1;      //出口元件1终点Qn+1的列编号
                                Integer out2H1 = pipeAndValveConnections.get(out2).get("H").get(2);      //出口元件2终点Hn的列编号
                                Integer out2H2 = pipeAndValveConnections.get(out2).get("H").get(3);      //出口元件2终点Hn+1的列编号
                                matrix[rowNumbPipeBoundaryQ][out1H1] = a1;
                                matrix[rowNumbPipeBoundaryQ][out1Q2] = -a2*Qcoef2[out1];
                                matrix[rowNumbPipeBoundaryQ][out1Q1] = a2*Qcoef2[out1];
                                matrix[rowNumbPipeBoundaryQ][out2H1] = -b1;
                                matrix[rowNumbPipeBoundaryQ][out2H2] = -b2;
                                b[rowNumbPipeBoundaryQ][0] = -a2*Hn[mm][colNumbForOut1];
                                System.out.println("out1被拆分的点:" + out1);
                                System.out.println("特殊节点b:" + b[rowNumbPipeBoundaryQ][0]);
                                System.out.println("特殊节点b:" + colNumbForOut1);
                            }
                            /*验证是第二次使用此节点后即可删去这个key*/
                            nodeConnections.get(pipeEndId).remove("used");
                        }

                    }
                    /*节点是一根管段的出口,两根管段的入口*/
                    if (nodeConnections.get(pipeEndId).get("in").size() == 2){
                        Integer in1 = nodeConnections.get(pipeEndId).get("in").get(0);     //元件编号(元件入口)
                        Integer in2 = nodeConnections.get(pipeEndId).get("in").get(1);     //元件编号(元件入口)
                        Integer out = nodeConnections.get(pipeEndId).get("out").get(0);   //元件编号(元件出口)
                        /*流量平衡*/
                        Integer inQ1 = pipeAndValveConnections.get(in1).get("Q").get(0);      //入口元件1起点Q2的列编号
                        Integer inQ2 = pipeAndValveConnections.get(in2).get("Q").get(0);      //入口元件2起点Q2的列编号
                        Integer outQ = pipeAndValveConnections.get(out).get("Q").get(1);      //出口元件终点Qn+2的列编号
                        matrix[rowNumbPipeBoundaryQ][inQ1] = -1;
                        matrix[rowNumbPipeBoundaryQ][inQ2] = -1;
                        matrix[rowNumbPipeBoundaryQ][outQ] = 1;
                        b[rowNumbPipeBoundaryQ][0] = 0;
                    }
                }
                /*特殊节点:出口*/
                if (elementStartNumb == 0 && nodeConnections.get(pipeEndId).containsKey("out")){
                    /*特殊节点:管网出口*/
                    if (nodeConnections.get(pipeEndId).get("out").size() == 1){
                        System.out.println("管段" + (i+1)+ "出口节点是管网出口");
                        /*流量平衡*/
                        Integer out = nodeConnections.get(pipeEndId).get("out").get(0);   //元件编号(元件出口)
                        Integer outQ = pipeAndValveConnections.get(out).get("Q").get(1);      //出口元件终点Qn+2的列编号
                        matrix[rowNumbPipeBoundaryQ][outQ] = 1;
                        b[rowNumbPipeBoundaryQ][0] = nodes.get(pipeEndId - 1).getFlow();    //nodes是一个List,在list内的编号比实际编号小一
                    }
                    /*特殊节点:管段双出口*/
                    if (nodeConnections.get(pipeEndId).get("out").size() == 2){
                        System.out.println("管段" + (i+1)+ "入口节点是管段双出口");
                    }
                    /*特殊节点:管段三出口*/
                    if (nodeConnections.get(pipeEndId).get("out").size() == 3){
                        System.out.println("管段" + (i+1)+ "入口节点是管段三出口");
                    }
                }

                /*管段入口压力平衡*/
                Integer pipeStartId = netWork.getPipes().get(i).getStartId();   //管段入口节点编号
                elementStartNumb = 0;   //给elementStartNumb重新赋值,用来判断
                /*中间节点:有进有出*/
                if (nodeConnections.get(pipeStartId).containsKey("in") && nodeConnections.get(pipeStartId).containsKey("out")){
                    elementStartNumb = nodeConnections.get(pipeStartId).get("in").size()
                            + nodeConnections.get(pipeStartId).get("out").size();
                }
                /*中间节点:弯头*/
                if (elementStartNumb == 2) {
                    System.out.println("管段" + (i+1)+ "入口节点是弯头");
                    Integer in = nodeConnections.get(pipeStartId).get("in").get(0);     //元件编号(元件入口)
                    Integer out = nodeConnections.get(pipeStartId).get("out").get(0);   //元件编号(元件出口)
                    /*出口元件是阀*/
                    if(out >= pipeCount && in <pipeCount){
                        /*压力平衡*/
                        Integer inH1 = pipeAndValveConnections.get(in).get("H").get(0);      //入口元件起点H2的列编号
                        Integer inH2 = pipeAndValveConnections.get(in).get("H").get(1);      //入口元件起点H3的列编号
                        Integer outH = pipeAndValveConnections.get(out).get("H").get(1);      //出口元件终点Hn的列编号
                        matrix[rowNumbPipeBoundaryH][inH1] = -1.5;
                        matrix[rowNumbPipeBoundaryH][inH2] = 0.5;
                        matrix[rowNumbPipeBoundaryH][outH] = 1;
                        b[rowNumbPipeBoundaryH][0] = 0;
                    }
                    /*出口元件是管段*/
                    if (out < pipeCount && in <pipeCount){
                        /*压力平衡*/
                        Integer inH1 = pipeAndValveConnections.get(in).get("H").get(0);      //入口元件起点H2的列编号
                        Integer inH2 = pipeAndValveConnections.get(in).get("H").get(1);      //入口元件起点H3的列编号
                        Integer outH1 = pipeAndValveConnections.get(out).get("H").get(2);      //出口元件终点Hn的列编号
                        Integer outH2 = pipeAndValveConnections.get(out).get("H").get(3);      //出口元件终点Hn+1的列编号
                        matrix[rowNumbPipeBoundaryH][inH1] = -1.5;
                        matrix[rowNumbPipeBoundaryH][inH2] = 0.5;
                        matrix[rowNumbPipeBoundaryH][outH1] = - dxn[out]/(dx+dxn[out]);
                        matrix[rowNumbPipeBoundaryH][outH2] = (dx+2*dxn[out])/(dx+dxn[out]);
                        b[rowNumbPipeBoundaryH][0] = 0;
                    }
                }
                /*中间节点:三通*/
                if (elementStartNumb == 3){
                    System.out.println("管段" + (i+1)+ "入口节点是三通");
                    Integer out = nodeConnections.get(pipeStartId).get("out").get(0);   //元件编号(元件出口)
                    Integer inH1 = pipeAndValveConnections.get(i).get("H").get(0);      //入口元件起点H2的列编号
                    Integer inH2 = pipeAndValveConnections.get(i).get("H").get(1);      //入口元件起点H3的列编号
                    matrix[rowNumbPipeBoundaryH][inH1] = -1.5;
                    matrix[rowNumbPipeBoundaryH][inH2] = 0.5;
                    b[rowNumbPipeBoundaryH][0] = 0;
                    /*出口元件是阀*/
                    if (out >= pipeCount){
                        /*压力平衡*/
                        Integer outH = pipeAndValveConnections.get(out).get("H").get(1);      //出口元件终点Hn的列编号
                        matrix[rowNumbPipeBoundaryH][outH] = 1;
                    }
                    /*出口元件是管段*/
                    if(out < pipeCount){
                        /*压力平衡*/
                        Integer outH1 = pipeAndValveConnections.get(out).get("H").get(2);      //出口元件终点Hn的列编号
                        Integer outH2 = pipeAndValveConnections.get(out).get("H").get(3);      //出口元件终点Hn+1的列编号
                        matrix[rowNumbPipeBoundaryH][outH1] = - dxn[out]/(dx+dxn[out]);
                        matrix[rowNumbPipeBoundaryH][outH2] = (dx+2*dxn[out])/(dx+dxn[out]);
                    }
                }
                /*特殊节点:入口*/
                if (elementStartNumb == 0 && nodeConnections.get(pipeStartId).containsKey("in")){
                    /*特殊节点:管网入口*/
                    if (nodeConnections.get(pipeStartId).get("in").size() == 1){
                        System.out.println("管段" + (i+1)+ "入口节点是管网入口");
                        Integer inH1 = pipeAndValveConnections.get(i).get("H").get(0);      //入口元件起点H2的列编号
                        Integer inH2 = pipeAndValveConnections.get(i).get("H").get(1);      //入口元件起点H3的列编号
                        matrix[rowNumbPipeBoundaryH][inH1] = 1.5;
                        matrix[rowNumbPipeBoundaryH][inH2] = -0.5;
                        b[rowNumbPipeBoundaryH][0] = nodes.get(pipeStartId - 1).getPressure() / rou / g;
                    }
                    /*特殊节点:管段双入口*/
                    if (nodeConnections.get(pipeStartId).get("in").size() == 2){
                        System.out.println("管段" + (i+1)+ "入口节点是管段双入口");
                    }
                    /*特殊节点:管段三入口*/
                    if (nodeConnections.get(pipeStartId).get("in").size() == 3){
                        System.out.println("管段" + (i+1)+ "入口节点是管段三入口");
                    }
                }
                System.out.println("管段" + (i+1) + "入口节点连接的元件数" + elementStartNumb);
                /*压力边界条件行编号自增*/
                rowNumbPipeBoundaryH += pipeSegments[i];
            }

            /*构建计算矩阵*/
            SimpleMatrix matrixA = new SimpleMatrix(matrix);
            SimpleMatrix matrixB = new SimpleMatrix(b);

            if (mm < 5){

                /*未优化直接求解*/
                long startTime = System.currentTimeMillis();
                result = matrixA.solve(matrixB);
                for(int i = 0;i < X.length;i++){
                    X[i] = result.get(i,0);
                }
                long endTime = System.currentTimeMillis();
                System.out.println("直接求解计算时间:" + (endTime - startTime));
                it1[mm] = (endTime - startTime);
            }
            else {
                System.out.println("迭代计算");

                /*迭代计算的初值输出*/
                for(int i = 0;i < X.length;i++){
                    Xout[mm][i] = X[i];
                }

                for (int i = 0; i < B.length; i++) {
                    B[i] = matrixB.get(i,0);
                }

                /*MTJ自带的迭代算法*/
                DenseMatrix Aold = new DenseMatrix(matrix);
                DenseVector Bnew = new DenseVector(B);
                DenseVector Xnew = new DenseVector(X);

                //行压缩格式
//                CompRowMatrix Anew = new CompRowMatrix(Aold);
//                //列压缩格式
//                CompColMatrix Anew = new CompColMatrix(Aold);
                //弹性压缩矩阵
                FlexCompRowMatrix Anew = new FlexCompRowMatrix(Aold);
//                //弹性压缩矩阵
//                FlexCompColMatrix Anew = new FlexCompColMatrix(Aold);
//                //对角压缩格式
//                CompDiagMatrix Anew = new CompDiagMatrix(Aold);
//                //不压缩格式
//                LinkedSparseMatrix Anew = new LinkedSparseMatrix(Aold);

                /*MTJ迭代求解*/
                long startTime5 = System.currentTimeMillis();

////
//
//                /*22222222222*/
                ILU ilu = new ILU(new CompRowMatrix(Aold));
//                ILU ilu = new ILU(Anew);
                ilu.setMatrix(Aold);
//                Vector apply = ilu.apply(Bnew, Xnew);
//                Vector vectorEntries = ilu.transApply(Bnew, Xnew);
//                System.out.println("apply" + apply);
//                System.out.println("vectorEntries" + vectorEntries);

                GMRES gmres = new GMRES(Xnew,2000);
                gmres.setPreconditioner(ilu);
                gmres.solve(Anew,Bnew,Xnew);


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

            }

            /*给下一时步的Qn\Hn赋值*/
            long startTime3 = System.currentTimeMillis();
            /*管段Qn\Hn赋值的行列编号*/
            int rowNumbForAssignPipeQn = 0;     //管段Qn行编号
            int rowNumbForAssignPipeHn = 1;     //管段Hn行编号
            int rowNumbForPipeResult = sum + pipeCount + 2*valveCount;      //计算结果中管段Hn行编号
            /*给管段所有分段节点的Qn与Hn赋值*/
            for (int i = 0; i < pipeCount; i++) {
                for (int j = 0; j < pipeSegments[i] + 1; j++) {
                    Qn[mm + 1][rowNumbForAssignPipeQn + j] = result.get(rowNumbForAssignPipeQn + j,0);
                }
                rowNumbForAssignPipeQn += pipeSegments[i] + 1;
                for (int j = 0; j < pipeSegments[i]; j++) {
                    Hn[mm + 1][rowNumbForAssignPipeHn + j] = result.get(rowNumbForPipeResult + j,0);
                }
                Hn[mm + 1][rowNumbForAssignPipeHn - 1] = 1.5 * Hn[mm + 1][rowNumbForAssignPipeHn] - 0.5 * Hn[mm + 1][rowNumbForAssignPipeHn + 1];
                Hn[mm + 1][rowNumbForAssignPipeHn + pipeSegments[i]] = Hn[mm + 1][rowNumbForAssignPipeHn + pipeSegments[i] - 1] * (dx + 2 * dxn[i]) / (dx + dxn[i]) - Hn[mm + 1][rowNumbForAssignPipeHn + pipeSegments[i] - 2] * dxn[i] / (dx + dxn[i]);

                rowNumbForAssignPipeHn += pipeSegments[i] + 2;
                rowNumbForPipeResult += pipeSegments[i];
            }

            /*阀门Qn\Hn赋值的行列编号*/
//            int rowNumbForAssignValveQn = sum + pipeCount;      //阀门Qn行编号
//            int rowNumbForAssignValveHn = sum + 2*pipeCount;     //阀门Hn行编号
//            int rowNumbForValveResult = 2*sum + pipeCount + 2*valveCount;       //计算结果中阀门Hn行编号
//            /*给阀门两端节点的Qn与Hn赋值*/
//            for (int i = 0; i < valveCount; i++) {
//                Qn[mm + 1][rowNumbForAssignValveQn] = result.get(rowNumbForAssignValveQn,0);
//                Qn[mm + 1][rowNumbForAssignValveQn + 1] = result.get(rowNumbForAssignValveQn + 1,0);
//                Hn[mm + 1][rowNumbForAssignValveHn] = result.get(rowNumbForValveResult,0);
//                Hn[mm + 1][rowNumbForAssignValveHn + 1] = result.get(rowNumbForValveResult + 1,0);
//                rowNumbForAssignValveQn += 2;
//                rowNumbForAssignValveHn += 2;
//                rowNumbForValveResult += 2;
//            }

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

//        long startTime4 = System.currentTimeMillis();
//        /*获取每个节点的压力*/
//        int numb1 = 1;
//        int numb2 = -1;
//        Map<String,Double> HH = new HashMap<>();
//        for (int i = 0; i < pipeCount; i++) {
//            numb2 = numb2 + pipeSegments[i] + 2;
//
//            //管段入口节点编号
//            String pipeInNumb = String.valueOf(pipes.get(i).getStartId());
//            //管段出口节点编号
//            String pipeOutNumb = String.valueOf(pipes.get(i).getEndId());
//
//            double H1 = Hn[times][numb1-1];
//            double H2 = Hn[times][numb2];
//
//            if ( !HH.containsKey(pipeInNumb) ){
//                HH.put(pipeInNumb,H1);
//            }
//            if ( !HH.containsKey(pipeOutNumb) ){
//                HH.put(pipeOutNumb,H2);
//            }
//
//            numb1 = numb1 + pipeSegments[i] + 2;
//        }
//
//        //节点压力(各管段入口)
//        double[] Hin = new double[nodes.size()];
//        for (int i = 0; i < nodes.size(); i++) {
//            String key = String.valueOf(i+1);
//            if ( HH.containsKey(key) ){
//                Hin[i] = HH.get(key);
//            }
//        }
//
//        long endTime4 = System.currentTimeMillis();
//        System.out.println("计算结束获取结果时间:" + (endTime4 - startTime4));
//
        for (int i = 0; i < times; i++) {
            System.out.println("直接求解法第" + i + "次的计算时间" + it1[i]);
        }
        for (int i = 0; i < times; i++) {
            System.out.println("迭代法第" + i + "次的计算时间" + it2[i]);
        }

        SimpleMatrix matrixA = new SimpleMatrix(matrix);

        this.matrixNewBee = matrixNew;
        this.Xout = Xout;
        this.Hin = Hin;
        this.Qn = Qn;
        this.Hn = Hn;
        this.b = b;
        this.connections = connections;
        this.matrix = matrix;
//        this.simpleMatrixA = matrixA;
        this.pipeLength = pipeLength;
        this.pipeSegments = pipeSegments;
        this.pipeSegmentsLength = pipeSegmentsLength;
    }

}
