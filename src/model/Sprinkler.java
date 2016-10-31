package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sprinkler implements Interruptable {

	private String id;
	private Location location;
	private Schedule groupSchedule;
	private Schedule individualSchedule;
	private boolean functional;
	private boolean on;
	private boolean systemInterrupted;
	private boolean userInterrupted;

	private BooleanProperty functionalProperty;
	private BooleanProperty onProperty;
	private StringProperty forceInterruptProperty;

	public Sprinkler(Location location, int locationId) {
		this.location = location;
		this.id = locationId + location.getVal();
		this.groupSchedule = new Schedule();
		this.individualSchedule = new Schedule();
		this.functional = false;
		this.on = false;
		this.systemInterrupted = false;
		this.userInterrupted = false;

		this.functionalProperty = new SimpleBooleanProperty(false);
		this.onProperty = new SimpleBooleanProperty(false);
		this.forceInterruptProperty = new SimpleStringProperty(" Enable");
	}

	public String getId() {
		return this.id;
	}

	public Location getLocation() {
		return this.location;
	}

	public Schedule getGroupSchedule() {
		return this.groupSchedule;
	}

	public Schedule getIndividualSchedule() {
		return this.individualSchedule;
	}

	public boolean isFunctional() {
		return this.functional;
	}

	public boolean isOn() {
		return this.on;
	}

	public boolean isSystemInterrupted() {
		return this.systemInterrupted;
	}

	public boolean isUserInterrupted() {
		return this.userInterrupted;
	}

	public BooleanProperty functionalProperty() {
		return this.functionalProperty;
	}

	public BooleanProperty onProperty() {
		return this.onProperty;
	}

	public StringProperty forceInterruptProperty() {
		return this.forceInterruptProperty;
	}

	@Override
	public void enableByTemperature() {
		this.functional = true;
		this.on = true;

		this.functionalProperty.set(true);
		this.onProperty.set(true);
		this.forceInterruptProperty.set("Disable");
	}

	@Override
	public void disableByTemperature() {
		this.functional = false;
		this.on = false;

		this.functionalProperty.set(false);
		this.onProperty.set(false);
		this.forceInterruptProperty.set(" Enable");
	}

	@Override
	public void enableByUser() {
		this.functional = true;
		this.on = true;

		this.functionalProperty.set(true);
		this.onProperty.set(true);
		this.forceInterruptProperty.set("Disable");
	}

	@Override
	public void disableByUser() {
		this.functional = true;
		this.on = false;

		this.functionalProperty.set(true);
		this.onProperty.set(false);
		this.forceInterruptProperty.set(" Enable");
	}

	@Override
	public void setGroupSchedule(Schedule schedule) {
		this.groupSchedule = schedule;
	}

	@Override
	public void setIndividualSchedule(Schedule schedule) {
		this.individualSchedule = schedule;
	}
}
