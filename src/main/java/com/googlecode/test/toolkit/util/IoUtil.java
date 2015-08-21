package com.googlecode.test.toolkit.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

public final class IoUtil {

	/**
	 * @param inputStream
	 * @param filePath
	 * @throws IOException
	 */
	public static void inputStreamToFile(InputStream inputStream,
			String filePath) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(filePath);
		try {
			inputStreamToOutputStream(inputStream, outputStream);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

	public static void inputStreamToOutputStream(InputStream inputStream,
			OutputStream outputStream) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				inputStream);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				outputStream);

		try {
			IOUtils.copy(bufferedInputStream, bufferedOutputStream);
			// call flush method due to user only can close outputStream but
			// some bytes in buffer mayn't be flush due to smaller size.
			bufferedOutputStream.flush();
		} finally {
			IOUtils.closeQuietly(bufferedInputStream);
		}
	}

	private IoUtil() {
	}

}
