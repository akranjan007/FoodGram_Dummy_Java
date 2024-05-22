<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LNCTRestaurant - Menu</title>
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
    
    <section class="menu">
        <div class="container">
            <h2>Our Menu</h2>
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
                                <div class="menu-item">
                                    <h4><%= item.getName() %></h4>
                                    <p><%= item.getDescription() %></p>
                                    <span><%= item.getPrice() %></span>
                                    <form action="cart" method="post">
                                    	<input type="hidden" name="action" value="add">
									    <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
									    <input type="hidden" name="item_name" value="<%= item.getName() %>">
									    <input type="hidden" name="item_description" value="<%= item.getDescription() %>">
									    <input type="hidden" name="item_price" value="<%= item.getPrice() %>">
									    <input type="number" name="quantity" value="1" min="1">
									    <button type="submit">Add to Cart</button>
									</form>
                                </div>
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
        </div>
    </section>
    
    <footer>
        <div class="container">
            <p>&copy; 2024 LNCTRestaurant. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
