package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Location;
import model.Schedule;
import model.Sprinkler;
import model.SprinklerGroup;

public class SprinklerSystemController implements Initializable {
	@FXML
	private Text yearText;
	@FXML
	private Text monthText;
	@FXML
	private Text dayText;
	@FXML
	private Text timeText;
	@FXML
	private Text dayOfWeekText;
	@FXML
	private Text temperatureText;
	@FXML
	private ChoiceBox<Location> groupConfigGroupChoiceBox;
	@FXML
	private CheckBox groupEnableDisableCheckBox;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxMon;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxMon;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxMon;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxTue;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxTue;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxTue;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxWed;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxWed;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxWed;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxThu;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxThu;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxThu;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxFri;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxFri;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxFri;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxSat;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxSat;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxSat;
	@FXML
	private ChoiceBox<Integer> groupStartTimeChoiceBoxSun;
	@FXML
	private ChoiceBox<Integer> groupEndTimeChoiceBoxSun;
	@FXML
	private ChoiceBox<Integer> groupVolumeChoiceBoxSun;
	@FXML
	private ChoiceBox<String> individualConfigIdChoiceBox;
	@FXML
	private CheckBox individualEnableCheckBox;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxMon;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxMon;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxMon;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxTue;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxTue;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxTue;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxWed;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxWed;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxWed;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxThu;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxThu;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxThu;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxFri;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxFri;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxFri;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxSat;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxSat;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxSat;
	@FXML
	private ChoiceBox<Integer> individualStartTimeChoiceBoxSun;
	@FXML
	private ChoiceBox<Integer> individualEndTimeChoiceBoxSun;
	@FXML
	private ChoiceBox<Integer> individualVolumeChoiceBoxSun;
	@FXML
	private Tab tabNorth;
	@FXML
	private VBox sprinklerNorthVBox;
	@FXML
	private Tab tabSouth;
	@FXML
	private VBox sprinklerSouthVBox;
	@FXML
	private Tab tabWest;
	@FXML
	private VBox sprinklerWestVBox;
	@FXML
	private Tab tabEast;
	@FXML
	private VBox sprinklerEastVBox;
	@FXML
	private BarChart<Integer, Integer> waterVolumeBarChart;
	@FXML
	private Canvas gardenMapCanvas;

	private GraphicsContext gc;

	private Stage stage;

	private final static List<Integer> TIME_CHOICES = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23);
	private final static List<Integer> VOLUME_CHOICES = Arrays.asList(0, 1, 2, 3, 4, 5);
	private final static ObservableList<Integer> OBSERVABLE_TIME_CHOICES = FXCollections
			.observableArrayList(TIME_CHOICES);
	private final static ObservableList<Integer> OBSERVABLE_VOLUME_CHOICES = FXCollections
			.observableArrayList(VOLUME_CHOICES);

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private WaterConsumptionSimulator waterConsumptionSimulator;
	private SprinklerController sprinklerController;
	private SprinklerGroup[] sprinklerGroup;
	private Map<Integer, Integer> waterConsumptionMap; // Keep track of
														// month/volume water
														// consumption
	private Map<Sprinkler, Integer> userInterruptMap; // Saves Sprinkler/Time
														// that's forced to be
														// interrupted by user

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTimeTemperatureSimulator();
		initWaterConsumptionSimulator();
		initGardenSprinklers();
		initSprinklerController();
		initViews();
		initListeners();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void initTimeTemperatureSimulator() {
		this.timeTemperatureSimulator = new TimeTemperatureSimulator();
		this.timeTemperatureSimulator.start();
		waitForThread(500);
	}

	private void initWaterConsumptionSimulator() {
		this.waterConsumptionSimulator = new WaterConsumptionSimulator(this.timeTemperatureSimulator);
		this.waterConsumptionSimulator.setBarChart(waterVolumeBarChart);
		this.waterConsumptionSimulator.start();
		waitForThread(500);
	}

	private void initSprinklerController() {
		this.sprinklerController = new SprinklerController(timeTemperatureSimulator, waterConsumptionSimulator,
				sprinklerGroup);
		this.sprinklerController.start();
		waitForThread(500);
	}

	private void initGardenSprinklers() {
		this.sprinklerGroup = new SprinklerGroup[4];
		this.waterConsumptionMap = new HashMap<Integer, Integer>();
		this.userInterruptMap = new HashMap<Sprinkler, Integer>();

		Location location = Location.North;
		int locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		List<Sprinkler> sprinklers = new ArrayList<Sprinkler>();
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		sprinklerGroup[0] = new SprinklerGroup(location, sprinklers);

		location = Location.South;
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers = new ArrayList<Sprinkler>();
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, false));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, false));
		sprinklerGroup[1] = new SprinklerGroup(location, sprinklers);

		location = Location.West;
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers = new ArrayList<Sprinkler>();
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, false));
		sprinklerGroup[2] = new SprinklerGroup(location, sprinklers);

		location = Location.East;
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers = new ArrayList<Sprinkler>();
		sprinklers.add(new Sprinkler(location, locationId, false));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId, true));
		sprinklerGroup[3] = new SprinklerGroup(location, sprinklers);
	}

	private void initViews() {
		initTimeTemperature();
		initGroupConfig();
		initIndividualConfig();
		initSprinklerStatusVBoxPane();
		initGardenCanvas();
	}

	public void initTimeTemperature() {
		this.yearText.textProperty().bind(timeTemperatureSimulator.yearProperty());
		this.monthText.textProperty().bind(timeTemperatureSimulator.monthProperty());
		this.dayText.textProperty().bind(timeTemperatureSimulator.dayProperty());
		this.timeText.textProperty().bind(timeTemperatureSimulator.timeProperty());
		this.dayOfWeekText.textProperty().bind(timeTemperatureSimulator.dayOfWeekProperty());
		this.temperatureText.textProperty().bind(timeTemperatureSimulator.temperatureProperty());
	}

	private void initGroupConfig() {
		this.groupConfigGroupChoiceBox.setItems(FXCollections.observableArrayList(Location.values()));
		this.groupConfigGroupChoiceBox.getSelectionModel().selectFirst();

		this.groupStartTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxMon.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxMon.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxMon.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxMon.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxTue.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxTue.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxTue.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxTue.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxWed.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxWed.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxWed.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxWed.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxThu.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxThu.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxThu.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxThu.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxFri.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxFri.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxFri.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxFri.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxSat.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxSat.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxSat.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxSat.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxSun.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxSun.getSelectionModel().select(18);
		this.groupVolumeChoiceBoxSun.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxSun.getSelectionModel().select(1);
	}

	private void initIndividualConfig() {
		List<String> sprinklerIds = new ArrayList<>();
		for (SprinklerGroup sg : sprinklerGroup) {
			for (Sprinkler sprinkler : sg.getSprinklers()) {
				sprinklerIds.add(sprinkler.getId());
			}
		}
		this.individualConfigIdChoiceBox.setItems(FXCollections.observableArrayList(sprinklerIds));
		this.individualConfigIdChoiceBox.getSelectionModel().selectFirst();

		this.individualStartTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxMon.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxMon.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxMon.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxMon.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxTue.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxTue.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxTue.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxTue.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxWed.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxWed.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxWed.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxWed.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxThu.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxThu.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxThu.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxThu.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxFri.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxFri.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxFri.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxFri.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxSat.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxSat.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxSat.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxSat.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxSun.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxSun.getSelectionModel().select(18);
		this.individualVolumeChoiceBoxSun.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxSun.getSelectionModel().select(1);
	}

	private void initSprinklerStatusVBoxPane() {
		for (SprinklerGroup sg : sprinklerGroup) {
			for (Sprinkler sprinkler : sg.getSprinklers()) {
				HBox hBox = new HBox();
				hBox.setPadding(new Insets(5, 5, 5, 5));
				hBox.setAlignment(Pos.CENTER);

				// Id label
				Label idLabel = new Label(sprinkler.getId());
				idLabel.setMinWidth(100);
				idLabel.setAlignment(Pos.CENTER);
				hBox.getChildren().add(idLabel);

				// Functional label
				Label functionalLabel = new Label("Functional: ");
				functionalLabel.setMinWidth(60);
				functionalLabel.setAlignment(Pos.CENTER);
				hBox.getChildren().add(functionalLabel);
				// Functional status
				CheckBox functionalCheckBox = new CheckBox();
				functionalCheckBox.disableProperty().set(true);
				functionalCheckBox.selectedProperty().bind(sprinkler.functionalProperty());
				functionalCheckBox.setMinWidth(20);
				hBox.getChildren().add(functionalCheckBox);

				// On label
				Label onLabel = new Label("On: ");
				onLabel.setMinWidth(20);
				onLabel.setAlignment(Pos.CENTER);
				hBox.getChildren().add(onLabel);
				// On Status
				CheckBox onCheckBox = new CheckBox();
				onCheckBox.disableProperty().set(true);
				onCheckBox.selectedProperty().bind(sprinkler.onProperty());
				onCheckBox.setMinWidth(20);
				hBox.getChildren().add(onCheckBox);

				// Force interrupt label
				Label interruptLabel = new Label("Force interrupt:");
				interruptLabel.setMinWidth(80);
				interruptLabel.setAlignment(Pos.CENTER);
				hBox.getChildren().add(interruptLabel);
				// Force enable/disable for a period
				Button forceInterruptButton = new Button();
				forceInterruptButton.setText(" Enable");
				forceInterruptButton.textProperty().bind(sprinkler.forceInterruptProperty());
				forceInterruptButton.disableProperty().bind(sprinkler.enableProperty());
				forceInterruptButton.setMinWidth(20);
				forceInterruptButton.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {

						int interruptHour = timeTemperatureSimulator.getHour();
						if (forceInterruptButton.getText().equals(" Enable")) {
							sprinkler.enableByForceInterrupt(interruptHour);
						} else {
							sprinkler.disableByForceInterrupt(interruptHour);
						}
					}
				});
				hBox.getChildren().add(forceInterruptButton);

				switch (sprinkler.getLocation()) {
				case North:
					this.sprinklerNorthVBox.getChildren().add(hBox);
					break;
				case South:
					this.sprinklerSouthVBox.getChildren().add(hBox);
					break;
				case West:
					this.sprinklerWestVBox.getChildren().add(hBox);
					break;
				case East:
					this.sprinklerEastVBox.getChildren().add(hBox);
					break;
				default:
					assert false;
				}
			}
		}
	}

	private void initGardenCanvas() {
		// Draw garden sprinklers based on "sprinklerGroup" map
		int size = 1;
		int canvisWidth = 410;
		int canvisHeight = 250;

		gc = gardenMapCanvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);
		// Draw border
		gc.strokeLine(0, 0, canvisWidth, 0);
		gc.strokeLine(canvisWidth, 0, canvisWidth, canvisHeight);
		gc.strokeLine(0, canvisHeight, canvisWidth, canvisHeight);
		gc.strokeLine(0, 0, 0, canvisHeight);
		// Draw anti/diagonal
		gc.strokeLine(0, 0, canvisWidth, canvisHeight);
		gc.strokeLine(0, canvisHeight, canvisWidth, 0);

		// Start point of the Oval(Sprinkler) for every new iteration(change
		// line)
		double start_x = 148.5; // For biggest Oval. Only one Oval.
		double start_y = 0;

		// Starting boundary, the middle point of the canvas
		double boundary_x = 205;
		double boundary_y = 125;

		// Location of every Oval(Sprinkler)
		double x = 148.5; // 7.0 is for (2,2). +50 If the oval is (30, 30)
		double y = 0;

		// Size of the Oval(Sprinkler). Changeable.
		double width = 113;
		double height = 113;

		int count = 0;

		double move_x = 20;
		double move_y = 20;

		double start_move_x = 15;

		double next_x = 70;
		switch (size) {
		case 1:
			start_move_x = 0;
			break;
		case 2:
			width = width - size * 20;
			height = height - size * 20;
			move_x += 25;
			move_y -= 25;
			next_x += 50;
			break;
		case 3:
			width = width - size * 15;
			height = height - size * 15;
			move_x += 5;
			move_y -= 5;
			next_x += 60;
			break;
		case 4:
			width = width - size * 15;
			height = height - size * 15;
			move_x -= 5;
			move_y -= 10;
			next_x += 30;
			break;
		case 5:
			width = width - size * 13;
			height = height - size * 13;
			move_x -= 10;
			move_y -= 15;
			next_x += 45;
			break;
		case 6:
			width = width - size * 10.5;
			height = height - size * 10.5;
			move_x -= 10;
			move_y -= 15;
			next_x += 40;
			break;
		case 7:
			width = width - size * 10.5;
			height = height - size * 10.5;
			start_move_x -= 2;
			move_x -= 11.5;
			move_y -= 14.5;
			next_x += 10;
			break;
		case 8:
			width = width - size * 9;
			height = height - size * 9;
			start_move_x -= 4;
			move_x -= 13.5;
			move_y -= 16.5;
			next_x += 11;
			break;
		case 9:
			width = width - size * 9;
			height = height - size * 9;
			start_move_x -= 4;
			move_x -= 14.5;
			move_y -= 17.5;
			next_x += 11;
			break;
		case 10:
			width = width - size * 8;
			height = height - size * 8;
			start_move_x -= 4;
			move_x -= 14.5;
			move_y -= 17.5;
			next_x += 11;
			break;
		case 11:
			width = width - size * 7;
			height = height - size * 7;
			start_move_x -= 6;
			move_x -= 17.0;
			move_y -= 18.5;
			next_x += 11;
			break;
		case 12:
			width = width - size * 7;
			height = height - size * 7;
			start_move_x -= 6;
			move_x -= 17.0;
			move_y -= 18.5;
			next_x += 11;
			break;
		case 13:
			width = width - size * 6.5;
			height = height - size * 6.5;
			start_move_x -= 6.8;
			move_x -= 17.0;
			move_y -= 18.5;
			next_x += 7;
			break;
		case 14:
			width = width - size * 6.5;
			height = height - size * 6.5;
			start_move_x -= 6.8;
			move_x -= 17.0;
			move_y -= 18.5;
			next_x += 7;
			break;
		case 15:
			width = width - size * 6;
			height = height - size * 6;
			start_move_x -= 7.6;
			move_x -= 17.0;
			move_y -= 18.5;
			next_x -= 9;
			break;
		case 16:
			width = width - size * 6;
			height = height - size * 6;
			start_move_x -= 7.6;
			move_x -= 17.2;
			move_y -= 18.7;
			next_x -= 9;
			break;
		}

		x = start_x - size * start_move_x;

		for (int i = 0; i < size; i++) {
			if (x <= boundary_x && y <= boundary_y) {

				gc.setFill(Color.GRAY);
				gc.fillOval(x, y, width, height);
				x += size * move_x;
				y += size * move_y;
				System.out.println("X: " + x + "  Y:" + y);
				System.out.println("1" + i);
			} else {
				gc.setFill(Color.GREEN);
				System.out.println("2:" + i);
				count++;
				x = start_x - size * start_move_x + count * next_x;
				y = 0;
				gc.fillOval(x, y, width, height);

				x += size * move_x;
				y += size * move_y;

				boundary_x += 35;
				boundary_y -= 15;
			}
		}
	}

	private void initListeners() {
		initTimeTemperatureListener();
		initGroupConfigListeners();
		initIndividualConfigListeners();
	}

	public void initTimeTemperatureListener() {

		timeTemperatureSimulator.timeProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				// System.out.println(observable);
				// TODO: check user forced interrupt timeout
				// TODO: check temperature limit for all Sprinkler
				// TODO: check all Sprinkler start/end time
				// TODO: calculate water consumption
			}
		});
	}

	public void initGroupConfigListeners() {

		groupConfigGroupChoiceBox.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				boolean enabled = false;
				switch (((ReadOnlyObjectProperty<Location>) observable).getValue()) {
				case North:
					enabled = sprinklerGroup[0].isEnabled();
					break;
				case South:
					enabled = sprinklerGroup[1].isEnabled();
					break;
				case West:
					enabled = sprinklerGroup[2].isEnabled();
					break;
				case East:
					enabled = sprinklerGroup[3].isEnabled();
					break;
				default:
					assert false;
				}
				groupEnableDisableCheckBox.setSelected(enabled);
				disableGroupWeekScheduleSelections(enabled);
			}
		});
	}

	public void initIndividualConfigListeners() {

		individualConfigIdChoiceBox.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				String id = ((ReadOnlyObjectProperty<String>) observable).getValue();
				Sprinkler targetSprinkler = getSprinkler(id);
				boolean enabled = targetSprinkler.isIndividualScheduleSet();
				boolean functional = targetSprinkler.isFunctional();

				if (functional) {
					individualEnableCheckBox.setDisable(false);
					individualEnableCheckBox.setSelected(enabled);
					disableIndividualWeekScheduleSelections(enabled);
				} else {
					individualEnableCheckBox.setDisable(true);
					individualEnableCheckBox.setSelected(false);
					disableIndividualWeekScheduleSelections(true);
				}
			}
		});
	}

	// Event Listener on CheckBox[#groupEnableDisableCheckBox].onMouseClicked
	@FXML
	public void enableOrDisableGroupConfiguration(MouseEvent event) {

		// Get target sprinkler group
		Location location = groupConfigGroupChoiceBox.getSelectionModel().selectedItemProperty().getValue();
		SprinklerGroup sprinklerGroup = null;
		switch (location) {
		case North:
			sprinklerGroup = this.sprinklerGroup[0];
			break;
		case South:
			sprinklerGroup = this.sprinklerGroup[1];
			break;
		case West:
			sprinklerGroup = this.sprinklerGroup[2];
			break;
		case East:
			sprinklerGroup = this.sprinklerGroup[3];
			break;
		default:
			assert false;
		}

		boolean groupEnabled = groupEnableDisableCheckBox.isSelected();
		sprinklerGroup.setEnabled(groupEnabled);
		disableGroupWeekScheduleSelections(groupEnabled);

		for (Sprinkler sprinkler : sprinklerGroup.getSprinklers()) {
			if (groupEnabled) {
				setGroupWeekSchedule(sprinkler);
			} else {
				resetGroupWeekSchedule(sprinkler);
			}
		}
	}

	private void resetGroupWeekSchedule(Sprinkler sprinkler) {
		sprinkler.setGroupSchedule(0, null);
		sprinkler.setGroupSchedule(1, null);
		sprinkler.setGroupSchedule(2, null);
		sprinkler.setGroupSchedule(3, null);
		sprinkler.setGroupSchedule(4, null);
		sprinkler.setGroupSchedule(5, null);
		sprinkler.setGroupSchedule(6, null);
	}

	private void setGroupWeekSchedule(Sprinkler sprinkler) {
		int startTime = this.groupStartTimeChoiceBoxMon.getSelectionModel().getSelectedItem();
		int endTime = this.groupEndTimeChoiceBoxMon.getSelectionModel().getSelectedItem();
		int volumePerHour = this.groupVolumeChoiceBoxMon.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleMon = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(0, sprinklerScheduleMon);

		startTime = this.groupStartTimeChoiceBoxTue.getSelectionModel().getSelectedItem();
		endTime = this.groupEndTimeChoiceBoxTue.getSelectionModel().getSelectedItem();
		volumePerHour = this.groupVolumeChoiceBoxTue.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleTue = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(1, sprinklerScheduleTue);

		startTime = this.groupStartTimeChoiceBoxWed.getSelectionModel().getSelectedItem();
		endTime = this.groupEndTimeChoiceBoxWed.getSelectionModel().getSelectedItem();
		volumePerHour = this.groupVolumeChoiceBoxWed.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleWed = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(2, sprinklerScheduleWed);

		startTime = this.groupStartTimeChoiceBoxThu.getSelectionModel().getSelectedItem();
		endTime = this.groupEndTimeChoiceBoxThu.getSelectionModel().getSelectedItem();
		volumePerHour = this.groupVolumeChoiceBoxThu.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleThu = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(3, sprinklerScheduleThu);

		startTime = this.groupStartTimeChoiceBoxFri.getSelectionModel().getSelectedItem();
		endTime = this.groupEndTimeChoiceBoxFri.getSelectionModel().getSelectedItem();
		volumePerHour = this.groupVolumeChoiceBoxFri.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleFri = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(4, sprinklerScheduleFri);

		startTime = this.groupStartTimeChoiceBoxSat.getSelectionModel().getSelectedItem();
		endTime = this.groupEndTimeChoiceBoxSat.getSelectionModel().getSelectedItem();
		volumePerHour = this.groupVolumeChoiceBoxSat.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleSat = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(5, sprinklerScheduleSat);

		startTime = this.groupStartTimeChoiceBoxSun.getSelectionModel().getSelectedItem();
		endTime = this.groupEndTimeChoiceBoxSun.getSelectionModel().getSelectedItem();
		volumePerHour = this.groupVolumeChoiceBoxSun.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleSun = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setGroupSchedule(6, sprinklerScheduleSun);
	}

	private void disableGroupWeekScheduleSelections(boolean disable) {

		this.groupStartTimeChoiceBoxMon.setDisable(disable);
		this.groupEndTimeChoiceBoxMon.setDisable(disable);
		this.groupVolumeChoiceBoxMon.setDisable(disable);

		this.groupStartTimeChoiceBoxTue.setDisable(disable);
		this.groupEndTimeChoiceBoxTue.setDisable(disable);
		this.groupVolumeChoiceBoxTue.setDisable(disable);

		this.groupStartTimeChoiceBoxWed.setDisable(disable);
		this.groupEndTimeChoiceBoxWed.setDisable(disable);
		this.groupVolumeChoiceBoxWed.setDisable(disable);

		this.groupStartTimeChoiceBoxThu.setDisable(disable);
		this.groupEndTimeChoiceBoxThu.setDisable(disable);
		this.groupVolumeChoiceBoxThu.setDisable(disable);

		this.groupStartTimeChoiceBoxFri.setDisable(disable);
		this.groupEndTimeChoiceBoxFri.setDisable(disable);
		this.groupVolumeChoiceBoxFri.setDisable(disable);

		this.groupStartTimeChoiceBoxSat.setDisable(disable);
		this.groupEndTimeChoiceBoxSat.setDisable(disable);
		this.groupVolumeChoiceBoxSat.setDisable(disable);

		this.groupStartTimeChoiceBoxSun.setDisable(disable);
		this.groupEndTimeChoiceBoxSun.setDisable(disable);
		this.groupVolumeChoiceBoxSun.setDisable(disable);
	}

	// Event Listener on CheckBox[#individualEnableCheckBox].onMouseClicked
	@FXML
	public void enableOrDisableIndividualConfiguration(MouseEvent event) {

		String id = this.individualConfigIdChoiceBox.selectionModelProperty().getValue().getSelectedItem();
		Sprinkler targetSprinkler = getSprinkler(id);
		boolean individualEnabled = this.individualEnableCheckBox.isSelected();
		disableIndividualWeekScheduleSelections(individualEnabled);

		if (individualEnabled) {
			setIndividualWeekSchedule(targetSprinkler);
		} else {
			resetIndividualWeekSchedule(targetSprinkler);
		}
	}

	private void resetIndividualWeekSchedule(Sprinkler sprinkler) {
		sprinkler.setIndividualSchedule(0, null);
		sprinkler.setIndividualSchedule(1, null);
		sprinkler.setIndividualSchedule(2, null);
		sprinkler.setIndividualSchedule(3, null);
		sprinkler.setIndividualSchedule(4, null);
		sprinkler.setIndividualSchedule(5, null);
		sprinkler.setIndividualSchedule(6, null);
	}

	private void setIndividualWeekSchedule(Sprinkler sprinkler) {

		int startTime = this.individualStartTimeChoiceBoxMon.getSelectionModel().getSelectedItem();
		int endTime = this.individualEndTimeChoiceBoxMon.getSelectionModel().getSelectedItem();
		int volumePerHour = this.individualVolumeChoiceBoxMon.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleMon = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(0, sprinklerScheduleMon);

		startTime = this.individualStartTimeChoiceBoxTue.getSelectionModel().getSelectedItem();
		endTime = this.individualEndTimeChoiceBoxTue.getSelectionModel().getSelectedItem();
		volumePerHour = this.individualVolumeChoiceBoxTue.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleTue = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(1, sprinklerScheduleTue);

		startTime = this.individualStartTimeChoiceBoxWed.getSelectionModel().getSelectedItem();
		endTime = this.individualEndTimeChoiceBoxWed.getSelectionModel().getSelectedItem();
		volumePerHour = this.individualVolumeChoiceBoxWed.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleWed = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(2, sprinklerScheduleWed);

		startTime = this.individualStartTimeChoiceBoxThu.getSelectionModel().getSelectedItem();
		endTime = this.individualEndTimeChoiceBoxThu.getSelectionModel().getSelectedItem();
		volumePerHour = this.individualVolumeChoiceBoxThu.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleThu = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(3, sprinklerScheduleThu);

		startTime = this.individualStartTimeChoiceBoxFri.getSelectionModel().getSelectedItem();
		endTime = this.individualEndTimeChoiceBoxFri.getSelectionModel().getSelectedItem();
		volumePerHour = this.individualVolumeChoiceBoxFri.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleFri = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(4, sprinklerScheduleFri);

		startTime = this.individualStartTimeChoiceBoxSat.getSelectionModel().getSelectedItem();
		endTime = this.individualEndTimeChoiceBoxSat.getSelectionModel().getSelectedItem();
		volumePerHour = this.individualVolumeChoiceBoxSat.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleSat = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(5, sprinklerScheduleSat);

		startTime = this.individualStartTimeChoiceBoxSun.getSelectionModel().getSelectedItem();
		endTime = this.individualEndTimeChoiceBoxSun.getSelectionModel().getSelectedItem();
		volumePerHour = this.individualVolumeChoiceBoxSun.getSelectionModel().getSelectedItem();
		Schedule sprinklerScheduleSun = new Schedule(startTime, endTime, volumePerHour);
		sprinkler.setIndividualSchedule(6, sprinklerScheduleSun);
	}

	private void disableIndividualWeekScheduleSelections(boolean disable) {
		this.individualStartTimeChoiceBoxMon.setDisable(disable);
		this.individualEndTimeChoiceBoxMon.setDisable(disable);
		this.individualVolumeChoiceBoxMon.setDisable(disable);

		this.individualStartTimeChoiceBoxTue.setDisable(disable);
		this.individualEndTimeChoiceBoxTue.setDisable(disable);
		this.individualVolumeChoiceBoxTue.setDisable(disable);

		this.individualStartTimeChoiceBoxWed.setDisable(disable);
		this.individualEndTimeChoiceBoxWed.setDisable(disable);
		this.individualVolumeChoiceBoxWed.setDisable(disable);

		this.individualStartTimeChoiceBoxThu.setDisable(disable);
		this.individualEndTimeChoiceBoxThu.setDisable(disable);
		this.individualVolumeChoiceBoxThu.setDisable(disable);

		this.individualStartTimeChoiceBoxFri.setDisable(disable);
		this.individualEndTimeChoiceBoxFri.setDisable(disable);
		this.individualVolumeChoiceBoxFri.setDisable(disable);

		this.individualStartTimeChoiceBoxSat.setDisable(disable);
		this.individualEndTimeChoiceBoxSat.setDisable(disable);
		this.individualVolumeChoiceBoxSat.setDisable(disable);

		this.individualStartTimeChoiceBoxSun.setDisable(disable);
		this.individualEndTimeChoiceBoxSun.setDisable(disable);
		this.individualVolumeChoiceBoxSun.setDisable(disable);
	}

	private void waitForThread(int millisecond) {
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Sprinkler getSprinkler(String id) {
		for (SprinklerGroup sg : sprinklerGroup) {
			for (Sprinkler sprinkler : sg.getSprinklers()) {
				if (sprinkler.getId().equals(id)) {
					return sprinkler;
				}
			}
		}
		return null;
	}
}
