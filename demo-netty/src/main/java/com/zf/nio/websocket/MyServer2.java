package com.zf.nio.websocket;


import com.zf.nio.tcp.MyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer2 {

    //监听端口
    private int PORT;

    public MyServer2(int PORT) {
        this.PORT = PORT;
    }

    public void run(){
        EventLoopGroup bossGroup=new NioEventLoopGroup(1);
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup); //设置两个线程组
            serverBootstrap.channel(NioServerSocketChannel.class); //使用NioSocketChannel 作为服务器的通道实现
//                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到连接个数
//                    .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {  //创建一个通道初始化对象(匿名对象)
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline=ch.pipeline();
                            //websocket基于http协议，使用http的编码和解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式写，添加ChunkedWriteHandler处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                              /*说明
                                1. http数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
                                2. 这就就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                                */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                               /*
                                说明
                                1. 对应websocket ，它的数据是以 帧(frame) 形式传递
                                2. 可以看到WebSocketFrame 下面有六个子类
                                3. 浏览器请求时 ws://localhost:7000/hello 表示请求的uri
                                4. WebSocketServerProtocolHandler 核心功能是将 http协议升级为 ws协议 , 保持长连接
                                5. 是通过一个 状态码 101
                              */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello2"));
                            //自定义的handler ，处理业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());
                        }
                    });  // 给我们的workerGroup 的 EventLoop 对应的管道设置处理器
            System.out.println("netty服务器启动");
            //监听启动
            ChannelFuture channelFuture=serverBootstrap.bind(PORT).sync();
            //监听关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args)
    {
        new MyServer2(7001).run();
    }
}
