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
}
