package com.googlecode.test.toolkit.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.database.config.DbConfig;
import com.googlecode.test.toolkit.database.config.DefaultDbConfig;
import com.googlecode.test.toolkit.database.config.ExplicitDbConfig;
import com.googlecode.test.toolkit.database.config.C3p0XmlDbConfig;
import com.googlecode.test.toolkit.database.exception.DbConfigException;
import com.googlecode.test.toolkit.database.exception.DbConnectionException;
import com.googlecode.test.toolkit.multithread.AbstractSynchronizedMapAccessor;
import com.googlecode.test.toolkit.util.StringUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

	private static volatile ComboPooledDataSource defaultComboPooledDataSource;
	private static volatile ConcurrentHashMap<String, ComboPooledDataSource> dbConfigDataSourceMap = new ConcurrentHashMap<String, ComboPooledDataSource>();
	private static volatile ConcurrentHashMap<String, ComboPooledDataSource> configNameDataSourceMap = new ConcurrentHashMap<String, ComboPooledDataSource>();

	static Connection getConnection(DbConfig dbConfig) {
		ValidationUtil.checkNull(dbConfig);

		if (dbConfig instanceof DefaultDbConfig)
			return getConnection();
		if (dbConfig instanceof ExplicitDbConfig)
			return getConnection((ExplicitDbConfig) dbConfig);
		if (dbConfig instanceof C3p0XmlDbConfig) {
			C3p0XmlDbConfig xmlDbConfig = (C3p0XmlDbConfig) dbConfig;
			String configName = xmlDbConfig.getConfigName();
			String configPath = xmlDbConfig.getConfigPath();
			if (configName == null)
				return getConnection();
			if (configPath == null)
				return getConnection(configName, configPath);
			return getConnection(configName);
		}

		throw new UnsupportedOperationException("not supported: " + dbConfig);
	}

	private static Connection getConnection() {
		LOGGER.info("use default c3p0 config");
		ComboPooledDataSource defaultComboPoolDataSource = getDefaultComboPoolDataSource();
		return getConnection(defaultComboPoolDataSource);
	}

	private static ComboPooledDataSource getDefaultComboPoolDataSource() {
		if (defaultComboPooledDataSource != null)
			return defaultComboPooledDataSource;

		synchronized (ConnectionFactory.class) {
			if (defaultComboPooledDataSource != null)
				return defaultComboPooledDataSource;

			ComboPooledDataSource comboPooledDataSource = ComboPooledDataSourceFactory
					.getDefaultComboPooledDataSource();
			judgeIfConfigLegal(comboPooledDataSource);
			defaultComboPooledDataSource = getComboPooledDataSource(null,
					comboPooledDataSource);

			return defaultComboPooledDataSource;
		}
	}

	private static void judgeIfConfigLegal(
			ComboPooledDataSource comboPooledDataSource) {
		if (comboPooledDataSource.getDriverClass() == null)
			throw new DbConfigException(
					"no config in classPath or driverClass not configured");
	}

	private static ComboPooledDataSource getComboPooledDataSource(
			final ExplicitDbConfig dbConfig,
			final ComboPooledDataSource comboPooledDataSource) {
		AbstractSynchronizedMapAccessor<String, ComboPooledDataSource> abstractCacheAccess = new AbstractSynchronizedMapAccessor<String, ComboPooledDataSource>(
				dbConfigDataSourceMap) {
			@Override
			protected ComboPooledDataSource createValue() {
				if (comboPooledDataSource != null)
					return comboPooledDataSource;
				return dbConfig.getComboPooledDataSource();
			}
		};
		String keyForDbConfigDataSourceMap = dbConfig == null ? getKeyForDbConfigDataSourceMap(comboPooledDataSource)
				: getKeyForDbConfigDataSourceMap(dbConfig);
		return abstractCacheAccess.getValue(keyForDbConfigDataSourceMap,
				configNameDataSourceMap);
	}

	private static String getKeyForDbConfigDataSourceMap(
			ExplicitDbConfig dbConfig) {
		return dbConfig.getIdenticalKey();
	}

	private static Connection getConnection(
			ComboPooledDataSource comboPooledDataSource) {
		synchronized (getConnectionSynchronizedKey(comboPooledDataSource)) {
			try {
				return comboPooledDataSource.getConnection();
			} catch (SQLException e) {
				throw new DbConnectionException(e.getMessage(), e);
			}
		}
	}

	private static String getConnectionSynchronizedKey(
			ComboPooledDataSource comboPooledDataSource) {
		return StringUtil.concatDirectly(
				getKeyForDbConfigDataSourceMap(comboPooledDataSource),
				"connection").intern();
	}

	private static String getKeyForDbConfigDataSourceMap(
			ComboPooledDataSource comboPooledDataSource) {
		return StringUtil.concatDirectly(comboPooledDataSource.getJdbcUrl()
				+ comboPooledDataSource.getUser());
	}

	private static Connection getConnection(String configName) {
		return getConnection(configName, null);
	}

	private static Connection getConnection(String configName, String configPath) {
		ComboPooledDataSource comboPooledDataSource = getComboPooledDataSource(
				configName, configPath);
		return getConnection(comboPooledDataSource);
	}

	private static ComboPooledDataSource getComboPooledDataSource(
			final String configName, final String configPath) {
		AbstractSynchronizedMapAccessor<String, ComboPooledDataSource> abstractCacheAccess = new AbstractSynchronizedMapAccessor<String, ComboPooledDataSource>(
				dbConfigDataSourceMap) {

			@Override
			protected ComboPooledDataSource createValue() {
				ComboPooledDataSource comboPooledDataSource = ComboPooledDataSourceFactory
						.getComboPooledDataSource(configName, configPath);
				return getComboPooledDataSource(null, comboPooledDataSource);
			}
		};

		return abstractCacheAccess.getValue(
				getKeyForConfigNameDataSourceMap(configName, configPath),
				configNameDataSourceMap);

	}

	private static String getKeyForConfigNameDataSourceMap(String configName,
			String configPath) {
		return StringUtil.concatDirectly(configName, configPath == null ? ""
				: configPath);
	}

	private static Connection getConnection(ExplicitDbConfig dbConfig) {
		ComboPooledDataSource comboPooledDataSource = getComboPooledDataSource(
				dbConfig, null);
		return getConnection(comboPooledDataSource);
	}

	private ConnectionFactory() {
	}

}
