package com.googlecode.test.toolkit.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * @author fu.jian
 * date Aug 10, 2012
 */
public final class DigestUtil {

	public static final DigestAlgorithm DEFAULT_DIGEST_ALGORITHM = DigestAlgorithm.MD5;
	/** * 1M */
	private static final int DEFAULT_UPDATE_MESSAGE_DIGEST_SIZE = 1024 * 1024;

	private static final Logger LOG = Logger.getLogger(DigestUtil.class);

	public static enum DigestAlgorithm {

		MD5("md5"), SHA_1("sha-1"), SHA_256("sha-256"), SHA_384("sha-384"), SHA_512("sha-512");

		private String value;

		private DigestAlgorithm(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value.toLowerCase();
		}
	}

	private static abstract class DigestComputor {
		private DigestAlgorithm algorithm;

		public DigestComputor(DigestAlgorithm digestAlgorithm) {
			super();
			this.algorithm = digestAlgorithm;
		}

		String compute() {
			MessageDigest instance;
			try {
				instance = MessageDigest.getInstance(algorithm.toString());
				updateMessageDigest(instance);
				return hex(instance.digest());
			} catch (NoSuchAlgorithmException e) {
				LOG.error(e.getMessage(), e);
				throw new UnsupportedOperationException(e.getMessage(), e);
			}
		}

		private String hex(byte[] arr) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; ++i) {
				sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}

		abstract void updateMessageDigest(MessageDigest instance);
	}

	private static class ByteBufferDigestComputor extends DigestComputor {
		private final ByteBuffer byteBuffer;

		public ByteBufferDigestComputor(ByteBuffer byteBuffer, DigestAlgorithm digestAlgorithm) {
			super(digestAlgorithm);
			this.byteBuffer = byteBuffer;
		}

		@Override
		void updateMessageDigest(MessageDigest instance) {
			instance.update(byteBuffer);
		}
	}

	private static class ByteArrayDigestComputor extends DigestComputor {
		private final byte[] byteArray;

		public ByteArrayDigestComputor(byte[] byteArray, DigestAlgorithm digestAlgorithm) {
			super(digestAlgorithm);
			this.byteArray = byteArray;
		}

		@Override
		void updateMessageDigest(MessageDigest instance) {
			instance.update(byteArray);
		}
	}

	private static class InputStreamDigestComputor extends DigestComputor {
		private final InputStream inputStream;

		public InputStreamDigestComputor(InputStream inputStream, DigestAlgorithm digestAlgorithm) {
			super(digestAlgorithm);
			this.inputStream = inputStream;
		}

		@Override
		void updateMessageDigest(MessageDigest instance) {
			byte[] byteArray = new byte[DEFAULT_UPDATE_MESSAGE_DIGEST_SIZE];
			int length;
			try {
				while ((length = inputStream.read(byteArray)) != -1) {
					instance.update(byteArray, 0, length);
				}
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	}

	public static String getDigest(ByteBuffer byteBuffer, DigestAlgorithm digestAlgorithm) {
		return new ByteBufferDigestComputor(byteBuffer, digestAlgorithm).compute();
	}

	public static String getDigest(byte[] byteArray, DigestAlgorithm digestAlgorithm) {
		return new ByteArrayDigestComputor(byteArray, digestAlgorithm).compute();
	}

	public static String getDigest(String filePath) throws FileNotFoundException, IOException {
		return getDigest(filePath, DEFAULT_DIGEST_ALGORITHM);
	}

	public static String getDigest(String filePath, DigestAlgorithm digestAlgorithm)
			throws FileNotFoundException, IOException {
		ValidationUtil.checkString(filePath);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(
				filePath));
		try {
			return getDigest(bufferedInputStream, digestAlgorithm);
		} finally {
			IOUtils.closeQuietly(bufferedInputStream);
		}
	}

	public static String getDigest(InputStream inputStream, DigestAlgorithm digestAlgorithm)
			throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		try {
			return new InputStreamDigestComputor(bufferedInputStream, digestAlgorithm).compute();
		} catch (RuntimeException runtimeException) {
			if (runtimeException.getCause() instanceof IOException) {
				throw (IOException) runtimeException.getCause();
			} else
				throw runtimeException;
		} finally {
			IOUtils.closeQuietly(bufferedInputStream);
		}
	}

	private DigestUtil() {
	}

}