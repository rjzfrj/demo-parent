package com.allscore.wireless.common;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties properties;
	static {
		// 加载属性文件
		try {
			InputStream inputStream = PropertyUtil.class.getClassLoader()
					.getResourceAsStream("/config.properties");
			try {
				properties = new Properties();
				properties.load(inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static Properties getProperties() {
		return properties;
	}
}
