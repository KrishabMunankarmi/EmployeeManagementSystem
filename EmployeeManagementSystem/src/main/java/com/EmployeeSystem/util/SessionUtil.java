package com.EmployeeSystem.util;

import com.EmployeeSystem.model.EmployeeSystemModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    public static final String SESSION_EMPLOYEE = "loggedInEmployee";

    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        return (session != null) ? session.getAttribute(key) : null;
    }

    public static void removeAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
    }

    public static void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public static EmployeeSystemModel getLoggedInEmployee(HttpServletRequest request) {
        Object obj = getAttribute(request, SESSION_EMPLOYEE);
        return (obj instanceof EmployeeSystemModel) ? (EmployeeSystemModel) obj : null;
    }
}
