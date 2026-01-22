package com.eazydeals.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet for removing delivery addresses
 * Handles deletion of user's saved addresses
 */
public class RemoveAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveAddressServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Remove Address GET request");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				response.sendRedirect("login.jsp");
				return;
			}

			String addressId = request.getParameter("addressId");

			// TODO: Remove address from database based on addressId
			response.sendRedirect("personalInfo.jsp?success=Address removed successfully");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error_exception.jsp");
		}
	}
}
