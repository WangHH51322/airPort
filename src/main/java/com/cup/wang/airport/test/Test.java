package com.cup.wang.airport.test;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/9/3 20:56
 */
public class Test {
    public static void main(String[] args) {

        // 关闭自动提交

        // 根据id查询

        String username = "admin";
        String password = "admin or 1 = 1";

        String sql = "select * from demo";

        try {
            // 转账 +
            ResultSet resultSet = Utils.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println(id);
                // 提交事务
            }
        } catch (SQLException e) {
            // 转账失败
            // 事务回滚
            System.out.println("查询出错");
        } finally {
            Utils.close();
        }
    }
}
