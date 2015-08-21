package com.googlecode.test.toolkit.file;

import com.googlecode.test.toolkit.util.ValidationUtil;

public class RandomFileFactory {

	public static RandomFile newRandomFile(FileType fileType) {
		ValidationUtil.checkNull(fileType);

		if (fileType == FileType.TXT)
			return new TxtFile();
		if (fileType == FileType.DOC)
			return new DocFile();
		if (fileType == FileType.JPG)
			return new JpgFile();
		if (fileType == FileType.PDF)
			return new PdfFile();

		throw new UnsupportedOperationException("not support this file type: "
				+ fileType.toString());
	}

	public static RandomFile newRandomFileForTxt() {
		return newRandomFile(FileType.TXT);
	}

	public static RandomFile newRandomFileForPdf(int pageNumber) {
		ValidationUtil.checkPositive(pageNumber);
		return new PdfFile(pageNumber);
	}

	public static RandomFile newRandomFileForJpg(int width, int height) {
		ValidationUtil.checkPositive(width, height);
		return new JpgFile(height, height);
	}

	public static RandomFile newRandomFileForTxt(long sizeInByte) {
		ValidationUtil.checkPositive(sizeInByte);
		return new TxtFile(sizeInByte);
	}

	private RandomFileFactory() {
	}
}
