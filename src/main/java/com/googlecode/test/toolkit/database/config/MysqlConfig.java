package com.googlecode.test.toolkit.database.config;

public class MysqlConfig extends ExplicitDbConfig {

	/**
	 * {@value}
	 */
	private static final String JDBC_DRIVER_FOR_MYSQL = "com.mysql.jdbc.Driver";

	/**
	 * @param url  jdbc:mysql://localhost:3306/databaseName
	 * @param username
	 * @param password
	 */
	public MysqlConfig(String url, String username, String password) {
		super(JDBC_DRIVER_FOR_MYSQL, url, username, password);
	}

}
