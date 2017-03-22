package com.my.wallet.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang3.StringUtils;



/****
 * 终端接口返回值xml生成类
 * 注：类属性中不能存在Map
 * @author user
 *
 */
public class ConverTool{
	
	//反射缓存，第一次生成此类的xml时生成，以后都从缓存获取要转换的属性及get方法
	private static Map<Class,List<FieldCache>> FIELDS_MAP_CACHE = new ConcurrentHashMap<Class,List<FieldCache>>();
	public static ThreadLocal<String> AcceptType = new ThreadLocal<String>();
	public static JsonConfig jsonConfig = new JsonConfig();
	
	static{
		jsonConfig.setJsonPropertyFilter(new IgnoreFieldProcessorImpl());
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonConfig.registerJsonValueProcessor(Timestamp.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
	}
	
	/****
	 * 生成指定对象的返回xml字符串
	 * @param bean 指定对象
	 * @param name xml中外层节点名称，如果为空则没有外层节点
	 * @return
	 * @throws Exception
	 */
	public static StringBuffer conver(Object bean,String name) throws Exception{
		//对象如果为空，则直接返回
		if(bean==null) return new StringBuffer();
		
		if(ListBaseBean.class.isAssignableFrom(bean.getClass())){
			//如果是list类对象则调用list对象的方法生成xml
			return ((ListBaseBean)bean).converJson(name);
		}else if(isBaseClass(bean.getClass())){
			//如果是String、Integer等基础类型则直接拼接
			return converItemJson(name,bean);
		}
		
		//其余类对象，通过反射机制拼接xml
		return converJson(bean,name);
	}

	private static StringBuffer converItemJson(String name, Object value) {
		JSONObject json = new JSONObject();
		json.put(name, value);
		return new StringBuffer(json.toString());
	}
	
	private static Map<String, String> initIgnoreFiled(Object bean) {
		Map<String, String> fieldMap= new HashMap<String, String>();
		try{
			Method getMethod = bean.getClass().getMethod("getNotConverXmlFields");
			if(getMethod!=null){
				String notConverXmlFields = (String)getMethod.invoke(bean, new Object[0]);
				if(notConverXmlFields!=null){
					String[] arr = notConverXmlFields.split(",");
					for(String fieldName:arr){
						fieldMap.put(fieldName, fieldName);
					}
				}
			}
		}catch(Exception e){
			
		}
		return fieldMap;
	}

	private static StringBuffer converJson(Object bean, String name ) {
		JSONObject json = null;
		if(StringUtils.isNotBlank(name)){
			json = new JSONObject();
			if(bean instanceof List){
				json.put(name, JSONArray.fromObject(bean,jsonConfig));
			}else{
				json.put(name, JSONObject.fromObject(bean,jsonConfig));
			}
		}else{
			if(bean instanceof List){
				return new StringBuffer(JSONArray.fromObject(bean,jsonConfig).toString());
			}else{
				json = JSONObject.fromObject(bean,jsonConfig);
			}
		}
		return new StringBuffer(json.toString());
	}
	
	private static boolean isBaseClass(Class curClass){
		if(String.class.equals(curClass)  || StringBuffer.class.equals(curClass)
				|| Integer.class.equals(curClass)  || Long.class.equals(curClass)
				|| Double.class.equals(curClass)  || Float.class.equals(curClass)
				|| Boolean.class.equals(curClass) || Date.class.equals(curClass)
				|| curClass==null
			){
			return true;
		}
		return false;
	}

}

/***
 * 缓存类，用于缓存反射中用到的属性及方法
 * @author user
 *
 */
class FieldCache {
	private Field field;
	private Method method;
	
	public FieldCache(Field field, Method method) {
		super();
		this.field = field;
		this.method = method;
	}
	
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
}



