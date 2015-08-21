package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Command {

	private String commandStr;

	public Command(String commandStr) {
		super();
		this.commandStr = commandStr;
	}

	public String getCommandStr() {
		return commandStr;
	}

	public void setCommandStr(String commandStr) {
		this.commandStr = commandStr;
	}

	public String toString() {
		return commandStr;
	}

}
