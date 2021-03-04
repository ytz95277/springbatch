package com.cpic.springbatch.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/4
 * Time: 下午9:32
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class MyDecider implements JobExecutionDecider {
    private int count;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        if (count%2==0) return new FlowExecutionStatus("even");
        else return new FlowExecutionStatus("odd");
    }
}