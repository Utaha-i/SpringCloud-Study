package com.utaha.kazusa.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 消息可靠投递，ack模式
 */
@Component
@Slf4j
public class RabbitConsumerOfDeploy {
    // 监听队列
    @RabbitListener(queues = "boot_fanout_queue1")
    public void listenMessage(Message message, Channel channel) throws Exception {
        // 消息投递序号，消息每次投递该值都会+1
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        byte[] body = message.getBody();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(body))) {
            /*
              签收消息
              参数1：消息投递序号
              参数2：是否一次可以签收多条消息，true:是；false:否
             */
            channel.basicAck(deliveryTag, true);
            Map<String, String> msgMap = (Map<String, String>) objectInputStream.readObject();
            String messageId = msgMap.get("messageId");
            String messageData = msgMap.get("messageContent");
            String createTime = msgMap.get("sendTime");
            log.error("rabbitConsumer:  messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
            log.error("消费的队列名：" + message.getMessageProperties().getConsumerQueue());
        } catch (Exception e) {
            /*
              拒签消息
              参数1：消息投递序号
              参数2：是否一次可以拒签多条消息，true:是；false:否
              参数3：拒签后消息是否重回队列，true:重回；false:不重回
             */
            log.error("消息消费失败！" + e);
            channel.basicNack(deliveryTag, true, true);
        }
    }
}