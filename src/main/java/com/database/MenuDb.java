package com.database;

import java.sql.*;
import java.util.*;

import com.databaseObjects.*;

public class MenuDb {
	public ArrayList<MenuItemObject> getMenuItems(){
		Connection con = null;
		ArrayList<MenuItemObject> menuItem = new ArrayList<>();
		try {
			con = DbConnection.connect();
			String q = "select * from menu_item";
			PreparedStatement ps = con.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int itemId = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				String category = rs.getString(4);
				float price = rs.getFloat(5);
				
				MenuItemObject menu = new MenuItemObject(itemId, name, description, category, price);
				menuItem.add(menu);
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return menuItem;
		
	}
	
	public void saveItem(ItemObj item) {
		Connection con = null;
		System.out.println(item.getCategory());
		try {
			con = DbConnection.connect();
			String q = "insert into menu_item(name, description, category, price) values(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, item.getName());
			ps.setString(2, item.getDescription());
			ps.setString(3, item.getCategory());
			ps.setDouble(4, item.getPrice());
			System.out.println("Before save");
			ps.execute();
			System.out.println("After Save");
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeItem(int itemId) {
		Connection con = null;
		String q = "delete from menu_item where itemId=?";
		PreparedStatement ps = null;
		try {
			con = DbConnection.connect();
			ps = con.prepareStatement(q);
			ps.setInt(1, itemId);
			ps.execute();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateItem(MenuItemObject item) {
	    String query = "UPDATE menu_item SET name = ?, description = ?, category = ?, price = ? WHERE itemId = ?";

	    try (Connection con = DbConnection.connect();
	         PreparedStatement ps = con.prepareStatement(query)) {

	        ps.setString(1, item.getName());
	        ps.setString(2, item.getDescription());
	        ps.setString(3, item.getCategory());
	        ps.setFloat(4, item.getPrice()); // Assuming price is BigDecimal
	        ps.setInt(5, item.getItemId());

	        int rowsUpdated = ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}













