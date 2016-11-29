package model.daomodel;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDAO {
	Statement stmt = null;
	ResultSet rs = null;

	public boolean checkLogin(Connection conn, String username, String password) {
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM tbl_admin where user_name = '" + username + "' and password = " + "'"
					+ md5Password(password) + "'";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private String md5Password(String password) {
		String hashpass = null;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(password.getBytes(), 0, password.length());
			hashpass = new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashpass;
	}
	
	
	public ResultSet getusers(Connection conn) {
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM tbl_admin";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	

}
