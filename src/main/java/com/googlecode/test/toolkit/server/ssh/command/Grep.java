package com.googlecode.test.toolkit.server.ssh.command;

import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Grep extends Command {

	private static final String GREP_COMMAND_FORMAT = "grep '%s' %s";

	public static Grep newInstance(String path, String atLeastOneKeyword,
			String... otherKeywords) {
		return new Grep(toCommandStr(path, atLeastOneKeyword, otherKeywords));
	}

	private static String toCommandStr(String path, String keyWord,
			String... otherKeyWords) {
 		ValidationUtil.checkString(keyWord,path);

		String commandStr = String.format(GREP_COMMAND_FORMAT, keyWord, path);
		return CommandUtil.addAllGrepKey(commandStr, otherKeyWords);

	}

	private Grep(String commandStr) {
		super(commandStr);
	}

}
