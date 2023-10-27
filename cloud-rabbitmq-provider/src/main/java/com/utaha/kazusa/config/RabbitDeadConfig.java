package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitDeadConfig {

    // 定义死信交换机
    private final String DEAD_EXCHANGE = "dead_exchange";
    // 定义死信队列
    private final String DEAD_QUEUE = "dead_queue";
    // 普通交换机
    private final String ORDINARY_EXCHANGE = "ordinary_exchange";
    // 普通死信队列
    private final String ORDINARY_QUEUE = "ordinary_queue";

    // 死信交换机
    @Bean(DEAD_EXCHANGE)
    public Exchange deadExchange() {
        return ExchangeBuilder
                .topicExchange(DEAD_EXCHANGE)
                .durable(true) // 持久化
                .build();
    }

    // 死信队列
    @Bean(DEAD_QUEUE)
    public Queue deadQueue() {
        return QueueBuilder
                .durable(DEAD_QUEUE)
                .build();
    }

    // 死信交换机绑定死信队列
    @Bean
    public Binding bindDeadQueue(@Qualifier(DEAD_EXCHANGE) Exchange exchange, @Qualifier(DEAD_QUEUE) Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.deadRouting.#")
                .noargs();
    }

    // 普通交换机
    @Bean(ORDINARY_EXCHANGE)
    public Exchange ordinaryExchange() {
        return ExchangeBuilder
                .topicExchange(ORDINARY_EXCHANGE)
                .durable(true)  // 持久化
                .build();
    }

    // 普通队列
    @Bean(ORDINARY_QUEUE)
    public Queue ordinaryQueue() {
        return QueueBuilder
                .durable(ORDINARY_QUEUE)
                .deadLetterExchange(DEAD_EXCHANGE) // 绑定死信交换机
                .deadLetterRoutingKey("deadRouting") // 死信队列路由关键字
                .ttl(15000) // 消息存活时间 15秒
                .maxLength(10)  // 队列最大长度
                .build();
    }

    // 普通交换机绑定普通队列
    @Bean
    public Binding bindOrdinaryQueue(@Qualifier(ORDINARY_EXCHANGE) Exchange exchange, @Qualifier(ORDINARY_QUEUE) Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.happyNewYear.#")
                .noargs();
    }

}