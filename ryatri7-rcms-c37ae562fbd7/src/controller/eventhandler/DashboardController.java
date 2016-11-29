package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.viewloader.AdminAdd;
import controller.viewloader.Asset;
import controller.viewloader.Event;
import controller.viewloader.Login;
import dbconfig.DBconfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.AssetDAO;
import model.daomodel.EventDAO;
import model.pojo.AssetModel;
import model.pojo.EventModel;

public class DashboardController implements Initializable {
	public ObservableList<EventModel> eventList = FXCollections.observableArrayList();
	public ObservableList<AssetModel> assetstList = FXCollections.observableArrayList();
	
	@FXML
	private ImageView logout;
	@FXML
	private TableView<EventModel> eventTable;
	@FXML
	private TableColumn<EventModel, Integer> snEvent;
	@FXML
	private TableColumn<EventModel, String> eventName;
	@FXML
	private TableColumn<EventModel, String> eventDate;
	@FXML
	private TableColumn<EventModel, String> trainer;
	@FXML
	private TableColumn<EventModel, String> status;

	@FXML
	private TableView<AssetModel> assetsTable;
	@FXML
	private TableColumn<AssetModel, Integer> snAssets;
	@FXML
	private TableColumn<AssetModel, String> assetsName;
	@FXML
	private TableColumn<AssetModel, Integer> totalQty;
	@FXML
	private TableColumn<AssetModel, Integer> availableQty;

	DBconfig dBconfig = new DBconfig();
	AssetDAO assetdao = new AssetDAO();
	EventDAO eventdao = new EventDAO();
	Connection conn;
	
	public void logout(MouseEvent evt){
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Login login = new Login();
		root.setScene(login.getLogin());
		root.resizableProperty().set(false);
	}

	public void memberModule(MouseEvent evt) {
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getMember());
		root.resizableProperty().set(false);
	}
	public void showTooltip(MouseEvent evt) {
		final Tooltip tooltip = new Tooltip();
		tooltip.setText("Logout");
	}

	public void assetsModule(MouseEvent evt) {
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Asset assets = new Asset();
		root.setScene(assets.returnAssets());
		root.resizableProperty().set(false);
	}

	public void eventModule(MouseEvent evt) {
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Event adminAdd = new Event();
		root.setScene(adminAdd.getEventList());
		root.resizableProperty().set(false);
	}

	public void trainerModule(MouseEvent evt) {
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getTrainerList());
		root.resizableProperty().set(false);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// for event table
		snEvent.setCellValueFactory(new PropertyValueFactory<EventModel, Integer>("event_id"));
		eventName.setCellValueFactory(new PropertyValueFactory<EventModel, String>("event_title"));
		eventDate.setCellValueFactory(new PropertyValueFactory<EventModel, String>("date"));
		trainer.setCellValueFactory(new PropertyValueFactory<EventModel, String>("description"));
		status.setCellValueFactory(new PropertyValueFactory<EventModel, String>("timeFrom"));

		// for assets table

		snAssets.setCellValueFactory(new PropertyValueFactory<AssetModel, Integer>("assets_id"));
		assetsName.setCellValueFactory(new PropertyValueFactory<AssetModel, String>("assets_name"));
		totalQty.setCellValueFactory(new PropertyValueFactory<AssetModel, Integer>("available_qty"));
		availableQty.setCellValueFactory(new PropertyValueFactory<AssetModel, Integer>("actual_qty"));

		try {
			conn = dBconfig.getConnection();
			ResultSet rs = eventdao.getEventList(conn);
			int i = 1;
			while (rs.next()) {

				EventModel eventModel = new EventModel(i++, rs.getString(2), rs.getString(6),
						String.valueOf(rs.getInt(4)), "Pending");
				eventList.add(eventModel);
				eventTable.setItems(eventList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			conn = dBconfig.getConnection();
			ResultSet rs = AssetDAO.getAssetList(conn);
			int i = 1;
			while (rs.next()) {

				AssetModel assetModel = new AssetModel(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				assetstList.add(assetModel);
				assetsTable.setItems(assetstList);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
