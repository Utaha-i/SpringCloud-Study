package com.utaha.kazusa.config;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息的可靠投递（生产者端）
 * <p>
 * 1、确认模式（confirm）：可以监听消息是否从生产者成功传递到交换机。
 * 2、退回模式（return）：可以监听消息是否从交换机成功传递到队列。
 * 3、消费者消息确认（Ack）：可以监听消费者是否成功处理消息。
 */
@Configuration
@Slf4j
public class RabbitConfirmConfig {

    /**
     * 重写RabbitTemplate对象中confirm、returnedMessage方法
     *
     * @param connectionFactory 链接工厂
     * @return RabbitTemplate对象
     */
    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启 Mandatory 强制执行调用回调函数
        rabbitTemplate.setMandatory(true);

        //确认模式 重写confirm方法
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 被调用的回调方法
             *
             * @param correlationData 相关配置信息
             * @param ack 交换机是否成功收到消息 可以根据 ack 做相关的业务逻辑处理
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    // 控制台 打印输出内容
                    log.error("生产者已成功将消息推送到交换机");
                } else {
                    // 控制台 打印输出内容
                    log.error("ConfirmInfo:相关配置信息: " + correlationData);
                    log.error("ConfirmInfo:确认结果: " + ack);
                    log.error("ConfirmInfo:原因: " + cause);
                    // 可做针对性的业务逻辑处理，例如：让消息重发、发送邮件通知程序员、做日志等等。
                }
            }
        });

        //退回模式 重写returnedMessage方法
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            /**
             * 被调用的回调方法
             *
             * @param returned 消息主题内容对象
             */
            @Override
            public void returnedMessage(@Nonnull ReturnedMessage returned) {
                log.error("ReturnInfo:消息对象：" + returned.getMessage());
                log.error("ReturnInfo:错误码：" + returned.getReplyCode());
                log.error("ReturnInfo:错误信息：" + returned.getReplyText());
                log.error("ReturnInfo:交换机：" + returned.getExchange());
                log.error("ReturnInfo:路由键：" + returned.getRoutingKey());
                // 可做针对性的业务逻辑处理，例如：发送邮件通知程序员、做日志等等。
            }
        });

        return rabbitTemplate;
    }
}