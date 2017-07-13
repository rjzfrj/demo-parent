package com.coderli.spring.batch.firstjob;


import org.junit.Assert;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.batch.core.JobExecution;  
import org.springframework.batch.test.JobLauncherTestUtils;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

/** 
* 任务启动器，通过JUnit测试方式启动 
* 
* @author OneCoder 
* @date 2014年9月28日 下午3:03:03 
*/  
@RunWith(SpringJUnit4ClassRunner. class)  
@ContextConfiguration(locations = { "classpath:batch-context.xml" })  
public class MyFirstJobTest {  

   @Autowired  
   private JobLauncherTestUtils jobLauncherTestUtils;  


   @Test  
   public void testJob() throws Exception {  
         jobLauncherTestUtils.launchJob();  
        JobExecution jobExecution = jobLauncherTestUtils .launchJob();  
        Assert. assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());  
   }  
}