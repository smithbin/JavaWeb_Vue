package com.javaweb.queue.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

// 注入为一个Bean
@Component
// 监听队列：
@RabbitListener(queues = "noticeFanoutQueue")
public class RabbitMQFanoutNoticeListener {

    /**
     * 接收消息并处理
     *
     * @param map
     */
    @RabbitHandler
    public void receiveMessage(Map map) {
        System.out.println("【通知队列】消费者收到消息");
    }

}
