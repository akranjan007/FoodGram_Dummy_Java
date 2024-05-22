package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.databaseObjects.CartItems;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
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
		String action = request.getParameter("action");
		
		if ("add".equals(action)) {
			int itemId = Integer.parseInt(request.getParameter("item_id"));
			String itemName = (String)request.getParameter("item_name");
			String itemDescription = (String)request.getParameter("item_description");
			double price = Double.parseDouble(request.getParameter("item_price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String id = itemId+"";
			
			CartItems cartItem = new CartItems(itemId, itemName, itemDescription, price, quantity);
			
			ArrayList<CartItems> cart = (ArrayList<CartItems>)session.getAttribute("cart");
			if(cart==null) {
				cart = new ArrayList<>();
			}
			boolean found = false;
            for (CartItems item : cart) {
            	String id1 = item.getItemId()+"";
                if (id1.equals(id)) {
                    item.setQuantity(item.getQuantity());
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(cartItem);
            }
            session.setAttribute("cart", cart);
		}else if(action.equals("increase") || action.equals("decrease")) {
			ArrayList<CartItems> cart = (ArrayList<CartItems>) session.getAttribute("cart");
			int itemId = Integer.parseInt(request.getParameter("item_id"));
			String id = itemId+"";
			for(CartItems item:cart) {
				String id1 = item.getItemId()+"";
				if(id1.equals(id)) {
					int quantity = item.getQuantity();
					if(action.equals("increase")) {
						item.setQuantity(quantity+1);
					}
					else if(action.equals("decrease")) {
						if(quantity >1)	item.setQuantity(quantity-1);
						else	cart.remove(item);
					}
					break;
				}
			}
		}else if(action.equals("remove")) {
			ArrayList<CartItems> cart = (ArrayList<CartItems>) session.getAttribute("cart");
			int itemId = Integer.parseInt(request.getParameter("item_id"));
			String id = itemId+"";
			for(CartItems item:cart) {
				String id1 = item.getItemId()+"";
				if(id1.equals(id)) {
					cart.remove(item);
					break;
				}
			}
		}
		
		response.sendRedirect("cart.jsp");
		
	}

}
