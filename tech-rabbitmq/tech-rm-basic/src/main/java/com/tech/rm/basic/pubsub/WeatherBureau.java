package com.tech.rm.basic.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tech.rm.basic.utils.RmConstants;
import com.tech.rm.basic.utils.RmUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author lw
 * @since 2024/3/30
 */

public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RmUtils.getConnection();
        String input = new Scanner(System.in).next();
        Channel channel = connection.createChannel();
        channel.basicPublish(RmConstants.EXCHANGE_WEATHER, "", null, input.getBytes());

        channel.close();
        connection.close();
    }
}
