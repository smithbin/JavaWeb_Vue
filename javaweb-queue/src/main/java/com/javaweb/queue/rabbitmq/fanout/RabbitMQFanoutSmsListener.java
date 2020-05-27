package com.javaweb.queue.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

// 注入为一个Bean
@Component
// 监听短信队列：smsFanoutQueue
@RabbitListener(queues = "smsFanoutQueue")
public class RabbitMQFanoutSmsListener {

    /**
     * 接收并处理消息
     *
     * @param map
     */
    @RabbitHandler
    public void receiveMessage(Map map) {
        System.out.println("【短信队列】消费者收到消息");
    }
}
