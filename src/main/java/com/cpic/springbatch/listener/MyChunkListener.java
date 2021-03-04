package com.cpic.springbatch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * Created with IntelliJ IDEA.
 * User: jessica
 * Date: 2021/3/5
 * Time: 上午12:14
 * To change this template use File | Settings | File Templates.
 * Description:
 */
//通过注解的方式创建监听
public class MyChunkListener {
    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        System.out.println(context.getStepContext().getStepName()+"\t before");
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        System.out.println(context.getStepContext().getStepName()+"\t after");
    }
}