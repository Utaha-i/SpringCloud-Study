package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 高级特性：优先级
 */
@Configuration
public class RabbitPriorityConfig {
    // 定义交换机
    private final String EXCHANGE_NAME = "priority_exchange";

    // 定义队列
    private final String QUEUE_NAME = "priority_queue";

    //创建交换机
    @Bean(value = "bootPriorityExchange")
    public Exchange getPriorityExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(true)  // 持久化
                .build();
    }

    // 创建队列
    @Bean(value = "bootPriorityQueue")
    public Queue getPriorityQueue() {
        return QueueBuilder
                .durable(QUEUE_NAME)
                .maxPriority(10) // 设置优先级参数值
                .build();
    }

    // 创建交换机绑定队列
    @Bean
    public Binding bindingPriorityExchangeQueue(@Qualifier("bootPriorityExchange") Exchange exchange, @Qualifier("bootPriorityQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.cx_active.#")
                .noargs();
    }
}