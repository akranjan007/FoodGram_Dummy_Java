<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LNCTRestaurant - Admin Panel</title>
<link rel="stylesheet" type="text/css" href="style.css">
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
                    <li><a href="admin.jsp">Admin</a></li>
                </ul>
            </nav>
        </div>
    </header>
    
    
    

    <section class="admin">
        <div class="container">
            <h2>Manage Menu</h2>
            <form action="AdminServlet" method="post">
                <input type="hidden" name="action" value="add">
                <div>
                    <label for="name">Item Name:</label>
                    <input type="text" id="name" name="name" required>
                </div>
                <div>
                    <label for="description">Description:</label>
                    <input type="text" id="description" name="description" required>
                </div>
                <div>
                    <label for="price">Price:</label>
                    <input type="number" step="0.01" id="price" name="price" required>
                </div>
                <button type="submit">Add Item</button>
            </form>

            <h2>Current Menu</h2>
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<MenuItem> menu = (List<MenuItem>) request.getAttribute("menu");
                        if (menu != null) {
                            for (MenuItem item : menu) {
                    %>
                        <tr>
                            <td><%= item.getName() %></td>
                            <td><%= item.getDescription() %></td>
                            <td><%= item.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP) %></td>
                            <td>
                                <form action="AdminServlet" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
                                    <button type="submit">Edit</button>
                                </form>
                                <form action="AdminServlet" method="post" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="item_id" value="<%= item.getItemId() %>">
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>

            <h2>Sales Reports</h2>
            <form action="AdminServlet" method="post">
                <input type="hidden" name="action" value="report">
                <button type="submit">Generate Report</button>
            </form>

            <div id="report">
                <%
                    List<SalesReport> reports = (List<SalesReport>) request.getAttribute("reports");
                    if (reports != null) {
                %>
                    <h3>Sales Report</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Quantity Sold</th>
                                <th>Total Sales</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (SalesReport report : reports) {
                            %>
                                <tr>
                                    <td><%= report.getItemName() %></td>
                                    <td><%= report.getQuantitySold() %></td>
                                    <td><%= report.getTotalSales().setScale(2, BigDecimal.ROUND_HALF_UP) %></td>
                                </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                <%
                    }
                %>
            </div>
        </div>
    </section>

    
    
    
    
    <footer>
        <div class="container">
            <p>&copy; 2024 LNCTRestaurant. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>