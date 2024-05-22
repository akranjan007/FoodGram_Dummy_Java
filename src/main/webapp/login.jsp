<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Restaurant - LNCTRestaurant</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <%@page import="java.util.*, com.databaseObjects.*" %>
<%@include file="bootsrap.jsp" %>
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
    
    <div class="container" style="margin-top:40px">
	    <div class="col-md-4"></div>
	    <div class="col-md-4 well">
	        <fieldset>
	            <legend class="text-center">Login</legend>
	            <form action="login" method="post">
	                <label style="margin-top:10px">
	                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	                    <input class="form-control" type="text" name="email" placeholder="Enter Email Address"/>
	                </label>
	                </br>
	                <label style="margin-top:10px">
	                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	                    <input class="form-control" type="password" name="password" placeholder="Enter Password"/>
	                </label>
	                </br>
	                <select name="role" class="form-control dropdown-toggle" style="margin-top:10px">
	                    <option value="admin">Admin</option>
	                    <option value="customer">Customer</option>
	                </select>
	                </br>
	                <input type="submit" value="Login" class="form-control btn btn-primary" style="margin-top:10px"/>
	            </form>
	            <form action="register.jsp" method="get">
	                <input type="submit" value="Register" class="form-control btn btn-secondary" style="margin-top:10px"/>
	            </form>
	        </fieldset>
	    </div>
	    <div class="col-md-4"></div>
	</div>

    
    <footer>
        <div class="container">
            <p>&copy; 2024 LNCTRestaurant. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
