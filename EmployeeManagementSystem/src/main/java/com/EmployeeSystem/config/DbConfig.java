package com.EmployeeSystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
    // Loading JDBC driver
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
    }

    // Getting Database  connection
    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/employeesystem?serverTimezone=UTC",
            "root", 
            ""      
        );
    }
}
