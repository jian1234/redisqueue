package com.idt.dao;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RedisDao {
    List<Map<String,Object>> getlist();
}
