package com.utaha.kazusa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Value("${spring.data.redis.host}")
    private String redisHost;
    @Value("${spring.data.redis.port}")
    private String redisPort;
    @Value("${spring.data.redis.password}")
    private String redisPwd;

    /**
     * 设置序列化和反序列化
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        /*
          这个错误提示是因为在使用Spring Data Redis时，尝试将包含java.time.LocalDateTime类型的对象序列化为JSON时出现了问题。
          java.time包下的日期时间类型（如LocalDateTime）在默认情况下不被Jackson库支持，而Jackson是Spring Data Redis用于序列化和反序列化JSON的默认库。
          为了解决这个问题，你需要添加jackson-datatype-jsr310这个模块到你的项目中，该模块提供了对Java 8日期时间API的支持。
         */
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(om);
        template.setValueSerializer(serializer);

        // 如果需要的话，还可以设置hash key和value的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * 配置分布式锁的redisson
     *
     * @return RedissonClient
     */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        //单机方式
        config.useSingleServer()
//                .setPassword(redisPwd)//密码为空需要不设置Password
                .setAddress("redis://" + redisHost + ":" + redisPort);
        //集群
        //config.useClusterServers().addNodeAddress("redis://192.31.21.1:6379","redis://192.31.21.2:6379")
        return Redisson.create(config);
    }

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
