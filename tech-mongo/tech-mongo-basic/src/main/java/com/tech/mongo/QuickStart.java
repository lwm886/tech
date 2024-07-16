package com.tech.mongo;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.eq;

/**
 * @author lw
 * @since 2024/7/16
 */
public class QuickStart {
    public static void main(String[] args) {
        //连接本地默认端口的Mongod
//        MongoClient mongoClient = MongoClients.create();
        //连接远程服务指定端口的Mongod
        MongoClient mongoClient = MongoClients.create("mongodb://192.168.50.161:27017");
        //连接主从复制集群
//        MongoClient mongoClient = MongoClients.create("mongodb://host1:27017,host2:27017,host3:27017/?replicaSet=myReplicaSet");
        //连接分片集群路由节点
//        MongoClient mongoClient = MongoClients.create("mongodb://host1:27017");
        //获取数据库
        MongoDatabase database = mongoClient.getDatabase("productdb");
        //获取集合
        MongoCollection<Document> collection = database.getCollection("productdesc");
        
        Document doc=new Document("name","MongoDB")
                .append("type","database")
                .append("count",1)
                .append("versions", Arrays.asList("v3.2","v3.0","v2.6"))
                .append("info",new Document("x",203).append("y","100"));
        collection.insertOne(doc);

        Bson eq=eq("name","MongoDB");
        FindIterable<Document> find = collection.find(eq);
        MongoCursor<Document> cursor = find.cursor();
        while (cursor.hasNext()){
            System.out.println(cursor.next());
        }

    }
}
