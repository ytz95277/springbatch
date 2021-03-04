package com.cpic.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
 * Time: 下午7:50
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
@EnableBatchProcessing//支持批处理
public class JobConfiguration {
    //创建任务对象的对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    //任务的执行由step决定

    //创建step对象的工厂
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloWorldJob() {
        return jobBuilderFactory.get("helloWorldJob").start(step()).build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("hello world!");
                    return RepeatStatus.FINISHED;//返回此步骤执行结果
                }).build();//指定step的任务
    }
}