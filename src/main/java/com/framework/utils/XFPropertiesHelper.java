package com.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Sorata
 * @date 2018/6/21 17:28
 */
public class XFPropertiesHelper {

	/** 读取 properties 文件
	 * @param inputStream 文件输入流
	 * @return map
	 */
	public static Map<String, String> loadProperties(InputStream inputStream) {
		try {
			Map<String, String> map = new HashMap<>(16);
			Properties properties = new Properties();
			properties.load(inputStream);
			Enumeration en = properties.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = properties.getProperty(key);
				map.put(key, value);
			}
			return map;
		} catch (IOException e) {
			//当出现异常时 读取文件失败
			e.printStackTrace();
			return new HashMap<>(0);
		} finally {
			if (null!=inputStream){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
