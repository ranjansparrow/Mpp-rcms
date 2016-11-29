package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import controller.viewloader.AdminAdd;
import controller.viewloader.Asset;
import controller.viewloader.Event;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.EventDAO;
import model.pojo.EventListModel;

public class EventController implements Initializable{
	int selectedmemb = 0;
	public ObservableList<EventListModel> eventlist = FXCollections.observableArrayList();
	DBconfig dBconfig = new DBconfig();
	EventDAO eventdao = new EventDAO();
	@FXML
	private TableView<EventListModel> tablelist;
	@FXML
	private TableColumn<EventListModel, Integer> sn;
	@FXML
	private TableColumn<EventListModel, String> eventTitle;
	@FXML
	private TableColumn<EventListModel, String> desc;
	@FXML
	private TableColumn<EventListModel, String> trainedBy;
	@FXML
	private TableColumn<EventListModel, String> date;
	@FXML
	private TableColumn<EventListModel, String> timeFrom;
	@FXML
	private TableColumn<EventListModel, String> timeTo;
	
	
	public void addEvent(ActionEvent evt){
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Event adminAdd = new Event();
		root.setScene(adminAdd.getEvent());
	}
	
	public void goToHome(MouseEvent evt){
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getDashboard());
		root.resizableProperty().set(false);
	}
	
	public void deleteEvent(ActionEvent evt){
		if(selectedmemb==0){
			
		}else{
			try {
				Connection conn = dBconfig.getConnection();
				if (eventdao.deleteEvent(conn, selectedmemb)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Triner has been deleted Successfully");
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
		}
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		Event adminAdd = new Event();
		root.setScene(adminAdd.getEventList());
		root.resizableProperty().set(false);
	}
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sn.setCellValueFactory(new PropertyValueFactory<EventListModel, Integer>("sn"));
		eventTitle.setCellValueFactory(new PropertyValueFactory<EventListModel, String>("event_title"));
		desc.setCellValueFactory(new PropertyValueFactory<EventListModel, String>("event_desc"));
		trainedBy.setCellValueFactory(new PropertyValueFactory<EventListModel, String>("trained_by"));
		date.setCellValueFactory(new PropertyValueFactory<EventListModel, String>("date"));
		timeFrom.setCellValueFactory(new PropertyValueFactory<EventListModel, String>("timefrom"));
		timeTo.setCellValueFactory(new PropertyValueFactory<EventListModel, String>("timeTo"));
		try {
			Connection conn = dBconfig.getConnection();
			ResultSet rs = eventdao.getDetailEventList(conn);
			int i = 1;
			while (rs.next()) {
				EventListModel model = new EventListModel(i++, rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(9), rs.getString(6), rs.getString(7), rs.getString(8));
				System.out.println(model);
				eventlist.add(model);
				tablelist.setItems(eventlist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		tablelist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label
				if (tablelist.getSelectionModel().getSelectedItem() != null) {
					TableViewSelectionModel selectionModel = tablelist.getSelectionModel();
					@SuppressWarnings("rawtypes")
					int selectedCells = selectionModel.getSelectedIndex();
					selectedmemb = eventlist.get(selectedCells).getEvent_id();

					// System.out.println("Selected Value " + selectedCells);
					 System.out.println("Database id " + selectedmemb);
				}
			}

		});
		
				
	}

}
