package com.zf.concurrent.lock;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther: zf
 * @date: 2020-03-03 16:47
 * @description: 重入锁 （又叫递归锁）
 * 指的是一个线程获取一把锁后，如果该锁范围内又调用来一个加锁的方法自动会获取进入
 *  换言之就锁线程可以进入任何一个它持有锁的代码快内的方法
 */

class MyData{

    Lock lock=new ReentrantLock();

    /**
     * case one 利用synchronized 锁可重入到例子
     */
    public synchronized void  method1(){
        System.out.println("线程name："+Thread.currentThread().getName());
        method2();
    }

    public synchronized void  method2(){
        System.out.println("线程name："+Thread.currentThread().getName());
        String abc = "abc";

    }


    /**
     * case two 利用synchronized 锁可重入到例子
     */
    public  void  methodA1(){

        lock.lock();
        try {
            System.out.println("线程name："+Thread.currentThread().getName());
            methodA2();
        } finally {
            lock.unlock();
        }

    }

    public  void  methodA2(){
        lock.lock();
        try {
            System.out.println("线程name："+Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }




}

public class ReenterLockDemo {

    public static void main(String[] args) {

//        caseOne();
//        caseTwo();

        Runnable adsfb = () -> System.out.println("adsfb");
        Optional.of(adsfb);

    }


    /**
     * case one
     */
    private static void caseOne() {
        MyData myData=new MyData();
        new Thread(()->{
            myData.method1();

        },"t1").start();


        new Thread(()->{
            myData.method1();

        },"t2").start();
    }


    private static void caseTwo() {
        MyData myData=new MyData();
        new Thread(()->{
            myData.methodA1();

        },"t1").start();


        new Thread(()->{
            myData.methodA1();

        },"t2").start();
    }
}
