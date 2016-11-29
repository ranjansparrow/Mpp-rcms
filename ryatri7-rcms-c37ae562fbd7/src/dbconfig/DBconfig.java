package dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfig {
	private String connection = "jdbc:mysql://localhost:3306/rcms?autoReconnect=true&useSSL=false";
	private String user = "root";
	private String password = "";
	private String driver = "com.mysql.jdbc.Driver";

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(connection, user, password);
		return conn;

	}

	public void closeDbConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
