package com.idt.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.util.HashSet;
import java.util.Set;

public class RedisConfig {
    @Value("${spring.cache.redis.isCluster}")
    private boolean isCluster;
    @Value("${spring.cache.redis.servers}")
    private String servers;
    @Value("${spring.cache.redis.maxRedirects}")
    private int maxRedirects;
    @Value("${spring.cache.redis.password}")
    private String password;
    @Value("${spring.cache.redis.maxActive}")
    private int maxActive;
    @Value("${spring.cache.redis.maxIdle}")
    private int maxIdle;
    @Value("${spring.cache.redis.minIdle}")
    private int minIdle;
    @Value("${spring.cache.redis.maxWait}")
    private int maxWait;
    @Value("${spring.cache.redis.timeout}")
    private int timeout;

    /*@Bean
    public JedisConnectionFactory getJedisConnectionFactory(){
        JedisConnectionFactory factory = null;
        if (isCluster){
            RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
            clusterConfiguration.setMaxRedirects(this.maxRedirects);
            String[] servers = this.servers.split(",");
            for (String server:servers) {
                String[] serverPort = server.split(":");
                RedisClusterNode clusterNode = new RedisClusterNode(serverPort[0],Integer.valueOf(serverPort[1]));
                clusterConfiguration.addClusterNode(clusterNode);
            }
            JedisPoolConfig config =  new JedisPoolConfig();
            config.setMaxTotal(this.maxActive);
            config.setMaxIdle(this.maxIdle);
            config.setMinIdle(this.minIdle);
            config.setMaxWaitMillis(this.maxWait);
            config.setTestOnBorrow(true);
            factory = new JedisConnectionFactory(clusterConfiguration,config);
            factory.setUsePool(true);
            factory.setTimeout(this.timeout);
        }else{
            factory = new JedisConnectionFactory();
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(this.maxActive);
            config.setMaxIdle(this.maxIdle);
            config.setMinIdle(this.minIdle);
            config.setMaxWaitMillis(this.maxWait);
            config.setTestOnBorrow(true);
            String[] serverAndPost = this.servers.split(":") ;
            JedisShardInfo shardInfo = new JedisShardInfo(serverAndPost[0],Integer.valueOf(serverAndPost[1]));
            if(this.password != null && !this.password.trim().equals("")
                    && !this.password.equals("${platform.queue.redis.password}")){
                shardInfo.setPassword(this.password);
            }
            factory.setUsePool(true);
            factory.setTimeout(this.timeout);
            factory.setPoolConfig(config);
            factory.setShardInfo(shardInfo);
        }
        return factory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Object> getRedisTemplate(){
        RedisTemplate<String,Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(getJedisConnectionFactory());
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        JdkSerializationRedisSerializer jdkserializer = new JdkSerializationRedisSerializer();
        template.setValueSerializer(jdkserializer);
        return template;
    }*/

    /*// 序列化
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        // 配置连接工厂
        template.setConnectionFactory(factory);
        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;

    }*/
}
