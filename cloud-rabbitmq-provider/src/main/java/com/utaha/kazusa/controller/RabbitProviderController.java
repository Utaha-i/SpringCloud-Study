package com.utaha.kazusa.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("bootRabbitMq")
public class RabbitProviderController {
    // 注入 RabbitTemplate 工具类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendNewYearMessage")
    public String sendMessage() {
        // 随机一个消息 ID
        String messageId = String.valueOf(UUID.randomUUID());
        // 消息主题内容
        String messageContent = "快过年了，提前祝你新年快乐。";
        String sendTime = String.valueOf(LocalDateTime.now());
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
        rabbitTemplate.convertAndSend("boot_topic_exchange", "happyNewYear", messageMap);
        // 模拟确认模式 使交换机故障让消息无法发送到交换机上
        //rabbitTemplate.convertAndSend("xxxx", "happyNewYear", messageMap);
        // 模拟退回模式 修改不存在的路由键 使交换机无法通过路由键把消息发送到队列中
        //rabbitTemplate.convertAndSend("boot_topic_exchange", "xxxx", messageMap);
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>新年祝福已发送</p>";
    }

    @GetMapping("/sendNewYearMessage2")
    public String sendMessage2() {
        // 随机一个消息 ID
        String messageId = String.valueOf(UUID.randomUUID());
        // 消息主题内容
        String messageContent = "快过年了，提前祝你新年快乐222。";
        String sendTime = String.valueOf(LocalDateTime.now());
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
        rabbitTemplate.convertAndSend("boot_fanout_exchange", "", messageMap);
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>新年祝福已发送222</p>";
    }

    //http://127.0.0.1:8021/bootRabbitMq/sendNewYearMessage3
    @GetMapping("/sendNewYearMessage3")
    public String sendMessage3() {
        int m = 0;
        for (int i = 1; i <= 100; i++) {
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
            rabbitTemplate.convertAndSend("interdict_exchange", "happyNewYear", messageMap);
            m++;
        }
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>第" + m + "封新年祝福信已发送</p>";
    }

    /**
     * 生产者设定消息存活时间
     *
     * @return String
     */
    @GetMapping("/sendNewYearMessageOfTtl")
    public String sendSecondMessage() {
        int m = 0;
        // 生产20条消息
        for (int i = 1; i <= 20; i++) {
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
            rabbitTemplate.convertAndSend("live_exchange", "happyNewYears", messageMap);
            m++;
        }
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>第" + m + "封新年祝福信已发送</p>";
    }

    /**
     * 生产者设定消息存活时间
     *
     * @return string
     */
    @GetMapping("/sendNewYearMessageOfTtl2")
    public String sendTtlMessage() {
        int m = 0;
        // 生产5条消息
        for (int i = 1; i <= 5; i++) {
            // 设置消息主题
            String messageId = String.valueOf(UUID.randomUUID());   // 随机一个消息 ID
            String messageContent = "快过年了，提前祝你新年快乐。第 " + i + "封信";   // 消息主题内容
            String sendTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            HashMap<String, Object> messageMap = new HashMap<>();
            messageMap.put("messageId", messageId);
            messageMap.put("messageContent", messageContent);
            messageMap.put("sendTime", sendTime);
            messageMap.put("expiration", 20);
            if (i == 4) {
                /*
                 * 模拟生成一条具有有效时间为1秒的消息
                 */
                // 设置消息属性
                MessageProperties messageProperties = new MessageProperties();
                // 设置消息存活时间
                messageProperties.setExpiration("1000");
                // 创建消息对象
                Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8), messageProperties);
                // 发送消息
                rabbitTemplate.convertAndSend("interdict_exchange", "happyNewYear", message);
            } else {
                /*
                 * 发送消息
                 * 参数1：交换机名称
                 * 参数2：路由 routeKey
                 * 参数3：消息主题内容
                 */
                rabbitTemplate.convertAndSend("interdict_exchange", "happyNewYear", messageMap);
            }
            m++;
        }
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>第" + m + "封新年祝福信已发送</p>";
    }

    /**
     * 消息优先级
     *
     * @return string
     */
    @GetMapping("/sendCxActiveMessage")
    public String sendPriorityMessage() {
        // 数据模拟 定义10、12为vip 用户
        Map<Integer, String> vips = new HashMap<>();
        vips.put(10, "张三");
        vips.put(12, "李四");
        // 生产消息
        MessageProperties messageProperties = new MessageProperties();
        String messageContent = "";
        int m = 0;
        for (int i = 1; i <= 50; i++) {
            String userName = vips.get(i);
            if (userName == null) {
                messageContent = "限时活动，进入个人中心领红包了，先到先得，领完为止。";
            } else {
                messageContent = "尊贵的 VIP用户" + userName + "您好，即可登录APP 进入个人中心领取全场 1000的通用红包，先到先得，领完为止。";
                messageProperties.setPriority(10);
            }
            Message message = new Message(messageContent.getBytes(StandardCharsets.UTF_8), messageProperties);
            rabbitTemplate.convertAndSend("priority_exchange", "cx_active", message);
            m++;
        }
        return "<p style=color:blue;text-align:center;top:20px;font-size:28px;>第" + m + "条消息已发送</p>";
    }

}