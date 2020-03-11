package com.zf.jvm;

public class UnableToCreateNewNativeThreadDemo {

    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            final int temp =i;
            new Thread(()->{
                System.out.println("------------------i="+Thread.currentThread().getName());
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },temp+"").start();
        }
    }
}
