package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TimeTemperatureSimulator extends Thread {

	private StringProperty timeProperty = new SimpleStringProperty();
	private StringProperty temperatureProperty = new SimpleStringProperty();

	private int hour = 6;
	private int minute = 0;
	private int second = 0;

	private double temperature = 60;

	public void run() {
		while (true) {
			try {
				if (second++ == 60) {
					second = 0;
					minute++;
				}
				if (minute == 60) {
					minute = 60;
					hour++;
				}
				if (hour == 24) {
					hour = 0;
				}

				timeProperty.set(String.format("%2d", hour) + ":" + String.format("%2d", minute) + ":"
						+ String.format("%2d", second));
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

	public int getSecond() {
		return this.second;
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
