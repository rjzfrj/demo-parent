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
 * 2.4、本地方法引用的对象
 *
 *
 * 对于根可达算法的示例说明
 *
 */
public class GCRootsDemo {

    public Object instance = null;
    //内存占位用，方便垃圾回收时从日志观察效果 2M
    private byte[] bigSize = new byte[1024*1024*3];
    public static void main(String[] args) {
        testGC();


    }

    /**
     * 如果采用引用计数判断对象，那么上面这种循环应用，对象的计数器永远不会为0，对象也就永远不会被GC回收，
     * 所以实际上GC并没有采用这种判断策略，运行结果如下：
     * [GC pause (G1 Humongous Allocation) (young) (initial-mark) 4767K->3568K(10M), 0.0022156 secs]
     * [GC concurrent-root-region-scan-start]
     * [GC pause (G1 Humongous Allocation) (young)[GC concurrent-root-region-scan-end, 0.0003780 secs]
     * [GC concurrent-mark-start]
     *  3568K->3581K(10M), 0.0022394 secs]
     * [GC concurrent-mark-end, 0.0020319 secs]
     * [GC remark, 0.0012655 secs]
     * [Full GC (System.gc())  6694K->393K(10M), 0.0023698 secs]
     * [GC cleanup, 0.0000192 secs]
     * [GC concurrent-mark-abort]
     */
    private static void testGC() {
        // 对象循环引用示例
        // 如果按照引用计数法，objectA，objectB创建时分别被引用一次，所以count1，count2都为1
        GCRootsDemo objectA = new GCRootsDemo();
        GCRootsDemo objectB = new GCRootsDemo();

        //又被引用一次，count1，count2都为2
        objectA.instance = objectB;
        objectB.instance = objectA;

        // 分别取消引用一次，count1，count2都为1
        objectA = null;
        objectB = null;
        // 强制JVM进行垃圾回收
        System.gc();
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("stop");
    }

    private static void test2GC() {
//        2.1、虚拟机栈中引用的对象(局部变量)
        GCRootsDemo object = new GCRootsDemo();

        // 强制JVM进行垃圾回收
        System.gc();
        try {
            Thread.sleep(1000*60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("stop");
    }
}
