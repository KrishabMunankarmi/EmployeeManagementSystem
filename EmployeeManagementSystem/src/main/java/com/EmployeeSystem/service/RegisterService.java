package com.EmployeeSystem.service;

import com.EmployeeSystem.config.DbConfig;
import com.EmployeeSystem.util.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class RegisterService {
    public boolean registerEmployee(HttpServletRequest request) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String empidStr = request.getParameter("empid");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("re-password");
        String ageStr = request.getParameter("age");
        String contactStr = request.getParameter("contact");
        String posidStr = request.getParameter("posid");
        String position = request.getParameter("position");
        String deptidStr = request.getParameter("deptid");
        String department = request.getParameter("department");
        String conidStr = request.getParameter("conid");
        String conperiod = request.getParameter("conperiod");

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        int empid = Integer.parseInt(empidStr);
        int age = Integer.parseInt(ageStr);
        long contact = Long.parseLong(contactStr);
        int posid = Integer.parseInt(posidStr);
        int deptid = Integer.parseInt(deptidStr);
        int conid = Integer.parseInt(conidStr);

        try (Connection conn = DbConfig.getDbConnection()) {
            if (recordExists(conn, "SELECT 1 FROM employee WHERE EmpID = ?", empid)) {
                return false;
            }

            conn.setAutoCommit(false);
            String hashedPassword = PasswordUtil.hashPassword(password);
            executeInsert(conn, "INSERT INTO employee (EmpID, FullName, Password, Age, ContactNo) VALUES (?,?,?,?,?)",
                    empid, name, hashedPassword, age, contact);

            if (!recordExists(conn, "SELECT 1 FROM position WHERE PositionID = ?", posid)) {
                executeInsert(conn, "INSERT INTO position (PositionID, PositionTitle) VALUES (?,?)", posid, position);
            }

            if (!recordExists(conn, "SELECT 1 FROM department WHERE DepartmentID = ?", deptid)) {
                executeInsert(conn, "INSERT INTO department (DepartmentID, DepartmentName) VALUES (?,?)", deptid, department);
            }

            if (!recordExists(conn, "SELECT 1 FROM contract WHERE ContractID = ?", conid)) {
                executeInsert(conn, "INSERT INTO contract (ContractID, ContractPeriod) VALUES (?,?)", conid, conperiod);
            }

            executeInsert(conn, "INSERT INTO EmployeeDepartment (EmpID, DepartmentID) VALUES (?,?)", empid, deptid);
            executeInsert(conn, "INSERT INTO employeedepartmentposition (EmpID, DepartmentID, PositionID) VALUES (?,?,?)", empid, deptid, posid);
            executeInsert(conn, "INSERT INTO employeecontract (EmpID, ContractID) VALUES (?,?)", empid, conid);

            conn.commit();
            return true;
        }
    }

    private boolean recordExists(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void executeInsert(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }
}