package com.googlecode.test.toolkit.util;

import static com.googlecode.test.toolkit.constants.MarkConstants.*;

import org.apache.log4j.Logger;

/**
 * @author fu.jian
 */
public final class PathUtil {

	private final static Logger LOGGER = Logger.getLogger(PathUtil.class);

	/**
	 * path formatted to : /xx/yy/zz
	 *
	 * @param path
	 * @return format such as: /xx/yy/zz
	 */
	public static String formatPath(String path) {
		ValidationUtil.checkString(path);
		String returnPath = path.trim();
		if (!returnPath.startsWith(SPLIT))
			returnPath = SPLIT + returnPath;
		if (returnPath.endsWith(SPLIT))
			returnPath = returnPath.substring(0, returnPath.length() - 1);

		LOGGER.info(String
					.format("[util]convert [%s] to [%s]", path, returnPath));
		return returnPath;
	}

	private PathUtil() {
	}
}
