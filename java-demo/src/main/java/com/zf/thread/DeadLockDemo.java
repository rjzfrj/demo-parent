package com.zf.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class MyDataLock implements Runnable {

    private String lockA;
    private String lockB;

    public MyDataLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "获得" + lockA + "正在尝试获取" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "获得" + lockB + "正在尝试获取" + lockA);
            }
        }
    }
}

/**
 * @auther: zf
 * @date: 2020-03-07 23:53
 * @description: 死锁是指两个线程或者两个以上的线程在进行过程中，因为争夺资源而照成的
 * 相互等待的现象，若无外力干涉他们讲无法进行推进
 * A持有A锁
 */

public class DeadLockDemo {


    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyDataLock(lockA, lockB), "t1").start();
        new Thread(new MyDataLock(lockB, lockA), "t2").start();
    }
}
