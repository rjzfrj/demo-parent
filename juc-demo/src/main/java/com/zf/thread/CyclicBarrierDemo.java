package com.zf.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;



/**
 * @auther: zf
 * @date: 2020-03-06 14:45
 * @description: 一个是加法，累积
 *   到齐开会,特战队有问题：例如集合五虎上将
 */
public class CyclicBarrierDemo {



    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()->{
            System.out.println("三国五虎上将集合完成");
        });
        for (int i = 1; i <=5 ; i++) {
            final int temp=i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"/t"+TeamEnum.getTeamEnumMsg(temp).getMsg());
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}





