package com.utaha.kazusa.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消息优先级
 */
@Component
@Slf4j
public class RabbitConsumerPriority {
    @RabbitListener(queues = "priority_queue")
    public void listenMessage(Message message, Channel channel) throws IOException {
        log.error(new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}