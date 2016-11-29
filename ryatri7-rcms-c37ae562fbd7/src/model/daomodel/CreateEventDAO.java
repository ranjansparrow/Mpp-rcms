package model.daomodel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEventDAO {
	Statement stmt = null;
	public void insert(Connection conn){
		try {
			String data1 = "data1";
			String sql = "Insert Into `createEvent` (createdBy,Date,timeFrom,timeTo,assignedBy) values ("+data1+",?,?,?,?)";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
