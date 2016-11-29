package controller.eventhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dbconfig.DBconfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.daomodel.LoginDAO;
import model.pojo.Student;

public class MainController implements Initializable {
	LoginDAO logindao;
	List<String> listval = new ArrayList<String>();
	@FXML
	private TableView<Student> table;
	@FXML
	private TableColumn<Student, String> username;
	@FXML
	private TableColumn<Student, String> password;
	@FXML
	private TableColumn<Student, String> address;

	public ObservableList<Student> list = FXCollections.observableArrayList();

	public MainController() {
		Connection conn =null;
		DBconfig dbconf = new DBconfig();
		try {
			conn = dbconf.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logindao = new LoginDAO();
		ResultSet rs =  logindao.getusers(conn);
		try {
			while(rs.next()){
				try {
					list.add(new Student(rs.getString(1), rs.getString(1),rs.getString(2)));
				} catch (SQLException e) {				
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		username.setCellValueFactory((new PropertyValueFactory<Student, String>("username")));
		password.setCellValueFactory((new PropertyValueFactory<Student, String>("password")));
		address.setCellValueFactory((new PropertyValueFactory<Student, String>("address")));
		table.setItems(list);

	}

}
