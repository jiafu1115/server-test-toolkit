package com.googlecode.test.toolkit.file;

import java.io.ByteArrayOutputStream;

import org.apache.commons.io.IOUtils;

import com.googlecode.test.toolkit.util.ValidationUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PdfFile extends RandomFile {

	public static final String EXTENSION = FileType.PDF.toString();

	private static byte[] getContentBytes(int pageNumber) {
		ValidationUtil.checkPositive(pageNumber);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			Document document = new Document();
			try {
				PdfWriter.getInstance(document, byteArrayOutputStream);
				document.open();
				document.setPageCount(pageNumber);
				document.add(new Paragraph(RandomFile.newContent()));
				for (int i = 0; i < pageNumber - 1; i++) {
					document.newPage();
					document.add(new Paragraph(RandomFile.newContent()));
				}
			} finally {
				if (document != null)
					document.close();
			}

			return byteArrayOutputStream.toByteArray();
		} catch (DocumentException e) {
			throw new RandomFileException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(byteArrayOutputStream);
		}
	}

	public PdfFile() {
		super(EXTENSION, getContentBytes(1));
	}

	public PdfFile(int pageNumber) {
		super(EXTENSION, getContentBytes(pageNumber));
	}

}
