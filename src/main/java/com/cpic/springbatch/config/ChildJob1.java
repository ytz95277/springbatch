package com.cpic.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.jsr.configuration.xml.StepFactoryBean;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/4
 * Time: 下午10:25
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class ChildJob1 {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step childJob1Step() {
        return stepBuilderFactory.get("childJob1Step").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("childJob1Step");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Job childJob1Job() {
        return jobBuilderFactory.get("childJob1Job").start(childJob1Step()).build();
    }

}