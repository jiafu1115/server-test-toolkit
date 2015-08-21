package com.googlecode.test.toolkit.server.common.user;

public class ServerUser {

	public static final String HOST = "host";
	public static final String PORT = "port";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

 	protected String host;
 	protected int port;
 	protected String username;
 	protected String password;

	protected ServerUser(String host, int port, String username, String password) {
		super();
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "ServerUser [host=" + host + ", port=" + port + ", username="
				+ username + ", password=" + password + "]";
	}

}
