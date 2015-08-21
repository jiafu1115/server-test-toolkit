package com.googlecode.test.toolkit.server.ssh.command;

import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class GroupCommands extends Command {

  	private static String toCommandStr(Command atLeastOneCommand, Command... otherCommands) {
		ValidationUtil.checkNull(atLeastOneCommand);

		StringBuilder commandStr = new StringBuilder(atLeastOneCommand.toString());
		for (Command otherCommand : otherCommands) {
			commandStr.append(";");
			commandStr.append(otherCommand.toString());
		}

		return commandStr.toString();
	}

	public static GroupCommands newInstance(Command atLeastOneCommand, Command... otherCommands) {
		return new GroupCommands(atLeastOneCommand, otherCommands);
	}

	private GroupCommands(Command atLeastOneCommand, Command... otherCommands) {
		super(toCommandStr(atLeastOneCommand, otherCommands));
	}

}
