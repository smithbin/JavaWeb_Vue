package com.javaweb.queue.activemq;

import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 短信消息生产者
 */
@Component
public class ActiveMQSmsSend {

    // 注入JmsMessagingTemplate
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

//    /**
//     * 使用ActiveMQ Server 服务
//     * 每隔30秒发送一次消息，将消息放入队列
//     */
//    @Scheduled(fixedDelay = 3000)
//    public void sendSms() {
//        try {
//
//            MapMessage mapMessage = new ActiveMQMapMessage();
//            mapMessage.setString("info", "你还在睡觉");
//            this.jmsMessagingTemplate.convertAndSend(this.queue, mapMessage);
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}
