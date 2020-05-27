package com.javaweb.queue.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;

/**
 * 短信消息监听者
 */
@Component
public class ActiveMQMessageListener2 {

    /**
     * 监听队列smsQueue，有待消费的消息就处理
     */
    @JmsListener(destination = "sms.topic")
    public void receiveMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            String info = mapMessage.getString("info");
            System.out.println("消费者2:收到消息：" + info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
