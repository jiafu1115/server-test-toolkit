package com.googlecode.test.toolkit.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * @author fu.jian date Aug 4, 2012
 */
public class DbUtil {

	private static final Logger LOGGER = Logger.getLogger(DbUtil.class);

	public static final class DbCloseUtil {

		public static void closeStatement(Statement createStatement) {
			try {
				if (createStatement != null)
					createStatement.close();
			} catch (SQLException e) {
				DbUtil.LOGGER.warn("close statement fail for: " + e.getMessage(), e);
			}
		}

		public static void closeConnection(Connection connection) {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				DbUtil.LOGGER.warn("close connection fail for: " + e.getMessage(), e);
			}
		}

		public static void closeResultSet(ResultSet resultSet) {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				DbUtil.LOGGER.warn("close resultSet fail for: " + e.getMessage(), e);
			}
		}

		private DbCloseUtil() {

		}

	}

	private DbUtil() {
	}

}
