package com.tech.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @author lw
 * @since 2023-04-04
 */
@Slf4j
public class Queue {
    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue a = new ArrayBlockingQueue(10);
//        BlockingQueue b = new LinkedBlockingQueue();
//        BlockingQueue c = new PriorityBlockingQueue<>();
//        DelayQueue d = new DelayQueue();
        //优先级队列测试
//        priorityQueueTest();

        //延迟队列测试
        DelayQueueTest();

    }

    private static void DelayQueueTest() throws InterruptedException {
        //延迟队列：它是一个基于优先级支持，时间调度的队列，也是一个阻塞队列
        DelayQueue<Order> delayQueue = new DelayQueue();
        delayQueue.put(new Order("SN100",3000));
        delayQueue.put(new Order("SN110",1000));
        delayQueue.put(new Order("SN111",5000));

        while (!delayQueue.isEmpty()){
            Order order = delayQueue.take(); //如果用poll，没到时间会返回null
            log.info(order.toString());
        }

    }



    private static void priorityQueueTest() {
        //优先级队列(堆排序 二叉树组织数据) 默认升序
        System.out.println("优先级队列：默认升序排列");
        PriorityQueue queue = new PriorityQueue();
        queue.offer(10);
        queue.offer(1);
        queue.offer(11);

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }

        System.out.println("优先级队列重写比较器：降序排列");
        //重写比较强，让优先级队列降序排列
        PriorityQueue<Integer> descQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        descQueue.offer(10);
        descQueue.offer(1);
        descQueue.offer(11);
        while (!descQueue.isEmpty()){
            log.info(descQueue.poll().toString());
        }
    }

}

//延迟队列中的元素必须实现Delayed接口，重写获取延迟时间和比较排序功能
class Order implements Delayed{
    //对象创建时间
    private long startTime=System.currentTimeMillis();
    //延迟任务名称
    private String orderNo;
    //延迟时间
    private long time;

    public Order(String orderNo,long time){
        this.orderNo=orderNo;
        this.time=time;
    }

    //获取延迟时间  截止时间-当前时间
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime+time-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    //比较排序  当前对象的延迟时间-比较对象的延迟时间
    @Override
    public int compareTo(Delayed o) {
        return (int)(this.getDelay(TimeUnit.MILLISECONDS)- o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "Order{" +
                "startTime=" + startTime +
                ", orderNo='" + orderNo + '\'' +
                ", time=" + time +
                '}';
    }
}