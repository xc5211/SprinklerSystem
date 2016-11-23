package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Location;

public class SprinklerDrawingMap {

	private static int width;
	private static int height;
	private static int rowMax;
	private static int colMax;
	private static int rowDiff;
	private static int colDiff;
	private static int sprinklerCountMax;
	private static Coordinate[][] coordinateMap;
	private static Map<Location, List<Coordinate>> locationCoordinateMap;

	public SprinklerDrawingMap(int mapWidth, int mapHeight, int sprinklerCount) {
		width = mapWidth;
		height = mapHeight;
		sprinklerCountMax = sprinklerCount;
		rowMax = sprinklerCount + 1;
		colMax = sprinklerCount + 1;
		rowDiff = height / rowMax;
		colDiff = width / colMax;
		initSprinklerCoordinateMap();
		initSprinklerLocationCoordinateMap();
	}

	private static void initSprinklerCoordinateMap() {
		coordinateMap = new Coordinate[rowMax][colMax];

		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < colMax; j++) {
				// Ignore anti/diagonal coordinates
				if (i == j || i + j == sprinklerCountMax) {
					continue;
				}

				int x = j * colDiff;
				int y = i * rowDiff;
				coordinateMap[i][j] = new Coordinate(x, y);
			}
		}
	}

	private void initSprinklerLocationCoordinateMap() {
		locationCoordinateMap = new HashMap<Location, List<Coordinate>>();
		locationCoordinateMap.put(Location.North, new ArrayList<Coordinate>());
		locationCoordinateMap.put(Location.South, new ArrayList<Coordinate>());
		locationCoordinateMap.put(Location.West, new ArrayList<Coordinate>());
		locationCoordinateMap.put(Location.East, new ArrayList<Coordinate>());

		initSprinklerLocationMapNorth();
		initSprinklerLocationMapSouth();
		initSprinklerLocationMapWest();
		initSprinklerLocationMapEast();
	}

	private void initSprinklerLocationMapNorth() {

		for (int i = 0; i < rowMax / 2; i++) {
			int left = getLeftBound(i);
			int right = getRightBound(i);

			while (left <= right) {
				locationCoordinateMap.get(Location.North).add(coordinateMap[i][left]);
				locationCoordinateMap.get(Location.North).add(coordinateMap[i][right]);
				coordinateMap[i][left++] = null;
				coordinateMap[i][right--] = null;
			}
		}
	}

	private void initSprinklerLocationMapSouth() {

		for (int i = rowMax - 1; i > (rowMax - 1) / 2; i--) {
			int left = getLeftBound(i);
			int right = getRightBound(i);

			while (left <= right) {
				locationCoordinateMap.get(Location.South).add(coordinateMap[i][left]);
				locationCoordinateMap.get(Location.South).add(coordinateMap[i][right]);
				coordinateMap[i][left++] = null;
				coordinateMap[i][right--] = null;
			}
		}
	}

	private void initSprinklerLocationMapWest() {

		for (int j = 0; j < colMax / 2; j++) {
			int up = getUpBound(j);
			int down = getDownBound(j);

			while (up <= down && up >= 0) {
				locationCoordinateMap.get(Location.West).add(coordinateMap[up][j]);
				locationCoordinateMap.get(Location.West).add(coordinateMap[down][j]);
				coordinateMap[up++][j] = null;
				coordinateMap[down--][j] = null;
			}
		}
	}

	private void initSprinklerLocationMapEast() {

		for (int j = colMax - 1; j > (colMax - 1) / 2; j--) {
			int up = getUpBound(j);
			int down = getDownBound(j);

			while (up <= down && up >= 0) {
				locationCoordinateMap.get(Location.East).add(coordinateMap[up][j]);
				locationCoordinateMap.get(Location.East).add(coordinateMap[down][j]);
				coordinateMap[up++][j] = null;
				coordinateMap[down--][j] = null;
			}
		}
	}

	private int getUpBound(int colNum) {
		for (int i = 0; i < rowMax / 2; i++) {
			if (coordinateMap[i][colNum] != null) {
				return i;
			}
		}
		return -1;
	}

	private int getDownBound(int colNum) {
		for (int i = rowMax - 1; i >= rowMax / 2; i--) {
			if (coordinateMap[i][colNum] != null) {
				return i;
			}
		}
		return -1;
	}

	private int getLeftBound(int rowNum) {
		for (int i = 0; i < colMax / 2; i++) {
			if (coordinateMap[rowNum][i] == null) {
				return i + 1;
			}
		}
		return -1;
	}

	private int getRightBound(int rowNum) {
		for (int i = colMax - 1; i >= colMax / 2; i--) {
			if (coordinateMap[rowNum][i] == null) {
				return i - 1;
			}
		}
		return -1;
	}

	public static boolean hasNextCoordinate(Location location) {
		return locationCoordinateMap.get(location).isEmpty();
	}

	public static Coordinate getNextCoordinate(Location location) {
		return locationCoordinateMap.get(location).remove(0);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rowMax; i++) {
			for (int j = 0; j < colMax; j++) {
				sb.append(coordinateMap[i][j]);
				sb.append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	// For test use
	public static void main(String[] args) {
		SprinklerDrawingMap sdm = new SprinklerDrawingMap(410, 250, 5);
		System.out.println(sdm);
		System.out.println();
		System.out.println("North: " + locationCoordinateMap.get(Location.North));
		System.out.println("South: " + locationCoordinateMap.get(Location.South));
		System.out.println("West:  " + locationCoordinateMap.get(Location.West));
		System.out.println("East:  " + locationCoordinateMap.get(Location.East));
	}
}
