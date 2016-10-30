package model;

public enum Location {

	North("N"), South("S"), West("W"), East("E");

	private String val;

	Location(String val) {
		this.val = val;
	}

	public String getVal() {
		return this.val;
	}
}
