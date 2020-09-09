package com.cup.wang.airport.test;

import java.sql.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/3 21:12
 */
public class Utils {
    private static String url = "jdbc:mysql://localhost:3306/wang";
    private static String username = "root";
    private static String password = "newpassword";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    private static PreparedStatement preparedStatement;
    private static Statement statement;
    private static Connection connection;

    static {
        try {
            //注册驱动
            Class.forName(driver);
            try {
                //获取连接
                connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static Statement getStatement() {
        return statement;
    }

    public static void close() {
        try {
            statement.close();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
