public class Test01 {


    /***
     * -XX:+PrintGC -Xms2m -Xmx20m -XX:+UseSerialGC  -XX:+PrintGCDetails -XX:+PrintCommandLineFlags
     * 说明 -XX是系统级别的
     *      -X是应用级别的
     *      + 是代表启用
     *      PrintGC 打印GC信息
     *      UseSerialGC 使用串行垃圾回收器
     *      PrintGCDetails 打印垃圾回收详情
     *      PrintCommandLineFlags 打印程序启动设置的所有参数
     *    总结：在实际工作中，我们可以直接把-Xms和-Xmx设置相等。
     * @param string
     */
    public static void main(String string[]){
        System.out.println("max memory: "+Runtime.getRuntime().maxMemory());
        System.out.println("free memory: "+Runtime.getRuntime().freeMemory());
        System.out.println("total memory: "+Runtime.getRuntime().totalMemory());
        byte[] b1=new byte[1*1024*1024];
        System.out.println("分配了1M");
        System.out.println("max memory: "+Runtime.getRuntime().maxMemory());
        System.out.println("free memory: "+Runtime.getRuntime().freeMemory());
        System.out.println("total memory: "+Runtime.getRuntime().totalMemory());
        byte[] b2=new byte[4*1024*1024];
        System.out.println("分配了4M");
        System.out.println("max memory: "+Runtime.getRuntime().maxMemory());
        System.out.println("free memory: "+Runtime.getRuntime().freeMemory());
        System.out.println("total memory: "+Runtime.getRuntime().totalMemory());
    }
}
