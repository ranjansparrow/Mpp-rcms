package model.daomodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.pojo.EventModel;

public class EventDAO {
	Statement stmt = null;
	ResultSet rs = null;
	
	public boolean AddEvent(Connection conn, EventModel m) {
		PreparedStatement stmt;
		try {
					
			String quString = "INSERT INTO `tbl_event`(`event_title`, `description`, `trainer_id`, `booked_by_id`, `date`, `timeFrom`, `timeTo`) VALUES (?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(quString);
			stmt.setString(1, m.getEvent_title());
			stmt.setString(2, m.getDescription());
			stmt.setInt(3, m.getTrainer_id());
			stmt.setInt(4, m.getBooked_by_id());
			stmt.setString(5, m.getDate());
			stmt.setString(6, m.getTimeFrom());
			stmt.setString(7, m.getTimeTo());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public ResultSet getEventList(Connection conn){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from tbl_event";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ResultSet getDetailEventList(Connection conn){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT * FROM tbl_event A INNER JOIN tbl_member B on A.trainer_id = B.member_id";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean deleteEvent(Connection conn,int event_id){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "delete FROM tbl_event where event_id = " + event_id;
			// System.out.println(sql);
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
