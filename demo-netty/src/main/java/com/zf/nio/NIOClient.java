package com.zf.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        //得到一个网络通道
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",1688);

        if(!socketChannel.connect(inetSocketAddress)){
            while(!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作。。。");
            }
        }
        String str="hello ,xxx";
        //wrap 相当于指定大小然后放入内容
        ByteBuffer buffer=ByteBuffer.wrap(str.getBytes());
        socketChannel.write(buffer);
        System.in.read();


    }
}
