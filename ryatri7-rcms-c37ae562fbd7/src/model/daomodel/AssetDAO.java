package model.daomodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.pojo.AssetBookModel;
import model.pojo.AssetModel;

public class AssetDAO {
	Statement stmt = null;
	ResultSet rs = null;

	public boolean AddAsset(Connection conn, AssetModel model) {

		Statement stmt;
		try {
			stmt = conn.createStatement();
			String quString = "INSERT INTO `tbl_assets`(`assets_name`, `available_qty`, `actual_qty`) VALUES ('"
					+ model.getAssets_name() + "', " + model.getAvailable_qty() + "," + model.getActual_qty() + ")";
			stmt.executeUpdate(quString);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean updateAsset(Connection conn, AssetModel model) {
		try {
			String sql = "UPDATE `tbl_assets` SET `assets_name`=?,`actual_qty`=? WHERE assets_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, model.getAssets_name());
			statement.setInt(2, model.getActual_qty());
			statement.setInt(3, model.getAssets_id());
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
	
	public boolean deleteAsset(Connection conn, int asset) {
		try {
			stmt = conn.createStatement();
			String sql = "DELETE FROM tbl_assets where assets_id = " + asset;
			System.out.println(sql);
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public static ResultSet getAssetList(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "select * from tbl_assets";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean AddBookAsset(Connection conn, AssetBookModel m) {
		PreparedStatement stmt;
		try {

			String quString = "INSERT INTO `tbl_assets_uses`(`assets_id`, `member_id`,`assigned_by`, `from_time`, `to_time`, `status`, `fee`) VALUES (?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(quString);
			stmt.setInt(1, m.getAssets_id());
			stmt.setInt(2, m.getMember_id());
			stmt.setInt(3, m.getAssigned_by());
			stmt.setString(4, m.getFrom_time());
			stmt.setString(5, m.getTo_time());
			stmt.setString(6, "1");
			stmt.setDouble(7, m.getFee());
			stmt.executeUpdate();

			String update = "UPDATE `tbl_assets` SET available_qty=available_qty - 1 WHERE `assets_id`=?";

			stmt = conn.prepareStatement(update);
			stmt.setInt(1, m.getAssets_id());
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static ResultSet getAssetsList(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT A.uses_id,D.full_name,B.assets_name,a.from_time,a.to_time,A.status from tbl_assets_uses A INNER join tbl_assets B ON A.assets_id = B.assets_id  INNER JOIN  tbl_member AS D ON A.member_id = D.member_id";
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ResultSet getAssetById(Connection conn, int selectedAsset) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT `assets_id`, `assets_name`, `available_qty`, `actual_qty` FROM `tbl_assets` WHERE assets_id="
					+ selectedAsset;
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean returnAssets(Connection conn, int selectedmemb, String r_date, String fine,int assetsid) {
		try {
			String sql = "UPDATE tbl_assets_uses  SET status=?,fee=?,received_date=? WHERE uses_id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, 0);
			statement.setDouble(2, Double.parseDouble(fine));
			statement.setString(3, r_date);
			statement.setInt(4, selectedmemb);
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				
				String update = "UPDATE `tbl_assets` SET available_qty=available_qty + 1 WHERE `assets_id`=?";

				statement = conn.prepareStatement(update);
				statement.setInt(1,assetsid);
				int rowsUpdatedassets = statement.executeUpdate();
				if(rowsUpdatedassets>0){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ResultSet getAssetsId(Connection conn,int uses_id) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String query = "SELECT assets_id FROM tbl_assets_uses WHERE uses_id = "+uses_id;
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
