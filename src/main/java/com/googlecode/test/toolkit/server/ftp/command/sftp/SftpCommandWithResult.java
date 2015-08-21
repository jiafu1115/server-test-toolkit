package com.googlecode.test.toolkit.server.ftp.command.sftp;

import com.jcraft.jsch.Session;

public abstract class SftpCommandWithResult extends SftpCommand {

	protected SftpCommandWithResult(Session session) {
		super(session);
	}

	public Object executeWithResult() {
		return super._execute();
	}

}
