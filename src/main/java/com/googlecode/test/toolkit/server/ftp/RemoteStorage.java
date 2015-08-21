package com.googlecode.test.toolkit.server.ftp;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import com.googlecode.test.toolkit.server.common.interfaces.RemoteConnection;

public interface RemoteStorage extends RemoteConnection {

	public void mkdir(String path);

	/**
	 * upload <tt>srcInputStream</tt> to <tt>dstFolder</tt> name
	 * <tt>dstFileName</tt>
	 *
	 * @param srcInputStream
	 * @param remoteFolder
	 * @param remoteFileName
	 */
	public void upload(InputStream srcInputStream, String remoteFolder, String remoteFileName);

	/**
	 * upload file to remote folder with name <tt>remoteFileName</tt>
	 *
	 * @param localFilePath
	 * @param remoteFolder
	 * @param remoteFileName
	 * @throws FileNotFoundException
	 *
	 */
	public void upload(String localFilePath, String remoteFolder, String remoteFileName);

	/**
	 * copy <tt>storagePath</tt> to <tt>outputStream</tt> the storagePath can be
	 * remote or local,it dependent to implements
	 *
	 * @param remotePath
	 * @param outputStream
	 */
	public void download(String remotePath, OutputStream outputStream);

	/**
	 * similar with {@link #download(String, OutputStream)}. but is this
	 * method. the OutputStream is file
	 *
	 * @param remotePath
	 * @param localFilePath
	 */
	public void download(String remotePath, String localFilePath);

}
