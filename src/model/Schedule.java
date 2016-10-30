package model;

public class Schedule {

	private int startTime;
	private int endTime;
	private int volumePerHour;

	public Schedule() {
		this.startTime = 7;
		this.endTime = 10;
		this.volumePerHour = 1;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public void setVolumePerHour(int volumePerHour) {
		this.volumePerHour = volumePerHour;
	}

	public int getStartTime() {
		return this.startTime;
	}

	public int getEndTime() {
		return this.endTime;
	}

	public int getVolumePerHour() {
		return this.volumePerHour;
	}
}
