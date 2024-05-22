package com.servlet;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.OrderDB;
import com.databaseObjects.*;

/**
 * Servlet implementation class BillServlet
 */
@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		String name = null;
		if(user!=null) {
			name = user.getName();
		}
		else	name = request.getParameter("name");
		Date date = new Date();
		String datetime = date+"";
		ArrayList<CartItems> cart = (ArrayList<CartItems>)session.getAttribute("cart");
		if(cart == null || cart.isEmpty()) {
			response.sendRedirect("menu.jsp");
			return;
		}
		double amount = 0;
		for(CartItems item:cart) {
			int quantity = item.getQuantity();
			double price = item.getPrice();
			amount += quantity*price;
		}
		OrderObject order = new OrderObject(name, datetime, amount);
		OrderDB orderDb = new OrderDB();
		orderDb.SaveOrder(order, cart);
		
		
		request.setAttribute("order", order);
        request.setAttribute("cart", cart);
        session.removeAttribute("cart");
        RequestDispatcher rd = request.getRequestDispatcher("bill.jsp");
        rd.forward(request, response);
	}

}
