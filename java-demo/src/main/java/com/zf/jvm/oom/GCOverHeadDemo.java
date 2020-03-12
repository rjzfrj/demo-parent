package com.zf.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 为了说明问题让错误好出现：设置堆大小： -Xms10m -Xmx10m
 *
 *  Exception in thread "main" ----------i:100898
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class GCOverHeadDemo {


    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        int i=0;
        try {
            while (true){
                i++;
                list.add(System.currentTimeMillis()+"");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("----------i:"+i);
        }
    }
}
