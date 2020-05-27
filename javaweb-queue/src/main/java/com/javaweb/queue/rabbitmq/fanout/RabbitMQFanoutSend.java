package com.javaweb.queue.rabbitmq.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 注册为一个Bean
@Component
public class RabbitMQFanoutSend {

    // 使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    RabbitTemplate rabbitTemplate;

    // 每隔3秒执行一次,将消息加入队列
    @Scheduled(fixedDelay = 30000)
    public void sendMessage() {
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "message: testFanoutMessage ";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageId", messageId);
//        map.put("messageData", messageData);
//        map.put("createTime", createTime);
//
//        // 将消息推送到扇形交换机：fanoutExchange
//        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
    }

}
