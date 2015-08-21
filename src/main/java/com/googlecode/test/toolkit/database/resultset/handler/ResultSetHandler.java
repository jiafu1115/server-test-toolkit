package com.googlecode.test.toolkit.database.resultset.handler;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {

	T handle(ResultSet resultSet);

}
