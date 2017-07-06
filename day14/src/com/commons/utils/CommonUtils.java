package com.commons.utils;
/**
 * 工具包编写
 */
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {
	/**
	 * 生成不重复的32位长的大写字符串
	 * @return
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	public static <T>T toBean(Map map,Class<T> clazz){
		try {
			/**
			 * 1）创建指定javabean对象
			 */
			T bean =clazz.newInstance();
			/**
			 * 2）把map中的数据封装到bean中。
			 */
			BeanUtils.populate(bean, map);
			/**
			 * 3)返回javabean对象
			 */
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
