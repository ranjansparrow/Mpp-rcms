package controller.viewloader;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Event {
	public Scene getEventList() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../view/EventList.fxml"));
			Scene scene = new Scene(root, 900, 505);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Scene getEvent() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../view/CreateEvent.fxml"));
			Scene scene = new Scene(root, 734, 505);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
