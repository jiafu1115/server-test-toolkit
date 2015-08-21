package com.googlecode.test.toolkit.server.ssh;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.ssh.command.Command;
import com.googlecode.test.toolkit.server.ssh.command.Cp;
import com.googlecode.test.toolkit.server.ssh.command.Grep;
import com.googlecode.test.toolkit.server.ssh.command.GroupCommandFactory;
import com.googlecode.test.toolkit.server.ssh.command.GroupCommands;
import com.googlecode.test.toolkit.server.ssh.command.Iptable;
import com.googlecode.test.toolkit.server.ssh.command.Ls;
import com.googlecode.test.toolkit.server.ssh.command.Mkdir;
import com.googlecode.test.toolkit.server.ssh.command.Sed;
import com.googlecode.test.toolkit.server.ssh.command.Tail;
import com.googlecode.test.toolkit.server.ssh.command.Touch;
import com.googlecode.test.toolkit.server.ssh.command.Vmstat;
import com.googlecode.test.toolkit.server.ssh.performance.PerformanceData;
import com.googlecode.test.toolkit.util.CollectionUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian date Jul 25, 2012
 */
public abstract class AbstractServerOperations implements ServerOperations {
	
	
	private final static Logger LOG=Logger.getLogger(AbstractServerOperations.class);

    private static final String REGEX = "\n";
    

    @Override
    public void cp(String fromPath, String toPath) {
        Command cp = Cp.newInstance(fromPath, toPath);
        executeCommandWithoutResult(cp);
    }

    @Override
    public void executeCommandWithoutResult(Command command) {
        executeCommandWithoutResult(command.getCommandStr());
    }

    @Override
    public void executeCommandWithoutResult(String command) {
        executeCommand(command, false, false);
    }

    abstract Map<String, String> executeCommand(String command, boolean returnResult, boolean isHanged);

    @Override
    public Map<String, String> ls(String path) {
        Command command = Ls.newInstanceLsPath(path);
        return executeCommandWithResult(command);
    }

    @Override
    public Map<String, String> getContentWithKeywords(String path, String atLeastOneKeyword, String... otherKeywords) {
        Command command = Grep.newInstance(path, atLeastOneKeyword, otherKeywords);
        return executeCommandWithResult(command);
    }

    @Override
    public void createFileWithoutContent(String fileName) {
        Command command = Touch.newInstance(fileName);
        executeCommandWithoutResult(command);
    }

    @Override
    public Map<String, PerformanceData> getPerformanceData() {
        Command command = Vmstat.newInstance();
        Map<String, String> result = executeCommandWithResult(command);
        Map<String, PerformanceData> returnMap = new HashMap<String, PerformanceData>(result.size());
        for (Map.Entry<String, String> entry : result.entrySet()) {
            returnMap.put(entry.getKey(), PerformanceData.parsePerformanceData(entry.getValue()));
        }

        return returnMap;
    }

    @Override
    public Map<String, String> getContentBetweenLines(String fullPath, int startLine, int endLine, String... keywords) {
        Command command = Sed.newInstanceForGetContentBetweenLines(fullPath, startLine, endLine, keywords);
        return executeCommandWithResult(command);
    }

    @Override
    public Map<String, String> getContentFromLine(String fullPath, long startline, String... keywords) {
        Command command = Tail.newInstanceForGetContentFromLine(fullPath, startline, keywords);
        return executeCommandWithResult(command);
    }

    @Override
    public Map<String, Long> getLineNumberForFile(String filePath) {
        Command sed = Sed.newInstanceForGetLineNumber(filePath);
        Map<String, String> tmpResultMap = executeCommandWithResult(sed);
        Map<String, Long> finalResultMap = new HashMap<String, Long>(tmpResultMap.size());
        for (Entry<String, String> entry : tmpResultMap.entrySet()) {
            String value = entry.getValue();
            String numberStr = value.substring(0, value.indexOf(REGEX));
            finalResultMap.put(entry.getKey(), Long.parseLong(numberStr));
        }

        return finalResultMap;
    }

    @Override
    public void blockConnectionsWithIp(String atLeastOneIp, String... otherIps) {
        blockConnectionsWithIp(true, atLeastOneIp, otherIps);
    }

    @Override
    public void blockConnectionsWithIp(boolean isOutput, String atLeastOneIp, String... otherIps) {
        addOrDeleteRuleForBlockConnectionsWithIp(false,isOutput,atLeastOneIp,otherIps);
    }

    @Override
    public void unblockConnectionsWithIp(String atLeastOneIp, String... otherIps) {
        unblockConnectionsWithIp(true, atLeastOneIp, otherIps);
    }


    @Override
    public void unblockConnectionsWithIp(boolean isOutput, String atLeastOneIp, String... otherIps) {
        addOrDeleteRuleForBlockConnectionsWithIp(true,isOutput,atLeastOneIp,otherIps);
    }

    private void addOrDeleteRuleForBlockConnectionsWithIp(boolean isDeleteRule,boolean isOutput, String atLeastOneIp, String... otherIps) {
        List<String> allIps = CollectionUtil.toList(atLeastOneIp, otherIps);

        for (String ip : allIps) {
            Iptable command = Iptable.newInstanceForBlockConnections(isOutput, ip);
            if(isDeleteRule)
                command.convertAddRuleToDeleteRule();
            executeCommandWithoutResult(command);
        }
    }


    @Override
    public void blockConnectionsExceptIp(String atLeastOneIp, String... otherIps) {
        blockConnectionsExceptIp(true, atLeastOneIp, otherIps);
    }

    @Override
    public void blockConnectionsExceptIp(boolean isOutput, String atLeastOneIp, String... otherIps) {
        addOrDeleteRuleForBlockConnectionsExceptIp(false,isOutput,atLeastOneIp,otherIps);
     }


    @Override
    public void unblockConnectionsExceptIp(String atLeastOneIp, String... otherIps) {
        unblockConnectionsExceptIp(true, atLeastOneIp, otherIps);
    }

    @Override
    public void unblockConnectionsExceptIp(boolean isOutput, String atLeastOneIp, String... otherIps) {
        addOrDeleteRuleForBlockConnectionsExceptIp(true,isOutput,atLeastOneIp,otherIps);
    }


    private void addOrDeleteRuleForBlockConnectionsExceptIp(boolean isDeleteRule,boolean isOutput, String atLeastOneIp, String... otherIps){
        List<String> allIps = CollectionUtil.toList(atLeastOneIp, otherIps);

        for (String ip : allIps) {
            Iptable command = Iptable.newInstanceForAcceptConnections(isOutput, ip);
            if(isDeleteRule)
                command.convertAddRuleToDeleteRule();
            executeCommandWithoutResult(command);
        }

        Iptable command = Iptable.newInstanceForBlockAllConnections(isOutput);
        if(isDeleteRule)
            command.convertAddRuleToDeleteRule();
        executeCommandWithoutResult(command);
    }

    @Override
    public void unblockPort(int port) {
        addOrDeleteRuleForBlockPort(true,port);
    }

    private void addOrDeleteRuleForBlockPort(boolean isDeleteRule,int port) {
        ValidationUtil.checkPositive(port);
        Iptable command = Iptable.newInstanceForBlockPort(port);
        if(isDeleteRule)
            command.convertAddRuleToDeleteRule();
        executeCommandWithoutResult(command);
    }

    @Override
    public void blockPort(int port) {
        addOrDeleteRuleForBlockPort(false,port);
    }

    @Override
    public void modifyFile(String path, int lineNumber, String newContentForLine) {
        String currentTimeStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String backupPath = new StringBuffer(path).append("_backup").append(currentTimeStr).toString();

        modifyFile(path, lineNumber, newContentForLine, backupPath);
    }

    @Override
    public void modifyFile(String editPath, int lineNumber, String newContentForLine, String backupPath) {
        GroupCommands groupCommands = GroupCommandFactory.changeFile(editPath, lineNumber, newContentForLine,
                backupPath);
        executeCommandWithoutResult(groupCommands);
    }

    @Override
    public void modifyFile(String editPath, String originalContent, String newContent, String backupPath) {
        GroupCommands groupCommands = GroupCommandFactory.changeFile(editPath, originalContent, newContent, backupPath);
        executeCommandWithoutResult(groupCommands);
    }

    @Override
    public Map<String, List<String>> getLineNumbersWithKeyword(String filePath, String keyword) {
        Command sed = Sed.newInstanceForGetLineNumbersWithKey(filePath, keyword);

        Map<String, String> tmpResultMap = executeCommandWithResult(sed);
        Map<String, List<String>> finalResultMap = new HashMap<String, List<String>>(tmpResultMap.size());
        for (Entry<String, String> entry : tmpResultMap.entrySet()) {
            String value = entry.getValue();
            List<String> list = Arrays.asList(value.split(REGEX));
            finalResultMap.put(entry.getKey(), list);
        }

        return finalResultMap;
    }

    @Override
    public void createPath(String path) {
        Command command = Mkdir.newInstance(path);
        executeCommandWithoutResult(command);
    }

    @Override
    public void cancelIptables() {
        Command command = Iptable.newInstanceForClearIptables();
        executeCommandWithoutResult(command);
    }

    @Override
    public Map<String, String> executeCommandWithResult(Command command) {
        return executeCommandWithResult(command.getCommandStr());
    }

    @Override
    public Map<String, String> executeCommandWithResult(String command) {
        return executeCommand(command, true, false);
    }

    @Override
    public Map<String, Long> getCurrentTimeSeconds() {
        Map<String, String> rawResult = executeCommandWithResult(com.googlecode.test.toolkit.server.ssh.command.Date
                .newInstanceForSeconds());

        return CollectionUtil.convertMapValueToLong(rawResult);
    }

    @Override
    public Map<String, String> getCurrentTime() {
          Map<String, String> rawMap = executeCommandWithResult(com.googlecode.test.toolkit.server.ssh.command.Date.newInstanceForCommon());
          return CollectionUtil.trimMapValue(rawMap);
    }

    @Override
    public Map<String, String> getCurrentTime(String format) {
        Map<String, String> rawMap= executeCommandWithResult(com.googlecode.test.toolkit.server.ssh.command.Date.newInstanceForFormat(format));
        return CollectionUtil.trimMapValue(rawMap);
    }

    @Override
    public void modifyCurrentTime(int timeOffsetInSeconds){
         Map<String, String> executeCommandWithResult = executeCommandWithResult(com.googlecode.test.toolkit.server.ssh.command.Date.newInstanceForModify(timeOffsetInSeconds));
         LOG.info(executeCommandWithResult);
    }

    @Override
    public void syncNtp(String ntpServerIp){
        executeCommandWithoutResult(com.googlecode.test.toolkit.server.ssh.command.Ntpdate.newInstance(ntpServerIp));
    }
    
    @Override
	public void restartIptables(){
        Command command = Iptable.newInstanceForRestartIptables();
        executeCommandWithoutResult(command);
	}
 
}
