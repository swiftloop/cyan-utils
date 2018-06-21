package com.framework.utils;


import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author Sorata
 * @date 2018/6/21 17:35
 */
public class XFNumberUtils {

	private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

	private static DecimalFormat decimalFormat2 = new DecimalFormat("#0.00%");

	private static final float FLAG = 0.1f;

	/**
	 * 随机一个长度数字
	 *
	 * @param x 随机的长度
	 * @return 随机数字的字符串
	 */
	public static String rand(int x) {

		double mo = nextDouble();
		while (mo < FLAG) {
			mo = nextDouble();
		}

		return String.valueOf((int) (Math.pow(10, x) * mo));
	}

	/**
	 * 随机一个指定长度的整数
	 *
	 * @param x 指定的长度
	 * @return int
	 */
	public static int randIntLen(int x) {
		double mo = nextDouble();
		while (mo < FLAG) {
			mo = nextDouble();
		}

		return (int) (Math.pow(10, x) * mo) ;
	}


	private static double nextDouble() {
		Random random = new Random();
		return random.nextDouble();
	}


	private static int randInt(int m) {
		Random random = new Random();
		return random.nextInt(m);
	}

	/**
	 * 获取一个范围的随机数
	 *
	 * @param x 最小值 包含
	 * @param y 最大值 不包含
	 * @return int
	 */
	public static int randInt(int x, int y) {
		int m = randInt(y);
		if (m >= x) {
			return m;
		}
		return randInt(x, y);
	}

	/**
	 * 获取当前的时间戳
	 *
	 * @return unix时间戳 到秒
	 */
	public static long dateTime() {
		return System.currentTimeMillis() / 1000;
	}


	/**
	 * 转化为货币格式
	 *
	 * @param f 需转的数
	 * @return flat
	 */
	public static Float getPrice(Float f) {
		return Float.valueOf(decimalFormat.format(f));
	}

	/**
	 * 转化为货币格式
	 *
	 * @param d 需转化的数
	 * @return double
	 */
	public static Double getPrice(Double d) {
		return Double.valueOf(decimalFormat.format(d));
	}


	/**
	 * 返回一个百分比数
	 *
	 * @param f 需要转化的数
	 * @return 百分比格式的数
	 */
	public static String getPercent(Float f) {
		return decimalFormat2.format(f);
	}

	/**
	 * 返回一个百分比数
	 *
	 * @param d double类型的数字
	 * @return string
	 */
	public static String getPercent(Double d) {
		return decimalFormat2.format(d);
	}


}
