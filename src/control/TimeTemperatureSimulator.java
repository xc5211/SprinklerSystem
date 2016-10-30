package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TimeTemperatureSimulator extends Thread {

	private StringProperty timeProperty = new SimpleStringProperty();
	private StringProperty temperatureProperty = new SimpleStringProperty();

	private int hour;
	private int minute;
	private int day;
	private int week;
	private int month;
	private int year;

	private double temperature = 60;

	public void run() {
		while (true) {
			try {
				minute += 6;
				if (minute == 60) {
					minute = 0;
					hour++;
				}
				if (hour == 24) {
					hour = 0;
					day++;
				}
				if (day == 7) {
					day = 0;
					week++;
				}
				if (week == 4) {
					week = 4;
					month++;
				}
				if (month == 12) {
					month = 0;
					year++;
				}

				timeProperty.set(String.format("%2d", hour) + ":" + String.format("%2d", minute));
				temperatureProperty.set(String.format("%.2f", temperature++ % 100));

				Thread.sleep(80);
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

	public int getDay() {
		return this.day;
	}

	public int getWeek() {
		return this.week;
	}

	public int getMonth() {
		return this.month;
	}

	public int getYear() {
		return this.year;
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
