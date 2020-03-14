package com.zf.thread;

import java.util.concurrent.Semaphore;

/**
 * @auther: zf
 * @date: 2020-03-07 09:44
 * @description: 秒杀限流 停车场停车
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(5);

        for (int i = 1; i <=10 ; i++) {
            new Thread(()->{
                try {

                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"线程进入");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"线程用完");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },"in"+i).start();
        }

//        for (int i = 1; i <=10 ; i++) {
//            new Thread(()->{
//                try {
//                    semaphore.acquire();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            },"out"+i).start();
//        }



    }

}
