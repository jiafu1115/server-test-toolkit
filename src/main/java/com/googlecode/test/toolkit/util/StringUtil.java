package com.googlecode.test.toolkit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.test.toolkit.constants.MarkConstants;

public final class StringUtil {

	/**
	 * to format such as "stringOne stringTwo stringThird" which takes the
	 * {@value com.googlecode.test.toolkit.constants.MarkConstants#SPACE} as the spliter.
	 *
	 * @param strings
	 * @return such as a b c
	 */
	public static String concatWithSpace(String... strings) {
		return concat(MarkConstants.SPACE, strings);
	}

	public static String concat(String spliter, String... strings) {
		ValidationUtil.checkNull(strings);
		StringBuffer stringBuffer = new StringBuffer();
		for (String string : strings) {
			stringBuffer.append(string);
			stringBuffer.append(spliter);
		}

		return stringBuffer.subSequence(0, stringBuffer.lastIndexOf(spliter)).toString();
 	}

	/**
	 * concat all strings directly
	 *
	 * @param strings
	 * @return such as abc
	 */
	public static String concatDirectly(String... strings) {
		return concat(MarkConstants.EMPTY_STRING, strings);

	}

	/**
	 * concat all strings with {@value com.googlecode.test.toolkit.constants.MarkConstants#SEMICOLON}
	 *
	 * @param strings
	 * @return such as a;b;c
	 */
	public static String concatWithSemicolon(String... strings) {
		return concat(MarkConstants.SEMICOLON, strings);

	}

       /**
     * @param str
     * @param regexContainedStr
     * @return  the count of regexContainedStr in str
     */
  public static int getCountForContainedStr(String str,String regexContainedStr)
    {
    	 Pattern compile = Pattern.compile(regexContainedStr);
    	 Matcher matcher = compile.matcher(str);
    	 int count=0;
    	 while(matcher.find()){
    		 count++;
    	 }

    	 return count;
     }

	private StringUtil() {
	}

}
