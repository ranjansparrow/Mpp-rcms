package model.daomodel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PackageDAO {
	Statement stmt = null;
	ResultSet rs = null;
	public static ResultSet getPackageList(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from tbl_package";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
