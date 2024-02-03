package com.tech.zookeeper.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author lw
 * @since 2024/2/3
 */
@Slf4j
public class ZookeeperClientTest {
    
    private static final String ZK_ADDRESS="192.168.50.149:2181";
    
    private static final int SESSION_TIMEOUT=5000;
    
    private static ZooKeeper zooKeeper;
    
    private static final String ZK_NODE="/zk-node";
    
    public void init() throws IOException, InterruptedException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        zooKeeper=new ZooKeeper(ZK_ADDRESS,SESSION_TIMEOUT,event -> {
            if(event.getState()== Watcher.Event.KeeperState.SyncConnected && event.getType()==Watcher.Event.EventType.None){
                countDownLatch.countDown();
                log.info("连接成功");
            }
        });
        log.info("连接中");
        countDownLatch.await();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new ZookeeperClientTest().init();
    }
}
