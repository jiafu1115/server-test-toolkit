package com.googlecode.test.toolkit.server.ftp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.common.exception.CommandExecuteException;
import com.googlecode.test.toolkit.server.common.user.ServerUser;
import com.googlecode.test.toolkit.util.ValidationUtil;

public abstract class AbstractRemoteStroage implements RemoteStorage {

	private static final Logger LOGGER=Logger.getLogger(AbstractRemoteStroage.class);

	protected ServerUser serverUser;

	public AbstractRemoteStroage(ServerUser serverUser) {
		ValidationUtil.checkNull(serverUser);
		this.serverUser = serverUser;
		connect();
	}

	public ServerUser getServerUser() {
		return serverUser;
	}

	@Override
	public void download(String storagePath, String localFilePath) {
		ValidationUtil.checkString(storagePath, localFilePath);
 		FileOutputStream outputStream = null;
		try {
			LOGGER.info(String.format("[storage]download  %s to %s", storagePath,localFilePath));
 			outputStream = new FileOutputStream(localFilePath);
			download(storagePath, outputStream);
 		} catch (FileNotFoundException e) {
			throw new CommandExecuteException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}

	}

	@Override
	public void upload(String localFilePath, String dstFolder,
			String dstFileName) {
		ValidationUtil.checkString(localFilePath, dstFolder, dstFileName);
		BufferedInputStream bufferedInputStream = null;
		try {
			bufferedInputStream = new BufferedInputStream(new FileInputStream(
					localFilePath));
			upload(bufferedInputStream, dstFolder, dstFileName);
		} catch (FileNotFoundException e) {
			throw new CommandExecuteException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(bufferedInputStream);
		}
	}

	@Override
	public String toString() {
		return "AbstractRemoteStroage [serverUser=" + serverUser + "]";
	}

}
