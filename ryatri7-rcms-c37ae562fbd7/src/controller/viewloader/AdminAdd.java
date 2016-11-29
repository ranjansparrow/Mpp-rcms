package controller.viewloader;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AdminAdd {
	public Scene adminAdd() {
		try {
			Parent root;
			root = FXMLLoader.load(new Login().getClass().getResource("../../view/addMember.fxml"));
			Scene scene = new Scene(root, 400, 400);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Scene getlist() {
		try {
			Parent root;
			root = FXMLLoader.load(new Login().getClass().getResource("../../view/listUser.fxml"));
			Scene scene = new Scene(root, 400, 400);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Scene getMember() {
		try {
			Parent root;
			root = FXMLLoader.load(new Login().getClass().getResource("../../view/addMember.fxml"));
			Scene scene = new Scene(root, 900, 500);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Scene getMemberList() {
		try {
			Parent root;
			root = FXMLLoader.load(new AdminAdd().getClass().getResource("../../view/MemberListView.fxml"));
			Scene scene = new Scene(root, 400, 400);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Scene getTrainerList() {
		try {
			Parent root;
			root = FXMLLoader.load(new AdminAdd().getClass().getResource("../../view/AddTrainer.fxml"));
			Scene scene = new Scene(root, 900, 500);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Scene getDashboard() {
		try {
			Parent root;
			root = FXMLLoader.load(new AdminAdd().getClass().getResource("../../view/Dashboard.fxml"));
			Scene scene = new Scene(root, 800, 500);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
