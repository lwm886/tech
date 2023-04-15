package com.tech.forkjoinpool.countedcompleter;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.ForkJoinPool;

/**
 * 任务完成时会触发一个自定义的钩子函数
 * 一个大数组拆分成若干小数组
 * 每个线程计算每个小数组中各个元素的阶乘并打印，线程计算完所有任务后打印一下计算了几个数
 *
 * @author lw
 * @since 2023-04-15
 */
@Slf4j
public class CountedCompleterExample {

    public static void main(String[] args) {
        List<BigInteger> list=new ArrayList<>();
        for(int i=3;i<20;i++){
            list.add(new BigInteger(i+""));
        }
        ForkJoinPool.commonPool().invoke(new FactoritialTask(null,list));
    }

    private static class FactoritialTask extends CountedCompleter<Void> {

        private static int SEQUENTIAL_THRESHOLD = 5;
        private List<BigInteger> integerList;
        private int numCalculated;

        public FactoritialTask(CountedCompleter<?> completer, List<BigInteger> integerList) {
            super(completer);
            this.integerList = integerList;
        }


        @Override
        public void compute() {
            if(integerList.size()<SEQUENTIAL_THRESHOLD){
                showFactoritial();
            }else{
                int mid=integerList.size()/2;
                List<BigInteger> leftList=integerList.subList(0,mid);
                List<BigInteger> rightList=integerList.subList(mid,integerList.size());
                addToPendingCount(2);
                FactoritialTask left=new FactoritialTask(this,leftList);
                FactoritialTask right=new FactoritialTask(this,rightList);
                left.fork();
                right.fork();
            }
            tryComplete();
        }
        @Override
        public void onCompletion(CountedCompleter<?> caller) {
            if(caller==this){
                log.info("numCaled={}",numCalculated);
            }
        }

        private void showFactoritial() {
            for(BigInteger i:integerList){
                BigInteger cal = cal(new BigInteger(i + ""));
                log.info("i={} f={}",i,cal);
                numCalculated++;
            }
        }

        //计算阶乘
        private BigInteger cal(BigInteger param){
            if(param.intValue()==1){
                return new BigInteger("1");
            }
            BigInteger subtract = param.subtract(new BigInteger("1"));
            return param.multiply(cal(subtract));
        }

    }
}
