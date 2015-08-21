package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Touch extends Command {

	public static Touch newInstance(String fileName) {
		return new Touch(String.format("touch %s", fileName));
	}

	private Touch(String commandStr) {
		super(commandStr);
	}

}
