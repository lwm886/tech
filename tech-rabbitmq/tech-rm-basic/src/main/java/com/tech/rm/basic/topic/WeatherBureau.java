package com.tech.rm.basic.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tech.rm.basic.utils.RmConstants;
import com.tech.rm.basic.utils.RmUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author lw
 * @since 2024/3/30
 */
public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {

        Map area = new LinkedHashMap<String, String>();
        area.put("china.hunan.changsha.20201127", "中国湖南长沙20201127天气数据");
        area.put("china.hubei.wuhan.20201127", "中国湖北武汉20201127天气数据");
        area.put("china.hunan.zhuzhou.20201127", "中国湖南株洲20201127天气数据");
        area.put("us.cal.lsj.20201127", "美国加州洛杉矶20201127天气数据");

        area.put("china.hebei.shijiazhuang.20201128", "中国河北石家庄20201128天气数据");
        area.put("china.hubei.wuhan.20201128", "中国湖北武汉20201128天气数据");
        area.put("china.henan.zhengzhou.20201128", "中国河南郑州20201128天气数据");
        area.put("us.cal.lsj.20201128", "美国加州洛杉矶20201128天气数据");
        
        Connection connection = RmUtils.getConnection();
        Channel channel = connection.createChannel();
        Iterator<Map.Entry<String, String>> iterator = area.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
//            args[交换机，路由key，额外属性，消息]
            channel.basicPublish(RmConstants.EXCHANGE_WEATHER_TOPIC,next.getKey(),null,next.getKey().getBytes());
        }
        channel.close();
        connection.close();
    }
}
