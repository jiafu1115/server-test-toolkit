package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Ntpdate extends Command {

	private static final String COMMAND_FORMAT = "/etc/init.d/ntpd stop;ntpdate %s; /etc/init.d/ntpd start";

	public static Ntpdate newInstance(String ntpServerIp) {
		String commandStr = String.format(COMMAND_FORMAT, ntpServerIp.trim());
		return new Ntpdate(commandStr);
	}

	private Ntpdate(String commandStr) {
		super(commandStr);
	}

}
