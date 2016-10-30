package model;

public enum Location {

	North(0), South(1), West(2), East(3);

	private int val;

	Location(int val) {
		this.val = val;
	}

	public int getVal() {
		return this.val;
	}
}
