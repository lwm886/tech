package com.cfg.selector;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2024/2/28
 */
public class LeaderSelectorDemo {
    private static final String CONNECT_STR = "192.168.50.150:2181";

    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(5 * 1000, 10);

    private static CuratorFramework curatorFramework;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        String appName = System.getProperty("appName");
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STR, retryPolicy);
        LeaderSelectorDemo.curatorFramework=curatorFramework;
        curatorFramework.start();

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter()
        {
            public void takeLeadership(CuratorFramework client) throws Exception
            {

                System.out.println(" I' m leader now . i'm , "+appName);

                TimeUnit.SECONDS.sleep(15);

            }
        };

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, "/cachePreHeat_leader", listener);
        leaderSelector.autoRequeue();
        leaderSelector.start();
        countDownLatch.await();
    }
}
