<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LNCTRestaurant - Checkout</title>
<link rel="stylesheet" type="text/css" href="style.css">
<%@page import="com.databaseObjects.*" %>
</head>
<body>
	<header>
        <div class="container">
            <div class="logo">
                <h1><a href="index.jsp">LNCTRestaurant</a></h1>
            </div>
            <nav>
                <ul>
                    <li><a href="menu.jsp">Menu</a></li>
                    <li><a href="cart.jsp">Cart</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                    <%
                    if(session==null || session.getAttribute("user")==null){
                    		%>
                    		<li><a href="login.jsp">Login</a></li><%
                    	}
                    	else{
                    		LoginUser user = (LoginUser)session.getAttribute("user");
                    		String role = user.getRole();
                    		if(role.equals("admin")){
                    			%>
                    			<li><a href="admin.jsp">Admin</a></li>
                    			<li><a href="logout">Log Out</a></li>
                    			<%
                    		}
                    		else{
                    			%>
                    			<li><a href="index.jsp">Profile</a></li>
                    			<li><a href="logout">Log Out</a></li>
                    			<%
                    		}
                    	}
                    %>
                </ul>
            </nav>
        </div>
    </header>
    
    <%
        if(session==null || session.getAttribute("user")==null){
        %>
          <form method="post" action="bill">
		    	<label>
		    		<p>Name</p> 
		    		<input type="text" name="name" placeholder="Enter Yout Name"/>
		    		</br>
		    		<input type="submit" value="Generate Bill"/>
		    	</label>
		    </form>
        <%
         }
        else{
        	request.getRequestDispatcher("bill").forward(request, response);
        }
        %>
    
    
    
    
    <footer>
        <div class="container">
            <p>&copy; 2024 LNCTRestaurant. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>