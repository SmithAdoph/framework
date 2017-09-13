package com.adoph.framework.dao.jdbc;

import sun.jdbc.odbc.JdbcOdbcDriver;

import java.sql.*;

/**
 * JdbcUtils
 *
 * @author Adoph
 * @version 1.0
 * @since 2017/9/5
 */
public class JdbcUtils {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/smzc_erp", "root", "root");
        String sql = "select * from malfunction_report";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.wasNull());
            System.out.println(resultSet.getLong("repair_remark"));
            System.out.println(resultSet.wasNull());
            System.out.println("-------------------");
        }
    }

}

