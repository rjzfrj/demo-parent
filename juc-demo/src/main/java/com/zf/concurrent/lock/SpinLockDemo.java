package com.zf.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @auther: zf
 * @date: 2020-03-03 17:57
 * @description: 自旋锁 核心关键是利用compareAndSet 利用AtomicReference类型利用Thread这样就可以控制线程了
 *
 */

class MyLock{
    AtomicReference<Thread> atomicReference =new AtomicReference<Thread>();

    public  void lock() {
        do {
//            System.out.println("Thread = [" + Thread.currentThread().getName() + "]");
        } while (!atomicReference.compareAndSet(null,Thread.currentThread()));
        //cas比较设置完成 如果没人用这个是true就过了别的线程此时此刻再来获取锁因为atomicReference中不为null所以自旋等待
        //
    }

    public  void unlock() {
//        解锁巧妙使用 compareAndSet 修改设置成0来实现解锁
        atomicReference.compareAndSet(Thread.currentThread(),null);
    }

}



public class SpinLockDemo {


    public static void main(String[] args)throws Exception {

        MyLock myLock=new MyLock();
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
