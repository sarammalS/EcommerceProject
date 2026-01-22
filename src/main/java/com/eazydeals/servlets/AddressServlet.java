package com.eazydeals.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet for managing user delivery addresses
 * Handles adding and retrieving delivery addresses for checkout
 */
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddressServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Address GET request received");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				response.sendRedirect("login.jsp");
				return;
			}

			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zipcode = request.getParameter("zipcode");

			// TODO: Add address to user's address list in database
			session.setAttribute("selectedAddress", address);
			response.sendRedirect("checkout.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error_exception.jsp");
		}
	}
}
