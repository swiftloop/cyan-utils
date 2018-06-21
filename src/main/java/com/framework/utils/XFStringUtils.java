package com.framework.utils;

import java.util.UUID;

/**
 * @author Sorata
 * @date 2018/6/21 17:25
 */
public class XFStringUtils {


	/**
	 * 文字替换 例如 abc${ddd12}def
	 *
	 * @param startRegex  起始的符号 ${
	 * @param endRegex    结束的符号  }
	 * @param oldStr      原字符  abc${ddd12}def
	 * @param replacement 需要换的字符 xyz
	 * @return 新字符 abcxyzdef
	 */
	public static String replace(String startRegex, String endRegex, String oldStr, String replacement) {

		if (null == oldStr || oldStr.length() == 0) {
			return "";
		}

		int start = oldStr.indexOf(startRegex);

		if (start == -1) {
			return oldStr;
		}

		int end = oldStr.indexOf(endRegex);

		if (end == -1) {
			return oldStr;
		}

		StringBuilder stringBuilder = new StringBuilder(oldStr.length() + replacement.length() - (end - start - 1));
		stringBuilder.append(oldStr, 0, start);
		stringBuilder.append(replacement);
		stringBuilder.append(oldStr, end + 1, oldStr.length());

		return stringBuilder.toString();
	}


	/**
	 * 字符翻转
	 *
	 * @param oldStr abcdef
	 * @return fedcba
	 */
	public static String reversal(String oldStr) {

		if (oldStr == null || oldStr.length() == 0) {
			return oldStr;
		}

		return reversal(oldStr.substring(1)) + oldStr.charAt(0);
	}


	/**
	 * 获取一个随机的UUID
	 *
	 * @return String  长度32
	 */
	public static String UUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


	/**
	 * 是否有内容
	 *
	 * @param str 需判断的字符串
	 * @return bool
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {return false;}
		for (int i = 0; i < str.length(); ++i) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否有长度
	 *
	 * @param str 字符串
	 * @return bool
	 */
	public static boolean hasLength(String str) {
		return null != str && str.length() > 0;
	}



	/**
	 * 是否为数字
	 *
	 * @param num 字符串
	 * @return bool
	 */
	public static boolean isNumber(String num) {
		if (!hasText(num)) {return false;}
		for (int i = 0; i < num.length(); ++i) {
			if (!Character.isDigit(num.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
