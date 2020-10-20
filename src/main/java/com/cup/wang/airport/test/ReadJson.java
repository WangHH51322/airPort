package com.cup.wang.airport.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/10/10 20:55
 */
public class ReadJson {

    public static void main(String[] args) {

        String fileName = "node1";
        fileName += ".Node";
        String Path="d:\\" + fileName + ".json";
        String laststr = "";

        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            System.out.println(laststr);
            reader.close();
            JSONObject data = JSON.parseObject(laststr);

            JSONObject dataItems = (JSONObject) data.get("dataItems");
            JSONObject nodeId = (JSONObject) dataItems.get("节点编号");
            String valueString = (String) nodeId.get("valueString");


            System.out.println(valueString);


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
