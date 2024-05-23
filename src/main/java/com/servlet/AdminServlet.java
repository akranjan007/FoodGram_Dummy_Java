package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.MenuDb;
import com.database.OrderDB;
import com.databaseObjects.ItemObj;
import com.databaseObjects.MenuItemObject;
import com.databaseObjects.OrderObject;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDb menuDb = new MenuDb();
        ArrayList<MenuItemObject> menu = menuDb.getMenuItems();
        Map<String, ArrayList<MenuItemObject>> categorizedMenu = new HashMap<>();
        
        for (MenuItemObject i : menu) {
            String cat = i.getCategory();
            if (!categorizedMenu.containsKey(cat)) {
                categorizedMenu.put(cat, new ArrayList<MenuItemObject>());
            }
            categorizedMenu.get(cat).add(i);
        }
        
        // Debugging: Print the categorizedMenu map
        System.out.println("Categorized Menu: " + categorizedMenu);
        
        // Check if the categorizedMenu is empty and print a message if it is
        if (categorizedMenu.isEmpty()) {
            System.out.println("The categorized menu is empty.");
        } else {
            System.out.println("The categorized menu is not empty.");
        }
        
        OrderDB orderdb = new OrderDB();
        ArrayList<OrderObject> order = orderdb.getAllOrders();
        if(order==null )System.out.println("empty");
        else	System.out.println("not emupty");
        System.out.println(order);
        
        request.setAttribute("report", order);
        request.setAttribute("menu", categorizedMenu);
        RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action.equals("add")) {
			addMenuItems(request, response);
		}
		else if(action.equals("remove")) {
			removeMenuItems(request, response);
		}
		else if(action.equals("edit")) {
			updateMenuItems(request, response);
		}
		response.sendRedirect("admin");
	}
	
	public void addMenuItems(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String name = request.getParameter("name");
		String desc = request.getParameter("description");
		String cat = request.getParameter("category");
		Double price = Double.parseDouble(request.getParameter("price"));
		
		ItemObj item = new ItemObj(name, desc, cat, price);
		MenuDb menudb = new MenuDb();
		menudb.saveItem(item);
	}
	public void removeMenuItems(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		
		MenuDb menudb = new MenuDb();
		menudb.removeItem(itemId);
	}
	public void updateMenuItems(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String name = request.getParameter("name");
		String desc = request.getParameter("description");
		String cat = request.getParameter("category");
		float price = Float.parseFloat(request.getParameter("price"));
		
		MenuItemObject item = new MenuItemObject(itemId, name, desc, cat, price);
		MenuDb menudb = new MenuDb();
		menudb.updateItem(item);
	}

}
