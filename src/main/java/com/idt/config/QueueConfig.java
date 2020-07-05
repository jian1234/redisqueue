package com.idt.config;

import com.idt.queue.consumer.MessageConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class QueueConfig {
    @Value("${platform.queue.upadateData.key}")
    private String queueUpdateDataKey;

    private MessageConsumer updateDataConsumer;

    @Resource(name="updateDataConsumer")
    public void setUpdateDataConsumer(MessageConsumer updateDataConsumer){
        this.updateDataConsumer = updateDataConsumer;
        System.out.println("启动同步数据队列消费线程!");
        Thread thread = new Thread((Runnable) updateDataConsumer);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                // TODO Auto-generated method stub
                t.start();
            }
        });
        thread.start();
    }
}
