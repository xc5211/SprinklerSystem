package model;

public interface Interruptable {

	public void enableByTemperature();

	public void disableByTemperature();

	@Deprecated
	public void enableByUser();

	@Deprecated
	public void disableByUser();

	public void enableByUserGroup();

	public void disableByUserGroup();

	public void enableByUserIndividual();

	public void disableByUserIndividual();

	public void setGroupSchedule(Schedule schedule);

	public void setIndividualSchedule(Schedule schedule);

}
