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
                    			<li><a href="admin">Admin</a></li>
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
        <section class="checkout">
    <div class="container">
        <h2>Checkout</h2>
        <form method="post" action="bill">
            <table>
                <tr>
                    <th>Name</th>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="name" placeholder="Enter Your Name" required/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" value="Generate Bill" class="btn btn-primary"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</section>

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