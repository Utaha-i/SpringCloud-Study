package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机
 */
@Configuration
public class RabbitConfig {
    // 指定交换机名称
    private final String EXCHANGE_NAME = "boot_topic_exchange";

    // 指定队列名
    private final String QUEUE_NAME = "boot_queue";

    // 创建交换机(主题)
    @Bean("bootExchange")
    public Exchange getExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(false) // 是否持久化
                .build();
    }

    // 创建队列
    @Bean("bootQueue")
    public Queue getMessageQueue() {
        return new Queue(QUEUE_NAME);
    }

    // 创建交换机绑定队列
    @Bean
    public Binding bindingExchangeQueue(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.happyNewYear.#")   // 通配符模式要匹配的路由键 RoutingKey
                .noargs();
    }
}