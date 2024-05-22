package com.database;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.servlet.http.HttpSession;
import com.databaseObjects.*;
import com.utils.PasswordEncryption;

public class UserDb {
	public boolean emailExists(String email) {
		boolean flag = false;
		
		Connection con = null;
		try {
			con = DbConnection.connect();
			String q = "select * from users where email = ?";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
                return count > 0;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public void saveUser(RegisterUser user) {
	    String password = user.getPassword();
	    String hashedPassword = PasswordEncryption.hashPassword(password);
	    System.out.println(hashedPassword);

	    String q = "INSERT INTO users(name, email, password, role) VALUES(?, ?, ?, ?)";
	    PreparedStatement ps = null;
	    Connection con = null;
	    try {
	        con = DbConnection.connect();
	        ps = con.prepareStatement(q);
	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, hashedPassword);
	        ps.setString(4, user.getRole());
	        ps.execute();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	public LoginUser userLogin(String email, String password, String role) {
	    String q = "SELECT name, password FROM users WHERE email=? AND role=?";
	    PreparedStatement ps = null;
	    Connection con = null;
	    boolean passwordMatch = false;
	    try {
	        con = DbConnection.connect();
	        ps = con.prepareStatement(q);
	        ps.setString(1, email);
	        ps.setString(2, role);

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            String hashedPassword = rs.getString("password");
	            String[] parts = hashedPassword.split(":");
	            byte[] salt = Base64.getDecoder().decode(parts[0]);
	            String storedHash = parts[1];
	            passwordMatch = PasswordEncryption.verifyPassword(password, storedHash, salt);

	            if (passwordMatch) {
	                System.out.println("Login Successful");
	                String name = rs.getString("name");
	                return new LoginUser(name, email, role, true);
	            } else {
	                System.out.println("Login Failed: Incorrect Password");
	            }
	        } else {
	            System.out.println("Login Failed: User not found or role mismatch");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return new LoginUser(null, null, null, false);
	}

}














