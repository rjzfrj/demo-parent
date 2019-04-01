package com.zf;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式单利模式
 *
 */
public class Singleton {

    private final static Singleton sin=new Singleton();
    AtomicLong atomicLong=new AtomicLong(100000);
    private Singleton(){}
    public static Singleton getInstance(){
        return sin;
    }
    public Long buildNum(){
        return  atomicLong.getAndAdd(1);
    }



}
