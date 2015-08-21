package com.googlecode.test.toolkit.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.googlecode.test.toolkit.database.DbUtil.DbCloseUtil;

/**
 * @author fu.jian date Aug 3, 2012
 */
public class JdbcClosableImpl implements JdbcClosable {

	protected JdbcClosableImpl() {
	}

	public void close(ResultSet resultSet, Statement statement,
			Connection connection) {
		close(resultSet, statement);
		DbCloseUtil.closeConnection(connection);
	}

	public void close(ResultSet resultSet, Statement statement) {
		DbCloseUtil.closeStatement(statement);
		DbCloseUtil.closeResultSet(resultSet);
	}

	@Override
	public void closeStatement(Statement statement) {
		DbCloseUtil.closeStatement(statement);
	}

	@Override
	public void closeResultSet(ResultSet resultSet) {
		DbCloseUtil.closeResultSet(resultSet);
	}

	@Override
	public void closeConnection(Connection connection) {
		DbCloseUtil.closeConnection(connection);
	}

	@Override
	public void close(Statement statement, Connection connection) {
		DbCloseUtil.closeStatement(statement);
		DbCloseUtil.closeConnection(connection);
	}

}