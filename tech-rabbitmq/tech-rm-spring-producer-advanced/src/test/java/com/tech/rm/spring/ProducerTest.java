package com.tech.rm.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/3/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {
    
    @Resource
    RabbitTemplate rabbitTemplate;

     
    /**
     *  测试confirm模式
     *  在配置文件中提前配置 publisher-confirms="true"
     *  生产者投递消息到交换机结果回调
     */
    @Test
    public void testConfirm() throws InterruptedException {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 相关配置信息
             * @param ack   exchange交换机 是否成功收到了消息。true 成功，false代表失败
             * @param cause 失败原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm方法被执行了....");
                //ack为true表示消息已经到达交换局
                if(ack){
                    //接收成功
                    System.out.println("接收成功消息"+cause);
                }else {
                    //接收成功
                    System.out.println("接收失败消息"+cause);
                    //做一些处理，让消息再次发送。
                }
            }
        });
        rabbitTemplate.convertAndSend("test_exchange_confirm","confirm","message confirm ...");
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 测试Return模式 
     * 在配置文件中提前配置 publisher-returns="true"
     * 交换机转发消息到队列失败回调
     */
    @Test
    public void testReturn() throws InterruptedException {
        //这个参数为true 交换机没有成功把消息转发到队列上，此时会把消息回退给生产者
        rabbitTemplate.setMandatory(true);
        
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             *
             * @param message   消息对象
             * @param replyCode 错误码
             * @param replyText 错误信息
             * @param exchange  交换机
             * @param routingKey 路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("return 执行了....");

                System.out.println("message:"+message);
                System.out.println("replyCode:"+replyCode);
                System.out.println("replyText:"+replyText);
                System.out.println("exchange:"+exchange);
                System.out.println("routingKey:"+routingKey);
                
                //处理
            }
        });
        rabbitTemplate.convertAndSend("test_exchange_confirm","confirm_error","message return ...");
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * 批量发送消息，让消费者每次拉取指定数量
     */
    @Test
    public void testQos(){
        for (int i = 0; i < 10; i++) {
            //发送消息
            rabbitTemplate.convertAndSend("test_exchange_confirm","confirm","message qos ..."+ i);

        }
    }

    /**
     * TTL:过期时间
     *  1 队列统一过期
     *  2 消息单独过期
     * 
     * 如果设置了队列过期时间，也设置了消息的过期时间，以过期时间短的为准
     * 队列过期后，会将队列中过期消息移除
     * 消息过期后，只有消息在队列顶端，才会判断其是否过期（移除掉）
     *  
     */
    @Test
    public void testTtl(){
        for (int i = 0; i < 10; i++) {
            //发送消息
            rabbitTemplate.convertAndSend("test_exchange_ttl","ttl.test","message ttl ..."+ i);

        }
    }

    /**
     * 测试死信消息：
     *  1. 过期时间 给队列设置了过期时间
     *  2. 长度限制 给队列设置了消息最大数量
     *  3. 消息拒收 消费者拒收消息，并且不将其重回队列
     */
    @Test
    public void testDlx(){
        //消息过期
//        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.ttl","ttl test ...");
        //发送消息数量超过队列容纳的最大数量
//        for (int i = 0; i < 100; i++) {
//            rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.ttl","消息数量 test ...");
//        }
        //在消费者端进行拒收
        rabbitTemplate.convertAndSend("test_exchange_dlx","test.dlx.ttl","消息拒收 ...");
    }
    
}
