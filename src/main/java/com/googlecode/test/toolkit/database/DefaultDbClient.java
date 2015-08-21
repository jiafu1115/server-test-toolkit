package com.googlecode.test.toolkit.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.database.config.DbConfig;
import com.googlecode.test.toolkit.database.config.DefaultDbConfig;
import com.googlecode.test.toolkit.database.exception.DbExecuteException;
import com.googlecode.test.toolkit.database.resultset.handler.ResultSetHandler;
import com.googlecode.test.toolkit.database.resultset.handler.ToMapListHandler;

public class DefaultDbClient extends AbstractDbClient {

	private static final Logger LOGGER = Logger
			.getLogger(DefaultDbClient.class);
	/**
	 * {@value}
	 */
	private static final String LOG_FROMAT_FOR_SQL_EXCEPTION = "fail to execute sql : %s for %s";

	/**
	 * use default c3p0 properties or xml config
	 */
	public DefaultDbClient() {
		this(new DefaultDbConfig());
	}

	public DefaultDbClient(DbConfig dbConfig) {
		super(dbConfig);
	}

	@Override
	public <T> T query(String sql, Object[] params,
			ResultSetHandler<T> resultSetHandler) {
		Connection connection = getConnnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; params != null && i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			resultSet = preparedStatement.executeQuery();

			return resultSetHandler.handle(resultSet);
		} catch (SQLException e) {
			String msg = String.format(LOG_FROMAT_FOR_SQL_EXCEPTION, sql,
					e.getMessage());
			throw new DbExecuteException(msg, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public Connection getConnnection() {
		return ConnectionFactory.getConnection(dbConfig);
	}
	
	
	@Override
	public Object query(String sql) {
		 ToMapListHandler toMapListHandler = new ToMapListHandler();
		 List<HashMap<String, ?>> result = query( sql,  toMapListHandler);
		 
		 return result.get(0).values().iterator().next();
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> resultSetHandler) {
		LOGGER.info("execute sql: " + sql);
		Connection connection = getConnnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			return resultSetHandler.handle(resultSet);
		} catch (SQLException e) {
			String msg = String.format(LOG_FROMAT_FOR_SQL_EXCEPTION, sql,
					e.getMessage());
			throw new DbExecuteException(msg, e);
		} finally {
			close(resultSet, statement, connection);
		}
	}

	@Override
	public boolean execute(String sql, Object[] params) {
		LOGGER.info("execute sql: " + sql);
		Connection connection = getConnnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; params != null && i < params.length; i++)
				preparedStatement.setObject(i + 1, params[i]);

			return preparedStatement.execute(sql);
		} catch (SQLException e) {
			String msg = String.format(LOG_FROMAT_FOR_SQL_EXCEPTION, sql,
					e.getMessage());
			throw new DbExecuteException(msg, e);
		} finally {
			close(preparedStatement, connection);
		}
	}

	@Override
	public boolean execute(String sql) {
		LOGGER.info("execute sql: " + sql);
		Connection connection = getConnnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			return statement.execute(sql);
		} catch (SQLException e) {
			String msg = String.format(LOG_FROMAT_FOR_SQL_EXCEPTION, sql,
					e.getMessage());
			throw new DbExecuteException(msg, e);
		} finally {
			close(statement, connection);
		}
	}

	@Override
	public int update(String sql) {
		LOGGER.info("execute sql: " + sql);
		Connection connection = getConnnection();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			String msg = String.format(LOG_FROMAT_FOR_SQL_EXCEPTION, sql,
					e.getMessage());
			throw new DbExecuteException(msg, e);
		} finally {
			close(statement, connection);
		}
	}

	@Override
	public int update(String sql, Object[] params) {
		Connection connection = getConnnection();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; params != null && i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbExecuteException(e.getMessage(), e);
		} finally {
			close(preparedStatement, connection);
		}
	}

}