package com.idt.controller;

import com.idt.service.RedisService;
import com.idt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RedisController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private TestService testService;
    @RequestMapping("getList")
    public List<Map<String,Object>> getList(){
        return  redisService.getRedis();
    }

    @RequestMapping("getRedis")
    public Object getRedis(){
        return  testService.getDataFromRedis();
    }
}
