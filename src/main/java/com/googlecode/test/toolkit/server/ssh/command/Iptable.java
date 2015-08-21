package com.googlecode.test.toolkit.server.ssh.command;

import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian
 * date Jul 26, 2012
 */
public class Iptable extends Command {

	private static final String IPTABLE_COMMAND_FORMAT_FOR_BLOCK_CONNECTIONS = "/sbin/iptables -t filter -A %s %s -j DROP";
	private static final String IPTABLE_COMMAND_FORMAT_FOR_BLOCK_ALL_CONNECTIONS = "/sbin/iptables -t filter -A %s -j DROP";

	private static final String IPTABLE_COMMAND_FORMAT_FOR_ACCEPT_CONNECTIONS = "/sbin/iptables -t filter -A %s %s -j ACCEPT";
 	private static final String IPTABLE_COMMAND_FORMAT_FOR_CLEAR_RULES = "/sbin/iptables -F";
 	private static final String IPTABLE_COMMAND_FORMAT_FOR_RESART = "service iptables restart";


 	public static Iptable newInstanceForClearIptables() {
		return new Iptable(IPTABLE_COMMAND_FORMAT_FOR_CLEAR_RULES);
	}
 	
 	public static Iptable newInstanceForRestartIptables() {
		return new Iptable(IPTABLE_COMMAND_FORMAT_FOR_RESART);
	}

	public static Iptable newInstanceForBlockConnections(boolean isOutput, String ip) {
		String forword = isOutput ? "INPUT -s" : "OUTPUT -d";
		String comandStr = String.format(IPTABLE_COMMAND_FORMAT_FOR_BLOCK_CONNECTIONS, forword, ip);

		return new Iptable(comandStr);
 	}

	public static Iptable newInstanceForBlockAllConnections(boolean isOutput) {
		String forword = isOutput ? "INPUT" : "OUTPUT";
		String comandStr = String.format(IPTABLE_COMMAND_FORMAT_FOR_BLOCK_ALL_CONNECTIONS, forword);

		return new Iptable(comandStr);
 	}

 	public static Iptable newInstanceForAcceptConnections(boolean isOutput, String ip) {
		String forword = isOutput ? "INPUT -s" : "OUTPUT -d";
		String comandStr = String.format(IPTABLE_COMMAND_FORMAT_FOR_ACCEPT_CONNECTIONS, forword, ip);

		return new Iptable(comandStr);
 	}

 	public void convertAddRuleToDeleteRule(){
 	    this.setCommandStr(getCommandStr().replace(" -A ", " -D "));
   	}

	/**
	 <pre>
	iptables -A OUTPUT -p tcp --sport 80 -j DROP
    iptables -A OUTPUT -p udp --sport 80 -j DROP
    iptables -A INPUT -p tcp --dport 80 -j DROP
    iptables -A INPUT -p udp --dport 80 -j DROP
	 </pre>
	 * @param port
	 * @return
	 */
	public static Iptable newInstanceForBlockPort(int port) {
	        ValidationUtil.checkPositive(port);
	        String command1="iptables -A INPUT -p tcp --dport "+port+" -j DROP";
	        String command2="iptables -A INPUT -p udp --dport "+port+" -j DROP";
	        String command3="iptables -A OUTPUT -p tcp --sport "+port+" -j DROP";
	        String command4="iptables -A OUTPUT -p udp --sport "+port+" -j DROP";
	        String command=String.format("%s;%s;%s;%s", command1,command2,command3,command4);

 	        return new Iptable(command);
	    }


	private Iptable(String commandStr) {
		super(commandStr);
	}

}
