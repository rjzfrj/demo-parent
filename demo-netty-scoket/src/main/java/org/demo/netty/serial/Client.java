package org.demo.netty.serial;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 解决粘包方案1 使用特殊的分隔符来解决粘包问题
 *
 */
public class Client {

	public static void main(String[] args) throws Exception {

		EventLoopGroup workgroup = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(workgroup)
		.channel(NioSocketChannel.class)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				ByteBuf buf=Unpooled.copiedBuffer("$_".getBytes());
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				sc.pipeline().addLast(new StringDecoder()); //设置字符串形式的解码器
				sc.pipeline().addLast(new ClientHandler());
			}
		});

		ChannelFuture cf1 = b.connect("127.0.0.1", 8765).sync();

		// 以特色的分隔符来解决粘包问题
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("test hello$_".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("hello word$_".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("what do you do$_".getBytes()));

		cf1.channel().closeFuture().sync();
		workgroup.shutdownGracefully();

	}
}
