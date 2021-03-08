package com.cpic.springbatch.job;

import com.cpic.springbatch.listener.StepParameterListener;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/8
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class ParametersDemo implements StepExecutionListener{
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    Map<String, JobParameter> parameters;
    @Bean
    public Job parameterJob() {
        return jobBuilderFactory.get("parameterJob2")
                .start(parameterStep())
                .build();
    }

    /**
     * 如何给job传递参数?
     * job执行的是step,job中使用的参数是在step中使用,
     * 我们只需要给step传递参数,可以通过监听,使用step级别的监听来传递数.
     */
    @Bean
    public Step parameterStep() {
        return stepBuilderFactory.get("parameterStep2")
                .listener(new StepParameterListener())
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        //System.out.println(parameters.get("info"));
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameters = stepExecution.getJobParameters().getParameters();
        System.out.println(stepExecution.getStepName()+" before,携带参数:"+parameters);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}

