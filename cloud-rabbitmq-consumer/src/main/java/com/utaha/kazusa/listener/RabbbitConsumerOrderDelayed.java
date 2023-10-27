package com.utaha.kazusa.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * 延时交换机监听队列
 */
@Component
@Slf4j
public class RabbbitConsumerOrderDelayed {
    // 监听队列
    @RabbitListener(queues = "delayed_queue")
    public void listenOrder(Message message) throws Exception {
        byte[] body = message.getBody();
        ObjectInputStream orderStream = new ObjectInputStream(new ByteArrayInputStream(body));
        Map<String, String> orderObj = (Map<String, String>) orderStream.readObject();
        String orderId = orderObj.get("orderId");
        String orderInfo = orderObj.get("orderInfo");
        log.error("付款成功，喜提一辆 " + orderInfo + "，订单号为：" + orderId);
    }
}