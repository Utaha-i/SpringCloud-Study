package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * 延迟交换机
 * 类型必须是 x-delayed-message
 */
@Configuration
public class RabbitOrderDelayedConfig {
    // 定义延迟交换机
    private final String DELAYED_EXCHANGE = "delayed_exchange";
    // 定义延迟的队列
    private final String DELAYED_QUEUE = "delayed_queue";

    // 延迟交换机
    @Bean(DELAYED_EXCHANGE)
    public Exchange expireExchange() {
        // 修正创建自定义的延迟交换机
        HashMap<String, Object> exchange = new HashMap<>();
        exchange.put("x-delayed-type", "topic"); // topic 类型的延迟交换机
        /**
         * 参数1：交换机名称
         * 参数2：类型必须是 x-delayed-message
         * 参数3：是否持久化（队列的声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化）
         * 参数4：是否自动删除（队列中的数据消费完成后是否自动删除队列，当最后一个消费者断开连接之后队列是否自动被删除）
         * 参数5：自定义交换机的 HashMap
         */
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", true, false, exchange);
    }

    // 延迟队列
    @Bean(DELAYED_QUEUE)
    public Queue expireQueue() {
        return QueueBuilder
                .durable(DELAYED_QUEUE)
                .build();
    }

    // 延迟交换机和队列进行绑定
    @Bean
    public Binding bindDelayedExchangeQueue(@Qualifier(DELAYED_EXCHANGE) Exchange exchange, @Qualifier(DELAYED_QUEUE) Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.delayed_orderRouting.#")
                .noargs();
    }
}