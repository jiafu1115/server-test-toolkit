package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public final class CommandUtil {

	/**
	 * @param command
	 * @param keyWords
	 * @return String
	 */
	public static String addAllGrepKey(String command, String... keyWords) {
		if (keyWords.length == 0)
			return command;
		command = command + " |grep '" + keyWords[0] + "'";
		command = CommandUtil.addRemainGrepKey(command, keyWords);

		return command;
	}

	/**
	 * @param command
	 * @param keyWords
	 * @return String
	 */
	private static String addRemainGrepKey(String command, String... keyWords) {
		for (int i = 1; i < keyWords.length; i++) {
			command += "|grep '" + keyWords[i] + "'";
		}
		return command;
	}

	private CommandUtil() {
 	}

}
