package com.googlecode.test.toolkit.server.ssh.performance;

public class IoData extends ComponentData {

	private static final String TYPE = "IO";

	private int bi;
	private int bo;

	public IoData(int bi, int bo) {
		super(TYPE);
		this.bi = bi;
		this.bo = bo;
	}

	public int getBi() {
		return bi;
	}

	public void setBi(int bi) {
		this.bi = bi;
	}

	public int getBo() {
		return bo;
	}

	public void setBo(int bo) {
		this.bo = bo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IoData [type=");
		builder.append(type);
		builder.append(", bi=");
		builder.append(bi);
		builder.append(", bo=");
		builder.append(bo);
		builder.append("]");
		return builder.toString();
	}

}
