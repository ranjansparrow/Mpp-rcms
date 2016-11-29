package controller.viewloader;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Login {

	public Scene getLogin() {
		try {
			Parent root;
			root = FXMLLoader.load(new Login().getClass().getResource("../../view/login.fxml"));
			Scene scene = new Scene(root, 700, 400);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
