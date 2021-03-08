package com.cpic.springbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/8
 * Time: 下午8:55
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Configuration
public class StepParameterListener implements StepExecutionListener {
    public Map<String, JobParameter> parameters;
    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.parameters = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}