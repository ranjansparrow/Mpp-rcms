package model.daomodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.pojo.Trainer;

public class TrainerDAO {
	Statement stmt = null;

	public boolean AddTrainer(Connection conn, Trainer model) {
		try {
			stmt = conn.createStatement();
			String sql = "Insert Into `tbl_trainer` (member_id,specialized_in,available_time,time_period) values ("
					+ model.getMember_id() + ",'" + model.getSpecializedIn() + "','" + model.getAvailabiltyTime()
					+ "','" + model.getTimePeriod() + "') ";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateTrainer(Connection conn, Trainer model) {
		try {
			String sql = "UPDATE tbl_trainer SET member_id=?,specialized_in=?, available_time=?,time_period=? WHERE trainer_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, model.getMember_id());
			statement.setString(2, model.getSpecializedIn());
			statement.setString(3, model.getAvailabiltyTime());
			statement.setString(4, model.getTimePeriod());
			statement.setInt(5, model.getDatabase_id());
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

	public boolean deleteTrainer(Connection conn, int trainer) {
		try {
			stmt = conn.createStatement();
			String sql = "delete FROM tbl_trainer where trainer_id = " + trainer;
			System.out.println(sql);
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet getTrainerById(Connection conn, int selectedmemb) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT A.*,B.full_name  FROM `tbl_trainer` as  A INNER JOIN tbl_member as B on A.`member_id` = B.member_id and A.trainer_id = "
					+ selectedmemb;
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ResultSet getTrainerList(Connection con) {
		Statement stmt;
		try {
			stmt = con.createStatement();
			String sql = "SELECT A.*,B.full_name  FROM `tbl_trainer` as  A INNER JOIN tbl_member as B on A.`member_id` = B.member_id";
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
