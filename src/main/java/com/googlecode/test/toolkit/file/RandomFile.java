package com.googlecode.test.toolkit.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

public abstract class RandomFile {

	private final static Logger LOGGER = Logger.getLogger(RandomFile.class);

	protected String fileName;
	protected String sha;
	protected String extension;
	protected long fileSize;
	protected InputStream fileInputStream;
	protected byte[] byteArray;

	public static String newContent() {
		return UUID.randomUUID().toString();
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public String getSha() {
		return sha;
	}

	public String getFileContent() {
		return byteArray.toString();
	}

	public String getExtension() {
		return extension;
	}

	/**
	 * @return bufferedInputStream
	 */
	public InputStream getInputStream() {
		return new BufferedInputStream(new ByteArrayInputStream(byteArray));
	}

	/**
	 * @param extension  format such as .txt , contain .
	 * @param byteArray
	 */
	public RandomFile(String extension, byte[] byteArray) {
		this.extension = extension;
		this.byteArray = byteArray;
		fileSize = byteArray.length;
		sha = FileUtil.calcuteSHA_1(byteArray);
		fileName = formatFileName();

		LOGGER.info(toString());
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("\n================ ["
				+ extension.toUpperCase().substring(1)
				+ "] ================");
		String format = "\n|| =>[%-9s]:[%s]";

		stringBuilder.append(String.format(format, "filename", fileName));
		stringBuilder.append(String.format(format, "filesize", fileSize + "B"));
		stringBuilder.append(String.format(format, "sha", sha));
		stringBuilder.append(String.format(format, "extension", extension));
		stringBuilder.append("\n=======================================\n");

		return stringBuilder.toString();
	}

	private String formatFileName() {
		return new SimpleDateFormat("yyyyMMddhhmm").format(new Date())
				.substring(4) + "_" + sha + extension;
	}

}
