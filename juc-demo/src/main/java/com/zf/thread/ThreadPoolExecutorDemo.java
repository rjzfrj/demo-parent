package com.zf.thread;

import java.util.Collections;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: zf
 * @date: 2020-03-08 20:33
 * @description:
 *
 * new ThreadPoolExecutor.AbortPolicy()      超过就拒绝
 * new ThreadPoolExecutor.CallerRunsPolicy() 超过最大线程数+队列数（15）  就会退给主线程
 * new ThreadPoolExecutor.DiscardOldestPolicy() 拒绝最老的一个
 * new ThreadPoolExecutor.DiscardPolicy()       放不下就丢弃
 *
 */
public class ThreadPoolExecutorDemo {


    public static void main(String[] args) throws Exception {
        AtomicInteger atomicInteger=new AtomicInteger();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i <200 ; i++) {
            threadPoolExecutor.execute(()->{
                System.out.println("t1"+Thread.currentThread().getName()+"多少："+atomicInteger.incrementAndGet());
            });
        }

    }
}
