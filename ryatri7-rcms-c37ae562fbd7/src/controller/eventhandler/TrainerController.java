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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.MemberDAO;
import model.daomodel.TrainerDAO;
import model.pojo.MemberPojo;
import model.pojo.Trainer;

public class TrainerController implements Initializable {
	int selectedmemb = 0;

	public ObservableList<Trainer> otrainerlist = FXCollections.observableArrayList();
	List<String> userlist = new ArrayList<>();
	TrainerDAO trainerDAO = new TrainerDAO();
	DBconfig dbconfig = new DBconfig();
	MemberDAO memberDA0 = new MemberDAO();
	Connection conn;

	public List<MemberPojo> memberModelList;
	List<String> memberNameList = new ArrayList<>();
	HashMap<String, Integer> memberNameKV = new HashMap<>();

	@FXML
	private ComboBox memberName;

	@FXML
	private TextField specializedIn;

	@FXML
	private TextField availTime;

	@FXML
	private TextField timePeriod;

	@FXML
	private Button add;

	@FXML
	private TableView<Trainer> trainerTable;

	@FXML
	private TableColumn<Trainer, Integer> serailNumber;
	@FXML
	private TableColumn<Trainer, String> fullName;
	@FXML
	private TableColumn<Trainer, String> specialized;
	@FXML
	private TableColumn<Trainer, String> available;
	@FXML
	private TableColumn<Trainer, String> timeperiod;
	
	
	public void goToHome(MouseEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getDashboard());
		root.resizableProperty().set(false);
	}
	
	public void refreshPage(ActionEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getTrainerList());
		root.resizableProperty().set(false);
	}	
	
	
	public void AddTrainer(ActionEvent evt) {
		if (add.getText().equals("Add")) {
			try {
				conn = dbconfig.getConnection();
				Trainer trainer = new Trainer(memberNameKV.get(memberName.getValue()), "na", specializedIn.getText(),
						availTime.getText(), timePeriod.getText());
				trainerDAO.AddTrainer(conn, trainer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				conn = dbconfig.getConnection();
				Trainer trainer = new Trainer(memberNameKV.get(memberName.getValue()),selectedmemb, "na", specializedIn.getText(),
						availTime.getText(), timePeriod.getText());
				if(trainerDAO.updateTrainer(conn, trainer)){
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
		AdminAdd traineform = new AdminAdd();
		root.setScene(traineform.getTrainerList());
	}

	public void deleteTrainer(ActionEvent evt) {
		try {
			conn = dbconfig.getConnection();
			if (trainerDAO.deleteTrainer(conn, selectedmemb)) {
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

		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		AdminAdd traineform = new AdminAdd();
		root.setScene(traineform.getTrainerList());
	}

	@SuppressWarnings("unchecked")
	public void editTrainer(ActionEvent evt) {
		try {
			conn = dbconfig.getConnection();
			ResultSet rs = trainerDAO.getTrainerById(conn, selectedmemb);
			while (rs.next()) {
				specializedIn.setText(rs.getString(3));
				availTime.setText(rs.getString(4));
				timePeriod.setText(rs.getString(5));
				memberName.getSelectionModel().select(rs.getString(6));
				add.setText("Update");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		serailNumber.setCellValueFactory(new PropertyValueFactory<Trainer, Integer>("member_id"));
		fullName.setCellValueFactory(new PropertyValueFactory<Trainer, String>("trainer_name"));
		specialized.setCellValueFactory(new PropertyValueFactory<Trainer, String>("specializedIn"));
		available.setCellValueFactory(new PropertyValueFactory<Trainer, String>("availabiltyTime"));
		timeperiod.setCellValueFactory(new PropertyValueFactory<Trainer, String>("timePeriod"));
		try {
			Connection conn = dbconfig.getConnection();
			ResultSet rs = TrainerDAO.getTrainerList(conn);
			int i = 1;
			while (rs.next()) {
				Trainer tr = new Trainer(i++, rs.getInt(1), rs.getString(6), rs.getString(3), rs.getString(4),
						rs.getString(5));
				otrainerlist.add(tr);
			}
			trainerTable.setItems(otrainerlist);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		trainerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label
				if (trainerTable.getSelectionModel().getSelectedItem() != null) {
					@SuppressWarnings("rawtypes")
					TableViewSelectionModel selectionModel = trainerTable.getSelectionModel();
					@SuppressWarnings("rawtypes")
					int selectedCells = selectionModel.getSelectedIndex();
					selectedmemb = otrainerlist.get(selectedCells).getDatabase_id();

					System.out.println("Selected Value  " + selectedCells);
					System.out.println("Database id  " + otrainerlist.get(selectedCells).getDatabase_id());
				}
			}

		});

		try {
			Connection conn = dbconfig.getConnection();
			ResultSet rs = MemberDAO.getMember(conn);
			while (rs.next()) {
				userlist.add(rs.getString(2));
				memberNameKV.put(rs.getString(2), rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList mbList = FXCollections.observableList(userlist);
		memberName.getItems().clear();
		memberName.setItems(mbList);

	}

}
