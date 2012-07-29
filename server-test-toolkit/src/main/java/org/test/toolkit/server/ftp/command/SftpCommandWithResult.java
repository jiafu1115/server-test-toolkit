package org.test.toolkit.server.ftp.command;

import com.jcraft.jsch.Session;

public abstract class SftpCommandWithResult extends SftpCommand {

	public SftpCommandWithResult(Session session) {
		super(session);
	}

	public Object execute() {
		return super._execute();
	}

}
