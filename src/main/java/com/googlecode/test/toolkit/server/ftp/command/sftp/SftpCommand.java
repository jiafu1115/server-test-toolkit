package com.googlecode.test.toolkit.server.ftp.command.sftp;

import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.common.exception.CommandExecuteException;
import com.googlecode.test.toolkit.server.common.util.JSchUtil;
import com.googlecode.test.toolkit.server.common.util.JSchUtil.JSchChannelUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public abstract class SftpCommand {

	private final static Logger LOGGER = Logger.getLogger(SftpCommand.class);

	private final Session session;

	protected SftpCommand(Session session) {
		ValidationUtil.checkNull(session);
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	public void execute() {
		_execute();
	}

	protected Object _execute() {
		ChannelSftp channelSftp = null;
		try {
			channelSftp = openChannel();
			return runCommandByChannelWithResult(channelSftp);
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
			throw new CommandExecuteException(e1.getMessage(), e1);
		}finally{
			JSchUtil.JSchChannelUtil.disconnect(channelSftp);
		}
	}

	private ChannelSftp openChannel() throws JSchException {
		ChannelSftp channelSftp = JSchChannelUtil.getSftpChannel(session);
		channelSftp.connect();

		return channelSftp;
	}

	abstract Object runCommandByChannelWithResult(ChannelSftp channelSftp) throws SftpException;

}
