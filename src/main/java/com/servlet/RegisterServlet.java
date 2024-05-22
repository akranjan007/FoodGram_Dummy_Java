package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.UserDb;
import com.databaseObjects.RegisterUser;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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

		UserDb userdb = new UserDb();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPass = request.getParameter("confirm_password");
		String role = request.getParameter("role");
		
		if(!password.equals(confirmPass)) {
			request.setAttribute("erro", "Password Mismatch..!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
		}
		
		if(userdb.emailExists(email)) {
			request.setAttribute("erro", "User Already Exists..!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
		}
		else {
			RegisterUser newUser = new RegisterUser(name, email, password, confirmPass, role);
			userdb.saveUser(newUser);
			request.setAttribute("success", "Registration successful.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		
	}

}
