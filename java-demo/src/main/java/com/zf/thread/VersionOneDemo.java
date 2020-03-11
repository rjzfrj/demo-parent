package com.zf.thread;



import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther: zf
 * @date: 2020-03-07 10:47
 * @description: 传统版的 4个线程分别对num交替加减1，轮10轮
 *
 *
 * 1线程 操作资源类
 * 2判断  工作  通知
 * 3防止虚假唤醒（调用加减的方法2就会出现虚假唤醒）
 *
 */
public class VersionOneDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();

            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    myData.add2();
                }
            }, "t1加法").start();



            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    myData.subtract2();
                }
            }, "t2减法").start();  new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    myData.add2();
                }
            }, "t3加法").start();



            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    myData.subtract2();
                }
            }, "t4减法").start();

    }

}



class MyData {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private volatile int num = 0;

    public void add() {
        try {
            lock.lock();  //1加锁
            while (num != 0) {   //2判断 如果不等于0等待 多线程的判断的使用while
                condition.await();
            }
            //3否则干活
            num++;
            System.out.println(Thread.currentThread().getName() + " num：" + num);
            condition.signalAll();//4干完活通知唤醒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void subtract() {
        try {
            lock.lock();
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "  num：" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //下面的方法回出现虚假唤醒
    public void add2() {
        try {
            lock.lock();  //1加锁
            if (num != 0) {   //2判断 如果不等于0等待
                condition.await();
            }
            //3否则干活
            num++;
            System.out.println(Thread.currentThread().getName() + " num：" + num);
            condition.signalAll();//4干完活通知唤醒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void subtract2() {
        try {
            lock.lock();
            if(num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "  num：" + num);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

