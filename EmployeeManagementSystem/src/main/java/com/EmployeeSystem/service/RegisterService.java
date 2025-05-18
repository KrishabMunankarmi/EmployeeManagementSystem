package com.EmployeeSystem.service;

import com.EmployeeSystem.config.DbConfig;
import com.EmployeeSystem.util.PasswordUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterService {

    public boolean registerEmployee(HttpServletRequest request, String imagePath)
            throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {

        // Read form parameters
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("re-password");
        String ageStr = request.getParameter("age");
        String contactStr = request.getParameter("contact");
        String position = request.getParameter("position");
        String department = request.getParameter("department");
        String conperiod = request.getParameter("conperiod");

        // Basic validation of required fields
        if (name == null || name.isBlank() ||
            password == null || password.isBlank() ||
            confirmPassword == null || confirmPassword.isBlank() ||
            ageStr == null || ageStr.isBlank() ||
            contactStr == null || contactStr.isBlank() ||
            position == null || position.isBlank() ||
            department == null || department.isBlank() ||
            conperiod == null || conperiod.isBlank()) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Parse numeric fields
        int age;
        long contact;
        try {
            age = Integer.parseInt(ageStr);
            contact = Long.parseLong(contactStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid age or contact number format");
        }

        // Debug log for uploaded image path
        System.out.println("DEBUG: imagePath received = " + imagePath);

        try (Connection conn = DbConfig.getDbConnection()) {
            conn.setAutoCommit(false);

            try {
                // Get or insert related entity IDs
                int positionId = getOrCreateId(conn, "position", "PositionTitle", "PositionID", position);
                int departmentId = getOrCreateId(conn, "department", "DepartmentName", "DepartmentID", department);
                int contractId = getOrCreateId(conn, "contract", "ContractPeriod", "ContractID", conperiod);

                // Hash password securely
                String hashedPassword = PasswordUtil.hashPassword(password);

                // Avoid null image path in DB
                if (imagePath == null) {
                    imagePath = "";
                }

                // Insert employee and get generated EmpID
                int empId = insertEmployee(conn, name, hashedPassword, age, contact, imagePath);

                // Insert relationship mappings
                executeInsert(conn, "INSERT INTO EmployeeDepartment (EmpID, DepartmentID) VALUES (?, ?)", empId, departmentId);
                executeInsert(conn, "INSERT INTO EmployeeDepartmentPosition (EmpID, DepartmentID, PositionID) VALUES (?, ?, ?)",
                        empId, departmentId, positionId);
                executeInsert(conn, "INSERT INTO EmployeeContract (EmpID, ContractID) VALUES (?, ?)", empId, contractId);

                conn.commit();
                return true;
            } catch (Exception e) {
                conn.rollback();
                throw e;
            }
        }
    }

    private int insertEmployee(Connection conn, String name, String password, int age, long contact, String imagePath) throws SQLException {
        String sql = "INSERT INTO employee (FullName, Password, Age, ContactNo, ImagePath) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            stmt.setInt(3, age);
            stmt.setLong(4, contact);
            stmt.setString(5, imagePath);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Return generated EmpID
                } else {
                    throw new SQLException("Failed to retrieve generated employee ID.");
                }
            }
        }
    }

    // Get ID from table or insert and return generated ID if not exists
    private int getOrCreateId(Connection conn, String table, String columnName, String idColumn, String value) throws SQLException {
        String selectSql = "SELECT " + idColumn + " FROM " + table + " WHERE " + columnName + " = ?";
        try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setString(1, value);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        String insertSql = "INSERT INTO " + table + " (" + columnName + ") VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, value);
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    throw new SQLException("Failed to insert and retrieve ID for " + table);
                }
            }
        }
    }

    // Helper method to execute simple inserts with parameters
    private void executeInsert(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }
}
