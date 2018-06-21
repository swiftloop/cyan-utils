package com.framework.utils;

import java.io.*;

/**
 * @author Sorata
 * @date 2018/6/21 17:35
 */
public class XFFileUtils {
	/**
	 * 文件扩展名间隔符
	 */
	private static final char EXTENSION_SEPARATOR = '.';

	private static final String EXTENSION_SEPARATOR_STRING = Character.toString(EXTENSION_SEPARATOR);

	private static final char UNIX_SEPARATOR = '/';

	private static final char WINDOWS_SEPARATOR = '\\';

	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;


	public static boolean isWindows() {
		return WINDOWS_SEPARATOR == File.separatorChar;
	}

	/**
	 * 获取文件的扩展名
	 *
	 * @param fileName 带有形如 .jpg  .png .mp4后缀的文件名字 判定依据 '.'
	 * @return jpg png
	 */
	public static String getExtension(String fileName) {
		if (null == fileName) {
			return null;
		}
		if (fileName.length() - fileName.lastIndexOf(EXTENSION_SEPARATOR) == 1) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf(EXTENSION_SEPARATOR) + 1, fileName.length());
	}

	/**得到去掉后缀名的文件路径
	 * @param fileName 文件名
	 * @return C:\www\addd\3fdsfsd.jpg C:\www\addd\3fdsfsd
	 */
	public static String getExcludeExtensionFileName(String fileName){
		if (null == fileName) {
			return null;
		}
		if (fileName.length() - fileName.lastIndexOf(EXTENSION_SEPARATOR) == 1) {
			return null;
		}
		return fileName.substring(0,fileName.lastIndexOf(EXTENSION_SEPARATOR));
	}

	/**得到文件所在的目录
	 * @param fileName 文件url
	 * @return 去掉文件名的目录 C:\www\addd\3fdsfsd.jpg  C:\www\addd
	 */
	public static String getFilePath(String fileName){
		if (null == fileName) {
			return null;
		}
		String filePath = null;
		if (fileName.lastIndexOf(Character.toString(UNIX_SEPARATOR)) != -1) {
			filePath = fileName.substring(0, fileName.lastIndexOf(Character.toString(UNIX_SEPARATOR)));
		}
		if (fileName.lastIndexOf(Character.toString(WINDOWS_SEPARATOR)) != -1) {
			filePath = fileName.substring(0, fileName.lastIndexOf(Character.toString(WINDOWS_SEPARATOR)));
		}
		return filePath;
	}


	/**
	 * 重命名文件名字  将 123456.jpg 更名为 789.jpg
	 * C:\www\addd\3fdsfsd.jpg  C:\www\addd\ddaw.jpg
	 *
	 * @param oldName 旧文件名字 带扩展名
	 * @param newName 修改文件的名字  不带扩展名
	 * @return 新的文件名字
	 */
	public static String getCustomFileName(String oldName, String newName) {
		if (null == oldName) {
			return null;
		}
		if (null == newName) {
			return oldName;
		}
		String filePath = null;
		if (oldName.lastIndexOf(Character.toString(UNIX_SEPARATOR)) != -1) {
			filePath = oldName.substring(0, oldName.lastIndexOf(Character.toString(UNIX_SEPARATOR)) + 1);
		}
		if (oldName.lastIndexOf(Character.toString(WINDOWS_SEPARATOR)) != -1) {
			filePath = oldName.substring(0, oldName.lastIndexOf(Character.toString(WINDOWS_SEPARATOR)) + 1);
		}
		String fileName = newName + EXTENSION_SEPARATOR_STRING + getExtension(oldName);
		return filePath == null ? fileName : filePath + fileName;
	}


	/**
	 * copy 一个文件
	 *
	 * @param in     文件的输入流
	 * @param out    文件的输出流
	 * @param buffer buffer
	 * @return 文件的大小
	 * @throws IOException io异常
	 */
	private static long copyLarge(InputStream in, OutputStream out, byte[] buffer) throws IOException {
		long count = 0;
		int n;
		while (-1 != (n = in.read(buffer))) {
			out.write(buffer, 0, n);
			count += n;
		}
		return count;
	}


	/**
	 * copy  当一个文件的大小大于2g的时候使用该方法
	 *
	 * @param in  输入流
	 * @param out 输出流
	 * @return 返回文件的大小
	 * @throws IOException io异常
	 */
	public static long copyLarge(InputStream in, OutputStream out) throws IOException {
		return copyLarge(in, out, new byte[DEFAULT_BUFFER_SIZE]);
	}

	/**
	 * copy 当文件的大小小于2G的时候 使用该方法  当文件大于2G时，返回-1
	 *
	 * @param in  输入流
	 * @param out 输出流
	 * @return 返回文件的大小
	 * @throws IOException io异常
	 */
	public static int copy(InputStream in, OutputStream out) throws IOException {
		long count = copyLarge(in, out);
		if (count > Integer.MAX_VALUE) {
			return -1;
		}
		return (int) count;
	}


	/**文件copy 当文件不存在的时候 返回-1
	 * @param filePath 源文件
	 * @param newFilePath 目标文件
	 * @return 文件大小
	 */
	public static long copyFile(String filePath, String newFilePath) {
		if (null == filePath || filePath.length() < 1) {
			return -1;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			return -1;
		}
		FileInputStream in = null;
		FileOutputStream out = null;
		long result;
		try {
			in = new FileInputStream(file);
			out = new FileOutputStream(newFilePath);
			return copyLarge(in, out);
		} catch (Exception e) {
			result = -1;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
