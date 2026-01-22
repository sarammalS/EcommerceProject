package com.eazydeals.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.eazydeals.entities.Admin;
import com.eazydeals.security.PasswordUtil;

public class AdminDao {
	
	private Connection con;
	
	public AdminDao(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean saveAdmin(Admin admin) {
		boolean flag = false;

		try {
			// Hash password before saving
			String hashedPassword = PasswordUtil.hashPassword(admin.getPassword());
			
			String query = "insert into admin(name, email, password, phone) values(?, ?, ?, ?)";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, admin.getName());
			psmt.setString(2, admin.getEmail());
			psmt.setString(3, hashedPassword);
			psmt.setString(4, admin.getPhone());

			psmt.executeUpdate();
			flag = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public Admin getAdminByEmailPassword(String email, String password) {
		Admin admin = null;
		try {
			// First get admin by email
			String query = "select * from admin where email = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, email);

			ResultSet set = psmt.executeQuery();
			while (set.next()) {
				// Verify password using bcrypt
				String hashedPasswordFromDB = set.getString("password");
				if (PasswordUtil.verifyPassword(password, hashedPasswordFromDB)) {
					admin = new Admin();
					admin.setId(set.getInt("id"));
					admin.setName(set.getString("name"));
					admin.setEmail(set.getString("email"));
					admin.setPassword(hashedPasswordFromDB);
					admin.setPhone(set.getString("phone"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
	public List<Admin> getAllAdmin(){
		List<Admin> list = new ArrayList<Admin>();
		try {
			
			String query = "select * from admin";
			Statement statement = this.con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Admin admin = new Admin();
				admin.setId(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getString("phone"));
				admin.setPassword(rs.getString("password"));
				
				list.add(admin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean deleteAdmin(int id) {
		boolean flag = false;
		try {
			String query = "delete from admin where id = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
