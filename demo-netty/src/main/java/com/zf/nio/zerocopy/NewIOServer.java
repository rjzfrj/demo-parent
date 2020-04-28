package com.zf.nio.zerocopy;


import sun.nio.ch.FileChannelImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress=new InetSocketAddress(1688);
        Selector selector=Selector.open();
        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
//        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(inetSocketAddress);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (true){
            SocketChannel socketChannel= serverSocketChannel.accept();
            int count=0;
            while (count == -1) {
                try {
                    count = socketChannel.read(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                byteBuffer.rewind();
            }
        }

    }
}
