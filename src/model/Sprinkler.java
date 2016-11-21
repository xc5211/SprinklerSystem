package model;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sprinkler implements Interruptable {

	private String id;
	private Location location;
	private Schedule[] groupSchedule;
	private boolean isGroupScheduleSet;
	private Schedule[] individualSchedule;
	private boolean isIndividualScheduleSet;
	private boolean functional;
	private boolean onGroup;
	private boolean onIndividual;
	private boolean onTemperatureInterrupted;
	private boolean onUserInterrupted;

	private BooleanProperty functionalProperty;
	private BooleanProperty enableProperty;
	private BooleanProperty onProperty;
	private StringProperty forceInterruptProperty;

	public Sprinkler(Location location, int locationId, boolean functional) {
		this.location = location;
		this.id = locationId + location.getVal();
		this.groupSchedule = new Schedule[7];
		this.isGroupScheduleSet = false;
		this.individualSchedule = new Schedule[7];
		this.isIndividualScheduleSet = false;
		this.functional = functional;
		this.onGroup = false;
		this.onIndividual = false;
		this.onTemperatureInterrupted = false;
		this.onUserInterrupted = false;

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

	public Schedule[] getGroupSchedule() {
		return this.groupSchedule;
	}

	public Schedule[] getIndividualSchedule() {
		return this.individualSchedule;
	}

	public boolean isGroupScheduleSet() {
		return this.isGroupScheduleSet;
	}

	public boolean isIndividualScheduleSet() {
		return this.isIndividualScheduleSet;
	}

	public boolean isFunctional() {
		return this.functional;
	}

	public boolean isOnGroup() {
		return this.onGroup;
	}

	public boolean isOnIndividual() {
		return this.onIndividual;
	}

	public boolean isTemperatureInterrupted() {
		return this.onTemperatureInterrupted;
	}

	public boolean isUserInterrupted() {
		return this.onUserInterrupted;
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
		this.onTemperatureInterrupted = true;
		this.setUserInterfaceProperties();
	}

	@Override
	public void disableByTemperature() {
		if (!isFunctional()) {
			return;
		}
		this.onTemperatureInterrupted = true;
		this.setUserInterfaceProperties();
	}

	@Deprecated
	@Override
	public void enableByUser() {
		if (!isFunctional()) {
			return;
		}
		this.onGroup = true;

		this.onProperty.set(true);
		this.forceInterruptProperty.set("Disable");
	}

	@Deprecated
	@Override
	public void disableByUser() {
		if (!isFunctional()) {
			return;
		}
		this.onGroup = false;

		this.onProperty.set(false);
		this.forceInterruptProperty.set(" Enable");
	}

	@Override
	public void enableByUserGroup() {
		if (!isFunctional()) {
			return;
		}
		this.onGroup = true;
		this.setUserInterfaceProperties();
	}

	@Override
	public void disableByUserGroup() {
		if (!isFunctional()) {
			return;
		}
		this.onGroup = false;
		this.setUserInterfaceProperties();
	}

	@Override
	public void enableByUserIndividual() {
		if (!isFunctional()) {
			return;
		}
		this.onIndividual = true;
		this.setUserInterfaceProperties();
	}

	@Override
	public void disableByUserIndividual() {
		if (!isFunctional()) {
			return;
		}
		this.onIndividual = false;
		this.setUserInterfaceProperties();
	}

	@Override
	public void disableTemperatureInterrupt() {
		this.onTemperatureInterrupted = false;
		this.setUserInterfaceProperties();
	}

	@Override
	public void setGroupSchedule(int day, Schedule schedule) {
		this.groupSchedule[day] = schedule;

		for (Schedule sch : this.groupSchedule) {
			if (sch == null) {
				this.isGroupScheduleSet = false;
				break;
			}
			this.isGroupScheduleSet = true;
		}
	}

	@Override
	public void setIndividualSchedule(int day, Schedule schedule) {
		this.individualSchedule[day] = schedule;

		for (Schedule sch : this.individualSchedule) {
			if (sch == null) {
				this.isIndividualScheduleSet = false;
				break;
			}
			this.isIndividualScheduleSet = true;
		}
	}

	private void setUserInterfaceProperties() {
		if (this.onTemperatureInterrupted || this.onGroup || this.onIndividual) {
			if (!Platform.isFxApplicationThread()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						onProperty.set(true);
						forceInterruptProperty.setValue("Disable");
					}
				});
			}
		} else {
			if (!Platform.isFxApplicationThread()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						onProperty.set(false);
						forceInterruptProperty.setValue(" Enable");
					}
				});
			}
		}
	}
}
