package com.googlecode.test.toolkit.server.ssh;

import com.googlecode.test.toolkit.server.common.Expect;
import com.googlecode.test.toolkit.server.common.user.SshUser;
import com.googlecode.test.toolkit.server.common.util.JSchUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;

public abstract class SshExpect {

	private Expect expect;
	private Session session;

	public SshExpect(SshUser sshUser) {
		try {
			
 			session=JSchUtil.JSchSessionUtil.getSession(sshUser);
 			Channel channel = session.openChannel("shell");
			expect = new Expect(channel.getInputStream(),
					channel.getOutputStream());
			channel.connect();
		} catch (Exception exception) {
			throw new RuntimeException(exception.getMessage(),exception);
		}

	}

	protected abstract void executeExpect(Expect expect) throws Exception;

	public void run() throws Exception {
		try {
			 executeExpect(expect);
 		}  finally {
			try{
				expect.close();
 			}finally{
				session.disconnect();

			}

		}
	}

	public Expect getExpect() {
		return expect;
	}
	
	

}