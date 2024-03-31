package com.tech.rm.basic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author lw
 * @since 2024/3/31
 */
@Component
public class AckListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        
        //获取消息ID
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try{
            //获取消息
            System.out.println("message: "+new String(message.getBody()));

            System.out.println("===进行业务处理===");
            
            //模拟出现异常
            System.out.println(1/0);
            
            //对消息进行确认，配置中设置了手动确认
            //args[消息TAG，是否批量(为true全部小于deliveryTag的消息都会被确认))]
            channel.basicAck(deliveryTag,true);
            
        }catch (Exception e){

            //拒收消息
            //args[消息TAG，是否批量，是否让消息重回队列-true让消息重回队列-false丢弃消息]
            channel.basicNack(deliveryTag,true,false);
        }

    }
}
