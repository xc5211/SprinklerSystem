package control;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.Location;
import model.LocationWaterConsumption;

public class WaterConsumptionSimulator extends Thread {

	private TimeTemperatureSimulator timeTemperatureSimulator;
	private BarChart<Integer, Integer> waterVolumeBarChart;
	private int currentMonth;
	private LocationWaterConsumption locationWaterConsumption;
	private int prevMin;

	private IntegerProperty[] monthlyConsumption;

	private static final String[] WATER_VOLUME_BARCHART_X_AXIS_LIST = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12" };

	public WaterConsumptionSimulator(TimeTemperatureSimulator timeTemperatureSimulator) {
		this.timeTemperatureSimulator = timeTemperatureSimulator;
		this.currentMonth = timeTemperatureSimulator.getMonth();
		this.locationWaterConsumption = new LocationWaterConsumption();

		this.monthlyConsumption = new SimpleIntegerProperty[12];
		for (int i = 0; i < monthlyConsumption.length; i++) {
			this.monthlyConsumption[i] = new SimpleIntegerProperty();
		}
	}

	public void run() {
		int currentMonthConsumption = 0;

		while (true) {
			int totalVolumePerHour = this.locationWaterConsumption.getTotalVolumePerHour();
			if (totalVolumePerHour == 0) {
				continue;
			}

			if (this.timeTemperatureSimulator.getMonth() != currentMonth) {
				currentMonth = this.timeTemperatureSimulator.getMonth();
				currentMonthConsumption = 0;
			} else {
				int currMin = this.timeTemperatureSimulator.getMinute();
				int timeDelta = currMin - prevMin;
				if (timeDelta < 0) {
					timeDelta += 60;
				}
				currentMonthConsumption += 1.0 * totalVolumePerHour / 60 * timeDelta;
				prevMin = currMin;

				monthlyConsumption[currentMonth].set(currentMonthConsumption);
				updateBarChart();
			}

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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

		if (volumePerHour != 0) {
			prevMin = this.timeTemperatureSimulator.getMinute();
		}
	}

	public IntegerProperty[] monthlyConsumption() {
		return this.monthlyConsumption;
	}

	public void setBarChart(BarChart<Integer, Integer> barChart) {
		this.waterVolumeBarChart = barChart;
	}

	private void updateBarChart() {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				ObservableList<XYChart.Series<Integer, Integer>> barChartData = FXCollections.observableArrayList();
				for (int i = 0; i < WATER_VOLUME_BARCHART_X_AXIS_LIST.length; i++) {
					XYChart.Series<Integer, Integer> monthData = new XYChart.Series(
							FXCollections.observableArrayList());
					monthData.dataProperty().setValue(FXCollections.observableArrayList(
							new XYChart.Data(WATER_VOLUME_BARCHART_X_AXIS_LIST[i], monthlyConsumption[i].get())));
					barChartData.add(monthData);
				}
				waterVolumeBarChart.setData(barChartData);
			}
		});

	}
}
