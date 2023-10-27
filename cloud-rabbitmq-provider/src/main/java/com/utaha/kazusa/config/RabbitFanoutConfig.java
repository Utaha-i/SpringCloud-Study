package com.utaha.kazusa.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机
 */
@Configuration
public class RabbitFanoutConfig {

    // 指定交换机名称
    private final String EXCHANGE_NAME = "boot_fanout_exchange";

    // 指定队列名
    private final String QUEUE_NAME1 = "boot_fanout_queue1";

    // 指定队列名
    private final String QUEUE_NAME2 = "boot_fanout_queue2";

    // 创建扇形交换机
    @Bean("bootFanoutExchange")
    public FanoutExchange getExchange() {
        return ExchangeBuilder
                .fanoutExchange(EXCHANGE_NAME)
                .durable(false) // 是否持久化
                .build();
    }

    // 创建队列
    @Bean("bootFanoutQueue1")
    public Queue getMessageFanoutQueue1() {
        return new Queue(QUEUE_NAME1);
    }

    // 创建队列
    @Bean("bootFanoutQueue2")
    public Queue getMessageFanoutQueue2() {
        return new Queue(QUEUE_NAME2);
    }

    //广播模式绑定
    @Bean
    public Binding fanoutExchangeBingingOne() {
        return BindingBuilder.bind(getMessageFanoutQueue1()).to(getExchange());
    }

    @Bean
    public Binding fanoutExchangeBingingTwo(@Qualifier("bootFanoutExchange") FanoutExchange exchange, @Qualifier("bootFanoutQueue2") Queue queue) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
