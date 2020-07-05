package com.idt.queue.produce.impl;

import com.idt.queue.produce.ProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("producerService")
public class ProducerServiceImpl implements ProducerService {
    RedisTemplate<String, Object> redisTemplate;
    @Value("${platform.queue.upadateData.key}")
    private String queueKey ;

    @Resource(name="redisTemplate")
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addUpdateMessage(Object message) {
        // TODO Auto-generated method stub
        this.sendMessage(queueKey, message);
    }

    private void sendMessage(String channel,Object message) {
        //System.out.println("通过rightPush(K key, V value)方法向最右边添加元素:" + list);
        this.redisTemplate.opsForList().rightPush(channel, message);
    }
}
