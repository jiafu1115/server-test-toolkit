package com.googlecode.test.toolkit.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fu.jian
 * date Aug 3, 2012
 */
public final class JavaBeanUtil {

	public static <T> T toJavaBean(Map<String, ?> map, Class<T> javaBeanClazz) throws IntrospectionException,
			IllegalAccessException, InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(javaBeanClazz);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		T obj = javaBeanClazz.newInstance();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				descriptor.getWriteMethod().invoke(obj, value);
			}
		}
		return obj;
	}

	public static Map<String, Object> toMap(Object javaBeanInstance) throws IntrospectionException,
			IllegalAccessException, InvocationTargetException {
		Class<?> type = javaBeanInstance.getClass();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(javaBeanInstance, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	private JavaBeanUtil() {
	}

}
