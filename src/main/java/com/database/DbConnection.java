package com.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	public static Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "patliputra1");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
