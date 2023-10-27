package com.utaha.kazusa.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AckConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private RabbitConsumer rabbitConsumer;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        // 设置消费者个数，当前设置为1
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setMaxConcurrentConsumers(1);
        listenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置一个已存在的队列
        listenerContainer.setQueueNames("boot_fanout_queue2");
        listenerContainer.setMessageListener(rabbitConsumer);
        return listenerContainer;
    }
}