package com.javaweb.queue.rabbitmq;

import org.springframework.context.annotation.Configuration;

/**
 * 消息生成端消息确认
 */
@Configuration
public class RabbitMQProConfirmConfig {

//    @Bean
//    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
//        rabbitTemplate.setMandatory(true);
//
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
//                System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
//                System.out.println("ConfirmCallback:     " + "原因：" + cause);
//            }
//        });
//
//        // 当交换机存在，找不到分发队列时回回调
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                System.out.println("ReturnCallback:     " + "消息：" + message);
//                System.out.println("ReturnCallback:     " + "回应码：" + replyCode);
//                System.out.println("ReturnCallback:     " + "回应信息：" + replyText);
//                System.out.println("ReturnCallback:     " + "交换机：" + exchange);
//                System.out.println("ReturnCallback:     " + "路由键：" + routingKey);
//            }
//        });
//
//        return rabbitTemplate;
//    }

}
