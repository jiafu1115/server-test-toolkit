package com.googlecode.test.toolkit.server.ssh.performance;

public abstract class ComponentData {

	protected String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	protected ComponentData(String type) {
		super();
		this.type = type;
	}

}
