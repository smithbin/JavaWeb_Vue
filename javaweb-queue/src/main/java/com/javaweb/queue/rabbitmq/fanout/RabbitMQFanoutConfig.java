package com.javaweb.queue.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机配置
 * 创建三个队列 ：fanout.A   fanout.B  fanout.C
 * 将三个队列都绑定在交换机 fanoutExchange 上
 * 因为是扇型交换机, 路由键无需配置,配置也不起作用
 */
@Configuration
public class RabbitMQFanoutConfig {

    /**
     * 短信队列
     *
     * @return
     */
    @Bean
    public Queue smsFanoutQueue() {
        return new Queue("smsFanoutQueue");
    }

    /**
     * 邮件队列
     *
     * @return
     */
    @Bean
    public Queue emailFanoutQueue() {
        return new Queue("emailFanoutQueue");
    }

    /**
     * 消息通知队列
     *
     * @return
     */
    @Bean
    public Queue noticeFanoutQueue() {
        return new Queue("noticeFanoutQueue");
    }

    /**
     * 创建交换机
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 将短信队列绑定到交换机
     *
     * @return
     */
    @Bean
    Binding bindingSmsExchange() {
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }

    /**
     * 将邮件队列绑定到交换机
     *
     * @return
     */
    @Bean
    Binding bindingEmailExchange() {
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }

    /**
     * 将通知队列绑定到交换机
     *
     * @return
     */
    @Bean
    Binding bindingNoticeExchange() {
        return BindingBuilder.bind(noticeFanoutQueue()).to(fanoutExchange());
    }

}
