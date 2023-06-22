package com.tech.redis.lock;

import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lw
 * @since 2023/6/20
 */
@RestController
public class LockController {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    Redisson redisson;

    @RequestMapping("deductStock")
    public String deductStock() {
        String lockKey = "product_1";
        String clientId = UUID.randomUUID().toString();
        RLock lock = redisson.getLock(lockKey);
        try {
            /*Boolean res = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            if(!res){
                return "error code";
            }*/
            lock.lock();
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            lock.unlock();
            /*if(clientId.equals(redisTemplate.opsForValue().get(lockKey))){
                redisTemplate.delete(lockKey);
            }*/
        }
        return "end";
    }

    /**
     * 使用红锁解决，主从架构，在主节点上获得锁，主节点尚未同步到从节点，主节点挂掉问题
     * 红锁通过给多个平级的Redis实setnx，半数以上成功则获取锁成功
     * @return
     */
    @RequestMapping("redLock")
    public String redLock(){
        String lockKey = "product_1";
        //这里需要使用不同Redis实例的Redisson客户端连接,这里只是伪代码用一个Redisson客户端简化了
        RLock lock1 = redisson.getLock(lockKey);
        RLock lock2 = redisson.getLock(lockKey);
        RLock lock3 = redisson.getLock(lockKey);
        //使用多个RLock对象构建RedissonRedLock
        RedissonRedLock redLock = new RedissonRedLock(lock1, lock2, lock3);
        try{
            try {
                /**
                 * waitTime 尝试获得锁的最大等待时间，超过这个值，则任务获取锁失败
                 * leaseTime 锁的持有时间，超过这个时间锁会自动失效（值应该设置为大于业务处理的时间，确保锁在有效期内业务能处理完）
                 */
                boolean res = redLock.tryLock(10, 30, TimeUnit.SECONDS);
                if(res){
                    //成功获得锁，在这里执行业务
                }
            } catch (Exception e) {
                throw new RuntimeException("lock fail");
            }
        }finally {
            //都要解锁
            redLock.unlock();
        }
        return "end";
    }

    /**
     * 分布式读写解决缓存数据库双写不一致问题
     * 读数据库 写缓存
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("getStock")
    public String getStock() throws InterruptedException {
        String lockKey="product_stock_1";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(lockKey);
        RLock rLock = readWriteLock.readLock();
        rLock.lock();
        System.out.println("获取读锁成功");
        String stock = redisTemplate.opsForValue().get("stock");
        if(stock==null){
            System.out.println("查询数据库库存 10");
            TimeUnit.SECONDS.sleep(5);
            redisTemplate.opsForValue().set("stock","10");
        }
        rLock.unlock();
        System.out.println("释放读锁成功");
        return "end";
    }

    /**
     * 分布式读写解决缓存数据库双写不一致问题
     * 写数据 删缓存
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("writeLock")
    public String writeStock() throws InterruptedException {
        String lockKey="product_stock_1";
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(lockKey);
        RLock rLock = readWriteLock.writeLock();
        rLock.lock();
        System.out.println("获取写锁成功");
        System.out.println("写数据库存 6");
        redisTemplate.delete(lockKey);
        TimeUnit.SECONDS.sleep(5);
        rLock.unlock();
        System.out.println("释放写锁成功");
        return "end";
    }
}
