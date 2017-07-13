package com.coderli.spring.batch.firstjob;

import org.springframework.batch.item.ItemReader;  
import org.springframework.batch.item.NonTransientResourceException;  
import org.springframework.batch.item.ParseException;  
import org.springframework.batch.item.UnexpectedInputException;  
  
/** 
* 自定义Reader类 
* 
* @author OneCoder 
* @date 2014年9月28日 下午2:33:43 
*/  
public class MyReader implements ItemReader<MyModel> {  
  
     private int count;  
  
     @Override  
     public MyModel read() throws Exception, UnexpectedInputException,  
              ParseException, NonTransientResourceException {  
           System.out.println( "This is my reader in step two of job: [MyJob.]");  
          MyModel model = null;  
           if ( count < 2) {  
               model = new MyModel();  
               model.setDescription( "My Description");  
               model.setId( "My ID");  
               model.setName( "My Name");  
               count++;  
          }  
           return model;  
     }  
  
}  