package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.databaseObjects.*;

public class OrderDB {
	
	public void SaveOrder(OrderObject order, ArrayList<CartItems> cart) {

		String q = "insert into orders(customerName, orderDate, amount) values(?, ?, ?)";
        String insertOrderItemSQL = "INSERT INTO orderlist (orderId, itemId, itemName, price, quantity) VALUES (?, ?, ?, ?, ?)";
		
		Connection con = null;
		
        PreparedStatement orderItemStmt = null;
		try {
			con = DbConnection.connect();
			con.setAutoCommit(false); // Start transaction
			
			PreparedStatement ps = con.prepareStatement(q, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, order.getName());
			ps.setString(2, order.getDatetime());
			ps.setDouble(3, order.getAmount());
			ps.executeUpdate();
			

            // Get generated order ID
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                // Insert order items
                orderItemStmt = con.prepareStatement(insertOrderItemSQL);
                for (CartItems item : cart) {
                    orderItemStmt.setInt(1, orderId);
                    orderItemStmt.setInt(2, item.getItemId());
                    orderItemStmt.setString(3, item.getItemName());
                    orderItemStmt.setDouble(4, item.getPrice());
                    orderItemStmt.setInt(5, item.getQuantity());
                    orderItemStmt.addBatch();
                }
                orderItemStmt.executeBatch(); // Execute batch insert
            }

            con.commit(); // Commit transaction
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
