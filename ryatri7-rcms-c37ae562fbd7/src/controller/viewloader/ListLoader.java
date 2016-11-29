package controller.viewloader;
	
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class ListLoader {
	public Scene getlist() {
		try {
			Parent root;
			root = FXMLLoader.load(new Login().getClass().getResource("../../view/Main.fxml"));
			Scene scene = new Scene(root, 400, 400);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
