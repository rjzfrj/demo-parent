package com.zf.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @auther: zf
 * @date: 2020-03-08 15:26
 * @description:
 */
public class BlockingQueueDemo {


    public static void main(String[] args)throws Exception {
        BlockingQueue<String> blockingDeque=new ArrayBlockingQueue<String>(2);
//      groupOne(blockingDeque);
//        groupTwo(blockingDeque);
//        groupThree(blockingDeque);
        groupFour(blockingDeque);
    }


    /**
     * 第一组抛出异常组
     */
    public static void groupOne(BlockingQueue<String> blockingDeque) {
        System.out.println(blockingDeque.add("1"));
        System.out.println(blockingDeque.add("2"));
//        System.out.println(blockingDeque.add("3")); //多插入一个就抛异常
        System.out.println(blockingDeque.remove());
        System.out.println(blockingDeque.remove());
//        System.out.println(blockingDeque.remove()); //多删除一个就抛异常
        System.out.println(blockingDeque.element());  //检查有没有数据没有就抛异常
    }

    /**
     * 第二组温柔 满了就返回false，取不到就返回null
     */
    public static void groupTwo(BlockingQueue<String> blockingDeque) throws InterruptedException {
        System.out.println(blockingDeque.offer("1")); //插入
        System.out.println(blockingDeque.offer("2"));
        System.out.println(blockingDeque.offer("3"));

        System.out.println(blockingDeque.take());//移除
        System.out.println(blockingDeque.take());
        System.out.println(blockingDeque.take());
        System.out.println(blockingDeque.peek()); //检查
    }


    /**
     * 第三组阻塞 满了就阻塞，取不到就阻塞 一致阻塞
     */
    public static void groupThree(BlockingQueue<String> blockingDeque) throws InterruptedException {
        blockingDeque.put("1"); //插入
        blockingDeque.put("2");
        blockingDeque.put("3");
        System.out.println(blockingDeque.take());//移除
        System.out.println(blockingDeque.take());
        System.out.println(blockingDeque.take());
    }
    /**
     * 第四组阻塞 满了就阻塞，取不到就阻塞 但是可以设置阻塞的时长
     */
    public static void groupFour(BlockingQueue<String> blockingDeque) throws InterruptedException {
        System.out.println(blockingDeque.offer("1", 2, TimeUnit.SECONDS));
        System.out.println(blockingDeque.offer("2", 2, TimeUnit.SECONDS));
        System.out.println(blockingDeque.offer("3", 2, TimeUnit.SECONDS));

        System.out.println(blockingDeque.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingDeque.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingDeque.poll(2, TimeUnit.SECONDS));
    }


}
