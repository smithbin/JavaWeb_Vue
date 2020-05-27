package com.javaweb.queue.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// 注入为一个Bean
@Component
// 监听队列：smsDirectQueue
@RabbitListener(queues = "smsDirectQueue")
public class RabbitMQDirectListener implements ChannelAwareMessageListener {

//    /**
//     * 接收并处理消息
//     *
//     * @param map 参数
//     */
//    @RabbitHandler
//    public void receiveMessage(Map<String, String> map) {
//        // 循环遍历参数
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            // 键
//            String mapKey = entry.getKey();
//            // 值
//            String mapValue = entry.getValue();
//            // 打印参数
//            System.out.println("参数打印:" + mapKey + "=>" + mapValue);
//        }
//    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim());
            String messageId = msgMap.get("messageId");
            String messageData = msgMap.get("messageData");
            String createTime = msgMap.get("createTime");
            System.out.println("消费者确认,messageId:" + messageId + "  messageData:" + messageData + "  createTime:" + createTime);
            channel.basicAck(deliveryTag, true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
        } catch (Exception e) {
//            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }

    //{key=value,key=value,key=value} 格式转换成map
    private Map<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
