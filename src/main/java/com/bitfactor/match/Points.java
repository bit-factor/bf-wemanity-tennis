package com.bitfactor.match;

public enum Points {
	LOVE("Love"),
	FIFTEEN("Fifteen"),
	THIRTY("Thirty"),
	FORTY("Forty");

	private final String name;
	private static Points[] vals = values();

	private Points(String n) {
		this.name = n;
	}

	public Points next()
	{
		return vals[(this.ordinal()+1) % vals.length];
	}

	@Override
	public String toString() {
		return this.name;
	}
}
