package com.zf.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @auther: zf
 * @date: 2020-03-05 19:16
 * @description: CountDownLatch解决：一个是减法，倒计时
 * 假设抗日时期某山谷有日本鬼子5辆军车经过,要求5名狙击手干掉5个司机都撤离后发起总攻 。
 * 必须是5名狙击手同都干掉日本鬼子司机后才可以发起总攻，这样我们损失才最小
 * 或者好理解的火箭发射倒计时的问题
 *
 */
public class CountDownLatchDemo {

    public static void main(String[] args)  {

        startKill1();
        startKill2();


    }


    /**
     * 不加控制容易照成5名狙击手未都干掉后就发起了总攻
     *     main 	 战役开始
     * t1--1号狙击手干掉日本司机得手
     * t2--2号狙击手干掉日本司机得手
     * t3--3号狙击手干掉日本司机得手
     * 开始发起总攻击
     * t4--4号狙击手干掉日本司机得手
     * t5--5号狙击手干掉日本司机得手
     *
     */
    public static void startKill1() {

        System.out.println(Thread.currentThread().getName() + " \t 战役开始");
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--" + temp + "号狙击手干掉日本司机得手");
            }, "t" + i).start();
        }

        System.out.println("开始发起总攻击");
    }

    /**
     * 加了CountDownLatch 可以很好的控制,达到5名狙击手得手后发起总攻
     * main 	 战役开始
     * t1--1号狙击手干掉日本司机得手
     * t2--2号狙击手干掉日本司机得手
     * t3--3号狙击手干掉日本司机得手
     * t4--4号狙击手干掉日本司机得手
     * t5--5号狙击手干掉日本司机得手
     * 开始发起总攻击
     */
    public static void startKill2() {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println(Thread.currentThread().getName() + " \t 战役开始");
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--" + temp + "号狙击手干掉日本司机得手");
                countDownLatch.countDown();
            }, "t" + i).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("开始发起总攻击"); //在这里等待
    }
}
