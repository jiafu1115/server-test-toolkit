package com.googlecode.test.toolkit.job.config;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.googlecode.test.toolkit.job.exception.JobConfigException;
import com.googlecode.test.toolkit.util.XmlUtil;

public class JobConfigImpl implements JobConfig {

	private final static String DEFAULT_CONFIG_PATH = "job.xml";
	private String configPath;


	public static JobConfigImpl getInstance() {
		return new JobConfigImpl(DEFAULT_CONFIG_PATH);
	}

	public static JobConfigImpl getInstance(String configPath) {
		return new JobConfigImpl(configPath);
	}

 	private JobConfigImpl(String configPath) {
		super();
		this.configPath = configPath;
	}

	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

	@Override
	public Collection<JobEntry<Job>> getJobEntrys() {
		List<JobEntry<Job>> list = new ArrayList<JobEntry<Job>>();
		Document document = null;
		try {
			document = XmlUtil.getDocument(configPath);
		} catch (DocumentException e) {
			throw new JobConfigException(e.getMessage(), e);
		}

		@SuppressWarnings("unchecked")
		List<Element> jobs = document.selectNodes("//job");
		for (Element job : jobs) {
			Map<String, String> hashMap = new HashMap<String, String>();
			@SuppressWarnings("unchecked")
			List<Element> paramsElements = job.elements("parameter");
			for (Element paramsElement : paramsElements) {
				Attribute key = paramsElement.attribute("name");
				Attribute value = paramsElement.attribute("value");
				hashMap.put(key.getValue(), value.getValue());
			}

			JobEntry<Job> jobEntry = new JobEntry<Job>();
			setJobEntry(hashMap, jobEntry);
			list.add(jobEntry);
		}

		return list;
	}

	public void setJobEntry(Map<String, String> hashMap, JobEntry<Job> jobEntry) {
		try {
			BeanUtils.copyProperties(jobEntry, hashMap);
		} catch (IllegalAccessException e) {
			throw new JobConfigException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new JobConfigException(e.getMessage(), e);
		}
	}

}
