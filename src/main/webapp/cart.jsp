<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LNCTRestaurant - Cart</title>
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
    
    
    <section class="cart">
        <div class="container">
            <h2>Your Cart</h2>
            <%
                ArrayList<CartItems> cart = (ArrayList<CartItems>) session.getAttribute("cart");
                if (cart != null && !cart.isEmpty()) {
            %>
                <table>
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            double grandTotal = 0;
                            for (CartItems item : cart) {
                                double total = item.getPrice() * item.getQuantity();
                                grandTotal += total;
                        %>
                        <tr>
                            <td><%= item.getItemName() %></td>
                            <td><%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= String.format("%.2f", total) %></td>
                            <td>
                            	<form action="cart" method="post">
									   <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
									   <input type="hidden" name="action" value="increase">
									   <button type="submit">+</button>
								</form>
                            </td>
                            <td>
                            	<form action="cart" method="post">
									   <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
									   <input type="hidden" name="action" value="decrease">
									   <button type="submit">-</button>
								</form>
                            </td>
                            <td>
                            	<form action="cart" method="post">
									   <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
									   <input type="hidden" name="action" value="remove">
									   <button type="submit">Remove Item</button>
								</form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <h3>Grand Total: <%= String.format("%.2f", grandTotal) %></h3>
                <a href="menu" class="btn"><button>Back to Menu</button></a>
                		<form action="checkout.jsp" method="post">
		                    <button type="submit">Proceed to Checkout</button>
		                </form>
                		
                
            <%
                } else {
            %>
                <p>Your cart is empty.</p>
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
