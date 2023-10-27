package com.utaha.kazusa.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

/**
 * 死信队列
 */
@RestController
@RequestMapping("provider")
public class DeadProviderController {
    // 注入 RabbitTemplate 工具类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //http://127.0.0.1:8021/provider/productMaxLengthMessage
    // 模拟演示当消息长度达到限制后，剩余消息进入死信
    @GetMapping("productMaxLengthMessage")
    public String productMaxLengthMessage() {
        return this.productMessage(100);
    }

    // 模拟演示当消息过期后进行死信
    @GetMapping("productTtlMessage")
    public String productTtlMessage() {
        return this.productMessage(10);
    }

    // 模拟演示消费者拒签后，消息进入死信
    @GetMapping("productRefuseMessage")
    public String productRefuseMessage() {
        return this.productMessage(10);
    }

    protected String productMessage(int num) {
        int m = 0;
        for (int i = 1; i <= num; i++) {
            String messageId = String.valueOf(UUID.randomUUID());   // 随机一个消息 ID
            String messageContent = "快过年了，提前祝你新年快乐。第 " + i + "封信";   // 消息主题内容
            String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            HashMap<String, Object> messageMap = new HashMap<>();
            messageMap.put("messageId", messageId);
            messageMap.put("messageContent", messageContent);
            messageMap.put("sendTime", sendTime);
            /*
             * 发送消息
             * 参数1：交换机名称
             * 参数2：路由 routeKey
             * 参数3：消息主题内容
             */
            rabbitTemplate.convertAndSend("ordinary_exchange", "happyNewYear", messageMap);
            m++;
        }
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>第" + m + "封新年祝福信已发送</p>";
    }
}