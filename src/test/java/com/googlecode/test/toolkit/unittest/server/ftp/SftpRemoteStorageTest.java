package com.googlecode.test.toolkit.unittest.server.ftp;

import org.junit.Test;

import com.googlecode.test.toolkit.server.common.user.SshUser;
import com.googlecode.test.toolkit.server.ftp.SftpRemoteStorage;

public class SftpRemoteStorageTest {

	@Test
	public void testDownloadStringOutputStream() {

		 SftpRemoteStorage sftpRemoteStorage = new SftpRemoteStorage(new SshUser("192.168.1.1", 333, "root", "***"));
		 sftpRemoteStorage.upload("c:\\1.txt", "/usr/local", "2.txt");
		 sftpRemoteStorage.disconnect();
  	}

	@Test
	public void testUploadInputStreamStringString() {
		 SftpRemoteStorage sftpRemoteStorage = new SftpRemoteStorage(new SshUser("192.168.1.1", 333, "root", "***"));
		 sftpRemoteStorage.download("/usr/local/2.txt", "c:\\3.txt");
		 sftpRemoteStorage.disconnect();

	}

}
