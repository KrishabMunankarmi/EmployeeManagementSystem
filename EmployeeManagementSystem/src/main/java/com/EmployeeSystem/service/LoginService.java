package com.EmployeeSystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.EmployeeSystem.config.DbConfig;
import com.EmployeeSystem.model.EmployeeSystemModel;

public class LoginService {

    private Connection dbConn;
    private boolean isConnectionError = false;

    public LoginService() {
        try {
            dbConn = DbConfig.getDbConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            isConnectionError = true;
        }
    }


    public Boolean loginUser(EmployeeSystemModel employee) {
        if (isConnectionError) {
            System.out.println("Connection Error!");
            return null;
        }

        String query = "SELECT empid, name, password FROM employee WHERE name = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                String dbPassword = result.getString("password");
                return dbPassword.equals(employee.getPassword());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return false;
    }
}
