package com.utaha.kazusa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

//    @Value("${spring.data.redis.host}")
//    private String redisHost;
//    @Value("${spring.data.redis.port}")
//    private String redisPort;
//    @Value("${spring.data.redis.password}")
//    private String redisPwd;

    /**
     * 设置序列化和反序列化
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 设置key的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置value的序列化器，使用Jackson2来处理对象序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 配置分布式锁的redisson
     * @return RedissonClient
     */
//    @Bean
//    public RedissonClient redissonClient(){
//        Config config = new Config();
//        //单机方式
//        config.useSingleServer().setPassword(redisPwd).setAddress("redis://"+redisHost+":"+redisPort);
//        //集群
//        //config.useClusterServers().addNodeAddress("redis://192.31.21.1:6379","redis://192.31.21.2:6379")
//        return Redisson.create(config);
//    }

    /**
     * 集群模式
     * 备注：可以用"rediss://"来启用SSL连接
     */
    /*
    @Bean
    public RedissonClient redissonClusterClient() {
        Config config = new Config();
        config.useClusterServers().setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
              .addNodeAddress("redis://127.0.0.1:7000")
              .addNodeAddress("redis://127.0.0.1:7002");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
    */
 
}
