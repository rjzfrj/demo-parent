package com.my.reflect;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import com.my.reflect.reflectionsuper.ReflectionUtils;
import com.my.reflect.reflectionsuper.Son;

/**
 * 测试类,用JUnit4 进行测试
 *
 */

public class ReflectionUtilsTest {

	/**
	 * 测试获取父类的各个方法对象
	 */
	
	@Test
	public void testGetDeclaredMethod() {
		
		Object obj = new Son() ;
		
		//获取公共方法名
		Method publicMethod = ReflectionUtils.getDeclaredMethod(obj, "publicMethod") ;
		System.out.println(publicMethod.getName());
		
		//获取默认方法名
		Method defaultMethod = ReflectionUtils.getDeclaredMethod(obj, "defaultMethod") ;
		System.out.println(defaultMethod.getName());
		
		//获取被保护方法名
		Method protectedMethod = ReflectionUtils.getDeclaredMethod(obj, "protectedMethod") ;
		System.out.println(protectedMethod.getName());
		
		//获取私有方法名
		Method privateMethod = ReflectionUtils.getDeclaredMethod(obj, "privateMethod") ;
		System.out.println(privateMethod.getName());
	}

	/**
	 * 测试调用父类的方法
	 * @throws Exception
	 */
	
	@Test
	public void testInvokeMethod() throws Exception {
		Object obj = new Son() ;
		
		//调用父类的公共方法
		ReflectionUtils.invokeMethod(obj, "publicMethod", null , null) ;
		
		//调用父类的默认方法
		ReflectionUtils.invokeMethod(obj, "defaultMethod", null , null) ;
		
		//调用父类的被保护方法
		ReflectionUtils.invokeMethod(obj, "protectedMethod", null , null) ;
		
		//调用父类的私有方法
		ReflectionUtils.invokeMethod(obj, "privateMethod", null , null) ;
	}

	/**
	 * 测试获取父类的各个属性名
	 */
	
	@Test
	public void testGetDeclaredField() {
		
		Object obj = new Son() ;
		
		//获取公共属性名
		Field publicField = ReflectionUtils.getDeclaredField(obj, "publicField") ;
		System.out.println(publicField.getName());
		
		//获取公共属性名
		Field defaultField = ReflectionUtils.getDeclaredField(obj, "defaultField") ;
		System.out.println(defaultField.getName());
		
		//获取公共属性名
		Field protectedField = ReflectionUtils.getDeclaredField(obj, "protectedField") ;
		System.out.println(protectedField.getName());
		
		//获取公共属性名
		Field privateField = ReflectionUtils.getDeclaredField(obj, "privateField") ;
		System.out.println(privateField.getName());
		
	}

	@Test
	public void testSetFieldValue() {
		
		Object obj = new Son() ;
		
		System.out.println("原来的各个属性的值: ");
		System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
		System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
		System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
		System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));
		
		ReflectionUtils.setFieldValue(obj, "publicField", "a") ;
		ReflectionUtils.setFieldValue(obj, "defaultField", "b") ;
		ReflectionUtils.setFieldValue(obj, "protectedField", "c") ;
		ReflectionUtils.setFieldValue(obj, "privateField", "d") ;
		
		System.out.println("***********************************************************");
		
		System.out.println("将属性值改变后的各个属性值: ");
		System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
		System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
		System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
		System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));
		
	}

	@Test
	public void testGetFieldValue() {
		
		Object obj = new Son() ;
		
		System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
		System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
		System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
		System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));
	}
	
	

	@Test
	public void testGetFieldValuess() throws IllegalArgumentException, IllegalAccessException {
		
		Son obj = new Son() ;
		
//		obj.setMoney(123.22);
//		obj.setId(999L);
		obj.setCreateTime(new Date());
		obj.setType(1);
//		Field[] fields=obj.getClass().getDeclaredFields();
//		for (int i = 0; i < fields.length; i++) {
//			System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, fields[i].toString()));
//		}
		Map map=convertBeanToMap(obj);
		System.out.println(map);
		
//		System.out.println("publicField = " + ReflectionUtils.getFieldValue(obj, "publicField"));
//		System.out.println("defaultField = " + ReflectionUtils.getFieldValue(obj, "defaultField"));
//		System.out.println("protectedField = " + ReflectionUtils.getFieldValue(obj, "protectedField"));
//		System.out.println("privateField = " + ReflectionUtils.getFieldValue(obj, "privateField"));
	}
	
	public static Field getDeclaredField(Object object, String fieldName){
		Field field = null ;
		
		Class<?> clazz = object.getClass() ;
		
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName) ;
				return field ;
			} catch (Exception e) {
				//这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
				
			} 
		}
	
		return null;
	}	
	
	
	
	
	/**
	 *能够遍历出对象中的所有属性包括父类中的属性
	 *可以不用实体的toString方法了因为那个会输出一些空的值
	 *
	 * @param bean
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws Exception
	 */
	public static TreeMap<String, String> convertBeanToMap(Object bean) throws IllegalArgumentException,IllegalAccessException {
        TreeMap<String, String> map = new TreeMap<String, String>();
        Class<?> clazz = bean.getClass();
        //关键代码适用了外层循环如果class不是Object.class往下循环找他的父类
        for(; clazz != Object.class;clazz = clazz.getSuperclass()){
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {// 获取bean的属性和值
                field.setAccessible(true);
                if (field.get(bean) != null) {
//                    if (field.get(bean) instanceof Double) {
//                        map.put(field.getName(), String.valueOf( field.get(bean)));
//                    } else if(field.get(bean) instanceof Long) {
                    	map.put(field.getName(), String.valueOf( field.get(bean)));
//                    }else if(field.get(bean) instanceof Long) {
//                        map.put(field.getName(), field.get(bean).toString());
//                    }
                }
            }
        }
        return map;
    }  


}

