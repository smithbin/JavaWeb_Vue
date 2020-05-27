package com.javaweb.queue.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

// 注册为一个Bean
@Component
// 监听队列：topic.A
@RabbitListener(queues = "topic.A")
public class RabbitMQTopicAListener {

    /**
     * 接收消息并处理
     *
     * @param map
     */
    @RabbitHandler
    public void receiveMessage(Map map) {
        System.out.println("TopicAReceiver消费者收到消息  : " + map.toString());
    }

}
