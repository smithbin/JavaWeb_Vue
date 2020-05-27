package com.javaweb.queue.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机配置类
 */
@Configuration
public class RabbitMQTopicRabbitConfig {

    //绑定键
    public final static String A = "topic.A";
    public final static String B = "topic.B";

    /**
     * 创建队列A
     *
     * @return
     */
    @Bean
    public Queue topicQueueA() {
        return new Queue(RabbitMQTopicRabbitConfig.A);
    }

    /**
     * 创建队列B
     *
     * @return
     */
    @Bean
    public Queue topicQueueB() {
        return new Queue(RabbitMQTopicRabbitConfig.B);
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将topicQueueA和topicExchange绑定，并分配路由键值为topic.A
     * 备注：只有消息携带路由是topic.A 才会分发到该队列
     *
     * @return
     */
    @Bean
    Binding bindingManExchange() {
        return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with(A);
    }

    /**
     * 将topicQueueB和topicExchange绑定，并且分配路由键值为：topic.#
     * 备注：主要消息携带的路由建是以topic.开头，都会分发到该队列
     *
     * @return
     */
    @Bean
    Binding bindingWomanExchange() {
        return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with("topic.#");
    }

}
