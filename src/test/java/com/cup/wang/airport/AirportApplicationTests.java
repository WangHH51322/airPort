package com.cup.wang.airport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cup.wang.airport.mapper.*;
import com.cup.wang.airport.model.Node;
import com.cup.wang.airport.model.Pipe;
import com.cup.wang.airport.model.Valve;
import com.cup.wang.airport.model.density.DensityFitting;
import com.cup.wang.airport.service.DensityFittingService;
import com.cup.wang.airport.simulator.simulate.FixedFunctions;
import com.cup.wang.airport.simulator.simulate.NetWork;
import no.uib.cipr.matrix.sparse.IterativeSolverNotConvergedException;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.List;

@SpringBootTest
class AirportApplicationTests {

    @Autowired
    PipeMapper pipeMapper;
    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    ValveMapper valveMapper;


    @Test
    void contextLoads() throws IterativeSolverNotConvergedException {

        long startTime1 = System.currentTimeMillis();
        List<Pipe> pipesByProjectId = pipeMapper.getPipesByProjectId(1);
        List<Node> nodesByProjectId = nodeMapper.getNodesByProjectId(1);
        List<Valve> valvesByProjectId = valveMapper.getValvesByProjectId(1);

        NetWork netWork = new NetWork();
        netWork.setPipes(pipesByProjectId);
        netWork.setNodes(nodesByProjectId);
        netWork.setValves(valvesByProjectId);
        long endTime1 = System.currentTimeMillis();
        System.out.println("从数据库获取数据时间:" + (endTime1 - startTime1));

        long startTime = System.currentTimeMillis();
        FixedFunctions fixedFunctions = new FixedFunctions();
        fixedFunctions.setDx(18.0);
        fixedFunctions.setDt(1.0);
        fixedFunctions.setA(1214.0);
        fixedFunctions.setT(4);

        fixedFunctions.setCoefficientMatrix(netWork);

        double[][] matrix = fixedFunctions.getMatrix();
        double[][] b = fixedFunctions.getB();
        double[][] Qn = fixedFunctions.getQn();
        double[][] Hn = fixedFunctions.getHn();
        double[] Hin = fixedFunctions.getHin();

        long endTime = System.currentTimeMillis();
        System.out.println("计算时间:" + (endTime - startTime));

        long startTime3 = System.currentTimeMillis();
        /*excel*/
        String filePath = "D:\\121.xlsx";
        SXSSFWorkbook sxssfWorkbook = null;
        BufferedOutputStream outputStream = null;

        try {
            //这样表示SXSSFWorkbook只会保留100条数据在内存中，其它的数据都会写到磁盘里，这样的话占用的内存就会很少
            sxssfWorkbook = new SXSSFWorkbook(getXSSFWorkbook(filePath),100);
            //获取第一个Sheet页
            SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);
            SXSSFSheet sheetB = sxssfWorkbook.getSheetAt(1);
            SXSSFSheet sheetQn = sxssfWorkbook.getSheetAt(2);
            SXSSFSheet sheetHn = sxssfWorkbook.getSheetAt(3);
            SXSSFSheet sheetHin = sxssfWorkbook.getSheetAt(4);
            for (int z = 0; z < matrix.length; z++) {
                SXSSFRow row = sheet.createRow(z);
                for (int j = 0; j < matrix[z].length; j++) {
                    row.createCell(j).setCellValue(matrix[z][j]);
                }
            }
            for (int z = 0; z < b.length; z++) {
                SXSSFRow row = sheetB.createRow(z);
                for (int j = 0; j < b[z].length; j++) {
                    row.createCell(j).setCellValue(b[z][j]);
                }
            }
            for (int z = 0; z < Qn.length; z++) {
                SXSSFRow row = sheetQn.createRow(z);
                for (int j = 0; j < Qn[z].length; j++) {
                    row.createCell(j).setCellValue(Qn[z][j]);
                }
            }
            for (int z = 0; z < Hn.length; z++) {
                SXSSFRow row = sheetHn.createRow(z);
                for (int j = 0; j < Hn[z].length; j++) {
                    row.createCell(j).setCellValue(Hn[z][j]);
                }
            }
            SXSSFRow row1 = sheetHin.createRow(0);
            for (int z = 0; z < Hin.length; z++) {
                row1.createCell(z).setCellValue(Hin[z]);
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
        long endTime3 = System.currentTimeMillis();
        System.out.println("导出excel时间:" + (endTime3 - startTime3));
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
            workbook.createSheet("系数矩阵");
            workbook.createSheet("b");
            workbook.createSheet("Qn");
            workbook.createSheet("Hn");
            workbook.createSheet("Hin");
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

    @Test
    void getAllNodes(){

        Node node = new Node();

        for (int i = 1; i <= 855; i++) {

            String fileName = "node";
            fileName += i;
            fileName += ".Node";
            String Path="D:\\airportData\\" + fileName + ".json";
            String lastStr = "";

            BufferedReader reader = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(Path);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                reader = new BufferedReader(inputStreamReader);
                String tempString = null;
                //获取一个json文件里面的全部行
                while ((tempString = reader.readLine()) != null) {
                    lastStr += tempString;
                }
                //System.out.println(lastStr);
                reader.close();
                JSONObject data = JSON.parseObject(lastStr);

                //节点名称
                JSONObject dataItems = (JSONObject) data.get("dataItems");
                JSONObject nodeName = (JSONObject) dataItems.get("名称");
                String nodeNameValue = (String) nodeName.get("valueString");
                node.setName(nodeNameValue);
                //节点编号
                JSONObject nodeId = (JSONObject) dataItems.get("节点编号");
                int nodeIdValue = Integer.parseInt((String) nodeId.get("valueString"));
                node.setNumb(nodeIdValue);
                //节点流量
                JSONObject nodeFlow = (JSONObject) dataItems.get("节点流量");
                Double nodeFlowValue = Double.parseDouble((String) nodeFlow.get("valueString"));
                nodeFlowValue = Math.abs(nodeFlowValue);
                node.setFlow(nodeFlowValue);
                //节点压力
                JSONObject nodePressure = (JSONObject) dataItems.get("节点压力");
                Double nodePressureValue = Double.parseDouble((String) nodePressure.get("valueString"));
                nodePressureValue = Math.abs(nodePressureValue);
                node.setPressure(nodePressureValue);
                //节点类型
                /*1`普通节点:NodeType = 3;边界类型:Q or 2
                * 2`起点:NodeType = 1;边界类型:P or 1
                * 3`终点:NodeType = 2;边界类型:Q or 2*/
                if (nodeFlowValue == 0.0 && nodePressureValue == 0.0){
                    node.setNodeType(3);
                    node.setBcType(2);
                }else if (nodeFlowValue == 0.0 && nodePressureValue != 0.0){
                    node.setNodeType(1);
                    node.setBcType(1);
                }else if (nodeFlowValue != 0.0 && nodePressureValue == 0.0){
                    node.setNodeType(2);
                    node.setBcType(2);
                }
                //节点项目编号
                node.setProjectId(1);
                //将数据存入数据库
                nodeMapper.insertSelective(node);
                //System.out.println(node);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    @Test
    void getAllPipes(){

        Pipe pipe = new Pipe();

        for (int i = 1; i <= 803; i++) {

            String fileName = "pipe";
            fileName += i;
            fileName += ".Pipe";
            String Path="D:\\airportData\\" + fileName + ".json";
            String lastStr = "";

            BufferedReader reader = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(Path);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                reader = new BufferedReader(inputStreamReader);
                String tempString = null;
                //获取一个json文件里面的全部行
                while ((tempString = reader.readLine()) != null) {
                    lastStr += tempString;
                }
                //System.out.println(lastStr);
                reader.close();
                JSONObject data = JSON.parseObject(lastStr);

                //管段名称
                JSONObject dataItems = (JSONObject) data.get("dataItems");
                JSONObject pipeName = (JSONObject) dataItems.get("名称");
                String pipeNameValue = (String) pipeName.get("valueString");
                pipe.setName(pipeNameValue);
                //管段编号
                JSONObject pipeId = (JSONObject) dataItems.get("hU编号");
                int pipeIdValue = Integer.parseInt((String) pipeId.get("valueString"));
                pipe.setNumb(pipeIdValue);
                //管段起点
                JSONObject pipeStartId = (JSONObject) dataItems.get("起点编号");
                int pipeStartIdValue = Integer.parseInt((String) pipeStartId.get("valueString"));
                pipe.setStartId(pipeStartIdValue);
                //管段终点
                JSONObject pipeEndId = (JSONObject) dataItems.get("终点编号");
                int pipeEndIdValue = Integer.parseInt((String) pipeEndId.get("valueString"));
                pipe.setEndId(pipeEndIdValue);
                //管段长度
                JSONObject pipeLength = (JSONObject) dataItems.get("长度");
                Double pipeLengthValue = Double.parseDouble((String) pipeLength.get("valueString"));
                pipe.setLength(pipeLengthValue);
                //管段外径
                JSONObject pipeDiameter = (JSONObject) dataItems.get("外径");
                Double pipeDiameterValue = Double.parseDouble((String) pipeDiameter.get("valueString"));
                pipe.setOutsideDiameter(pipeDiameterValue);
                //管段壁厚
                JSONObject pipeThickness = (JSONObject) dataItems.get("壁厚");
                Double pipeThicknessValue = Double.parseDouble((String) pipeThickness.get("valueString"));
                pipe.setThickness(pipeThicknessValue);
                //管段粗糙度
                JSONObject pipeRoughness = (JSONObject) dataItems.get("粗糙度");
                Double pipeRoughnessValue = Double.parseDouble((String) pipeRoughness.get("valueString"));
                pipe.setRoughness(pipeRoughnessValue);
                //管段总传热系数
                JSONObject pipeK = (JSONObject) dataItems.get("总传热系数");
                Double pipeKValue = Double.parseDouble((String) pipeK.get("valueString"));
                pipe.setK(pipeKValue);
                //管段项目编号
                pipe.setProjectId(1);
                //将数据存入数据库
                pipeMapper.insertSelective(pipe);
                //System.out.println(node);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    @Test
    void getAllRegulatingValves(){

        Valve regulatingValve = new Valve();

        for (int i = 1; i <= 64; i++) {

            String fileName = "regulatingValve";
            fileName += i;
            fileName += ".RegulatingValve";
            String Path="D:\\airportData\\" + fileName + ".json";
            String lastStr = "";

            BufferedReader reader = null;
            try {
                FileInputStream fileInputStream = new FileInputStream(Path);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                reader = new BufferedReader(inputStreamReader);
                String tempString = null;
                //获取一个json文件里面的全部行
                while ((tempString = reader.readLine()) != null) {
                    lastStr += tempString;
                }
                //System.out.println(lastStr);
                reader.close();
                JSONObject data = JSON.parseObject(lastStr);

                //调节阀名称
                JSONObject dataItems = (JSONObject) data.get("dataItems");
                JSONObject regulatingValveName = (JSONObject) dataItems.get("名称");
                String regulatingValveNameValue = (String) regulatingValveName.get("valueString");
                regulatingValve.setName(regulatingValveNameValue);
                //调节阀编号
                JSONObject regulatingValveId = (JSONObject) dataItems.get("hU编号");
                int regulatingValveIdValue = Integer.parseInt((String) regulatingValveId.get("valueString"));
                regulatingValveIdValue -= 803;
                regulatingValve.setNumb(regulatingValveIdValue);
                //调节阀起点
                JSONObject regulatingValveStartId = (JSONObject) dataItems.get("起点编号");
                int regulatingValveStartIdValue = Integer.parseInt((String) regulatingValveStartId.get("valueString"));
                regulatingValve.setStartId(regulatingValveStartIdValue);
                //调节阀终点
                JSONObject regulatingValveEndId = (JSONObject) dataItems.get("终点编号");
                int regulatingValveEndIdValue = Integer.parseInt((String) regulatingValveEndId.get("valueString"));
                regulatingValve.setEndId(regulatingValveEndIdValue);
                //调节阀流量系数
                JSONObject regulatingValveCv = (JSONObject) dataItems.get("流量系数");
                Double regulatingValveCvValue = Double.parseDouble((String) regulatingValveCv.get("valueString"));
                regulatingValve.setCv(regulatingValveCvValue);
                //管段项目编号
                regulatingValve.setProjectId(1);
                //将数据存入数据库
                valveMapper.insertSelective(regulatingValve);
                //System.out.println(node);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

}
