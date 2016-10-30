package control;

import java.util.HashMap;
import java.util.Map;

import model.Location;

public class LocationSprinklerIdManager {

	private static Map<Location, Integer> locationMap;

	static {
		locationMap = new HashMap<Location, Integer>();
		locationMap.put(Location.North, 0);
		locationMap.put(Location.South, 0);
		locationMap.put(Location.West, 0);
		locationMap.put(Location.East, 0);
	}

	public static int getNextIdNumber(Location location) {
		int id = locationMap.get(location);
		locationMap.put(location, id + 1);
		return id;
	}
}
