package com.idt.service.impl;

import com.idt.queue.produce.ProducerService;
import com.idt.service.RedisService;
import com.idt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisService redisService;
    @Resource(name = "producerService")
    private ProducerService producerService;

    @Override
    public Object getDataFromRedis() {
//        redisService.set("xjkey","我是测试redis",60);
//        Object data = redisService.get("xjkey");
//        System.out.println(data);
        for (int i = 0; i < 500; i++) {
            producerService.addUpdateMessage("待处理消息"+i);
        }
        return "完成";
    }
}
