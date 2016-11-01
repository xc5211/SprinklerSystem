package model;

public class LocationWaterConsumption {

	private int volumePerHourTotal; // Change this initial value for test use
	private int volumePerHourNorth;
	private int volumePerHourSouth;
	private int volumePerHourWest;
	private int volumePerHourEast;

	public void setVolumePerHourNorth(int volumePerHour) {
		this.volumePerHourTotal -= this.volumePerHourNorth;
		this.volumePerHourTotal += volumePerHour;
		this.volumePerHourNorth = volumePerHour;
	}

	public void setVolumePerHourSouth(int volumePerHour) {
		this.volumePerHourTotal -= this.volumePerHourSouth;
		this.volumePerHourTotal += volumePerHour;
		this.volumePerHourSouth = volumePerHour;
	}

	public void setVolumePerHourWest(int volumePerHour) {
		this.volumePerHourTotal -= this.volumePerHourWest;
		this.volumePerHourTotal += volumePerHour;
		this.volumePerHourWest = volumePerHour;
	}

	public void setVolumePerHourEast(int volumePerHour) {
		this.volumePerHourTotal -= this.volumePerHourEast;
		this.volumePerHourTotal += volumePerHour;
		this.volumePerHourEast = volumePerHour;
	}

	public int getTotalVolumePerHour() {
		return this.volumePerHourTotal;
	}
}
