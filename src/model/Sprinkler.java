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
	private BooleanProperty enableProperty;
	private BooleanProperty onProperty;
	private StringProperty forceInterruptProperty;

	public Sprinkler(Location location, int locationId, boolean functional) {
		this.location = location;
		this.id = locationId + location.getVal();
		this.groupSchedule = new Schedule();
		this.individualSchedule = new Schedule();
		this.functional = functional;
		this.on = false;
		this.systemInterrupted = false;
		this.userInterrupted = false;

		this.functionalProperty = new SimpleBooleanProperty(functional);
		this.enableProperty = new SimpleBooleanProperty(!functional);
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
	
	public BooleanProperty enableProperty() {
		return this.enableProperty;
	}

	public BooleanProperty onProperty() {
		return this.onProperty;
	}

	public StringProperty forceInterruptProperty() {
		return this.forceInterruptProperty;
	}

	@Override
	public void enableByTemperature() {
		if (!isFunctional()) {
			return;
		}
		this.on = true;

		this.onProperty.set(true);
		this.forceInterruptProperty.set("Disable");
	}

	@Override
	public void disableByTemperature() {
		if (!isFunctional()) {
			return;
		}
		this.on = false;

		this.onProperty.set(false);
		this.forceInterruptProperty.set(" Enable");
	}

	@Override
	public void enableByUser() {
		if (!isFunctional()) {
			return;
		}
		this.on = true;

		this.onProperty.set(true);
		this.forceInterruptProperty.set("Disable");
	}

	@Override
	public void disableByUser() {
		if (!isFunctional()) {
			return;
		}
		this.on = false;

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
