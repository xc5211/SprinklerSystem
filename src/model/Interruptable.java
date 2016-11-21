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

	public void disableTemperatureInterrupt();

	/**
	 * Set the group schedule for "this" sprinkler. It takes two arguments,
	 * "day" and "schedule". "day" takes seven values, 0 through 6. 0 represents
	 * Monday, 1 represents Tuesday, etc.
	 * 
	 * @param day
	 *            the specific day an which the schedule targets.
	 * @param schedule
	 *            the group schedule.
	 */
	public void setGroupSchedule(int day, Schedule schedule);

	/**
	 * Set the individual schedule for "this" sprinkler.It takes two arguments,
	 * "day" and "schedule". "day" takes seven values, 0 through 6. 0 represents
	 * Monday, 1 represents Tuesday, etc.
	 * 
	 * @param day
	 *            the specific day an which the schedule targets.
	 * @param schedule
	 *            the individual schedule.
	 */
	public void setIndividualSchedule(int day, Schedule schedule);

}
