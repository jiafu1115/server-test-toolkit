package com.googlecode.test.toolkit.file;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;


public class JpgFile extends RandomFile {

	public static final String EXTENSION = FileType.JPG.toString();

	private static int getRandomWidthOrHeight() {
		return new Random().nextInt(1024);
  	}
 
 	private static byte[] getContentBytes(int width, int height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException(String.format("width[%d] or height[%d] < 0 "
					+ System.getProperty("line.seperator"), width, height));

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics createGraphics = bufferedImage.getGraphics();
		createGraphics.drawString(FileUtil.getRandomString(), 0, height / 2);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
		try {
 			ImageIO.write(bufferedImage, "jpg", bufferedOutputStream);
  			return byteArrayOutputStream.toByteArray();
		}  catch (IOException e2) {
 			throw new RandomFileException("io exception when generating image file content", e2);
		} finally {
			IOUtils.closeQuietly(bufferedOutputStream);
		}
 	}

	public JpgFile() {
		this(getRandomWidthOrHeight(), getRandomWidthOrHeight());
 	}

	public JpgFile(int width, int height) {
		super(EXTENSION, getContentBytes(width, height));
	}
 
 }
