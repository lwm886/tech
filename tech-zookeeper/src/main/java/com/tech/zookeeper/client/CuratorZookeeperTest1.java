package com.tech.zookeeper.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/2/8
 */
@Slf4j
public class CuratorZookeeperTest1 {
    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.50.150:2181", new ExponentialBackoffRetry(1000, 3));
        client.start();
//        testCreate(client);
//        testCreateWithParent(client);
//        testGetData(client);
        testSetData(client);
//        testAsync(client);
    }
    
    //创建节点
    public static void testCreate(CuratorFramework client) throws Exception {
        String path = client.create().forPath("/curator-node");
        log.info("curator create node :{} successfully.",path);
    }
    
    //创建多级节点
    public static void testCreateWithParent(CuratorFramework client) throws Exception {
        String locations="/node-parent/sub-node-1";
        String path = client.create().creatingParentsIfNeeded().forPath(locations);
        log.info("curator create node :{} successfully",path);
    }
    
    //获取节点数据
    public static void testGetData(CuratorFramework client) throws Exception {
        byte[] bytes = client.getData().forPath("/curator-node");
        log.info("get data from node:{} successfully.",new String(bytes));
    }
    
    //更新节点
    public static void testSetData(CuratorFramework client) throws Exception {
        client.setData().forPath("/curator-node","changed1111111".getBytes());
        byte[] bytes = client.getData().forPath("/curator-node");
        log.info("get data from node /curator-node : {} successfully",new String(bytes));
    }
    
    //删除节点
    public static void testDelete(CuratorFramework client) throws Exception {
        String path="/node-parent";
        client.delete().guaranteed().deletingChildrenIfNeeded().forPath(path);
    }
    
    public static void testAsync(CuratorFramework client) throws Exception {
        client.getData().inBackground((item1,item2)->{
            log.info(" background:{}",item2);
        }).forPath("/curator-node");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
