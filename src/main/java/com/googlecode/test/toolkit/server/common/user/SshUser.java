package com.googlecode.test.toolkit.server.common.user;


/**
 * default port is {@link #DEFAULT_PORT}
 * @author Administrator
 *
 */
public class SshUser extends ServerUser {

	/**
	 * DEFAULT_PORT = {@value}
	 */
	public static final int DEFAULT_PORT = 22;

	public SshUser(String host, String username, String password) {
		super(host, DEFAULT_PORT, username, password);
	}

	public SshUser(String host, int port, String username, String password) {
		super(host, port, username, password);
	}

}
