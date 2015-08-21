package com.googlecode.test.toolkit.server.ssh.command;

/**
 * @author fu.jian date Jul 26, 2012
 */
public class Date extends Command {

    private static final String DATE_COMMAND_FORMAT_COMMON = "date";
    private static final String DATE_COMMAND_FORMAT_SECONDS = "date +%s";
    private static final String DATE_COMMAND_FORMAT_FORMAT = "date +%s";
    private static final String DATE_COMMAND_FORMAT_MODIFY = "date;date --set='%d seconds';clock -w";
 
 
    public static Date newInstanceForSeconds() {
        return new Date(DATE_COMMAND_FORMAT_SECONDS);
    }

    public static Date newInstanceForCommon() {
        return new Date(DATE_COMMAND_FORMAT_COMMON);
    }

    public static Date newInstanceForFormat(String format) {
        return new Date(String.format(DATE_COMMAND_FORMAT_FORMAT, format));
    }

    public static Date newInstanceForModify(int timeOffsetInSeconds) {
        return new Date(String.format(DATE_COMMAND_FORMAT_MODIFY, timeOffsetInSeconds));
    }


    private Date(String commandStr) {
        super(commandStr);
    }

}
