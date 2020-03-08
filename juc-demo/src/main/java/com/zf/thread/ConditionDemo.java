package com.zf.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther: zf
 * @date: 2020-03-07 13:05
 * @description: 多线程之间的调用顺序 Condition 的精确唤醒
 *
 * 题目：多线程之间的调用顺序实现:T1,T2,T3,T4 四个线按照T1-》T2—》T3-》-》T4 的顺序进行
 * T1打印1次，T2打印2次，T3打印3次，T4打印4次
 * 然后再T1打印1次，T2打印2次，T3打印3次，T4打印4次
 * 往返10轮
 *
 */
public class ConditionDemo {

    public static void main(String[] args) {
        MyDataCondition myDataCondition=new MyDataCondition();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
            myDataCondition.methodA();
            }
        },"t1").start();


        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                myDataCondition.methodB();
            }

        },"t2").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                myDataCondition.methodC();

            }
        },"t3").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                myDataCondition.methodD();
            }

        },"t4").start();
    }


}
class MyDataCondition{

    private volatile int num=1;
    Lock lock=new ReentrantLock();
    Condition condition1=lock.newCondition();
    Condition condition2=lock.newCondition();
    Condition condition3=lock.newCondition();
    Condition condition4=lock.newCondition();

    public void methodA() {
        lock.lock();
        try {
            while (num!=1){
                condition1.await();
            }
            num=2;
            System.out.println("-----------------------" );
            System.out.println("线程"+Thread.currentThread().getName()+"\t 调用methodA"+num );
//            methodB();
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void methodB() {
        lock.lock();
        try {
            while (num!=2){
                condition2.await();
            }
            num=3;
            for (int i = 1; i <=2 ; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+"\t 调用methodB"+i );
            }
//            methodC();
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void methodC() {
        lock.lock();
        try {
            while (num!=3){
                condition3.await();
            }
            num=4;
            for (int i = 1; i <=3 ; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+"\t 调用methodC "+i );
            }
//             methodD();
            condition4.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void methodD() {
        lock.lock();
        try {
            while (num!=4){
                condition4.await();
            }
            num=1;
            for (int i = 1; i <=4 ; i++) {
                System.out.println("线程"+Thread.currentThread().getName()+"\t 调用methodD "+i );
            }
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
