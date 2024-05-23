<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Restaurant - LNCTRestaurant</title>
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
                    <li><a href="menu">Menu</a></li>
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
    
    <section class="hero">
        <div class="container">
            <h2>Delicious Food Delivered To You</h2>
            <p>Order your favorite meals online and enjoy fast delivery!</p>
            <a href="menu" class="btn">View Menu</a>
        </div>
    </section>
    
    <section class="about">
        <div class="container">
            <h2>About Us</h2>
            <p>Welcome to our restaurant where we serve the finest dishes crafted with the freshest ingredients. We believe in providing an unforgettable dining experience.</p>
        </div>
    </section>
    
    <footer>
        <div class="container">
            <p>&copy; 2024 LNCTRestaurant. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
