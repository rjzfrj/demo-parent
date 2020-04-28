package com.zf.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOService {

    public static void main(String[] args) throws IOException {

        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
       //得到一个Selector
        Selector selector=Selector.open();
        //serverSocketChannel绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(1688));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到Selector上去 ,事件为 OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            if(selector.select(1000)==0){
                System.out.println("服务器等待1秒，无客户连接");
                continue;
            }
            //得到注册到Selector的集合
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            Iterator<SelectionKey> iterators= selectionKeys.iterator();
            while (iterators.hasNext()){
                SelectionKey selectionKey= iterators.next();
//            }
//            for (SelectionKey selectionKey : selectionKeys) {

                if (selectionKey.isAcceptable()){
                    //生产一个客户端SocketChannel
                    SocketChannel socketChannel= serverSocketChannel.accept();
                    System.out.println("客户端连接成功 生产了一个socketChannel "+socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel= (SocketChannel) selectionKey.channel();
                   ByteBuffer byteBuffer= (ByteBuffer) selectionKey.attachment();
                    channel.read(byteBuffer);
                    System.out.println("form客户端"+new String(byteBuffer.array(),"utf-8"));
                }

                iterators.remove();
            }
        }
    }
}
