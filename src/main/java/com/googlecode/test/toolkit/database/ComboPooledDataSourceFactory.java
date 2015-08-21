package com.googlecode.test.toolkit.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ComboPooledDataSourceFactory {

	static ComboPooledDataSource getDefaultComboPooledDataSource() {
		return new ComboPooledDataSource();
	}

	static ComboPooledDataSource getComboPooledDataSource(String configName) {
		return new ComboPooledDataSource(configName);
	}

	static ComboPooledDataSource getComboPooledDataSource(String configName, String configPath) {
		if (configPath == null || configPath.isEmpty())
			return getComboPooledDataSource(configName);
		else {
			System.setProperty("com.mchange.v2.c3p0.cfg.xml", configPath);
			return getComboPooledDataSource(configName);
		}
	}

	private ComboPooledDataSourceFactory() {
	}
}
