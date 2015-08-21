package com.googlecode.test.toolkit.server.ssh.performance;

public class CpuData extends ComponentData {

	private static final String TYPE = "CPU";

	private int us;
	private int sy;
	private int id;
	private int wa;
	private int st;

	public CpuData(int us, int sy, int id, int wa, int st) {
		super(TYPE);
		this.us = us;
		this.sy = sy;
		this.id = id;
		this.wa = wa;
		this.st = st;
	}

	public int getUs() {
		return us;
	}

	public void setUs(int us) {
		this.us = us;
	}

	public int getSy() {
		return sy;
	}

	public void setSy(int sy) {
		this.sy = sy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWa() {
		return wa;
	}

	public void setWa(int wa) {
		this.wa = wa;
	}

	public int getSt() {
		return st;
	}

	public void setSt(int st) {
		this.st = st;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CpuData [type=");
		builder.append(type);
		builder.append(", us=");
		builder.append(us);
		builder.append(", sy=");
		builder.append(sy);
		builder.append(", id=");
		builder.append(id);
		builder.append(", wa=");
		builder.append(wa);
		builder.append(", st=");
		builder.append(st);
		builder.append("]");
		return builder.toString();
	}

}
