package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 高级特性：存活时间
 */
@Configuration
public class RabbitSecondConfig {
    // 指定交换机名称
    private final String EXCHANGE_NAME = "live_exchange";

    // 指定队列名
    private final String QUEUE_NAME = "live_queue";

    // 创建交换机
    @Bean("bootSecondExchange")
    public Exchange getSecondExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(true) // 是否持久化
                .build();
    }

    // 创建队列
    @Bean("bootSecondQueue")
    public Queue getSecondMessageQueue() {
        return QueueBuilder
                .durable(QUEUE_NAME)
                .ttl(15000) // 设定该队列里所有消息的存活时间是 15秒
                .build();
    }

    // 创建交换机绑定队列
    @Bean
    public Binding bindingSecondExchangeQueue(@Qualifier("bootSecondExchange") Exchange exchange, @Qualifier("bootSecondQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.happyNewYears.#")   // 通配符模式 要匹配的路由键 RoutingKey
                .noargs();
    }
}