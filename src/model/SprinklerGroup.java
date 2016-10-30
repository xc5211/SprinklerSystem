package model;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SprinklerGroup {

	private Location location;
	private List<Sprinkler> sprinklers;
	private boolean enabled;

	private BooleanProperty enabledProperty;

	public SprinklerGroup(Location location, List<Sprinkler> sprinklers) {
		this.location = location;
		this.sprinklers = sprinklers;
		this.enabled = false;

		this.enabledProperty = new SimpleBooleanProperty();
	}

	public Location getLocation() {
		return this.location;
	}

	public List<Sprinkler> getSprinklers() {
		return this.sprinklers;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public BooleanProperty enabledProperty() {
		return this.enabledProperty;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		enabledProperty.set(enabled);
	}
}
