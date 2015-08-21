package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Ls extends Command {

	private static final String LS_COMMAND_FORMAT = "ls %s";

	public static Ls newInstanceLsPath(String filePath) {
		String commandStr = String.format(LS_COMMAND_FORMAT, filePath);
		return new Ls(commandStr);
	}

	private Ls(String commandStr) {
		super(commandStr);
	}

}
