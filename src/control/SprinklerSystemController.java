package control;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Location;
import model.Sprinkler;

public class SprinklerSystemController implements Initializable {
	@FXML
	private Text timeText;
	@FXML
	private Text temperatureText;
	@FXML
	private Text timeText11;
	@FXML
	private ChoiceBox groupConfigGroupChoiceBox;
	@FXML
	private CheckBox groupEnableDisableCheckBox;
	@FXML
	private Button groupConfirmButton;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxMon;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxMon;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxMon;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxTue;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxTue;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxTue;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxWed;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxWed;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxWed;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxThu;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxThu;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxThu;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxFri;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxFri;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxFri;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxSat;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxSat;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxSat;
	@FXML
	private ChoiceBox groupStartTimeChoiceBoxSun;
	@FXML
	private ChoiceBox groupEndTimeChoiceBoxSun;
	@FXML
	private ChoiceBox groupVolumeChoiceBoxSun;
	@FXML
	private ChoiceBox individualConfigIdChoiceBox;
	@FXML
	private CheckBox individualEnableCheckBox;
	@FXML
	private Button individualConfirmButton;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxMon;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxMon;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxMon;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxTue;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxTue;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxTue;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxWed;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxWed;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxWed;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxThu;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxThu;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxThu;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxFri;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxFri;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxFri;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxSat;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxSat;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxSat;
	@FXML
	private ChoiceBox individualStartTimeChoiceBoxSun;
	@FXML
	private ChoiceBox individualEndTimeChoiceBoxSun;
	@FXML
	private ChoiceBox individualVolumeChoiceBoxSun;
	@FXML
	private Tab tabNorth;
	@FXML
	private VBox sprinklerNorthScrollPane;
	@FXML
	private Tab tabSouth;
	@FXML
	private VBox sprinklerSouthScrollPane;
	@FXML
	private Tab tabWest;
	@FXML
	private VBox sprinklerWestScrollPane;
	@FXML
	private Tab tabEast;
	@FXML
	private VBox sprinklerEastScrollPane;
	@FXML
	private BarChart waterVolumeBarGraph;
	@FXML
	private Canvas gardenMapCanvas;

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private Map<Location, Sprinkler> sprinklerMap;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initDailyTemperatureSimulator();
		initGardenSprinklers();
		initViews();
		initListeners();
		// TODO: more?
	}

	private void initDailyTemperatureSimulator() {
		timeTemperatureSimulator = new TimeTemperatureSimulator();
		timeTemperatureSimulator.start();

		timeText.textProperty().bind(timeTemperatureSimulator.getTimeStringProperty());
		temperatureText.textProperty().bind(timeTemperatureSimulator.getTemperatureProperty());
	}

	private void initGardenSprinklers() {
		sprinklerMap = new HashMap<Location, Sprinkler>();

		sprinklerMap.put(Location.North, new Sprinkler());
		sprinklerMap.put(Location.North, new Sprinkler());
		sprinklerMap.put(Location.North, new Sprinkler());

		sprinklerMap.put(Location.South, new Sprinkler());
		sprinklerMap.put(Location.South, new Sprinkler());
		sprinklerMap.put(Location.South, new Sprinkler());

		sprinklerMap.put(Location.West, new Sprinkler());
		sprinklerMap.put(Location.West, new Sprinkler());
		sprinklerMap.put(Location.West, new Sprinkler());

		sprinklerMap.put(Location.East, new Sprinkler());
		sprinklerMap.put(Location.East, new Sprinkler());
		sprinklerMap.put(Location.East, new Sprinkler());
	}

	private void initViews() {
		// TODO Auto-generated method stub
	}

	private void initListeners() {
		// TODO Auto-generated method stub
	}

	// Event Listener on ChoiceBox[#groupConfigGroupChoiceBox].onMouseClicked
	@FXML
	public void selectSprinklerGroup(MouseEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on CheckBox[#groupEnableDisableCheckBox].onMouseClicked
	@FXML
	public void enableOrDisableGroupConfiguration(MouseEvent event) {
		// TODO Autogenerated
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
		// TODO Autogenerated
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
