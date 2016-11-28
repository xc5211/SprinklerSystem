package control;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.Location;
import model.LocationWaterConsumption;

public class WaterConsumptionSimulator extends Thread {

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private BarChart<Integer, Integer> waterVolumeBarChart;
	private LocationWaterConsumption locationWaterConsumption;

	private IntegerProperty[] monthlyConsumption;
	private int totalWater;
	private StringProperty totalWaterProperty;

	private static final String[] WATER_VOLUME_BARCHART_X_AXIS_LIST = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12" };

	public WaterConsumptionSimulator(TimeTemperatureSimulator timeTemperatureSimulator) {
		this.timeTemperatureSimulator = timeTemperatureSimulator;
		this.locationWaterConsumption = new LocationWaterConsumption();

		this.monthlyConsumption = new SimpleIntegerProperty[12];
		for (int i = 0; i < monthlyConsumption.length; i++) {
			this.monthlyConsumption[i] = new SimpleIntegerProperty();
		}
		this.totalWater = 0;
		this.totalWaterProperty = new SimpleStringProperty("0");
	}

	public void run() {
		int currentMonth = 0;
		int currentMonthConsumption = 0;
		int prevMin = 0;

		while (true) {
			int totalVolumePerHour = this.locationWaterConsumption.getVolumePerHourTotal();

			if (this.timeTemperatureSimulator.getMonth() != currentMonth) {
				currentMonth = this.timeTemperatureSimulator.getMonth();
				currentMonthConsumption = 0;
			} else {
				int currMin = this.timeTemperatureSimulator.getMinute();
				int timeDelta = currMin - prevMin;
				if (timeDelta < 0) {
					timeDelta += 60;
				}
				
				double deltaConsumption = 1.0 * totalVolumePerHour / 60 * timeDelta;
				currentMonthConsumption += deltaConsumption;
				totalWater += deltaConsumption;
				prevMin = currMin;

				monthlyConsumption[currentMonth].set(currentMonthConsumption);
				updateDisplayProperty();
				updateBarChart(currentMonth);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateDisplayProperty() {
		this.totalWaterProperty.set(String.valueOf(totalWater));
	}

	public void setVolumePerHour(Location location, int volumePerHour) {
		switch (location) {
		case North:
			locationWaterConsumption.setVolumePerHourNorth(volumePerHour);
			break;
		case South:
			locationWaterConsumption.setVolumePerHourSouth(volumePerHour);
			break;
		case West:
			locationWaterConsumption.setVolumePerHourWest(volumePerHour);
			break;
		case East:
			locationWaterConsumption.setVolumePerHourEast(volumePerHour);
			break;
		default:
			assert false;
		}
	}

	public int getVolumePerHour(Location location) {
		int volumePerHour = 0;
		switch (location) {
		case North:
			volumePerHour = locationWaterConsumption.getVolumePerHourNorth();
			break;
		case South:
			volumePerHour = locationWaterConsumption.getVolumePerHourSouth();
			break;
		case West:
			volumePerHour = locationWaterConsumption.getVolumePerHourWest();
			break;
		case East:
			volumePerHour = locationWaterConsumption.getVolumePerHourEast();
			break;
		default:
			assert false;
		}
		return volumePerHour;
	}

	public IntegerProperty[] monthlyConsumption() {
		return this.monthlyConsumption;
	}

	public void setBarChart(BarChart<Integer, Integer> barChart) {
		this.waterVolumeBarChart = barChart;
		initBarChart();
	}

	private void initBarChart() {
		ObservableList<XYChart.Series<Integer, Integer>> barChartData = FXCollections.observableArrayList();
		for (int i = 0; i < WATER_VOLUME_BARCHART_X_AXIS_LIST.length; i++) {
			XYChart.Series<Integer, Integer> monthData = new XYChart.Series(FXCollections.observableArrayList());
			monthData.dataProperty().setValue(
					FXCollections.observableArrayList(new XYChart.Data(WATER_VOLUME_BARCHART_X_AXIS_LIST[i], 0)));
			barChartData.add(monthData);
		}
		waterVolumeBarChart.setData(barChartData);
	}

	private void updateBarChart(int month) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				ObservableList<XYChart.Series<Integer, Integer>> barChartData = waterVolumeBarChart.getData();
				XYChart.Series<Integer, Integer> monthData = barChartData.get(month);
				monthData.setData(FXCollections.observableArrayList(
						new XYChart.Data(WATER_VOLUME_BARCHART_X_AXIS_LIST[month], monthlyConsumption[month].get())));
			}
		});

	}

	public StringProperty totalWaterProperty() {
		return this.totalWaterProperty;
	}
}
