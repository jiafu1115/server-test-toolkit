package com.googlecode.test.toolkit.server.ftp;

import com.googlecode.test.toolkit.server.common.user.ServerUser;


/**
 * default port is {@link #DEFAULT_PORT}
 * @author Administrator
 *
 */
public final class FtpUser extends ServerUser{

	/**
	 *  DEFAULT_PORT = {@value}
	 */
	public final static int DEFAULT_PORT = 21;

 	public FtpUser(String host, int port, String username, String password) {
		super(host, port, username, password);
 	}

 	public FtpUser(String host, String username, String password) {
		super(host, DEFAULT_PORT, username, password);
 	}

}
