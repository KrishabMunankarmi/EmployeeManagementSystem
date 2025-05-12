package com.EmployeeSystem.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();

        boolean isLoginPage = uri.endsWith("/login");
        boolean isRegisterPage = uri.endsWith("/register");
        boolean isPublicResource = uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg");

        HttpSession session = req.getSession(false);
        String role = (session != null) ? (String) session.getAttribute("role") : null;
        boolean isLoggedIn = role != null;

        boolean isAdminOnly = uri.endsWith("/dashboard") || uri.endsWith("/adminemployee") || uri.endsWith("/admindepartment");

        if (isPublicResource || isLoginPage || isRegisterPage) {
            chain.doFilter(request, response);
            return;
        }

        if (!isLoggedIn) {
            res.sendRedirect(contextPath + "/login");
            return;
        }

        if (isAdminOnly && !"admin".equalsIgnoreCase(role)) {
            res.sendRedirect(contextPath + "/home");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
