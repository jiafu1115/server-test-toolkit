package com.googlecode.test.toolkit.server.ftp;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.common.user.SshUser;
import com.googlecode.test.toolkit.server.common.util.JSchUtil.JSchSessionUtil;
import com.googlecode.test.toolkit.server.ftp.command.sftp.SftpCommandWithoutResult;
import com.googlecode.test.toolkit.server.ftp.command.sftp.SftpGetCommand;
import com.googlecode.test.toolkit.server.ftp.command.sftp.SftpMkdirCommand;
import com.googlecode.test.toolkit.server.ftp.command.sftp.SftpPutCommand;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.jcraft.jsch.Session;

public class SftpRemoteStorage extends AbstractRemoteStroage {

	private final static Logger LOGGER = Logger.getLogger(SftpRemoteStorage.class);

	private Session session;

 	/**
	 * after instance created, the connection will be created by default, but you should call
	 * {@link #disconnect()} to release the connection.
	 * @param sshUser
	 */
	public SftpRemoteStorage(SshUser sshUser) {
		super(sshUser);
	}

	public Session getSession() {
		return session;
	}

	@Override
	public void disconnect() {
		JSchSessionUtil.disconnect(session);
	}

	@Override
	public void connect() {
		session = JSchSessionUtil.getSession(serverUser);
	}

	@Override
	public void download(String remotePath, OutputStream outputStream) {
		LOGGER.info(String.format("[storage]download from %s", remotePath));

		SftpCommandWithoutResult sftpGetCommand = new SftpGetCommand(session, remotePath, outputStream);
		sftpGetCommand.execute();
	}

	@Override
	public void upload(InputStream srcInputStream, String remoteFolder, String remoteFileName) {
		LOGGER.info(String.format("[storage]update file to  %s as %s", remoteFolder, remoteFileName));
		SftpCommandWithoutResult sftpPutCommand = new SftpPutCommand(session, srcInputStream, remoteFolder,
				remoteFileName);
		sftpPutCommand.execute();
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void mkdir(String path) {
		ValidationUtil.checkString(path);
		LOGGER.info(String.format("[storage]create path %s",path));

		SftpCommandWithoutResult sftpPutCommand = new SftpMkdirCommand(session, path);
		sftpPutCommand.execute();
	}

}
