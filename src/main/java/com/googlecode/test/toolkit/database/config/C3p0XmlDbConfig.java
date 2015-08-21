package com.googlecode.test.toolkit.database.config;

public class C3p0XmlDbConfig implements DbConfig {

	private String configPath;
	private String configName;

 	/**
 	 * use default c3po-config.xml
 	 */
 	public C3p0XmlDbConfig() {
  	}

 	/**
 	 * if use default c3po-config.xml, can use this method to get instance. one
	 * xml can config serveral db configs, every db config in xml has one
 	 * @param configName
 	 */
 	public C3p0XmlDbConfig(String configName) {
		super();
		this.configName = configName;
	}

	/**
	 * set c3po-config.xml's path by
	 * <code>configPath<code>,one xml can config serveral db config, every db config in xml has one <code>configName</code>
	 * similar with  {@link C3p0XmlDbConfig#XmlDbConfig(String)}.
	 * @param configPath c3po_config.xml path
	 * @param configName
	 */
	public C3p0XmlDbConfig(String configPath, String configName) {
		super();
		this.configPath = configPath;
		this.configName = configName;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Override
	public String toString() {
		return "XmlDbConfig [configPath=" + configPath + ", configName="
				+ configName + "]";
	}
 }
