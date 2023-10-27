package com.utaha.kazusa.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    /**
     * feign 日志记录级别
     * NONE：无日志记录（默认）
     * BASIC：只记录请求方法和 url 以及响应状态代码和执行时间。
     * HEADERS：记录请求和响应头的基本信息。
     * FULL：记录请求和响应的头、正文和元数据。
     *
     * @return Logger.Level
     */
    @Bean
    public Logger.Level feignLogLevel() {
        //设置feign客户端的日志打印级别为FULL
        return Logger.Level.FULL;
    }
}
