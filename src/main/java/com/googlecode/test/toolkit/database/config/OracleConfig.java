package com.googlecode.test.toolkit.database.config;

public class OracleConfig extends ExplicitDbConfig {

	/**
	 * {@value}
	 */
	private static final String JDBC_DRIVER_FOR_ORACLE = "oracle.jdbc.driver.OracleDriver";


	/**
	 * @param url such as:jdbc:oracle:thin:@10.1.1.2:1521:shdb
	 * @param username
	 * @param password
	 */
	public OracleConfig(String url, String username, String password) {
		super(JDBC_DRIVER_FOR_ORACLE, url, username, password);
	}

}

