package com.idt.queue.consumer.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.idt.queue.consumer.MessageConsumer;

@Service("updateDataConsumer")
public class MessageConsumerImpl implements MessageConsumer, Runnable {

	@Value("${platform.queue.upadateData.key}")
	private String queueUpdateDataKey ;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, Object> redisTemplate ;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (true) {
			while (true) {
				Object message = redisTemplate.opsForList().leftPop(queueUpdateDataKey, 0, TimeUnit.MINUTES);
				if(message != null){
					handleMessage(message);
				}
			}
		}
	}

	@Override
	public void handleMessage(Object message) {
		// TODO Auto-generated method stub
		System.out.println("sql"+message);
//		logger.error("error");
//		logger.warn("warn");
	}

}
