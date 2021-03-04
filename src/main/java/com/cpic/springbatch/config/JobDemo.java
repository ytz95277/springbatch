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
 * Time: 下午8:28
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
@EnableBatchProcessing
public class JobDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    //Job执行的是step,一个job可以有多个step
    @Bean
    public Job jobDemoJob() {
        //start...next:按照指定步骤顺序执行
        //return jobBuilderFactory.get("jobDemoJob").start(step1()).next(step2()).next(step3()).build();
        //on用于判断满足什么条件时执行后续流程
        //fail(),stopAndRestart()
        return jobBuilderFactory.get("jobDemoJob").start(step1())
                .on("COMPLETED").to(step2())
                .from(step2()).on("COMPLETED").to(step3())
                .from(step3()).end()
                .build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                //具体执行的内容,需要将执行结果返回,用于判断后续流程是否执行
                System.out.println("step3");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("step2");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                System.out.println("step1");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}