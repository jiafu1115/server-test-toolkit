package com.googlecode.test.toolkit.unittest.ssh;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Test;

import com.googlecode.test.toolkit.util.MemoryUtil;


public class MemoryUtilTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAvailableMemory() {
		
		long availableMemory = MemoryUtil.getAvailableMemory();
		System.out.println(availableMemory);
 	}
	
	public static void main(String[] args) {
		String oneSshUser="a";
		String [] otherSshUsers={"b","c"};
 		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(otherSshUsers));
 		arrayList.add(oneSshUser);
 		System.out.println(arrayList);
		

	}

}
