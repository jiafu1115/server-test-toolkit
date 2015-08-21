package com.googlecode.test.toolkit.unittest.ssh;

import java.io.IOException;

import com.googlecode.test.toolkit.server.common.Expect;
import com.googlecode.test.toolkit.server.common.Expect.EOFException;
import com.googlecode.test.toolkit.server.common.Expect.TimeoutException;
import com.googlecode.test.toolkit.server.common.user.SshUser;
import com.googlecode.test.toolkit.server.ssh.SshExpect;

public class TestSsh {
	
	public static void main(String[] args) throws Exception {
		
		final SshUser sshUser=new SshUser("10.224.56.15", "root", "pass");
		
		SshExpect sshExpect = new SshExpect(sshUser) {
			
			@Override
			protected void executeExpect(Expect expect) throws TimeoutException, EOFException, IOException {
				
					    expect.expect("#");
			  		 //   System.out.println(expect.before + expect.match);
			  		    expect.send("/test 10.224.56.15:2046\n");
			  		    expect.expect(">>");
 			  		    expect.send("bye\n");
			  		    Object object[]={"##"};
			     		expect.expectOrThrow(3, object);
   				
			}
		};
		
		sshExpect.run();
		
		Expect expect = sshExpect.getExpect();
		String match = expect.match;
		
		System.out.println(match);
		System.out.println("+++++++++++++++++++++++");
		System.out.println(expect.before);

		
	}

}
