package com.zf;
public class ThreadMy implements Runnable {
    private  Singleton singleton;

    public Singleton getSingleton() {
        return singleton;
    }

    public void setSingleton(Singleton singleton) {
        this.singleton = singleton;
    }

    ThreadMy(Singleton singleton){
        this.singleton=singleton;
    }
    @Override
    public void run() {
        System.out.println(singleton.buildNum());
    }
}