package com.idt.service.impl;

import com.idt.dao.RedisDao;
import com.idt.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisDao redisDao;

    private RedisTemplate<String,Object> redisTemplate;
    @Resource(name="redisTemplate")
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Map<String,Object>> getRedis() {
        return redisDao.getlist();
    }

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }


    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void remove(String ... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                Collection<String> keys = Arrays.asList(key) ;
                redisTemplate.delete(keys);
            }
        }
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public Object get(String ...keys) {

        StringBuffer sb = new StringBuffer();
        for(String key : keys ){
            if( key != null ){
                Object val = redisTemplate.opsForValue().get(key) ;
                if( val != null ){
                    sb.append( val );
                }
            }
        }
        return sb.toString() ;

    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("redis插入时报错！");
            return false;
        }

    }

    public boolean set(String key , List<Object> values ){
        try {
            for(int i = 0 ; i < values.size() ; i ++ ){
                redisTemplate.opsForValue().set(key+ "." + i, values.get(i),0);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
