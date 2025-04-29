package com.EmployeeSystem.service;

import com.EmployeeSystem.config.DbConfig;
import com.EmployeeSystem.model.EmployeeSystemModel;

import java.sql.*;

public class ProfileService {

    public EmployeeSystemModel getEmployeeById(int empId) {
        String sql = "SELECT FullName, Age, ContactNo FROM employee WHERE EmpID = ?";
        EmployeeSystemModel emp = null;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                emp = new EmployeeSystemModel();
                emp.setName(rs.getString("FullName"));
                emp.setAge(rs.getInt("Age"));
                emp.setContact(rs.getString("ContactNo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    public boolean updateEmployeeProfile(int empId, String fullName, int age, String contact) {
        String sql = "UPDATE employee SET FullName = ?, Age = ?, ContactNo = ? WHERE EmpID = ?";
        boolean updated = false;

        try (Connection conn = DbConfig.getDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fullName);
            stmt.setInt(2, age);
            stmt.setString(3, contact);
            stmt.setInt(4, empId);

            updated = stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
