package com.zf;

import java.util.concurrent.atomic.AtomicLong;

public class BuildOrderNumSingle {
    AtomicLong atomicLong=new AtomicLong(1);

    private BuildOrderNumSingle(){}


    private static class BuildOrderNumSingleInstance {
        private static final  BuildOrderNumSingle  buildOrderNumSingle=new BuildOrderNumSingle();
    }

    public static BuildOrderNumSingle getInstance(){
       return BuildOrderNumSingleInstance.buildOrderNumSingle;
    }

    public Long buildNum(){
       Long i= atomicLong.getAndAdd(1);
        if(i>99){
            atomicLong.set(1);
        }
      return i;
    }



}
