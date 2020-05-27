package com.javaweb.queue.rabbitmq.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 注册为一个Bean
@Component
public class RabbitMQTopicSend2 {

    // 注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    // 每隔3秒执行一次将消息放入队列
    @Scheduled(fixedDelay = 3000)
    public void send() {
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "message: woman is all ";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> womanMap = new HashMap<>();
//        womanMap.put("messageId", messageId);
//        womanMap.put("messageData", messageData);
//        womanMap.put("createTime", createTime);
//        rabbitTemplate.convertAndSend("topicExchange", "topic.B", womanMap);
    }

}
