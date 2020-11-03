package com.cup.wang.airport.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cup.wang.airport.simulator.others.ExcelInput;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/30 17:00
 */

/**
 * excel读写工具类 */
public class GetExcelInfo {

    public static void main(String[] args) throws IOException {
//        //读取Excel文件
//        Workbook workbook = getWorkbook("D:\\123.xlsx");
//        //读取Sheet
//        Sheet sheet = getSheet(workbook);
//        //获取Excel内容
//        List<Map<Integer,String>> dpiList = getDpiList(sheet);
//
//        //测试用，遍历Excel内容
//        for (Map<Integer,String> colMap : dpiList) {
//            for (Map.Entry<Integer,String> entry : colMap.entrySet()) {
//                System.out.print(entry.getValue() + '\t');
//            }
//            System.out.println();
//        }
        ExcelInput excelInput = new ExcelInput("D:\\123.xlsx");
        Map temp = excelInput.read();

        List<List<String>> data1 = (List<List<String>>) temp.get("Sheet1");
        List<List<String>> data2 = (List<List<String>>) temp.get("Sheet2");

        for (List<String> strings : data1) {
            for (String string : strings) {
                System.out.println("Sheet1" + string);
            }
        }
        for (List<String> strings : data2) {
            for (String string : strings) {
                System.out.println("sheet2" + string);
            }
        }
    }

    public static Workbook getWorkbook(String filePath) throws IOException {
        FileInputStream is = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        workbook.close();
        return workbook;
    }

    public static Sheet getSheet(Workbook workbook) {
        //获取第一页内容
        Sheet sheet = workbook.getSheetAt(0);
        return sheet;
    }

    public static List<Map<Integer,String>> getDpiList(Sheet sheet) {
        List<Map<Integer,String>> dpiRuleList = new ArrayList<Map<Integer, String>>();
        Row row = sheet.getRow(0);
        //获取最大行数
        int rownum = sheet.getPhysicalNumberOfRows();
        //获取最大列数
        int colnum = row.getPhysicalNumberOfCells();
        for (int i = 0; i < rownum; i++) {
            //获取第i行数据
            row = sheet.getRow(i);
            HashMap<Integer, String> colMap = new HashMap<Integer, String>();
            for (int j = 0; j < colnum; j++) {
                Cell cell = row.getCell(j);
                String cellText = String.valueOf(cell);
                colMap.put(j, cellText);
            }
            dpiRuleList.add(colMap);
        }
        return dpiRuleList;
    }

}
