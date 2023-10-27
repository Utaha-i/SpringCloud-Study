package com.utaha.kazusa.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 死信队列监听器
 */
@Component
@Slf4j
public class DeadConsumer {
    @RabbitListener(queues = "ordinary_queue")
    public void listenMessage(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.error("监听到了" + deliveryTag + "," + channel);
    }
}