package com.cpic.springbatch.config;

import com.cpic.springbatch.listener.MyChunkListener;
import com.cpic.springbatch.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/5
 * Time: 上午12:21
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class JobListenerDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobListener() {
        return jobBuilderFactory.get("jobListener")
                .start(stepListener())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step stepListener() {
        return stepBuilderFactory.get("stepListener")
                .<String,String>chunk(2)   //read,process,writer.每读两次后处理一次,泛型用于指定读写类型
                .faultTolerant()//容错
                .listener(new MyChunkListener())
                .reader(read())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<String> writer() {
        return list -> list.forEach(System.out::println);
    }

    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("hello", "world", "springBatch", "hugh", "jessica"));
    }

}