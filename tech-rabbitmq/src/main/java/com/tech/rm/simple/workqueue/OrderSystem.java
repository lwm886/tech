package com.tech.rm.simple.workqueue;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tech.rm.simple.utils.RmConstants;
import com.tech.rm.simple.utils.RmUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lw
 * @since 2024/3/27
 */
public class OrderSystem {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RmUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RmConstants.QUEUE_SMS,false,false,false,null);
        for (int i = 0; i < 100; i++) {
            SMS sms = new SMS("MEMBER-" + i, "15666666" + i, "create order ok");
            String json = new Gson().toJson(sms);
            channel.basicPublish("",RmConstants.QUEUE_SMS,null,json.getBytes());
        }
        System.out.println("发送数据成功");
        channel.close();
        connection.close();
    }
}
