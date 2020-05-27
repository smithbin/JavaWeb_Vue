package com.javaweb.queue.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * ActiveMQ的配置类
 */
@Configuration
public class ActiveMQConfig {

    /**
     * P2P消息模型，生产者生产了一个消息，只能由一个消费者进行消费
     * 备注：是持久化消息
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("sms.queue");
    }

    /**
     * 主题/广播（Pub/Sub消息模型），生产者生产了一个消息，可以由多个消费者进行消费
     *
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("sms.topic");
    }

}
