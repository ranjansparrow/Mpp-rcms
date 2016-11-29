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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.daomodel.MemberDAO;
import model.daomodel.PackageDAO;
import model.pojo.MemberPojo;

public class MemberController implements Initializable {
	int selectedmemb = 0;

	public ObservableList<MemberPojo> list = FXCollections.observableArrayList();
	List<String> userlist = new ArrayList<>();
	HashMap<String, Integer> keyvalpackage = new HashMap<>();
	HashMap<String, Integer> keyvaltype = new HashMap<>();
	PackageDAO packagedao = new PackageDAO();
	DBconfig dBconfig = new DBconfig();
	MemberDAO membdao = new MemberDAO();

	@FXML
	TextField fullName;

	@FXML
	TextField address;

	@FXML
	ComboBox memberType;

	@FXML
	ComboBox packageType;

	@FXML
	Button addMember;

	@FXML
	private TableView<MemberPojo> tablelist;
	@FXML
	private TableColumn<MemberPojo, Integer> serailNumbert;
	@FXML
	private TableColumn<MemberPojo, String> fullNamet;
	@FXML
	private TableColumn<MemberPojo, String> addresst;
	@FXML
	private TableColumn<MemberPojo, Integer> memberTypet;
	@FXML
	private TableColumn<MemberPojo, Integer> packageTypet;

	public void goToHome(MouseEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getDashboard());
	}	
	public void refreshPage(ActionEvent evt) {
		Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
		AdminAdd adminAdd = new AdminAdd();
		root.setScene(adminAdd.getMember());
		root.resizableProperty().set(false);
	}	
	
	public void editMember(ActionEvent evt) {
		try {
			Connection conn = dBconfig.getConnection();
			ResultSet rs = membdao.getMemberById(conn, selectedmemb);
			while (rs.next()) {
				fullName.setText(rs.getString(2));
				address.setText(rs.getString(3));
				memberType.getSelectionModel().select(rs.getInt(4));
				packageType.getSelectionModel().select(rs.getInt(5));
				addMember.setText("Update");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void deleteMember(ActionEvent evt) {
		try {
			Connection conn = dBconfig.getConnection();
			if (membdao.deleteMember(conn, selectedmemb)) {
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
		root.setScene(traineform.getMember());
		root.resizableProperty().set(false);
	}

	public void saveMember(ActionEvent evt) {
		MemberPojo member = new MemberPojo(fullName.getText(), address.getText(), keyvaltype.get(memberType.getValue()),
				keyvalpackage.get(packageType.getValue()));
		System.out.println(member);

		if (addMember.getText().equals("Add Member")) {
			try {
				Connection conn = dBconfig.getConnection();
				System.out.println(membdao.AddMember(conn, member));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				Connection conn = dBconfig.getConnection();
				System.out.println(membdao.updateMember(conn, member,selectedmemb));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Stage root = (Stage) ((Node) evt.getSource()).getScene().getWindow();
		AdminAdd traineform = new AdminAdd();
		root.setScene(traineform.getMember());
		root.resizableProperty().set(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serailNumbert.setCellValueFactory(new PropertyValueFactory<MemberPojo, Integer>("member_id"));
		fullNamet.setCellValueFactory(new PropertyValueFactory<MemberPojo, String>("fullname"));
		addresst.setCellValueFactory(new PropertyValueFactory<MemberPojo, String>("address"));
		memberTypet.setCellValueFactory(new PropertyValueFactory<MemberPojo, Integer>("member_type"));
		packageTypet.setCellValueFactory(new PropertyValueFactory<MemberPojo, Integer>("fee_package"));

		try {
			Connection conn = dBconfig.getConnection();
			MemberDAO membdao = new MemberDAO();
			ResultSet rs = MemberDAO.getMember(conn);
			int i = 0;
			while (rs.next()) {
				MemberPojo pojo = new MemberPojo(++i, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5));
				//System.out.println(pojo);
				list.add(pojo);
				tablelist.setItems(list);
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
					@SuppressWarnings("rawtypes")
					TableViewSelectionModel selectionModel = tablelist.getSelectionModel();
					@SuppressWarnings("rawtypes")
					int selectedCells = selectionModel.getSelectedIndex();
					selectedmemb = list.get(selectedCells).getDatabase_id();

					// System.out.println("Selected Value " + selectedCells);
					 System.out.println("Database id " + selectedmemb);
				}
			}

		});

		try {
			Connection conn = dBconfig.getConnection();
			ResultSet rs = PackageDAO.getPackageList(conn);
			while (rs.next()) {
				userlist.add(rs.getString(2));
				keyvalpackage.put(rs.getString(2), rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		List<String> list = new ArrayList<String>();
		list.add("Student");
		keyvaltype.put("Student", 1);
		list.add("Mum Staff");
		keyvaltype.put("Mum Staff", 2);
		list.add("Other Member");
		keyvaltype.put("Other Member", 3);
		ObservableList obList = FXCollections.observableList(list);
		ObservableList packagelist = FXCollections.observableList(userlist);
		memberType.getItems().clear();
		packageType.getItems().clear();
		memberType.setItems(obList);
		packageType.setItems(packagelist);
	}
}
