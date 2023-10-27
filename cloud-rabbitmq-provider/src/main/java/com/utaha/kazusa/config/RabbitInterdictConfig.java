package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 高级特性：消息限流 与 不公平分发
 */
@Configuration
public class RabbitInterdictConfig {
    // 指定交换机名称
    private final String EXCHANGE_NAME = "interdict_exchange";

    // 指定队列名
    private final String QUEUE_NAME = "interdict_queue";

    // 创建交换机
    @Bean("bootInterdictExchange")
    public Exchange getExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(true) // 是否持久化
                .build();
    }

    // 创建队列
    @Bean("bootInterdictQueue")
    public Queue getMessageQueue() {
        return new Queue(QUEUE_NAME);
    }

    // 创建交换机绑定队列
    @Bean
    public Binding bindingInterdictExchangeQueue(@Qualifier("bootInterdictExchange") Exchange exchange, @Qualifier("bootInterdictQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.happyNewYear.#")   // 通配符模式 要匹配的路由键 RoutingKey
                .noargs();
    }
}