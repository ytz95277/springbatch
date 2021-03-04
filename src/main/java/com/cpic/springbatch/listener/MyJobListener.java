package com.cpic.springbatch.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/5
 * Time: 上午12:10
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//通过实现接口的方式创建监听
public class MyJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance().getJobName()+"\t before...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getJobInstance().getJobName()+"\t after...");
    }
}