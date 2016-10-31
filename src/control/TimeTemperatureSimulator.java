package control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TimeTemperatureSimulator extends Thread {

	private StringProperty yearProperty = new SimpleStringProperty("0");
	private StringProperty monthProperty = new SimpleStringProperty("0");
	private StringProperty dayProperty = new SimpleStringProperty("0");
	private StringProperty timeProperty = new SimpleStringProperty();
	private StringProperty temperatureProperty = new SimpleStringProperty();

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

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
					this.dayProperty.set("" + day);
				}
				if (day == 30) {
					day = 0;
					month++;
					this.monthProperty.set("" + month);
				}
				if (month == 12) {
					month = 0;
					year++;
					this.yearProperty.set("" + year);
				}

				timeProperty.set(String.format("%2d", hour) + ":" + String.format("%2d", minute));

				// TODO: figure out temperature vs time equation
				// if(clock.minuteSum() >= 0 && clock.minuteSum() < 360){
				// temperature = (float) (55.0 - (float)clock.minuteSum()/24.0);
				// }
				// if(clock.minuteSum() >= 360 && clock.minuteSum() < 840){
				// temperature = (float) (10.0 + (float)clock.minuteSum()/12.0);
				// }
				// if(clock.minuteSum() >= 840 && clock.minuteSum() < 1440){
				// temperature = (float) (115.0- (float)clock.minuteSum()/24.0);
				// }
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

	public int getMonth() {
		return this.month;
	}

	public int getYear() {
		return this.year;
	}

	public double getTemperature() {
		return this.temperature;
	}

	public StringProperty yearProperty() {
		return this.yearProperty;
	}

	public StringProperty monthProperty() {
		return this.monthProperty;
	}

	public StringProperty dayProperty() {
		return this.dayProperty;
	}

	public StringProperty timeProperty() {
		return this.timeProperty;
	}

	public StringProperty temperatureProperty() {
		return this.temperatureProperty;
	}
}
