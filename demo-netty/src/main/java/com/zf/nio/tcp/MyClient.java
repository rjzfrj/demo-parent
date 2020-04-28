package com.zf.nio.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;


public class MyClient {
    //属性
    private final String host;
    private final int port;

    public MyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        new MyClient("127.0.0.1", 8888).run();
    }

        private  void run() throws InterruptedException {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientHandler());
            System.out.println("客户端ok");
            //关于 ChannelFuture 要分析，涉及到netty的异步模型
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            //得到channel
            Channel channel = channelFuture.channel();
            System.out.println("-------" + channel.localAddress()+ "--------");
            //客户端需要输入信息，创建一个扫描器
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                //通过channel 发送到服务器端
                channel.writeAndFlush(msg+"\r\n");
            }
        } finally {
            //关闭组
            group.shutdownGracefully();
        }
    }
//    public void run() throws Exception {
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        try {
//
//
//            Bootstrap bootstrap = new Bootstrap()
//                    .group(group)
//                    .channel(NioSocketChannel.class)
//                    .handler(new ChannelInitializer<SocketChannel>() {
//
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//
//                            //得到pipeline
//                            ChannelPipeline pipeline = ch.pipeline();
//                            //加入相关handler
//                            pipeline.addLast("decoder", new StringDecoder());
//                            pipeline.addLast("encoder", new StringEncoder());
//                            //加入自定义的handler
//                            pipeline.addLast(new MyChannelInitializer());
//                        }
//                    });
//
//            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
//            //得到channel
//            Channel channel = channelFuture.channel();
//            System.out.println("-------" + channel.localAddress() + "--------");
//            //客户端需要输入信息，创建一个扫描器
//            Scanner scanner = new Scanner(System.in);
//            while (scanner.hasNextLine()) {
//                String msg = scanner.nextLine();
//                //通过channel 发送到服务器端
//                channel.writeAndFlush(msg + "\r\n");
//            }
//        } finally {
//            group.shutdownGracefully();
//        }
//    }


}
