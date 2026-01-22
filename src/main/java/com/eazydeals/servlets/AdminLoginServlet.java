package com.eazydeals.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.eazydeals.dao.AdminDao;
import com.eazydeals.entities.Admin;

/**
 * Servlet for admin login authentication
 * Validates admin credentials and creates admin session
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Admin Login GET request");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			AdminDao adminDao = new AdminDao(com.eazydeals.helper.ConnectionProvider.getConnection());
			Admin admin = adminDao.getAdminByEmailPassword(email, password);

			if (admin != null) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				response.sendRedirect("admin.jsp");
			} else {
				response.sendRedirect("adminlogin.jsp?error=Invalid credentials");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error_exception.jsp");
		}
	}
}
