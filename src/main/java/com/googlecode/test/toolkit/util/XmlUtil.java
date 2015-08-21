package com.googlecode.test.toolkit.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public final class XmlUtil {

	public static Document getDocument(String xmlPath) throws DocumentException {
		InputStream resourceAsStream = XmlUtil.class.getClassLoader().getResourceAsStream(xmlPath);
		SAXReader saxReader = new SAXReader();

		return saxReader.read(resourceAsStream);
	}

	private XmlUtil() {
	}

}
