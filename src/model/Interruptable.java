package model;

public interface Interruptable {

	public void enableByTemperature();

	public void disableByTemperature();

	public void enableByUser();

	public void disableByUser();

	public void setGroupSchedule(Schedule schedule);

	public void setIndividualSchedule(Schedule schedule);

}
