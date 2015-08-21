package com.googlecode.test.toolkit.server.ssh.performance;

public class SystemData extends ComponentData {

	private static final String TYPE = "SYSTEM";

	private int in;
	private int cs;

	public SystemData(int in, int cs) {
		super(TYPE);
		this.in = in;
		this.cs = cs;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public int getCs() {
		return cs;
	}

	public void setCs(int cs) {
		this.cs = cs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SystemData [type=");
		builder.append(type);
		builder.append(", in=");
		builder.append(in);
		builder.append(", cs=");
		builder.append(cs);
		builder.append("]");
		return builder.toString();
	}

}
