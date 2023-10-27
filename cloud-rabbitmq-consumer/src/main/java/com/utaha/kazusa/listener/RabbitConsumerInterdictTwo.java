package com.utaha.kazusa.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

@Component
@Slf4j
public class RabbitConsumerInterdictTwo {
    @RabbitListener(queues = "interdict_queue")
    public void listenMessage(Message message, Channel channel) throws Exception {
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
            log.error("2号消费者，消费的队列名：" + message.getMessageProperties().getConsumerQueue());
            //模拟接收消息，所需处理时间 1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("err", e);
            //java interrupt()函数会中断线程（本质更新线程为中断状态）。
            //若sleep()函数检测到线程中断(interrupt()函数触发) 会抛 InterruptedException, 被catch住后线程中断状态更新为未中断
            Thread.currentThread().interrupt();
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