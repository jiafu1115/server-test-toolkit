package com.googlecode.test.toolkit.server.ssh.performance;

public class MemoryData extends ComponentData {

	private static final String TYPE = "MEMORY";

	private int swpd;
	private int free;
	private int buff;
	private int cache;

	public MemoryData(int swpd, int free, int buff, int cache) {
		super(TYPE);
		this.swpd = swpd;
		this.free = free;
		this.buff = buff;
		this.cache = cache;
	}

	public int getSwpd() {
		return swpd;
	}

	public void setSwpd(int swpd) {
		this.swpd = swpd;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public int getBuff() {
		return buff;
	}

	public void setBuff(int buff) {
		this.buff = buff;
	}

	public int getCache() {
		return cache;
	}

	public void setCache(int cache) {
		this.cache = cache;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemoryData [type=");
		builder.append(type);
		builder.append(", swpd=");
		builder.append(swpd);
		builder.append(", free=");
		builder.append(free);
		builder.append(", buff=");
		builder.append(buff);
		builder.append(", cache=");
		builder.append(cache);
		builder.append("]");
		return builder.toString();
	}

}
