package com.eazydeals.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.eazydeals.entities.Message;
import com.eazydeals.security.SessionManager;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = request.getParameter("user");
		HttpSession session = request.getSession(false);
		
		if(user != null && user.trim().equals("user")) {
			// Use SessionManager for proper session cleanup
			SessionManager.invalidateUserSession(session);
			// Create new session for message
			session = request.getSession();
			Message message = new Message("Logout successfully!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("login.jsp");
		} else if(user != null && user.trim().equals("admin")) {
			// Use SessionManager for proper session cleanup
			SessionManager.invalidateAdminSession(session);
			// Create new session for message
			session = request.getSession();
			Message message = new Message("Logout successfully!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("adminlogin.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
