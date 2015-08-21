package com.googlecode.test.toolkit.server.ssh.command;

import java.util.UUID;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public final class GroupCommandFactory {

	public static GroupCommands changeFile(String path, int lineNumber, String newContentForLine, String backupPath) {
		String tmpFileName = getRandomFileName(path);

		Command backupCmd = Cp.newInstance(path, backupPath);
		Command modifyFileToTmpCmd = Sed.newInstanceForModifyFile(path, lineNumber, newContentForLine,
				tmpFileName);
		Command overwriteCmd = Cp.newInstance(tmpFileName, path);
		Command deleteTmpCmd = Rm.newInstance(tmpFileName);

		GroupCommands groupCommands = GroupCommands.newInstance(backupCmd, modifyFileToTmpCmd, overwriteCmd,
				deleteTmpCmd);

		return groupCommands;
	}
 	
	public static GroupCommands changeFile(String editPath, String originalContent, String newContent, String backupPath) {
		String tmpFileName = getRandomFileName(editPath);

		Command backupCmd = Cp.newInstance(editPath, backupPath);
		Command modifyFileToTmpCmd = Sed.newInstanceForModifyFile(editPath, originalContent, newContent, backupPath);
		Command overwriteCmd = Cp.newInstance(tmpFileName, editPath);
		Command deleteTmpCmd = Rm.newInstance(tmpFileName);

		GroupCommands groupCommands = GroupCommands.newInstance(backupCmd, modifyFileToTmpCmd, overwriteCmd,
				deleteTmpCmd);

		return groupCommands;
	}

	private static String getRandomFileName(String path) {
		return UUID.randomUUID().toString() + path.substring(path.lastIndexOf("."));
	}

	private GroupCommandFactory() {
	}

}
