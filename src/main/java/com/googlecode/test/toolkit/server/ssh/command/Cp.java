package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Cp extends Command {

	private static final String CP_COMMAND_FORMAT = "cp -f %s %s";

	public static Cp newInstance(String fromPath,String toPath) {
		String commandStr = String.format(CP_COMMAND_FORMAT, fromPath, toPath);
		return new Cp(commandStr);
	}

	private Cp(String commandStr) {
		super(commandStr);
 	}

 }
