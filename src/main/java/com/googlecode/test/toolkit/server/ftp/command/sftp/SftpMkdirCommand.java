package com.googlecode.test.toolkit.server.ftp.command.sftp;


import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpMkdirCommand extends SftpCommandWithoutResult {

	private String path;

	public SftpMkdirCommand(Session session, String path) {
		super(session);
		ValidationUtil.checkString(path);
		this.path = path;
	}

	@Override
	protected void runCommandByChannel(ChannelSftp channelSftp)
			throws SftpException {
		channelSftp.mkdir(path);
 	}

}
