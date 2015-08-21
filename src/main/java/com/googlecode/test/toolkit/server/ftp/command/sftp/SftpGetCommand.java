package com.googlecode.test.toolkit.server.ftp.command.sftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import com.googlecode.test.toolkit.server.common.exception.CommandExecuteException;
import com.googlecode.test.toolkit.util.IoUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpGetCommand extends SftpCommandWithoutResult {

	private String storagePath;
	private OutputStream outputStream;

	public SftpGetCommand(Session session, String storagePath, OutputStream outputStream) {
		super(session);
		ValidationUtil.checkNull(storagePath);

		this.storagePath = storagePath;
		this.outputStream = outputStream;
	}
  
	@Override
	protected void runCommandByChannel(ChannelSftp channelSftp) throws SftpException {
		InputStream inputStream = channelSftp.get(storagePath);
		try {
			IoUtil.inputStreamToOutputStream(inputStream, outputStream);
		} catch (IOException e) {
			throw new CommandExecuteException(e.getMessage(),e);
 		}		
	}

}
