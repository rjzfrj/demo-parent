package com.zf;
public class ThreadMy2 implements Runnable {
    private  BuildOrderNumSingle buildOrderNumSingle;



    ThreadMy2(BuildOrderNumSingle singleton){
        this.buildOrderNumSingle=singleton;
    }
    @Override
    public void run() {
        System.out.println(buildOrderNumSingle.buildNum());
    }


    public BuildOrderNumSingle getBuildOrderNumSingle() {
        return buildOrderNumSingle;
    }

    public void setBuildOrderNumSingle(BuildOrderNumSingle buildOrderNumSingle) {
        this.buildOrderNumSingle = buildOrderNumSingle;
    }
}