package application;

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
			Pane page = (Pane) FXMLLoader.load(Main.class.getResource("/application/SprinklerSystem.fxml"));
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.setTitle(APPLICATION_TITLE);
			primaryStage.show();
			// BorderPane root = new BorderPane();
			// Scene scene = new Scene(root,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// primaryStage.setScene(scene);
			// primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
