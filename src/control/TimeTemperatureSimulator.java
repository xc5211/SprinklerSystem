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
					if (day++ == 30) {
						day = 0;
						if (month++ == 12) {
							month = 0;
							year++;
							this.yearProperty.set("" + year);
						}
						this.monthProperty.set("" + month);
					}
					this.dayProperty.set("" + day);
				}

				timeProperty.set(String.format("%2d", hour) + ":" + String.format("%2d", minute));

                // temperature has to be both 55 and 90 in a day(hour changes from 0~24)
				if(hour >= 0 && hour < 12){
					if(temperature > 96 ){
						temperature = (temperature - 0.1) ;
					} else if(temperature < 30){
						temperature = (temperature + 1) ;
					}
					else if(temperature < 90){
						temperature = (temperature + 0.6) ;
					} else {
					    temperature = (temperature+0.1) ;
					}
					
				} else if(hour >= 12 && hour < 24){
					if(temperature > 96){
						temperature = (temperature - 1) ;
					} else if(temperature < 30){
						temperature = (temperature + 1.2) ;
					}
					if(temperature > 55){
						temperature = (temperature - 0.5) ;
					} else{
					    temperature = (temperature- 0.2) ;
					}

				}
				temperatureProperty.set(String.format("%.2f", temperature % 100));

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
