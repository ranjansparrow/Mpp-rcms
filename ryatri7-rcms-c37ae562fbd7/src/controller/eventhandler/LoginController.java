package controller.eventhandler;

import java.sql.Connection;
import java.sql.SQLException;

import controller.viewloader.AdminAdd;
import dbconfig.DBconfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.daomodel.LoginDAO;
public class LoginController {
	DBconfig dbconfig;
	Connection conn;
	LoginDAO logindao = new LoginDAO();
	@FXML 
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Label lblerror;
	String usernameStr;
	String passwordStr;
	public void login(ActionEvent evt){		
		usernameStr = username.getText().toString();
		passwordStr = password.getText().toString();
		dbconfig = new DBconfig();
		try {
			conn = dbconfig.getConnection();
			if(logindao.checkLogin(conn, usernameStr, passwordStr)){
				Stage root = (Stage)((Node) evt.getSource()).getScene().getWindow();
				AdminAdd adminAdd = new AdminAdd();
				root.setScene(adminAdd.getDashboard());
				root.resizableProperty().set(false);
			}else{
				lblerror.setText("Username or Password doesnot match");
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		//Event adminAdd = new Event();
		//root.setScene(adminAdd.getEvent());
		//Asset assets = new Asset();
		//root.setScene(assets.getAsset());
		//for return
		//root.setScene(assets.returnAssets());
		//ListLoader listloader = new ListLoader();
		//root.setScene(adminAdd.getMember());
		//root.setScene(adminAdd.getMemberList());	
		//root.setScene(adminAdd.getTrainerForm());
		//root.setScene(adminAdd.getTrainerList());
		//root.setScene(adminAdd.getlist());
		//root.setScene(listloader.getlist());
		
		
	}
	
	
}
