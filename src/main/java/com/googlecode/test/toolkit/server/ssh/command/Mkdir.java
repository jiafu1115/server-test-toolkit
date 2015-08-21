package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Mkdir extends Command {

	private static final String MKDIR_COMMAND_FORMAT = "mkdir -p %s";

	public static Mkdir newInstance(String filePath) {
		String commandStr = String.format(MKDIR_COMMAND_FORMAT, filePath);
		return new Mkdir(commandStr);
	}

	private Mkdir(String commandStr) {
		super(commandStr);
	}

}
