package com.zf.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: zf
 * @date: 2020-03-07 16:09
 * @description: 利用Bloke
 */
public class VersionTwoDemo {


    public static void main(String[] args) throws Exception {
        MyDataResource myDataResource=new MyDataResource(new ArrayBlockingQueue<String>(3));
//        for (int i = 0; i <3 ; i++) {
        new Thread(()->{
                myDataResource.providute();

        },"生产线程").start();
//        }

        new Thread(()->{
                myDataResource.consumer();
        },"消费线程").start();

        System.out.println("");System.out.println("");System.out.println("");


        Thread.sleep(10000);
        myDataResource.stop();
        System.out.println("10秒后全部叫停"+myDataResource.getFlag());

    }


}

class MyDataResource{


    private volatile boolean flag=true;
    private AtomicInteger atomicInteger=new AtomicInteger();

    private BlockingQueue<String> blockingQueue=null;

    public MyDataResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }


    public void providute(){
        int data=0;
        while (flag) {
            try {
                data=atomicInteger.incrementAndGet();
                boolean isno=  blockingQueue.offer(data+"",2L, TimeUnit.SECONDS);
                if (isno) {
                    System.out.println(Thread.currentThread().getName()+"\t 生产了"+data+"成功");
                }else {
                    System.out.println(Thread.currentThread().getName()+"\t 生产失败");
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("不在生产");
    }

    public void consumer(){
        String ret=null;
        while (flag) {
            try {
              ret=blockingQueue.poll(2,TimeUnit.SECONDS);  //因为是阻塞队列如果没数据会在这等说要
                if (ret == null || "".equals(ret)) {
                    System.out.println("没有数据可以消费");
                    return;
                }else{
                    System.out.println(Thread.currentThread().getName()+"\t 消费了"+ret+"成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("不在消费");
    }

    public void stop(){
        this.flag=false;
    }

    public boolean getFlag() {
        return flag;
    }
}