<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LNCTRestaurant - Bill</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <%@page import="java.util.*, com.databaseObjects.*" %>
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
    
    <section class="bill">
        <div class="container">
            <h2>Bill</h2>
            <%
                OrderObject order = (OrderObject) request.getAttribute("order");
                ArrayList<CartItems> cart = (ArrayList<CartItems>) request.getAttribute("cart");

                if (order != null && cart != null) {
            %>
                <p><strong>Customer Name:</strong> <%= order.getName() %></p>
                <table>
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Rate</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        for (CartItems item : cart) {
                            Double itemTotal = item.getPrice()*item.getQuantity();
                    %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= itemTotal %></td>
                        </tr>
                    <%
                        }
                    %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="4" style="text-align:right;"><strong>Grand Total:</strong></td>
                            <td><strong><%= order.getAmount() %></strong></td>
                        </tr>
                    </tfoot>
                </table>
            <%
                } else {
            %>
                <p>No order details available.</p>
            <%
                }
            %>
        </div>
    </section>
    
    
    <footer>
        <div class="container">
            <p>&copy; 2024 LNCTRestaurant. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
