package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.viewloader.AdminAdd;
import controller.viewloader.Asset;
import dbconfig.DBconfig;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.AssetDAO;
import model.pojo.AssetModel;

public class AssetController implements Initializable {
	int selectedAsset = 0;
	public ObservableList<AssetModel> oassetlist = FXCollections.observableArrayList();
	AssetDAO assetDAO = new AssetDAO();
	DBconfig dbconfig = new DBconfig();
	Connection conn;
	
	@FXML private TextField asset_Name;
	@FXML private TextField actual_quantity;
	@FXML private Button add;
	
	@FXML private TableView<AssetModel> assetTable;

	@FXML
	private TableColumn<AssetModel, Integer> serailNumber;
	@FXML
	private TableColumn<AssetModel, String> assetName;
	@FXML
	private TableColumn<AssetModel, String> actualQuantity;
	@FXML
	private TableColumn<AssetModel, String> availableQuantity;
	
	
	public void goToHome(MouseEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.returnAssets());
		root.resizableProperty().set(false);
	}
	
	public void refreshPage(ActionEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.getAssetList());
		root.resizableProperty().set(false);
	}
	
	public void AddAsset(ActionEvent evt) {
		if (add.getText().equals("Add")) {
			try {
				conn = dbconfig.getConnection();
				AssetModel assetModel = new AssetModel(asset_Name.getText(), Integer.parseInt(actual_quantity.getText()));
				assetDAO.AddAsset(conn, assetModel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				conn = dbconfig.getConnection();
				AssetModel assetModel = new AssetModel(selectedAsset, asset_Name.getText(), Integer.parseInt(actual_quantity.getText()), 0);
				if(assetDAO.updateAsset(conn, assetModel)){
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Triner has been Updated Successfully");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Asset asset = new Asset();
		root.setScene(asset.getAssetList());
		root.resizableProperty().set(false);
	}

	public void deleteAsset(ActionEvent evt) {
		try {
			conn = dbconfig.getConnection();
			if (assetDAO.deleteAsset(conn, selectedAsset)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Asset has been deleted Successfully");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Problem while deleting trainer");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Asset asset = new Asset();
		root.setScene(asset.getAssetList());
		root.resizableProperty().set(false);
	}

	@SuppressWarnings("unchecked")
	public void editAsset(ActionEvent evt) {
		try {
			conn = dbconfig.getConnection();
			ResultSet rs = assetDAO.getAssetById(conn, selectedAsset);
			while (rs.next()) {
				asset_Name.setText(rs.getString(2));
				actual_quantity.setText(rs.getString(4));
				add.setText("Update");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serailNumber.setCellValueFactory(new PropertyValueFactory<AssetModel, Integer>("assets_id"));
		assetName.setCellValueFactory(new PropertyValueFactory<AssetModel, String>("assets_name"));
		actualQuantity.setCellValueFactory(new PropertyValueFactory<AssetModel, String>("actual_qty"));
		availableQuantity.setCellValueFactory(new PropertyValueFactory<AssetModel, String>("available_qty"));
		
		try {
			Connection conn = dbconfig.getConnection();
			ResultSet rs = AssetDAO.getAssetList(conn);
			int i = 1;
			while (rs.next()) {
				AssetModel am = new AssetModel(i++, rs.getString(2), rs.getInt(4), rs.getInt(3));
				oassetlist.add(am);
			}
			assetTable.setItems(oassetlist);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		assetTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label
				if (assetTable.getSelectionModel().getSelectedItem() != null) {
					@SuppressWarnings("rawtypes")
					TableViewSelectionModel selectionModel = assetTable.getSelectionModel();
					@SuppressWarnings("rawtypes")
					int selectedCells = selectionModel.getSelectedIndex();
					selectedAsset = oassetlist.get(selectedCells).getAssets_id();
				}
			}	
		});
	}

}
