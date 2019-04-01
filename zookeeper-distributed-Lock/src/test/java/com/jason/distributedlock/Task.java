package com.jason.distributedlock;

import com.jason.pandaLock.core.serverImpl.ZkPandaLock;

public class Task implements  Runnable {
    private int num;
    public Task(int num){
        this.num=num;
    }
    @Override
    public void run() {
        try {
            ZkPandaLock zkPandaLock = new ZkPandaLock();
            zkPandaLock.connectZooKeeper("192.168.2.101:2181", "jason");
            zkPandaLock.lock();
            System.out.println(Thread.currentThread().getName()+"在做事，做完就释放锁");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"我做完事情了");
            zkPandaLock.releaseLock();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
