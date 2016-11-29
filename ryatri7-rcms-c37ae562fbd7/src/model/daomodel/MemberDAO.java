package model.daomodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.pojo.MemberPojo;

public class MemberDAO {
	public boolean AddMember(Connection conn, MemberPojo model) {

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String quString = "INSERT INTO `tbl_member`(`full_name`, `address`, `member_type`,`package_type`) VALUES ('"
					+ model.getFullname() + "', '" + model.getAddress() + "'," + model.getMember_type() + ","
					+ model.getFee_package() + ")";
			System.out.println(quString);
			stmt.executeUpdate(quString);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static ResultSet getMember(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from tbl_member";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ResultSet getMemberById(Connection conn, int membmer_id) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from tbl_member where member_id = " + membmer_id;
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean deleteMember(Connection conn, int membmer_id) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "delete FROM tbl_member where member_id = " + membmer_id;
			// System.out.println(sql);
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateMember(Connection conn, MemberPojo model,int database_id) {
		System.out.println(model);
		try {
			String sql = "UPDATE tbl_member SET full_name=?,address=?, member_type=?,package_type=? WHERE member_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, model.getFullname());
			statement.setString(2, model.getAddress());
			statement.setInt(3, model.getMember_type());
			statement.setInt(4, model.getFee_package());
			statement.setInt(5, database_id);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("An existing user was updated successfully!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ResultSet getAdminList(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT m.* FROM `tbl_member` as m inner JOIN `tbl_admin` as a on m.member_id = a.member_id";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
