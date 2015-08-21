package com.googlecode.test.toolkit.server.ftp.command.sftp;

import java.io.InputStream;


import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpPutCommand extends SftpCommandWithoutResult {

	private InputStream srcInputStream;
	private String dstFolder;
	private String dstFileName;

	public SftpPutCommand(Session session, InputStream srcInputStream, String dstFolder,
			String dstFileName) {
		super(session);
		ValidationUtil.checkNull(srcInputStream, dstFileName);

		this.srcInputStream = srcInputStream;
		this.dstFolder = dstFolder;
		this.dstFileName = dstFileName;
	}

	@Override
	protected void runCommandByChannel(ChannelSftp channelSftp) throws SftpException {
		if (dstFolder != null)
 			channelSftp.cd(dstFolder);
 		channelSftp.put(srcInputStream, dstFileName);
	}

}
