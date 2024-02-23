package com.tech.zookeeper.client;

import com.sun.org.apache.bcel.internal.generic.FALOAD;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/2/3
 */
@Slf4j
public class ZookeeperClientTest {
    
    private static final String ZK_ADDRESS="192.168.50.150:2181";
    
    private static final int SESSION_TIMEOUT=5000;
    
    private static ZooKeeper zooKeeper;
    
    private static final String ZK_NODE="/zk-node";
    
    //创建客户端实例
    public void init() throws IOException, InterruptedException, KeeperException {
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        zooKeeper=new ZooKeeper(ZK_ADDRESS,SESSION_TIMEOUT,event -> {
            if(event.getState()== Watcher.Event.KeeperState.SyncConnected && event.getType()==Watcher.Event.EventType.None){
                countDownLatch.countDown();
                log.info("连接成功");
            }
        });
        log.info("连接中");
        countDownLatch.await();
//        createTest();
//        createAsync();
        setTest();
    }
    
    //同步创建节点
    public void createTest() throws InterruptedException, KeeperException {
        String path = zooKeeper.create(ZK_NODE, "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        log.info("created path: {}",path);
    }
    
    //异步创建节点
    public void createAsync() throws InterruptedException {
        zooKeeper.create(ZK_NODE,"data".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,(rc,path,ctx,name)->{
            log.info("rc {},path {},ctx {},name {}",rc,path,ctx,name);
        },"context");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
    
    //修改节点
    public void setTest() throws InterruptedException, KeeperException {
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData(ZK_NODE, false, stat);
        log.info("修改前：{}",new String(data));
        zooKeeper.setData(ZK_NODE,"changed!".getBytes(), stat.getVersion());
        byte[] dataAfter = zooKeeper.getData(ZK_NODE, false, stat);
        log.info("修改后：{}",new String(dataAfter));

    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        new ZookeeperClientTest().init();
    }
}
