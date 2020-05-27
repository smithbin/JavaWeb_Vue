package com.javaweb.queue.rabbitmq.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连型交换机配置
 * https://blog.csdn.net/qq_35387940/article/details/100514134
 */
@Configuration
public class RabbitMQDirectConfig {

    /**
     * 创建队列名
     *
     * @return
     */
    @Bean
    public Queue smsDirectQueue() {
        return new Queue("smsDirectQueue", true);  //true 是否持久
    }

    /**
     * 创建交换机名
     *
     * @return
     */
    @Bean
    DirectExchange smsDirectExchange() {
        return new DirectExchange("smsDirectExchange");
    }

    /**
     * 将队列和交换机绑定，并设置路邮键
     *
     * @return
     */
    @Bean
    Binding bindingDirectExchange() {
        return BindingBuilder.bind(smsDirectQueue()).to(smsDirectExchange()).with("smsDirectRoutingKey");
    }


    // 创建交换机
    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

}
