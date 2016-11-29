package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import controller.viewloader.AdminAdd;
import controller.viewloader.Event;
import dbconfig.DBconfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.EventDAO;
import model.daomodel.MemberDAO;
import model.pojo.EventModel;

public class EventBookController implements Initializable {
	EventDAO eventDAO = new EventDAO();
	public EventModel eModel;
	DBconfig dbconfig = new DBconfig();
	Connection conn;

	@FXML
	private TextField eventName;
	@FXML
	private DatePicker eventdate;
	@FXML
	private TextField timeFrom;
	@FXML
	private TextField timeTo;
	@FXML
	private TextArea eventDesc;

	@FXML
	private ComboBox<String> bookedby;
	HashMap<String, Integer> memberNameKV = new HashMap<>();
	List<String> memberList = new ArrayList<>();

	@FXML
	private ComboBox<String> assignedBy;
	HashMap<String, Integer> adminNameKV = new HashMap<>();
	List<String> adminList = new ArrayList<>();

	
	public void goToHome(MouseEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		Event event = new Event();
		root.setScene(event.getEventList());
		root.resizableProperty().set(false);
	}
	
	
	
	public void AddEvent(ActionEvent evt) {
		eModel = new EventModel();

		eModel.setEvent_title(eventName.getText());
		eModel.setDescription(eventDesc.getText());
		eModel.setTrainer_id(memberNameKV.get(bookedby.getValue()));
		eModel.setBooked_by_id(adminNameKV.get(assignedBy.getValue()));
		eModel.setDate(eventdate.getValue().toString());
		eModel.setTimeFrom(timeFrom.getText());
		eModel.setTimeTo(timeTo.getText());

	

		try {
			conn = dbconfig.getConnection();
			eventDAO.AddEvent(conn, eModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();

		Event asset = new Event();
		root.setScene(asset.getEvent());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			conn = dbconfig.getConnection();
			ResultSet rsMember = MemberDAO.getMember(conn);
			ResultSet rsAdmin = MemberDAO.getAdminList(conn);

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

		ObservableList<String> obList1 = FXCollections.observableList(memberList);
		bookedby.getItems().clear();
		bookedby.setItems(obList1);

		ObservableList<String> obList3 = FXCollections.observableList(adminList);
		assignedBy.getItems().clear();
		assignedBy.setItems(obList3);

	}

}
