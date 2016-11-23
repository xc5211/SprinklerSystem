package control;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TimeTemperatureSimulator extends Thread {

	private StringProperty yearProperty = new SimpleStringProperty("0");
	private StringProperty monthProperty = new SimpleStringProperty("0");
	private StringProperty dayProperty = new SimpleStringProperty("0");
	private StringProperty timeProperty = new SimpleStringProperty();
	private StringProperty dayOfWeekProperty = new SimpleStringProperty("Mon");
	private StringProperty temperatureProperty = new SimpleStringProperty();

	private int year;
	private int month;
	private int day;
	private int dayOfWeek;
	private int hour;
	private int minute;

	private double temperature = 30;

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
					if (dayOfWeek++ == 6) {
						dayOfWeek = 0;
					}

					if (day++ == 30) {
						day = 0;
						if (month++ == 12) {
							month = 0;
							year++;
						}
					}
				}

				temperature = simulateCurrentTemperature(temperature);
				setUiProperties();

				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void setUiProperties() {
		if (Platform.isFxApplicationThread()) {
			setProperties();
		} else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					setProperties();
				}
			});
		}
	}

	private void setProperties() {
		setDayOfWeek();
		this.yearProperty.set(String.valueOf(year));
		this.monthProperty.set(String.valueOf(month));
		this.dayProperty.set(String.valueOf(day));
		this.timeProperty.set(String.format("%2d", hour) + ":" + String.format("%2d", minute));
		this.temperatureProperty.set(String.format("%.2f", temperature));
	}

	private void setDayOfWeek() {
		switch (dayOfWeek) {
		case 0:
			dayOfWeekProperty.set("Mon");
			break;
		case 1:
			dayOfWeekProperty.set("Tue");
			break;
		case 2:
			dayOfWeekProperty.set("Wed");
			break;
		case 3:
			dayOfWeekProperty.set("Thu");
			break;
		case 4:
			dayOfWeekProperty.set("Fri");
			break;
		case 5:
			dayOfWeekProperty.set("Sat");
			break;
		case 6:
			dayOfWeekProperty.set("Sun");
			break;
		default:
			assert false;
		}
	}

	// temperature has to be both 55 and 90 in a day(hour changes from 0~24)
	private double simulateCurrentTemperature(double previousTemp) {
		double temperature = previousTemp;
		if (hour >= 6 && hour < 18) {
			if (temperature > 108) {
				temperature = (temperature - 0.1);
			} else if (temperature < 30) {
				temperature = (temperature + 0.1);
			} else if (temperature < 90) {
				temperature = (temperature + 0.6);
			} else {
				temperature = (temperature + 0.1);
			}
		} else if ((hour >= 18 && hour < 24) || hour > 0) {
			if (temperature > 108) {
				temperature = (temperature - 1);
			} else if (temperature < 30) {
				temperature = (temperature + 1.2);
			}
			if (temperature > 55) {
				temperature = (temperature - 0.5);
			} else {
				temperature = (temperature - 0.2);
			}
		}
		return temperature;
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

	public int getDayOfWeek() {
		return this.dayOfWeek;
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

	public StringProperty dayOfWeekProperty() {
		return this.dayOfWeekProperty;
	}

	public StringProperty temperatureProperty() {
		return this.temperatureProperty;
	}

}
