package controller.viewloader;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Asset {
	public Scene getAsset() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../view/BookAsset.fxml"));
			Scene scene = new Scene(root,734, 505);			
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Scene returnAssets() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../../view/ReturnAsset.fxml"));
			Scene scene = new Scene(root,780, 505);			
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Scene getAssetList() {
		try {
			Parent root;
			root = FXMLLoader.load(new AdminAdd().getClass().getResource("../../view/AssetCRUD.fxml"));
			Scene scene = new Scene(root, 800, 500);
			return scene;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
