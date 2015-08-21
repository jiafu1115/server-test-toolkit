package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Rm extends Command {

	private static final String RM_COMMAND_FORMAT = "rm -f %s";

	public static Rm newInstance(String deletePath) {
		String commandStr = String.format(RM_COMMAND_FORMAT, deletePath);
		return new Rm(commandStr);
	}

	private Rm(String commandStr) {
		super(commandStr);
	}

}
