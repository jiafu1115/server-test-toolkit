package com.googlecode.test.toolkit.database.resultset.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.googlecode.test.toolkit.database.exception.DbExecuteException;

/**
 * MapList's map is column name: column value
 * @author fu.jian
 * date Aug 13, 2012
 */
public class ToMapListHandler implements ResultSetHandler<List<HashMap<String, ?>>> {

	@Override
	public List<HashMap<String, ?>> handle(ResultSet resultSet) {
		List<HashMap<String, ?>> list = new ArrayList<HashMap<String, ?>>();
		try {
			while (resultSet.next()) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for (int index = 1; index <= columnCount; index++) {
					String key = resultSetMetaData.getColumnName(index);
					Object value = resultSet.getObject(index);
					hashMap.put(key, value);
				}

				list.add(hashMap);
			}
			return list;
		} catch (SQLException e) {
			throw new DbExecuteException(e.getMessage(), e);
		}
	}

}
