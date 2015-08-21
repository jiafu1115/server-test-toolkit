package com.googlecode.test.toolkit.unittest.database;

import static org.junit.Assert.*;

import org.junit.Test;

import com.googlecode.test.toolkit.database.DefaultDbClient;
import com.googlecode.test.toolkit.database.config.C3p0XmlDbConfig;

public class DefaultDbClientTest {

	@Test
	public void testDefaultDbClient() {
 		DefaultDbClient defaultDbClient = new DefaultDbClient();
		defaultDbClient.getConnnection();
 	}

	@Test
	public void testDefaultDbClient2() {
 		DefaultDbClient defaultDbClient = new DefaultDbClient(new C3p0XmlDbConfig(
				"test"));
		defaultDbClient.getConnnection();
 	}

	@Test
	public void testDefaultDbClient3() {
 		DefaultDbClient defaultDbClient = new DefaultDbClient(new C3p0XmlDbConfig(
				"default"));
		defaultDbClient.getConnnection();
 	}

	@Test
	public void testDefaultDbClient4() {
 		DefaultDbClient defaultDbClient = new DefaultDbClient(new C3p0XmlDbConfig());
		defaultDbClient.getConnnection();
 	}

	@Test
	public void testDefaultDbClientDbConfig() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryStringObjectArrayResultSetHandlerOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConnnection() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryStringResultSetHandlerOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteStringObjectArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteString() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateString() {
 	}

	@Test
	public void testUpdateStringObjectArray() {
		fail("Not yet implemented");
	}

}
