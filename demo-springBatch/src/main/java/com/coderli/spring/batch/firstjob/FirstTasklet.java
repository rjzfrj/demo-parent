package com.coderli.spring.batch.firstjob;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FirstTasklet implements Tasklet {

     @Override
     public RepeatStatus execute(StepContribution contribution,
              ChunkContext chunkContext) throws Exception {
//           log.info( "This is tasklet one in step one of job MyJob");
           return RepeatStatus.FINISHED;
     }
}