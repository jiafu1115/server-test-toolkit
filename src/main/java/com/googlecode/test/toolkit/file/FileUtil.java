package com.googlecode.test.toolkit.file;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

import com.googlecode.test.toolkit.util.DigestUtil;
import com.googlecode.test.toolkit.util.ValidationUtil;
import com.googlecode.test.toolkit.util.DigestUtil.DigestAlgorithm;

public final class FileUtil {

	static String calcuteSHA_1(byte[] byteArray) {
		return DigestUtil.getDigest(byteArray, DigestAlgorithm.SHA_1);
	}

	static String calcuteSHA_1(InputStream inputStream) throws IOException {
		return DigestUtil.getDigest(inputStream, DigestAlgorithm.SHA_1);
 	}

	static byte[] generateBytes(long sizeInByte) {
		ValidationUtil.checkPositive(sizeInByte);

		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteOutputStream, 1024 * 512);

		int allocateSize = 32;
 		int storeSize = 0;
		byte[] bytes = null;
		try {
			while (storeSize < sizeInByte) {
				byte[] array = new byte[allocateSize];
				Arrays.fill(array, (byte) (new Random().nextInt()));

				int needBytesCount = (int) (sizeInByte - storeSize);
				if (allocateSize > needBytesCount)
					bufferedOutputStream.write(array, 0, needBytesCount);
				else
					bufferedOutputStream.write(array);
				storeSize += allocateSize;
			}
			bufferedOutputStream.flush();
		} catch (IOException e) {
			throw new RandomFileException(e.getMessage(),e);
		} finally {
			bytes = byteOutputStream.toByteArray();
			IOUtils.closeQuietly(bufferedOutputStream);
 		}

		return bytes;
	}

	static int getRandomSizeInByteUnit() {
		return new Random().nextInt(10000) + 1;
	}

	static byte[] getRandomBytes() {
		return getRandomString().getBytes();
	}

	static String getRandomString() {
		return UUID.randomUUID().toString();
	}

	private FileUtil() {
 	}

}
