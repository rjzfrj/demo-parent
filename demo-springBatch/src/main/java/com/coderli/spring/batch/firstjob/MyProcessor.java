package com.coderli.spring.batch.firstjob;


import org.springframework.batch.item.ItemProcessor;  

import com.alibaba.fastjson.JSON;
  
  
/** 
* 
* @author OneCoder 
* @date 2014年9月28日 下午2:39:48 
*/  
public class MyProcessor implements ItemProcessor<MyModel, String> {  
  
     @Override  
     public String process(MyModel item) throws Exception {  
    	 System.out.println( "This is my process in step two of job: [MyJob].");  
    	 System.out.println( "Transfer MyModel to JSON string.");  
         return JSON.toJSONString(item);  
     }  
}  