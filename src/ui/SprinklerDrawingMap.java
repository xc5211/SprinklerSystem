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
	private static double rowDiff;
	private static double colDiff;
	private static double rowDelta;
	private static double colDelta;
	private static int sprinklerCountMax;
	private static Coordinate[][] coordinateMap;
	private static Map<Location, List<Coordinate>> locationCoordinateMap;

	public static void setDrawingMapParameters(int mapWidth, int mapHeight, int sprinklerCount) {
		width = mapWidth;
		height = mapHeight;
		if (sprinklerCount % 2 == 0) {
			sprinklerCount += 1;
		}
		sprinklerCountMax = sprinklerCount;
		rowMax = sprinklerCount + 1;
		colMax = sprinklerCount + 1;
		rowDiff = height / rowMax;
		colDiff = width / colMax;
		rowDelta = Math.floor(rowDiff / 2);
		colDelta = Math.floor(colDiff / 2);
		initSprinklerCoordinateMap();
		initSprinklerLocationCoordinateMap();
	}

	public SprinklerDrawingMap(int mapWidth, int mapHeight, int sprinklerCount) {
		width = mapWidth;
		height = mapHeight;
		if (sprinklerCount % 2 == 0) {
			sprinklerCount += 1;
		}
		sprinklerCountMax = sprinklerCount;
		rowMax = sprinklerCount + 1;
		colMax = sprinklerCount + 1;
		rowDiff = height / rowMax;
		colDiff = width / colMax;
		rowDelta = Math.floor(rowDiff / 2);
		colDelta = Math.floor(colDiff / 2);
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

				double x = j * colDiff;
				double y = i * rowDiff;
				coordinateMap[i][j] = new Coordinate(x, y);
			}
		}
	}

	private static void initSprinklerLocationCoordinateMap() {
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

	private static void initSprinklerLocationMapNorth() {

		for (int i = 0; i < rowMax / 2; i++) {
			int left = getLeftIndexBound(i);
			int right = getRightIndexBound(i);

			while (left <= right) {
				Coordinate coordinate = coordinateMap[i][left];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.North).add(coordinate);

				coordinate = coordinateMap[i][right];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.North).add(coordinate);

				coordinateMap[i][left++] = null;
				coordinateMap[i][right--] = null;
			}
		}
	}

	private static void initSprinklerLocationMapSouth() {

		for (int i = rowMax - 1; i > (rowMax - 1) / 2; i--) {
			int left = getLeftIndexBound(i);
			int right = getRightIndexBound(i);

			while (left <= right) {
				Coordinate coordinate = coordinateMap[i][left];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.South).add(coordinate);

				coordinate = coordinateMap[i][right];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.South).add(coordinate);

				coordinateMap[i][left++] = null;
				coordinateMap[i][right--] = null;
			}
		}
	}

	private static void initSprinklerLocationMapWest() {

		for (int j = 0; j < colMax / 2; j++) {
			int up = getUpIndexBound(j);
			int down = getDownIndexBound(j);

			while (up <= down && up >= 0) {
				Coordinate coordinate = coordinateMap[up][j];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.West).add(coordinate);

				coordinate = coordinateMap[down][j];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.West).add(coordinate);

				coordinateMap[up++][j] = null;
				coordinateMap[down--][j] = null;
			}
		}
	}

	private static void initSprinklerLocationMapEast() {

		for (int j = colMax - 1; j > (colMax - 1) / 2; j--) {
			int up = getUpIndexBound(j);
			int down = getDownIndexBound(j);

			while (up <= down && up >= 0) {
				Coordinate coordinate = coordinateMap[up][j];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.East).add(coordinate);

				coordinate = coordinateMap[down][j];
				coordinate.setX(Math.floor(coordinate.getX() + colDelta));
				coordinate.setY(Math.floor(coordinate.getY() + rowDelta));
				locationCoordinateMap.get(Location.East).add(coordinate);

				coordinateMap[up++][j] = null;
				coordinateMap[down--][j] = null;
			}
		}
	}

	private static int getUpIndexBound(int colNum) {
		for (int i = 0; i < rowMax / 2; i++) {
			if (coordinateMap[i][colNum] != null) {
				return i;
			}
		}
		return -1;
	}

	private static int getDownIndexBound(int colNum) {
		for (int i = rowMax - 1; i >= rowMax / 2; i--) {
			if (coordinateMap[i][colNum] != null) {
				return i;
			}
		}
		return -1;
	}

	private static int getLeftIndexBound(int rowNum) {
		for (int i = 0; i < colMax / 2; i++) {
			if (coordinateMap[rowNum][i] == null) {
				return i + 1;
			}
		}
		return -1;
	}

	private static int getRightIndexBound(int rowNum) {
		for (int i = colMax - 1; i >= colMax / 2; i--) {
			if (coordinateMap[rowNum][i] == null) {
				return i - 1;
			}
		}
		return -1;
	}

	public static boolean hasNextCoordinate(Location location) {
		return !locationCoordinateMap.get(location).isEmpty();
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
