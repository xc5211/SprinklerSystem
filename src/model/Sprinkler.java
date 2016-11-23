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
	private boolean onTemperature;
	private boolean onForceInterrupted;
	private boolean temperatureInterrupted;
	private boolean forceInterrupted;
	private int interruptHour;

	private BooleanProperty functionalProperty;
	private BooleanProperty enableProperty;
	private BooleanProperty onProperty;
	private StringProperty forceInterruptProperty;

	public Sprinkler(Location location, int locationId, boolean functional) {
		this.location = location;
		this.id = locationId + location.value();
		this.groupSchedule = new Schedule[7];
		this.isGroupScheduleSet = false;
		this.individualSchedule = new Schedule[7];
		this.isIndividualScheduleSet = false;
		this.functional = functional;
		this.onGroup = false;
		this.onIndividual = false;
		this.onTemperature = false;
		this.onForceInterrupted = false;
		this.temperatureInterrupted = false;
		this.forceInterrupted = false;
		this.interruptHour = 0;

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

	public int getInterruptHour() {
		return this.interruptHour;
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

	public boolean isOnForceInterrupted() {
		return this.onForceInterrupted;
	}

	public boolean isTemperatureInterrupted() {
		return this.temperatureInterrupted;
	}

	public boolean isForceInterrupted() {
		return this.forceInterrupted;
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
	public void enableByForceInterrupt(int interruptHour) {
		if (!isFunctional()) {
			return;
		}
		this.interruptHour = interruptHour;

		this.onForceInterrupted = true;
		this.forceInterrupted = true;
		this.setUserInterfaceInterruptProperties(true, true);
	}

	@Override
	public void disableByForceInterrupt(int interruptHour) {
		if (!isFunctional()) {
			return;
		}
		this.interruptHour = interruptHour;

		this.onForceInterrupted = false;
		this.forceInterrupted = true;
		this.setUserInterfaceInterruptProperties(true, false);
	}

	@Override
	public void enableByTemperature() {
		if (!isFunctional()) {
			return;
		}
		this.onTemperature = true;
		this.temperatureInterrupted = true;
		this.setUserInterfaceProperties();
	}

	@Override
	public void disableByTemperature() {
		if (!isFunctional()) {
			return;
		}
		this.onTemperature = false;
		this.temperatureInterrupted = true;
		this.setUserInterfaceProperties();
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
	public void disableForceInterrupt() {
		this.onForceInterrupted = false;
		this.forceInterrupted = false;
		this.setUserInterfaceInterruptProperties(false, false);
	}

	@Override
	public void disableTemperatureInterrupt() {
		this.onTemperature = false;
		this.temperatureInterrupted = false;
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

	private void setUserInterfaceInterruptProperties(boolean buttonEnable, boolean on) {
		if (this.forceInterrupted) {
			String forceInterruptPropertyValue = onForceInterrupted ? "Disable" : "Enable";

			if (!Platform.isFxApplicationThread()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						onProperty.set(on);
						enableProperty.set(buttonEnable);
						forceInterruptProperty.setValue(forceInterruptPropertyValue);
					}
				});
			} else {
				onProperty.set(on);
				enableProperty.set(buttonEnable);
				forceInterruptProperty.setValue(forceInterruptPropertyValue);
			}
		}
	}

	private void setUserInterfaceProperties() {
		if (this.forceInterrupted) {
			return;
		}

		if (this.onTemperature || this.onGroup || this.onIndividual) {
			if (!Platform.isFxApplicationThread()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						onProperty.set(true);
						enableProperty.set(false);
						forceInterruptProperty.setValue("Disable");
					}
				});
			} else {
				onProperty.set(true);
				enableProperty.set(false);
				forceInterruptProperty.setValue("Disable");
			}
		} else {
			if (!Platform.isFxApplicationThread()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						onProperty.set(false);
						enableProperty.set(false);
						forceInterruptProperty.setValue(" Enable");
					}
				});
			} else {
				onProperty.set(false);
				enableProperty.set(false);
				forceInterruptProperty.setValue(" Enable");
			}
		}
	}
}
