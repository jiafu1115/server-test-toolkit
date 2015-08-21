package com.googlecode.test.toolkit.server.ssh.performance;

public class SwapData extends ComponentData {

	private static final String TYPE = "SWAP";

	private int si;
	private int so;

	public SwapData(int si, int so) {
		super(TYPE);
		this.si = si;
		this.so = so;
	}

	public int getSi() {
		return si;
	}

	public void setSi(int si) {
		this.si = si;
	}

	public int getSo() {
		return so;
	}

	public void setSo(int so) {
		this.so = so;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SwapData [type=");
		builder.append(type);
		builder.append(", si=");
		builder.append(si);
		builder.append(", so=");
		builder.append(so);
		builder.append("]");
		return builder.toString();
	}

}
