package com.javaweb.queue.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import javax.jms.Message;

/**
 * 短信消息监听者
 */
@Component
public class ActiveMQMessageListener {

    /**
     * 监听队列smsQueue，有待消费的消息就处理
     */
    @JmsListener(destination = "sms.queue")
    public void receiveMessage(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            String info = mapMessage.getString("info");
            System.out.println("消费者1:收到消息：" + info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 监听队列smsQueue，有待消费的消息就处理
     */
    @JmsListener(destination = "sms.queue")
    public void receiveMessage2(Message message) {
        try {
            MapMessage mapMessage = (MapMessage) message;
            String info = mapMessage.getString("info");
            System.out.println("消费者10000:收到消息：" + info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
