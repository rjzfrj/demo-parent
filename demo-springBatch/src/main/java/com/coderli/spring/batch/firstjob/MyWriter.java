package com.coderli.spring.batch.firstjob;

import java.util.List;  

  
import org.springframework.batch.item.ItemWriter;  
  
/** 
* 自定义Writer类 
* 
* @author OneCoder 
* @date 2014年9月28日 下午2:46:20 
*/  
public class MyWriter implements ItemWriter<String> {  
  
     @Override  
     public void write(List<? extends String> items) throws Exception {  
            System.out.println( "This is my writer in step two for job: [MyJob].");  
            System.out.println( "Write the JSON string to the console.");  
           for (String item : items) {  
                System.out.println( "Write item: {}"+item);  
          }  
     }  
  
}  