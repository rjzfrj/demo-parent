package org.demo.netty.serial;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {

	
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup boosGroup=new NioEventLoopGroup();  // 1 第一个是用于处理服务器端接收客户端链接的
		EventLoopGroup workGroup=new NioEventLoopGroup();//2 第一个是进行网络通信的（网络读写的
		ServerBootstrap b=new ServerBootstrap();		//3 创建一个辅助类Bootstap ,就是对我的Server进行一系列的配置
		b.group(boosGroup,workGroup) //把两个工作线程加入进来
		.channel( NioServerSocketChannel.class) //要使用NioServerSocketChannel 这种类型的通道
		.option(ChannelOption.SO_BACKLOG, 1024)
		.option(ChannelOption.SO_SNDBUF, 1024*32)
		.option(ChannelOption.SO_RCVBUF, 1024*32)
		//一定要使用 childHandler 去绑定具体的 事件处理器
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//设置特殊分割符
				ByteBuf buf=Unpooled.copiedBuffer("$_".getBytes());
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				sc.pipeline().addLast(new StringDecoder()); //设置字符串形式的解码器
				sc.pipeline().addLast(new ServerHandler());   //以后需要做的就是这个ServerHandler的内容其他都是模板方式的
			}
		})
		.childOption(ChannelOption.SO_KEEPALIVE, true);
		//绑定指定的端口 进行监听
		ChannelFuture f=b.bind(8765).sync();
		//Thread.sleep(1000000);
		f.channel().closeFuture().sync();
		workGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
		
	}
}
