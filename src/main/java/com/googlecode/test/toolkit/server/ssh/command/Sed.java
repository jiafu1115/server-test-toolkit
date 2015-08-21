package com.googlecode.test.toolkit.server.ssh.command;

import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Sed extends Command {

 	public static Sed newInstanceForGetLineNumbersWithKey(String filePath,
			String keyword) {
		ValidationUtil.checkString(filePath);

		String commandStr = String.format("sed -n '/%s/=' %s ", keyword,
				filePath);
		return new Sed(commandStr);
	}

	/**
	 * sed -e '3c\newContent' /usr/1.conf >c.conf
	 * @param filePath
	 * @param line
	 * @param newContentForLine
	 * @param tmpFilePath
	 * @return sed command
	 */
	public static Sed newInstanceForModifyFile(String filePath, int line,
			String newContentForLine, String tmpFilePath) {
		ValidationUtil.checkNull(filePath, tmpFilePath);

		String commandStr = String.format("sed -e '%dc\\%s' %s >%s ", line,
				newContentForLine, filePath, tmpFilePath);
		return new Sed(commandStr);
	}
	
	/**
	 * 
	 * sed "s/originalContent/newContent/g" file
	 * @param filePath
	 * @param originalContent
	 * @param newContent
	 * @param tmpFilePath
	 * @return
	 */
	public static Sed newInstanceForModifyFile(String filePath, String originalContent,
			String newContent, String tmpFilePath) {
		ValidationUtil.checkNull(filePath, tmpFilePath);

		String commandStr = String.format("sed \"s/%s/%s/g\" %s >%s ", originalContent,newContent, filePath, tmpFilePath);
		return new Sed(commandStr);
	}
	

	public static Sed newInstanceForGetContentBetweenLines(String filePath,
			int startLine, int endLine, String... keywords) {
		ValidationUtil.checkString(filePath);

		String commandStr = String.format("sed -n '%d,%dp' %s", startLine,
				endLine, filePath);
		commandStr = CommandUtil.addAllGrepKey(commandStr, keywords);

		return new Sed(commandStr);
	}

	/**
	 * command: (1) sed -n '$=' /opt/webex/idmap/log/result.log (2) wc -l
	 *
	 * @param filePath
	 * @return sed command
	 */
	public static Sed newInstanceForGetLineNumber(String filePath) {
		ValidationUtil.checkString(filePath);

		String commandStr = String.format("sed -n '$=' %s", filePath);
		return new Sed(commandStr);
	}

	private Sed(String commandStr) {
		super(commandStr);
	}

}
