package com.servlet;

import java.io.IOException;
import com.databaseObjects.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.database.MenuDb;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
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
        
        request.setAttribute("menu", categorizedMenu);
        RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
        rd.forward(request, response);
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
