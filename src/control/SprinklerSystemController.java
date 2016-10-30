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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Location;
import model.Sprinkler;

public class SprinklerSystemController implements Initializable {
	@FXML
	private Text timeText;
	@FXML
	private Text temperatureText;
	@FXML
	private ChoiceBox<Location> groupConfigGroupChoiceBox;
	@FXML
	private CheckBox groupEnableDisableCheckBox;
	@FXML
	private Button groupConfirmButton;
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
	private Button individualConfirmButton;
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
	private BarChart<Integer, Integer> waterVolumeBarGraph;
	@FXML
	private Canvas gardenMapCanvas;

	private Stage stage;

	private final static List<Integer> TIME_CHOICES = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23);
	private final static List<Integer> VOLUME_CHOICES = Arrays.asList(0, 1, 2, 3, 4, 5);
	private final static ObservableList<Integer> OBSERVABLE_TIME_CHOICES = FXCollections
			.observableArrayList(TIME_CHOICES);
	private final static ObservableList<Integer> OBSERVABLE_VOLUME_CHOICES = FXCollections
			.observableArrayList(VOLUME_CHOICES);

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private List<Sprinkler> sprinklers;
	private Map<Integer, Integer> waterConsumptionMap; // Keep track of
														// month/volume water
														// consumption
	private Map<Sprinkler, Integer> userInterruptMap; // Saves Sprinkler/Time
														// that's forced to be
														// interrupted by user

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initTimeTemperatureSimulator();
		initGardenSprinklers();
		initViews();
		initListeners();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	private void initTimeTemperatureSimulator() {
		this.timeTemperatureSimulator = new TimeTemperatureSimulator();
		this.timeTemperatureSimulator.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void initGardenSprinklers() {
		this.sprinklers = new ArrayList<Sprinkler>();
		this.waterConsumptionMap = new HashMap<Integer, Integer>();
		this.userInterruptMap = new HashMap<Sprinkler, Integer>();

		Location location = Location.North;
		int locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));

		location = Location.South;
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));

		location = Location.West;
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));

		location = Location.East;
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
		locationId = LocationSprinklerIdManager.getNextIdNumber(location);
		sprinklers.add(new Sprinkler(location, locationId));
	}

	private void initViews() {
		initTimeTemperature();
		initGroupConfig();
		initIndividualConfig();
		initSprinklerStatusScrollPane();
		initVolumeBarChart();
		initGardenCanvas();
	}

	public void initTimeTemperature() {
		this.timeText.textProperty().bind(timeTemperatureSimulator.getTimeStringProperty());
		this.temperatureText.textProperty().bind(timeTemperatureSimulator.getTemperatureProperty());
	}

	private void initGroupConfig() {
		this.groupConfigGroupChoiceBox.setItems(FXCollections.observableArrayList(Location.values()));
		this.groupConfigGroupChoiceBox.getSelectionModel().selectFirst();

		this.groupStartTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxMon.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxMon.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxMon.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxMon.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxTue.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxTue.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxTue.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxTue.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxWed.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxWed.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxWed.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxWed.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxThu.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxThu.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxThu.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxThu.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxFri.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxFri.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxFri.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxFri.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxSat.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxSat.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxSat.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxSat.getSelectionModel().select(1);

		this.groupStartTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupStartTimeChoiceBoxSun.getSelectionModel().select(8);
		this.groupEndTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.groupEndTimeChoiceBoxSun.getSelectionModel().select(8);
		this.groupVolumeChoiceBoxSun.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.groupVolumeChoiceBoxSun.getSelectionModel().select(1);
	}

	private void initIndividualConfig() {
		List<String> sprinklerIds = new ArrayList<>();
		for (Sprinkler sprinkler : sprinklers) {
			sprinklerIds.add(sprinkler.getId());
		}
		this.individualConfigIdChoiceBox.setItems(FXCollections.observableArrayList(sprinklerIds));
		this.individualConfigIdChoiceBox.getSelectionModel().selectFirst();

		this.individualStartTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxMon.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxMon.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxMon.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxMon.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxMon.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxTue.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxTue.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxTue.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxTue.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxTue.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxWed.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxWed.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxWed.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxWed.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxWed.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxThu.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxThu.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxThu.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxThu.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxThu.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxFri.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxFri.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxFri.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxFri.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxFri.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxSat.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxSat.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxSat.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxSat.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxSat.getSelectionModel().select(1);

		this.individualStartTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualStartTimeChoiceBoxSun.getSelectionModel().select(8);
		this.individualEndTimeChoiceBoxSun.setItems(OBSERVABLE_TIME_CHOICES);
		this.individualEndTimeChoiceBoxSun.getSelectionModel().select(8);
		this.individualVolumeChoiceBoxSun.setItems(OBSERVABLE_VOLUME_CHOICES);
		this.individualVolumeChoiceBoxSun.getSelectionModel().select(1);
	}

	private void initSprinklerStatusScrollPane() {
		for (Sprinkler sprinkler : sprinklers) {
			HBox hBox = new HBox();
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
			forceInterruptButton.setText("" + sprinkler.isOn());
			forceInterruptButton.textProperty().bind(sprinkler.forceInterruptProperty());
			forceInterruptButton.setMinWidth(20);
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

	private void initVolumeBarChart() {
		// TODO Auto-generated method stub

	}

	private void initGardenCanvas() {
		// TODO Auto-generated method stub

	}

	private void initListeners() {

		timeTemperatureSimulator.getTimeStringProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				// System.out.println(observable);
				// TODO: check all Sprinkler start/end time
				// TODO: check user forced interrupt timeout
				// TODO: calculate water consumption
			}
		});

		timeTemperatureSimulator.getTemperatureProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable observable) {
				// TODO: check temperature limit for all Sprinkler
			}
		});

		// TODO: add listener to update "user forced interrupt" state
	}

	// Event Listener on ChoiceBox[#groupConfigGroupChoiceBox].onMouseClicked
	@FXML
	public void selectSprinklerGroup(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on CheckBox[#groupEnableDisableCheckBox].onMouseClicked
	@FXML
	public void enableOrDisableGroupConfiguration(MouseEvent event) {
		// TODO: start recording water volume

		if (groupEnableDisableCheckBox.isSelected()) {
			this.groupStartTimeChoiceBoxMon.setDisable(true);
			this.groupEndTimeChoiceBoxMon.setDisable(true);
			this.groupVolumeChoiceBoxMon.setDisable(true);

			this.groupStartTimeChoiceBoxTue.setDisable(true);
			this.groupEndTimeChoiceBoxTue.setDisable(true);
			this.groupVolumeChoiceBoxTue.setDisable(true);

			this.groupStartTimeChoiceBoxWed.setDisable(true);
			this.groupEndTimeChoiceBoxWed.setDisable(true);
			this.groupVolumeChoiceBoxWed.setDisable(true);

			this.groupStartTimeChoiceBoxThu.setDisable(true);
			this.groupEndTimeChoiceBoxThu.setDisable(true);
			this.groupVolumeChoiceBoxThu.setDisable(true);

			this.groupStartTimeChoiceBoxFri.setDisable(true);
			this.groupEndTimeChoiceBoxFri.setDisable(true);
			this.groupVolumeChoiceBoxFri.setDisable(true);

			this.groupStartTimeChoiceBoxSat.setDisable(true);
			this.groupEndTimeChoiceBoxSat.setDisable(true);
			this.groupVolumeChoiceBoxSat.setDisable(true);

			this.groupStartTimeChoiceBoxSun.setDisable(true);
			this.groupEndTimeChoiceBoxSun.setDisable(true);
			this.groupVolumeChoiceBoxSun.setDisable(true);
		} else {
			this.groupStartTimeChoiceBoxMon.setDisable(false);
			this.groupEndTimeChoiceBoxMon.setDisable(false);
			this.groupVolumeChoiceBoxMon.setDisable(false);

			this.groupStartTimeChoiceBoxTue.setDisable(false);
			this.groupEndTimeChoiceBoxTue.setDisable(false);
			this.groupVolumeChoiceBoxTue.setDisable(false);

			this.groupStartTimeChoiceBoxWed.setDisable(false);
			this.groupEndTimeChoiceBoxWed.setDisable(false);
			this.groupVolumeChoiceBoxWed.setDisable(false);

			this.groupStartTimeChoiceBoxThu.setDisable(false);
			this.groupEndTimeChoiceBoxThu.setDisable(false);
			this.groupVolumeChoiceBoxThu.setDisable(false);

			this.groupStartTimeChoiceBoxFri.setDisable(false);
			this.groupEndTimeChoiceBoxFri.setDisable(false);
			this.groupVolumeChoiceBoxFri.setDisable(false);

			this.groupStartTimeChoiceBoxSat.setDisable(false);
			this.groupEndTimeChoiceBoxSat.setDisable(false);
			this.groupVolumeChoiceBoxSat.setDisable(false);

			this.groupStartTimeChoiceBoxSun.setDisable(false);
			this.groupEndTimeChoiceBoxSun.setDisable(false);
			this.groupVolumeChoiceBoxSun.setDisable(false);
		}
	}

	// Event Listener on Button[#groupConfirmButton].onMouseClicked
	@FXML
	public void applyGroupConfiguration(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxMon].onMouseClicked
	@FXML
	public void selectGroupStartTimeMon(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxMon].onMouseClicked
	@FXML
	public void selectGroupEndTimeMon(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxMon].onMouseClicked
	@FXML
	public void selectGroupVolumeMon(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxTue].onMouseClicked
	@FXML
	public void selectGroupStartTimeTue(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxTue].onMouseClicked
	@FXML
	public void selectGroupEndTimeTue(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxTue].onMouseClicked
	@FXML
	public void selectGroupVolumeTue(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxWed].onMouseClicked
	@FXML
	public void selectGroupStartTimeWed(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxWed].onMouseClicked
	@FXML
	public void selectGroupEndTimeWed(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxWed].onMouseClicked
	@FXML
	public void selectGroupVolumeWed(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxThu].onMouseClicked
	@FXML
	public void selectGroupStartTimeThu(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxThu].onMouseClicked
	@FXML
	public void selectGroupEndTimeThu(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxThu].onMouseClicked
	@FXML
	public void selectGroupVolumeThu(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxFri].onMouseClicked
	@FXML
	public void selectGroupStartTimeFri(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxFri].onMouseClicked
	@FXML
	public void selectGroupEndTimeFri(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxFri].onMouseClicked
	@FXML
	public void selectGroupVolumeFri(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxSat].onMouseClicked
	@FXML
	public void selectGroupStartTimeSat(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxSat].onMouseClicked
	@FXML
	public void selectGroupEndTimeSat(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxSat].onMouseClicked
	@FXML
	public void selectGroupVolumeSat(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupStartTimeChoiceBoxSun].onMouseClicked
	@FXML
	public void selectGroupStartTimeSun(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupEndTimeChoiceBoxSun].onMouseClicked
	@FXML
	public void selectGroupEndTimeSun(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#groupVolumeChoiceBoxSun].onMouseClicked
	@FXML
	public void selectGroupVolumeSun(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualConfigIdChoiceBox].onMouseClicked
	@FXML
	public void selectSprinklerIndividual(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on CheckBox[#individualEnableCheckBox].onMouseClicked
	@FXML
	public void enableOrDisableIndividualConfiguration(MouseEvent event) {
		// TODO: start recording water volume

		if (this.individualEnableCheckBox.isSelected()) {
			this.individualStartTimeChoiceBoxMon.setDisable(true);
			this.individualEndTimeChoiceBoxMon.setDisable(true);
			this.individualVolumeChoiceBoxMon.setDisable(true);

			this.individualStartTimeChoiceBoxTue.setDisable(true);
			this.individualEndTimeChoiceBoxTue.setDisable(true);
			this.individualVolumeChoiceBoxTue.setDisable(true);

			this.individualStartTimeChoiceBoxWed.setDisable(true);
			this.individualEndTimeChoiceBoxWed.setDisable(true);
			this.individualVolumeChoiceBoxWed.setDisable(true);

			this.individualStartTimeChoiceBoxThu.setDisable(true);
			this.individualEndTimeChoiceBoxThu.setDisable(true);
			this.individualVolumeChoiceBoxThu.setDisable(true);

			this.individualStartTimeChoiceBoxFri.setDisable(true);
			this.individualEndTimeChoiceBoxFri.setDisable(true);
			this.individualVolumeChoiceBoxFri.setDisable(true);

			this.individualStartTimeChoiceBoxSat.setDisable(true);
			this.individualEndTimeChoiceBoxSat.setDisable(true);
			this.individualVolumeChoiceBoxSat.setDisable(true);

			this.individualStartTimeChoiceBoxSun.setDisable(true);
			this.individualEndTimeChoiceBoxSun.setDisable(true);
			this.individualVolumeChoiceBoxSun.setDisable(true);
		} else {
			this.individualStartTimeChoiceBoxMon.setDisable(false);
			this.individualEndTimeChoiceBoxMon.setDisable(false);
			this.individualVolumeChoiceBoxMon.setDisable(false);

			this.individualStartTimeChoiceBoxTue.setDisable(false);
			this.individualEndTimeChoiceBoxTue.setDisable(false);
			this.individualVolumeChoiceBoxTue.setDisable(false);

			this.individualStartTimeChoiceBoxWed.setDisable(false);
			this.individualEndTimeChoiceBoxWed.setDisable(false);
			this.individualVolumeChoiceBoxWed.setDisable(false);

			this.individualStartTimeChoiceBoxThu.setDisable(false);
			this.individualEndTimeChoiceBoxThu.setDisable(false);
			this.individualVolumeChoiceBoxThu.setDisable(false);

			this.individualStartTimeChoiceBoxFri.setDisable(false);
			this.individualEndTimeChoiceBoxFri.setDisable(false);
			this.individualVolumeChoiceBoxFri.setDisable(false);

			this.individualStartTimeChoiceBoxSat.setDisable(false);
			this.individualEndTimeChoiceBoxSat.setDisable(false);
			this.individualVolumeChoiceBoxSat.setDisable(false);

			this.individualStartTimeChoiceBoxSun.setDisable(false);
			this.individualEndTimeChoiceBoxSun.setDisable(false);
			this.individualVolumeChoiceBoxSun.setDisable(false);
		}
	}

	// Event Listener on Button[#individualConfirmButton].onMouseClicked
	@FXML
	public void applyIndividualConfiguration(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxMon].onMouseClicked
	@FXML
	public void selectIndividualStartTimeMon(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxMon].onMouseClicked
	@FXML
	public void selectIndividualEndTimeMon(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxMon].onMouseClicked
	@FXML
	public void selectIndividualVolumeMon(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxTue].onMouseClicked
	@FXML
	public void selectIndividualStartTimeTue(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxTue].onMouseClicked
	@FXML
	public void selectIndividualEndTimeTue(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxTue].onMouseClicked
	@FXML
	public void selectIndividualVolumeTue(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxWed].onMouseClicked
	@FXML
	public void selectIndividualStartTimeWed(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxWed].onMouseClicked
	@FXML
	public void selectIndividualEndTimeWed(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxWed].onMouseClicked
	@FXML
	public void selectIndividualVolumeWed(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxThu].onMouseClicked
	@FXML
	public void selectIndividualStartTimeThu(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxThu].onMouseClicked
	@FXML
	public void selectIndividualEndTimeThu(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxThu].onMouseClicked
	@FXML
	public void selectIndividualVolumeThu(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxFri].onMouseClicked
	@FXML
	public void selectIndividualStartTimeFri(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxFri].onMouseClicked
	@FXML
	public void selectIndividualEndTimeFri(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxFri].onMouseClicked
	@FXML
	public void selectIndividualVolumeFri(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxSat].onMouseClicked
	@FXML
	public void selectIndividualStartTimeSat(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxSat].onMouseClicked
	@FXML
	public void selectIndividualEndTimeSat(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxSat].onMouseClicked
	@FXML
	public void selectIndividualVolumeSat(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualStartTimeChoiceBoxSun].onMouseClicked
	@FXML
	public void selectIndividualStartTimeSun(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on
	// ChoiceBox[#individualEndTimeChoiceBoxSun].onMouseClicked
	@FXML
	public void selectIndividualEndTimeSun(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on ChoiceBox[#individualVolumeChoiceBoxSun].onMouseClicked
	@FXML
	public void selectIndividualVolumeSun(MouseEvent event) {
		// TODO Autogenerated
	}
}
