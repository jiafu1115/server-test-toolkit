package com.googlecode.test.toolkit.server.ssh;

import java.util.List;
import java.util.Map;

import com.googlecode.test.toolkit.server.common.interfaces.RemoteConnection;
import com.googlecode.test.toolkit.server.ssh.command.Command;
import com.googlecode.test.toolkit.server.ssh.performance.PerformanceData;

/**
 * @author fu.jian
 * date Jul 25, 2012
 */
public interface ServerOperations extends RemoteConnection {

	public Map<String, String> executeCommandWithResult(String command);


    /**
     *  It will judge if the command be executed success though without reading the command's result,
     * So if the command is hanged without return, it should use {@link #executeCommandHanged(String)}
     * @param command
     */
	public void executeCommandWithoutResult(String command);

    /**
     *  It will judge if the command be executed success though without reading the command's result,
     * So if the command is hanged without return, it should use {@link #executeCommandHanged(String)}
     * @param command
     */
	public void executeCommandWithoutResult(Command command);

	public void executeCommandHanged(String command);

	public Map<String, String> executeCommandWithResult(Command command);

 	public void cp(String fromPath, String toPath);

	public Map<String, String> ls(String path);

	public Map<String, String> getContentWithKeywords(String path,
			String atLeastOneKeyword, String... otherKeywords);

	public void createFileWithoutContent(String fileName);

	public Map<String, String> getContentBetweenLines(String fullPath,
			int startLine, int endLine, String... keywords);

	public Map<String, String> getContentFromLine(String fullPath,
			long startline, String... keywords);

	public Map<String, Long> getLineNumberForFile(String filePath);

	/**
	 * use iptable tool to block the connections with IP or IPs, drop output
	 * data
	 *
	 * @param atLeastOneIp
	 * @param otherIps
	 */
	public void blockConnectionsWithIp(String atLeastOneIp, String... otherIps);

	/**
	 * similar with {@link ServerOperations#blockConnectionsWithIp(String, String...)}, but
	 * can control drop input data or drop output data
	 *
	 * @param isOutput
	 *            drop output or input data
	 * @param atLeastOneIp
	 * @param otherIps
	 */
	public void blockConnectionsWithIp(boolean isOutput, String atLeastOneIp,
			String... otherIps);

	public void blockPort(int port);

	public void blockConnectionsExceptIp(String atLeastOneIp, String... otherIps);

	public void blockConnectionsExceptIp(boolean isOutput,String atLeastOneIp, String... otherIps);

  	public void unblockConnectionsWithIp(boolean isOutput, String atLeastOneIp,
	            String... otherIps);

	public void unblockPort(int port);

	public void unblockConnectionsExceptIp(String atLeastOneIp, String... otherIps);

	public void unblockConnectionsExceptIp(boolean isOutput,String atLeastOneIp, String... otherIps);

	public void unblockConnectionsWithIp(String atLeastOneIp, String... otherIps);

	public void modifyFile(String editPath, int lineNumber,
			String newContentForLine, String backupPath);

	/**
	 * call {@link #modifyFile(String, int, String, String)} with {@code backupPath}=path+"_backup_"+current time;
	 * @param editPath
	 * @param lineNumber
	 * @param newContentForLine
	 */
	public void modifyFile(String editPath, int lineNumber,
			String newContentForLine);

	public void modifyFile(String editPath, String originalContent, String newContent, String backupPath);

	public Map<String, List<String>> getLineNumbersWithKeyword(String filePath,
			String keyword);

	public void createPath(String path);

	public void cancelIptables();
	
	/**
	 * service iptables restart
	 */
	public void restartIptables();

	public Map<String, PerformanceData> getPerformanceData();

	/**
	 * seconds since 1970-01-01 00:00:00 UTC
	 * @return {[ip]=1399442275}
	 */
	public Map<String, Long> getCurrentTimeSeconds();

	/**
	 * @return {[ip]=Wed May  7 05:57:55 GMT 2014}
	 */
	public Map<String, String> getCurrentTime();

	/**
	 * @param format  such as "%y/%m/%d_%H:%M:%S" can see linux command date's help
	 * @return  {[ip]=14/05/07_05:57:55}
	 */
	public Map<String, String> getCurrentTime(String format);

	/**
	 * @param timeOffsetInSeconds: >0 mean set time after now, <0 mean time before now
	 */
	public void modifyCurrentTime(int timeOffsetInSeconds);

	/**
	 * use command format: /etc/init.d/ntpd stop;ntpdate 10.224.38.24; /etc/init.d/ntpd start
	 * @param ntpServerHost
	 */
	public void syncNtp(String ntpServerHost);
	
	public void setCommandTimeOutTime(int commandTimeOutTime);
	
	public void setMaxChannelNumberForEachSession(int maxChannelNumberForEachSession);
 
 }