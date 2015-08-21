package com.googlecode.test.toolkit.database.config;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;


import com.googlecode.test.toolkit.database.exception.DbConfigException;
import com.googlecode.test.toolkit.util.JavaBeanUtil;
import com.googlecode.test.toolkit.util.StringUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ExplicitDbConfig implements DbConfig {

	/**
	 * {@value}}
	 */
	public static final int DEFAULT_MAX_POOL_SIZE = 5;

	protected String driverClass;
	protected String jdbcUrl;
	protected String user;
	protected String password;
	protected String automaticTestTable;

	protected int minPoolSize;
	protected int maxPoolSize;
	protected int initialPoolSize;
	protected int maxIdleTime;
	protected int acquireIncrement;
	protected int acquireRetryAttempts;
	protected int acquireRetryDelay;
	protected int checkoutTimeout;

	protected boolean testConnectionOnCheckin;
	protected boolean testConnectionOnCheckout;

	/**
	 * add this no-arg constructor is to make this class to java bean.
	 * if not using java bean, suggest to use {@link this#ExplicitDbConfig(String, String, String, String)}
	 */
	public ExplicitDbConfig() {
		super();
	}

	public ExplicitDbConfig(String driverClass, String jdbcUrl, String user,
			String password) {
		super();
		this.driverClass = driverClass;
		this.jdbcUrl = jdbcUrl;
		this.user = user;
		this.password = password;

		setDefaultConfig();
	}

	private void setDefaultConfig() {
		this.maxPoolSize = DEFAULT_MAX_POOL_SIZE;
		this.acquireRetryAttempts=3;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public int getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(int acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public int getAcquireRetryAttempts() {
		return acquireRetryAttempts;
	}

	public void setAcquireRetryAttempts(int acquireRetryAttempts) {
		this.acquireRetryAttempts = acquireRetryAttempts;
	}

	public int getAcquireRetryDelay() {
		return acquireRetryDelay;
	}

	public void setAcquireRetryDelay(int acquireRetryDelay) {
		this.acquireRetryDelay = acquireRetryDelay;
	}

	public String getAutomaticTestTable() {
		return automaticTestTable;
	}

	public void setAutomaticTestTable(String automaticTestTable) {
		this.automaticTestTable = automaticTestTable;
	}

	public int getCheckoutTimeout() {
		return checkoutTimeout;
	}

	public void setCheckoutTimeout(int checkoutTimeout) {
		this.checkoutTimeout = checkoutTimeout;
	}

	public boolean isTestConnectionOnCheckin() {
		return testConnectionOnCheckin;
	}

	public void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		this.testConnectionOnCheckin = testConnectionOnCheckin;
	}

	public boolean isTestConnectionOnCheckout() {
		return testConnectionOnCheckout;
	}

	public void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		this.testConnectionOnCheckout = testConnectionOnCheckout;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jdbcUrl == null) ? 0 : jdbcUrl.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExplicitDbConfig other = (ExplicitDbConfig) obj;
		if (jdbcUrl == null) {
			if (other.jdbcUrl != null)
				return false;
		} else if (!jdbcUrl.equals(other.jdbcUrl))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public ComboPooledDataSource getComboPooledDataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		Field[] fields = ExplicitDbConfig.class.getDeclaredFields();
		try {
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers())) {
					field.setAccessible(true);
					Object value = field.get(this);
					Class<?> type = field.getType();
					String fieldName = field.getName();
					String setMethodName = getSetMethodName(fieldName);
					Method method = ComboPooledDataSource.class.getMethod(
							setMethodName, type);
					method.setAccessible(true);
					method.invoke(comboPooledDataSource, value);
				}

			}
			return comboPooledDataSource;
		} catch (Exception e) {
			throw new DbConfigException(e.getMessage(), e);
		}
	}

	private String getSetMethodName(String name) {
		return StringUtil.concatDirectly("set", name.substring(0, 1)
				.toUpperCase(), name.substring(1));
	}

	public String getIdenticalKey() {
		return StringUtil.concatDirectly(this.jdbcUrl, this.user);
	}

	public static ExplicitDbConfig fromMap(Map<String, ?> map) {
		try {
			return JavaBeanUtil.toJavaBean(map, ExplicitDbConfig.class);
		} catch (Exception e) {
			throw new DbConfigException(e.getMessage(), e);
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DbConfig [driverClass=");
		builder.append(driverClass);
		builder.append(", jdbcUrl=");
		builder.append(jdbcUrl);
		builder.append(", user=");
		builder.append(user);
		builder.append(", password=");
		builder.append(password);
		builder.append(", automaticTestTable=");
		builder.append(automaticTestTable);
		builder.append(", minPoolSize=");
		builder.append(minPoolSize);
		builder.append(", maxPoolSize=");
		builder.append(maxPoolSize);
		builder.append(", initialPoolSize=");
		builder.append(initialPoolSize);
		builder.append(", maxIdleTime=");
		builder.append(maxIdleTime);
		builder.append(", acquireIncrement=");
		builder.append(acquireIncrement);
		builder.append(", acquireRetryAttempts=");
		builder.append(acquireRetryAttempts);
		builder.append(", acquireRetryDelay=");
		builder.append(acquireRetryDelay);
		builder.append(", checkoutTimeout=");
		builder.append(checkoutTimeout);
		builder.append(", testConnectionOnCheckin=");
		builder.append(testConnectionOnCheckin);
		builder.append(", testConnectionOnCheckout=");
		builder.append(testConnectionOnCheckout);
		builder.append("]");

		return builder.toString();
	}

}
