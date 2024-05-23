<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Restaurant - LNCTRestaurant</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <%@page import="com.databaseObjects.*, java.util.*" %>
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
    
    <section class="admin-panel">
        <div class="container">
            <h2>Manage Menu</h2>
            <form action="admin" method="post">
                <input type="hidden" name="action" value="add">
                <label>Item Name:</label>
                <input type="text" name="name">
                <label>Description:</label>
                <input type="text" name="description">
                <label>Category:</label>
                <input type="text" name="category">
                <label>Price:</label>
                <input type="text" name="price">
                <button type="submit">Add Item</button>
            </form>
			
			<h2>Edit or Remove Menu Items</h2>
            <% 
            	Map<String, ArrayList<MenuItemObject>> menu = new HashMap<>();
            	menu = (Map<String, ArrayList<MenuItemObject>>)request.getAttribute("menu");
            	if (menu != null) {
                    for (Map.Entry<String, ArrayList<MenuItemObject>> categoryEntry : menu.entrySet()) {
                        String category = categoryEntry.getKey();
                        ArrayList<MenuItemObject> items = categoryEntry.getValue();
            %>
                        <h3><%= category %></h3>
                        <div class="menu-items">
            <%
                            for (MenuItemObject item : items) {
            %>
                                
                     <ul>
                       <li>
                        <form action="admin" method="post">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <input type="text" name="name" value="<%= item.getName() %>">
                            <input type="text" name="description" value="<%= item.getDescription() %>">
                            <input type="text" name="category" value="<%= item.getCategory() %>">
                            <input type="text" name="price" value="<%= item.getPrice() %>">
                            <button type="submit">Edit</button>
                        </form>
                        <form action="admin" method="post">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <button type="submit">Remove</button>
                        </form>
                    </li>
                   </ul>
            <%
                            }
            %>
                        </div>
            <%
                    }
                } else {
            %>
                <p>No menu items available.</p>
            <%
                }
            %>

            
            <h2>Sales Reports</h2>
            <%
                ArrayList<OrderObject> orders = (ArrayList<OrderObject>) request.getAttribute("report");
            	int orderNo = 0;
            	double totAmount = 0;
                if (orders != null) {
                	%>
                	<table>
                		<tr>
                			<th>Order Id</th>
	                		<th>Customer Name</th>
	                		<th>Amount</th>
	                		<th>Date</th>
                		</tr>
                		
                	
                	<%
                    for (OrderObject order : orders) {
                    	orderNo++;
                    	totAmount += order.getAmount();
            %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getName() %></td>
                    <td><%= order.getAmount() %></td>
                    <td><%= order.getDatetime() %></td>
                </tr>
            <%
                    }
                	%>
                		<tr>
                			<td></td>
                			<td></td>
                			<td></td>
                			<td></td>
                		</tr>
                		<tr>
                			<th>Total Orders :- </th>
                			<td><%= orderNo %></td>
                			<th>Total Sales :- </th>
                			<td><%= String.format("%.2f", totAmount) %></td>
                		</tr>
                	</table>
                	<%
                }
                else{
                	out.println("Order History is empty");
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
