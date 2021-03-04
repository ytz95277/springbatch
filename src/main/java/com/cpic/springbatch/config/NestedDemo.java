package com.cpic.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/4
 * Time: 下午10:31
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class NestedDemo {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private Job childJob1Job;
    @Autowired
    private Job childJob2Job;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private JobRepository repository;
    @Autowired
    private PlatformTransactionManager manager;

    @Bean
    public Job nestedDemoJob() {
        return jobBuilderFactory.get("nestedDemoJob").start(childJob1(repository, manager)).next(childJob2(repository, manager)).build();
    }

    private Step childJob2(JobRepository repository, PlatformTransactionManager manager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                .job(childJob2Job)
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(manager)
                .build();
    }

    private Step childJob1(JobRepository repository,PlatformTransactionManager manager) {
        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(childJob1Job)
                .launcher(jobLauncher)
                .repository(repository)
                .transactionManager(manager)
                .build();
    }
}