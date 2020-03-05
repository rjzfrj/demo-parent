package com.zf.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @auther: zf
 * @date: 2020-03-05 19:16
 * @description:
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {


        CountDownLatch countDownLatch=new CountDownLatch(3);

        System.out.println("开始");
        countDownLatch.countDown();

    }
}
