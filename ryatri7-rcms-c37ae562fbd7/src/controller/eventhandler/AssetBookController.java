package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import controller.viewloader.Asset;
import dbconfig.DBconfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.AssetDAO;
import model.daomodel.MemberDAO;
import model.pojo.AssetBookModel;
import model.pojo.AssetModel;

public class AssetBookController implements Initializable {
	AssetDAO assetDAO = new AssetDAO();
	MemberDAO memberDAO = new MemberDAO();
	DBconfig dbconfig = new DBconfig();
	Connection conn;
	public AssetModel assetModel;
	public AssetBookModel abModel;
	public List<AssetModel> assetModelList;
	@FXML private DatePicker fromDate;
	@FXML private DatePicker toDate;
	@FXML private ComboBox<String> memberName;
	HashMap<String, Integer> memberNameKV = new HashMap<>();
	List<String> memberList = new ArrayList<>();

	@FXML private TextField assignedBy;
	@FXML private TextField fee;
	@FXML private ComboBox<String> assetName;
	HashMap<String, Integer> assetNameKV = new HashMap<>();
	List<String> assetList = new ArrayList<>();

	@FXML private ComboBox<String> adminName;
	HashMap<String, Integer> adminNameKV = new HashMap<>();
	List<String> adminList = new ArrayList<>();

	public List<AssetModel> loadAsset() {
		try {
			conn = dbconfig.getConnection();
			ResultSet rs = AssetDAO.getAssetList(conn);
			List<AssetModel> assetModels = new ArrayList<>();
			try {
				while (rs.next()) {
					AssetModel data = new AssetModel();
					data.setAssets_name(rs.getString(2));
					data.setAvailable_qty(rs.getInt(3));
					data.setActual_qty(rs.getInt(4));
					assetModels.add(data);
				}
				return assetModels;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				dbconfig.closeDbConn(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public void goToHome(MouseEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.returnAssets());
		root.resizableProperty().set(false);
	}
	

	public void AddAsset(ActionEvent evt) {
		// model. = username.getText().toString();
		// passwordStr = password.getText().toString();
		try {
			conn = dbconfig.getConnection();
			System.out.println(assetDAO.AddAsset(conn, assetModel));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		/*
		 * Dashboard dashboard = new Dashboard();
		 * root.setScene(dashboard.getDashboard());
		 */
		Asset asset = new Asset();
		root.setScene(asset.getAsset());
		root.resizableProperty().set(false);
	}

	public void AddBookAsset(ActionEvent evt) {
		// assetModelList = loadAsset();
		abModel = new AssetBookModel();
		String selected = assetName.getValue();

		abModel.setAssets_id(assetNameKV.get(selected));

		abModel.setFrom_time(fromDate.getValue().toString());
		abModel.setTo_time(toDate.getValue().toString());
		abModel.setMember_id(memberNameKV.get(memberName.getValue()));
		abModel.setAssigned_by(adminNameKV.get(adminName.getValue()));
		abModel.setFee(Double.parseDouble(fee.getText()));
		// abModel.setAssets_id(assetName.getValue().toString());
		// passwordStr = password.getText().toString();

		try {
			conn = dbconfig.getConnection();
			assetDAO.AddBookAsset(conn, abModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		/*
		 * Dashboard dashboard = new Dashboard();
		 * root.setScene(dashboard.getDashboard());
		 */
		Asset asset = new Asset();
		root.setScene(asset.getAsset());
		root.resizableProperty().set(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// List<String> assetList = new ArrayList<String>();
		try {
			conn = dbconfig.getConnection();
			ResultSet rsAsset = AssetDAO.getAssetList(conn);
			ResultSet rsMember = MemberDAO.getMember(conn);
			ResultSet rsAdmin = MemberDAO.getAdminList(conn);
			try {
				while (rsAsset.next()) {
					assetList.add(rsAsset.getString(2));
					assetNameKV.put(rsAsset.getString(2), rsAsset.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rsMember.next()) {
					memberList.add(rsMember.getString(2));
					memberNameKV.put(rsMember.getString(2), rsMember.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (rsAdmin.next()) {
					adminList.add(rsAdmin.getString(2));
					adminNameKV.put(rsAdmin.getString(2), rsAdmin.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ObservableList<String> obList = FXCollections.observableList(assetList);
		assetName.getItems().clear();
		assetName.setItems(obList);

		ObservableList<String> obList1 = FXCollections.observableList(memberList);
		memberName.getItems().clear();
		memberName.setItems(obList1);

		ObservableList<String> obList3 = FXCollections.observableList(adminList);
		adminName.getItems().clear();
		adminName.setItems(obList3);

	}
}
