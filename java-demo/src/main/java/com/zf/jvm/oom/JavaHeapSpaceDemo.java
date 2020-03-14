package com.zf.jvm.oom;

/**
 *  为了容易出现异常假设故意把堆内存设置 ：-Xms10m -Xmx10m 很快会出现下面的错误
 *
 *  java.lang.OutOfMemoryError: Java heap space
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String abc = "abc";
        while (true){
            abc+=abc+System.currentTimeMillis()+"a";
        }


    }
}
