package com.googlecode.test.toolkit.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class DocFile extends RandomFile {

	public static final String EXTENSION = FileType.DOC.toString();
	private static final String WORD_TYPE = "WordDocument";
	
	private static byte[] getContentBytes() {
		byte byteArray[] = FileUtil.getRandomBytes();
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
 		try {
			POIFSFileSystem fs = new POIFSFileSystem();
			DirectoryEntry directory = fs.getRoot();
			directory.createDocument(WORD_TYPE, byteArrayInputStream);
			fs.writeFilesystem(byteArrayOutputStream);

			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
 			throw new RandomFileException("create doc file content fail", e);
		} finally {
			IOUtils.closeQuietly(byteArrayInputStream);
			IOUtils.closeQuietly(byteArrayOutputStream);
		}
	}

	public DocFile() {
		super(EXTENSION, getContentBytes());
	}


}
