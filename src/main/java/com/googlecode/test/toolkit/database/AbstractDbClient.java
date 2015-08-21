package com.googlecode.test.toolkit.database;

import java.util.HashMap;
import java.util.List;

import com.googlecode.test.toolkit.database.config.DbConfig;
import com.googlecode.test.toolkit.database.resultset.handler.ToMapListHandler;
import com.googlecode.test.toolkit.util.ValidationUtil;

/**
 * @author fu.jian
 * date Aug 3, 2012
 */
public abstract class AbstractDbClient extends JdbcClosableImpl implements JdbcExecutable {

	protected DbConfig dbConfig;

	protected AbstractDbClient(DbConfig dbConfig) {
		super();
		this.dbConfig = dbConfig;
	}

	/**
 	 * @param tableName
	 * @return all records count for the <code>tableName</code>
	 */
	public long getTotalCount(String tableName) {
		ValidationUtil.checkNull(tableName);

		String sqlForTableRecordCount = CommonSql.getSqlForTableRecordCount(tableName);
		List<HashMap<String, ?>> resultList = query(sqlForTableRecordCount, new ToMapListHandler());
		HashMap<String, ?> hashMap = resultList.get(0);
		Object object = hashMap.get(SqlConstants.TOTALCOUNT);

		return Long.valueOf(object.toString());
	}

}