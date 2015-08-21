package com.googlecode.test.toolkit.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author fu.jian date Aug 3, 2012
 */
public interface JdbcClosable {

	void closeResultSet(ResultSet resultSet);

	void closeStatement(Statement statement);

	void closeConnection(Connection connection);

	void close(ResultSet resultSet, Statement statement, Connection connection);

	void close(ResultSet resultSet, Statement statement);

	void close(Statement statement, Connection connection);

}