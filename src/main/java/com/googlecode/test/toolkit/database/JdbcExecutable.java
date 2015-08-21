package com.googlecode.test.toolkit.database;

import java.sql.Connection;

import com.googlecode.test.toolkit.database.resultset.handler.ResultSetHandler;

/**
 * @author fu.jian
 * date Aug 3, 2012
 */
public interface JdbcExecutable {

	Connection getConnnection();

	boolean execute(String sql);

	int update(String sql);

	int update(String sql, Object[] params);

	boolean execute(String sql, Object[] params);

	<T> T query(String sql, Object[] params, ResultSetHandler<T> resultSetHandler);

	<T> T query(String sql, ResultSetHandler<T> resultSetHandler);

	/**
	 * @param sql the sql only return one record and one column.
	 * @return
	 */
	Object query(String sql);

}