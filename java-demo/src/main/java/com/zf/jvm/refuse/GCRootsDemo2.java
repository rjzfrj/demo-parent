package com.zf.jvm.refuse;

/**
 * @auther: zf
 * @date: 2020-03-14 14:06
 * @description: 垃圾回收 有两种算法：
 * 1、引用计数算法 （对于引用计数存在的循环引用的问题 在主流的JVM中没有选用引用计数法来管理内存，
 *   最主要的原因就是引用计数法无法解决对象的循环引用问题。）
 * 2、根可达算法（Root Tracing）
 *  可以作为GC Roots的对象有如下几种：
 * 2.1、虚拟机栈中引用的对象(局部变量)
 * 2.2、方法去中类静态属性引用的对象(static对象实例)
 * 2.3、方法区中常量引用的对象(常量实例)
 * 2.4、本地方法栈中JNI（即一说的native方法）引用的对象 具体指线程中star()方法也叫做native方法
 *
 *
 * 对于根可达算法的示例说明
 *
 */
public class GCRootsDemo2 {

    //内存占位用，方便垃圾回收时从日志观察效果 2M
    private byte[] bigSize = new byte[1024*1024*3];

//     2.2、方法去中类静态属性引用的对象(static对象实例)
    private static GCRootsDemo2 t2;
//    2.3、方法区中常量引用的对象(常量实例)
    private static final GCRootsDemo2 t3=new GCRootsDemo2();

    public static void main(String[] args) {
        test2GC();
    }


    private static void test2GC() {
//        2.1、虚拟机栈中引用的对象(局部变量) object就是虚拟机栈中引用的对象（ 堆中堆对象）
        GCRootsDemo2 object = new GCRootsDemo2();

        t2=object;

        System.out.println(t3.hashCode());
        // 强制JVM进行垃圾回收
        System.gc();

        new Thread(()->{
            System.out.println("111");
        }).start();

//        try {
//            Thread.sleep(6);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("stop");
    }
}
