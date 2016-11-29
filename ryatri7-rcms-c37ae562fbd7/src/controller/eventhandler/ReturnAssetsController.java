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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.AssetDAO;
import model.daomodel.EventDAO;
import model.pojo.AssetBookModel;
import model.pojo.AssetModel;
import model.pojo.EventModel;
import model.pojo.UsesModel;

public class ReturnAssetsController implements Initializable{
	DBconfig dBconfig = new DBconfig();
	AssetDAO assetdao = new AssetDAO();
	Connection conn;
	int selectedmemb = 0;
	int returnedstatus = 1;
	public ObservableList<UsesModel> booklist = FXCollections.observableArrayList();
	
	@FXML
	DatePicker received_date;
	@FXML
	TextField fee;
	@FXML
	private TableView<UsesModel> bookAssetsList;
	@FXML
	private TableColumn<UsesModel, Integer> sn;
	@FXML
	private TableColumn<UsesModel, String> member;
	@FXML
	private TableColumn<UsesModel, String> assets;
	@FXML
	private TableColumn<UsesModel, String> fromdate;
	@FXML
	private TableColumn<UsesModel, String> todate;
	@FXML
	private TableColumn<UsesModel, String> status;
	
	
	public void goToHome(MouseEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getDashboard());
		root.resizableProperty().set(false);
	}
	
	public void refreshPage(ActionEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.returnAssets());
		root.resizableProperty().set(false);
	}	
	
	public void bookAssets(ActionEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.getAsset());
		root.resizableProperty().set(false);
	}
	
	public void assetsManagement(ActionEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.getAssetList());
		root.resizableProperty().set(false);
	}
	
	public void returnAssets(ActionEvent evt){
		if(selectedmemb==0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Please select table row first");
			alert.showAndWait();
		}else if(returnedstatus ==0){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("The item is already returned");
			alert.showAndWait();
		}else{
			String r_date = received_date.getValue().toString();
			String fine = fee.getText();
			int assetsid = 0;
			try {
				conn = dBconfig.getConnection();
				ResultSet rs = assetdao.getAssetsId(conn, selectedmemb);
				while(rs.next()){
					assetsid = rs.getInt(1);
				}
				assetdao.returnAssets(conn,selectedmemb,r_date,fine,assetsid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Asset returnassets = new Asset();
		root.setScene(returnassets.returnAssets());
		root.resizableProperty().set(false);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		sn.setCellValueFactory(new PropertyValueFactory<UsesModel, Integer>("sn"));
		member.setCellValueFactory(new PropertyValueFactory<UsesModel, String>("member"));
		assets.setCellValueFactory(new PropertyValueFactory<UsesModel, String>("assets"));
		fromdate.setCellValueFactory(new PropertyValueFactory<UsesModel, String>("from_date"));
		todate.setCellValueFactory(new PropertyValueFactory<UsesModel, String>("to_date"));
		status.setCellValueFactory(new PropertyValueFactory<UsesModel, String>("status"));
		
		
		try {
			conn = dBconfig.getConnection();
			ResultSet rs = assetdao.getAssetsList(conn);
			int i = 1;
			while (rs.next()) {
				String status = "";
				if(rs.getInt(6)==0){
					status = "Returned";
				}else{
					status = "Not Returned";
				}
				UsesModel model = new UsesModel(i++,rs.getInt(1),rs.getString(2), rs.getString(3),  rs.getString(4),  rs.getString(5),status,rs.getInt(6));				
				booklist.add(model);
				bookAssetsList.setItems(booklist);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		bookAssetsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label
				if (bookAssetsList.getSelectionModel().getSelectedItem() != null) {
					@SuppressWarnings("rawtypes")
					TableViewSelectionModel selectionModel = bookAssetsList.getSelectionModel();
					@SuppressWarnings("rawtypes")
					int selectedCells = selectionModel.getSelectedIndex();
					selectedmemb = booklist.get(selectedCells).getUses_id();
					returnedstatus = booklist.get(selectedCells).getStatusint();

					System.out.println("Selected Value  " + selectedCells);
					System.out.println("Selected Value db " + selectedmemb);
//					System.out.println("Database id  " + booklist.get(selectedCells).getDatabase_id());
				}
			}

		});
		
		
	}

}
