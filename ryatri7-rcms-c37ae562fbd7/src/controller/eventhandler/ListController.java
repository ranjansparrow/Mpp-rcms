package controller.eventhandler;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.pojo.User;

public class ListController {
	@FXML
	private TableView<User> table;
	@FXML
	private TableColumn<User, String> username;
	@FXML
	private TableColumn<User, String> password;
	@FXML
	private TableColumn<User, String> address;
	
	public ObservableList<User> list = FXCollections.observableArrayList(
			new User("Ram", "Shyam123", "Kumar"),
			new User("Shyam", "Ram123", "Prasad"),
			new User("Hari", "RamShyam123", "Bahadur")
			);
	public void initialize(URL location, ResourceBundle resources) {
		username.setCellValueFactory((new PropertyValueFactory<User, String>("username")));
		password.setCellValueFactory((new PropertyValueFactory<User, String>("password")));
		address.setCellValueFactory((new PropertyValueFactory<User, String>("address")));
		table.setItems(list);
		
	}
	
	
}
