package com.zf.jvm.oom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 当一些方法无限制的调用或者方法太多会占用栈内存直到内存用完，常出现的问题就是递归调用没有条件调出
 * 会出现 java.lang.StackOverflowError 错误
 *
 */
public class StackOverflowErrorDemo {



    public static void main(String[] args) {
        MyData myData=new MyData();
        myData.meth();
    }


}
class MyData{
    AtomicInteger atomicIntege=new AtomicInteger();

    public  void meth(){
       int ret= atomicIntege.incrementAndGet();
        System.out.println(ret);
        meth();
    }
}

