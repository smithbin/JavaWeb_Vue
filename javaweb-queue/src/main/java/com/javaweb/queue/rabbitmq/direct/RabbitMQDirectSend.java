package com.javaweb.queue.rabbitmq.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//注册为一个bean
@Component
public class RabbitMQDirectSend {

    // 注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    /**
     * 每个3秒执行一次，将消息放入队列
     */
    @Scheduled(fixedDelay = 3000)
    public void sendSms() {
//        // 生成6位数验证码
//        String code = CommonUtils.getRandomStr(true, 6);
//        Map<String, String> map = new HashMap<>();
//        map.put("RegionId", aliSmsConfig.getRegionId());
//        map.put("PhoneNumbers", "15295504151");
//        map.put("SignName", aliSmsConfig.getSignName());
//        map.put("TemplateCode", aliSmsConfig.getTemplateCode());
//        map.put("TemplateParam", "{\"code\":\"" + code + "\"}");
//
//        // 将携带绑定键值：smsDirectRouting 的消息发送到交换机： smsDirectExchange
//        rabbitTemplate.convertAndSend("smsDirectExchange", "smsDirectRoutingKey", map);


//        // 测试不存在交换机
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "message: non-existent-exchange test message ";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageId", messageId);
//        map.put("messageData", messageData);
//        map.put("createTime", createTime);
//        rabbitTemplate.convertAndSend("non-existent-exchange", "smsDirectRoutingKey", map);


//        // 测试存在交换机，但是不存在队列
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "message: lonelyDirectExchange test message ";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageId", messageId);
//        map.put("messageData", messageData);
//        map.put("createTime", createTime);
//        rabbitTemplate.convertAndSend("lonelyDirectExchange", "smsDirectRoutingKey", map);

//        // 发送一条正确的消息，测试消费端消息确认机制
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "message: non-existent-exchange test message ";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String, Object> map = new HashMap<>();
//        map.put("messageId", messageId);
//        map.put("messageData", messageData);
//        map.put("createTime", createTime);
//        rabbitTemplate.convertAndSend("smsDirectExchange", "smsDirectRoutingKey", map);
    }
}
