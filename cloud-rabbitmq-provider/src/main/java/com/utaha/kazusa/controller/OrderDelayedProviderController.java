package com.utaha.kazusa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

/**
 * 延时队列模拟订单Controller
 */
@RestController
@RequestMapping("provider")
@Slf4j
public class OrderDelayedProviderController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/makeOrder")
    public String makeOrder() {
        // 设置消息的有效时间为15秒 模拟订单有效的支付时间
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setDelay(15000);
            return message;
        };
        // 创建订单信息
        String orderId = String.valueOf(UUID.randomUUID()); // 生成随机订单号
        String orderInfo = "迈巴赫 S480";
        HashMap<String, String> orderMap = new HashMap<>();
        orderMap.put("orderId", orderId);
        orderMap.put("orderInfo", orderInfo);
        // 将订单信息发送到 队列
        rabbitTemplate.convertAndSend("delayed_exchange", "delayed_orderRouting", orderMap, messagePostProcessor);
        log.error("付款中...");
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>下单成功，订单号为：" + orderId + "</p>";
    }
}