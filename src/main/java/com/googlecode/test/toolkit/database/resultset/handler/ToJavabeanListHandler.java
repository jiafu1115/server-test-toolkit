package com.googlecode.test.toolkit.database.resultset.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.database.exception.DbExecuteException;

/**
 * java bean definition can refer to:
  <a ref="http://docs.oracle.com/cd/B19306_01/java.102/b14188/datamap.htm#CHDBJAGH">SQL and PL/SQL Data Type to Oracle and JDBC Mapping Classes</a>
 * @author jiafu
 *
 * @param <T>
 */
public class ToJavabeanListHandler<T> implements ResultSetHandler<List<T>> {

	private static final Logger LOGGER = Logger.getLogger(ToJavabeanListHandler.class);

	private Class<T> javabeanClass;

	public ToJavabeanListHandler(Class<T> javaBeanClass) {
		this.javabeanClass = javaBeanClass;
	}

	@Override
	public List<T> handle(ResultSet resultSet) {
		LOGGER.info("[DB][ToJavabeanListHandle][BEGIN]");
		List<T> list = new ArrayList<T>();
		try {
			while (resultSet.next()) {
				T entity = javabeanClass.newInstance();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for (int index = 1; index <= columnCount; index++) {
					String key = resultSetMetaData.getColumnName(index);
					Object value = resultSet.getObject(index);
					if (LOGGER.isDebugEnabled()) {
						String msg = String.format("[DB][Column][Key:Value][%s:%s]", key, value);
						LOGGER.debug(msg);
					}

					BeanUtils.copyProperty(entity, key, value);
				}
				list.add(entity);
			}
			LOGGER.info("[DB][ToJavabeanListHandle][END][SUCCESS][SIZE:"+list.size()+"]");
			return list;
		} catch (Exception e) {
			LOGGER.error("[DB][ToJavabeanListHandle][END][FAIL] for" + e.getMessage(), e);
			throw new DbExecuteException(e.getMessage(), e);
		}
	}

}
