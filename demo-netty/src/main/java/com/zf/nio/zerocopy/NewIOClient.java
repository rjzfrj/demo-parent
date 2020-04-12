package com.zf.nio.zerocopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {

    public static void main(String[] args) throws IOException {
//        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",1688);
//        SocketChannel socketChannel=SocketChannel.open();
//        socketChannel.connect(inetSocketAddress);
////        socketChannel.configureBlocking(false);
//        long startTime = System.currentTimeMillis();
//        FileChannel fileChannel = new FileOutputStream("nginx-1.6.2.tar.gz").getChannel();
//        System.out.println(System.getProperties().getProperty("os.name"));
//       String separator= System.getProperties().getProperty("file.separator");
//        System.out.println("===========file.separator:"+System.getProperties().getProperty("file.separator"));
//        if(separator.equals("\\")){
//            System.out.println("win");
//           long size= fileChannel.size();
//           if(size>8){
//              long num= size/8;
//               long yu= size%8;
//               if(yu!=0){
//                   num=num+1;
//               }
//               for (int i = 0; i <=num ; i++) {
//                   long transferCount=  fileChannel.transferTo(i, num*(i+1),socketChannel);
//                   System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
//               }
//           }else{
//               long transferCount=  fileChannel.transferTo(0, fileChannel.size(),socketChannel);
//               System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
//           }
//
//        }else{
//            long transferCount=  fileChannel.transferTo(0, fileChannel.size(),socketChannel);
//            System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));
//        }
//
//        fileChannel.close();


        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 1688));
        String filename = "nginx-1.6.2.tar.gz";

        //得到一个文件channel
        FileChannel fileChannel = new FileInputStream(filename).getChannel();

        //准备发送
        long startTime = System.currentTimeMillis();

        //在linux下一个transferTo 方法就可以完成传输
        //在windows 下 一次调用 transferTo 只能发送8m , 就需要分段传输文件, 而且要主要
        //传输时的位置 =》 课后思考...
        //transferTo 底层使用到零拷贝
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总的字节数 =" + transferCount + " 耗时:" + (System.currentTimeMillis() - startTime));

        //关闭
        fileChannel.close();
    }
}
