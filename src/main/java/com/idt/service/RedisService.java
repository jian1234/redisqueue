package com.idt.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
    List<Map<String,Object>> getRedis();

    public boolean expire(String key, long time) ;

    public long getExpire(String key);

    public boolean containsKey(String key);

    public void  remove(String ...key );

    public Object get( String key  );

    public Object get(String ...keys) ;

    public boolean set(String key , Object value );

    public boolean set(String key , List<Object> values );

    public boolean set(String key , Object value, long time);
}
