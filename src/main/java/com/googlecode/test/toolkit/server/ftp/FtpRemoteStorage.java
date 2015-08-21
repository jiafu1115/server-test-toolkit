package com.googlecode.test.toolkit.server.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import com.googlecode.test.toolkit.server.common.exception.CommandExecuteException;
import com.googlecode.test.toolkit.server.common.exception.ServerConnectionException;
import com.googlecode.test.toolkit.util.IoUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;

public class FtpRemoteStorage extends AbstractRemoteStroage {

	private final static Logger LOGGER = Logger.getLogger(FtpRemoteStorage.class);

	private FTPClient ftpClient;

	/**
	 * after instance created, the connection will be created by default, but you
	 * should call {@link #disconnect()} to release the connection.
	 *
	 * @param ftpUser
	 */
	public FtpRemoteStorage(FtpUser ftpUser) {
		super(ftpUser);
	}

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	@Override
	public void connect() {
		if (ftpClient == null) {
			ftpClient = new FTPClient();
			try {
				ftpClient.connect(serverUser.getHost(), serverUser.getPort());
				ftpClient.login(serverUser.getUsername(), serverUser.getPassword());
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				throw new ServerConnectionException(e.getMessage(), e);
			}
		}
	}

	@Override
	public void disconnect() {
		try {
			if (ftpClient != null)
				ftpClient.disconnect();
		} catch (IOException e) {
			LOGGER.warn("release connection fail for: " + e.getMessage(), e);
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public void upload(InputStream srcInputStream, String remoteFolder, String remoteFileName) {
		LOGGER.info(String.format("[storage]upload to %s as %s", remoteFolder, remoteFileName));
		try {
			if (remoteFolder != null) {
				ftpClient.changeWorkingDirectory(remoteFolder);
			}
			ftpClient.storeFile(remoteFileName, srcInputStream);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new CommandExecuteException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(srcInputStream);
		}
	}

	@Override
	public void download(String remotePath, OutputStream outputStream) {
		LOGGER.info(String.format("[storage]download  %s", remotePath));

		try {
			InputStream inputStream = ftpClient.retrieveFileStream(remotePath);
			IoUtil.inputStreamToOutputStream(inputStream, outputStream);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new ServerConnectionException(e.getMessage(), e);
		}
	}

	@Override
	public void mkdir(String path) {
		ValidationUtil.checkString(path);
		LOGGER.info(String.format("[storage]create path %s",path));

		try {
			ftpClient.mkd(path);
		} catch (IOException e) {
			throw new CommandExecuteException(e.getMessage(), e);
 		}
	}
}
