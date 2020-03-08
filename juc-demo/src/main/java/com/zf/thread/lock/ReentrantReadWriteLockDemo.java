package com.zf.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther: zf
 * @date: 2020-03-05 14:03
 * @description:运行接口可以得知 写写互斥，写读互斥，读读共享
 *  * 随机数线程w0开始设置值
 *  * 线程w0设置完成
 *  * 线程w1开始设置值
 *  * 线程w1设置完成
 *  * 线程w2开始设置值
 *  * 线程w2设置完成
 *  * 线程w3开始设置值
 *  * 线程w3设置完成
 *  * 线程w4开始设置值
 *  * 线程w4设置完成
 *  * 线程w0开始读取
 *  * 线程w0开始读取完成：0
 *  * 线程w1开始读取
 *  * 线程w1开始读取完成：1
 *  * 线程w2开始读取
 *  * 线程w2开始读取完成：2
 *  * 线程w3开始读取
 *  * 线程w3开始读取完成：3
 *  * 线程w4开始读取
 *  * 线程w4开始读取完成：4
 */

class MyCache{
    private volatile Map<String,String> map=new HashMap<String,String>();
    ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();


    public void put(String key,String value) {

        readWriteLock.writeLock().lock();
        System.out.println("线程"+Thread.currentThread().getName()+"开始设置值");
        try {
            int num=(int)Math.random()*100+100;
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
        } finally {
            System.out.println("线程"+Thread.currentThread().getName()+"设置完成");
            readWriteLock.writeLock().unlock();
        }
    }

    public String get(String key) {
        readWriteLock.readLock().lock();

        try {
            System.out.println("线程"+Thread.currentThread().getName()+"开始读取");
            String o = map.get(key);
            System.out.println("线程"+Thread.currentThread().getName()+"开始读取完成："+o);
            return o;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        double num=Math.random()*120;

        System.out.printf("随机数", num);
        ConcurrentMap map=new ConcurrentHashMap();

        MyCache myCache=new MyCache();

        for (int i = 0; i <5 ; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },"w"+i).start();

        }

        for (int i = 0; i <5 ; i++) {
            final int temp=i;
            new Thread(()->{
                myCache.get(temp+"");
            },"w"+i).start();

        }

    }
}
