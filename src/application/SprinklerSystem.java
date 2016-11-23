package application;

import control.SprinklerSystemController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.tools.jar.Main;

public class SprinklerSystem extends Application {

	private final static String APPLICATION_TITLE = "HummingBee Home Garden Sprinkler System";

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/application/SprinklerSystem.fxml"));
			Pane root = (Pane) loader.load();
			SprinklerSystemController controller = (SprinklerSystemController) loader.getController();
			controller.setStage(primaryStage);

			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(APPLICATION_TITLE);
			primaryStage.show();
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
