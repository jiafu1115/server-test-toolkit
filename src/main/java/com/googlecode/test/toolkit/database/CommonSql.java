package com.googlecode.test.toolkit.database;

import static com.googlecode.test.toolkit.database.SqlConstants.FROM;
import static com.googlecode.test.toolkit.database.SqlConstants.SELECT;
import static com.googlecode.test.toolkit.database.SqlConstants.TOTALCOUNT;

import com.googlecode.test.toolkit.util.StringUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;

public class CommonSql {

 	private final static String SQL_FORMAT_FOR_TABLE_RECORD_COUNT = StringUtil.concatWithSpace(
			SELECT, TOTALCOUNT, FROM, "%s");

	/**
	 * @param tableName
	 * @return use {@link #SQL_FORMAT_FOR_TABLE_RECORD_COUNT} and {@code tableName} to get sql for records count
	 */
	public static String getSqlForTableRecordCount(String tableName) {
		ValidationUtil.checkString(tableName);
		return String.format(SQL_FORMAT_FOR_TABLE_RECORD_COUNT, tableName);

	}

	private CommonSql() {
	}

}
