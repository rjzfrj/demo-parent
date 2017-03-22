package com.my.wallet.web.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.util.PropertyFilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
/**
 * <p>Title: 忽略属性</p>
 * <p>Description：忽略JAVABEAN的指定属性、是否忽略集合类属性</p>
 * 
 */
public class IgnoreFieldProcessorImpl implements PropertyFilter {

	private static Map<Class,Map<String,Boolean>> IGNORE_FIELDS_MAP_CACHE = new ConcurrentHashMap<Class,Map<String,Boolean>>();
	
	Log log = LogFactory.getLog(this.getClass());
 
	/**
	 * 空参构造方法<br/>
	 * 默认不忽略集合
	 */
	public IgnoreFieldProcessorImpl() {
		// empty
	}
 
	public boolean apply(Object source, String name, Object value) {
//		System.out.println("IGNORE_FIELDS_MAP_CACHE:"+source.getClass().getName()+"------>"+name);
		//剔除自定义属性和空值
		if(value==null||"notConverXmlFields".equals(name)){
			return true;
		}
		Map<String, Boolean> ignoreFields = IGNORE_FIELDS_MAP_CACHE.get(source.getClass());
		if(ignoreFields==null){
			try {
				ignoreFields = initIgnoreFieldsMap(source);
			} catch (Exception e) {}
		}
		if(ignoreFields!=null&&ignoreFields.get(name)!=null){
			return true;
		}
		
		return false;
	}
	
	private static Map<String, Boolean> initIgnoreFieldsMap(Object source) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		Method getMethod = source.getClass().getMethod("getNotConverXmlFields");
		if(getMethod!=null){
			String notConverXmlFields = (String)getMethod.invoke(source, new Object[0]);
			if(notConverXmlFields!=null){
				String[] arr = notConverXmlFields.split(",");
				for(String fieldName:arr){
					map.put(fieldName, true);
				}
			}
		}
		IGNORE_FIELDS_MAP_CACHE.put(source.getClass(),map);
		return map;
	}

}

