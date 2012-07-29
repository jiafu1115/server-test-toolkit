package org.test.toolkit.server.ftp;

import java.io.InputStream;

import org.test.toolkit.server.common.user.SshUser;
import org.test.toolkit.server.common.util.JSchUtil.JSchSessionUtil;
import org.test.toolkit.server.ftp.command.GetSftpCommand;
import org.test.toolkit.server.ftp.command.PutSftpCommand;
import org.test.toolkit.server.ftp.command.SftpCommandWithResult;
import org.test.toolkit.server.ftp.command.SftpCommandWithoutResult;

import com.jcraft.jsch.Session;

public class SftpRemoteStorageImpl extends AbstractRemoteStroage {

	private Session session;

	/**
	 * @param sshUser
	 */
	public SftpRemoteStorageImpl(SshUser sshUser) {
		super(sshUser);
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
	public InputStream getFile(String storagePath) {
		SftpCommandWithResult sftpGetCommand = new GetSftpCommand(session, storagePath);
 		return (InputStream) sftpGetCommand.executeWithResult();
 	}

	@Override
	public void storeFile(InputStream srcInputStream, String dstFolder, String dstFileName) {
		SftpCommandWithoutResult sftpPutCommand = new PutSftpCommand(session, srcInputStream, dstFolder,
				dstFileName);
		sftpPutCommand.executeWithoutResult();
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
