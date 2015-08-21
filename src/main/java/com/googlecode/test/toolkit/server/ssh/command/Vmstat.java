package com.googlecode.test.toolkit.server.ssh.command;



/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Vmstat extends Command {

	private static final String VMSTAT_COMMAND_FORMAT = "vmstat";

 	public static Vmstat newInstance() {
		return new Vmstat(VMSTAT_COMMAND_FORMAT);
	}

	private Vmstat(String commandStr) {
		super(commandStr);
	}


}
