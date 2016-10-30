package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TimeTemperatureSimulator extends Thread {

	private StringProperty timeProperty = new SimpleStringProperty();
	private StringProperty temperatureProperty = new SimpleStringProperty();

	private int hour;
	private int minute;

	private double temperature = 60;

	public void run() {
		while (true) {
			try {
				if (minute++ == 60) {
					minute = 0;
					hour++;
				}
				if (hour == 24) {
					hour = 0;
				}

				timeProperty.set(String.format("%2d", hour) + ":" + String.format("%2d", minute));
				temperatureProperty.set(String.format("%.2f", temperature++ % 100));

				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public double getTemperature() {
		return this.temperature;
	}

	public StringProperty getTimeStringProperty() {
		return this.timeProperty;
	}

	public StringProperty getTemperatureProperty() {
		return this.temperatureProperty;
	}
}
