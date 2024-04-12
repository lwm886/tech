package com.tech.rm.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.apache.rocketmq.spring.support.RocketMQUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.StringMessageConverter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lw
 * @since 2024/4/12
 */
@Slf4j
//当有多个事务消息生产者组时，需要配置多个rocketMQTemplate，一个组对应一个rocketMQTemplate
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")
public class TransactionListenerConfig implements RocketMQLocalTransactionListener {

    ConcurrentHashMap<Object, Message> localTrans = new ConcurrentHashMap();

    //生产者执行本地事务
    //最后一个参数args对应发送消息时rocketMQTemplate.sendMessageInTransaction(destination, message, destination); ---- 的第三个参数 destination
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object args) {
        try {
            String myProp = message.getHeaders().get( "myProp").toString();
            //在Header里获取Tags TRANSACTION_ID等属性需要加前缀
            String oTag = message.getHeaders().get((RocketMQHeaders.PREFIX + RocketMQHeaders.TAGS)).toString();
            Object transId = message.getHeaders().get(RocketMQHeaders.PREFIX + RocketMQHeaders.TRANSACTION_ID);

            String destination = args.toString();
            localTrans.put(transId, message);


            //转成RocketMQ的Message对象
            org.apache.rocketmq.common.message.Message msg = RocketMQUtil.convertToRocketMessage(new StringMessageConverter(), "UTF-8", destination, message);
            String tags = msg.getTags();
            log.info("executeLocalTransaction ------> transId:{} tags:{} myProp:{}  CONVERTED tags:{} ", transId, oTag, myProp,tags);

            if ("tagA".equals(tags)) {
                return RocketMQLocalTransactionState.COMMIT;
            } else if ("tagB".equals(tags)) {
                return RocketMQLocalTransactionState.ROLLBACK;
            } else {
                return RocketMQLocalTransactionState.UNKNOWN;
            }
        } catch (Exception e) {
            log.error("执行本地事务错误",e);
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    //延迟回查本地事务
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transId = message.getHeaders().get(RocketMQHeaders.PREFIX + RocketMQHeaders.TRANSACTION_ID).toString();
        Message origonMessage = localTrans.get(transId);
        System.out.println("origon Message:"+origonMessage);
        //获取标签时，需要拼接Prefix
        String tags = message.getHeaders().get(RocketMQHeaders.PREFIX + RocketMQHeaders.TAGS).toString();
        log.info("checkLocalTransaction ---------> tags : {} transId:{}",tags,transId);
        if ("tagC".equals(tags)) {
            return RocketMQLocalTransactionState.COMMIT;
        } else if ("tagD".equals(tags)) {
            return RocketMQLocalTransactionState.ROLLBACK;
        } else {
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
}
