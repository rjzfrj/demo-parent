package com.zf.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: zf
 * @date: 2020-03-03 17:57
 * @description:
 */

class MyLock{
    AtomicInteger atomicInteger=new AtomicInteger(0);

    public  void lock() {
        do {
//            System.out.println("Thread = [" + Thread.currentThread().getName() + "]");
        } while (!atomicInteger.compareAndSet(0,1));

    }

    public  void unlock() {
        atomicInteger.compareAndSet(1,0);
    }

}



public class SpinLockDemo {


    public static void main(String[] args)throws Exception {

        MyLock myLock=new MyLock()
        new Thread(()->{
            myLock.lock();
            System.out.println("进入"+Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myLock.unlock();
        },"t1").start();

        new Thread(()->{

            myLock.lock();
            System.out.println("进入"+Thread.currentThread().getName());
            myLock.unlock();
        },"t2").start();

    }


}
