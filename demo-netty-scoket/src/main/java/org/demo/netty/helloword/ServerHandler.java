package org.demo.netty.helloword;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			//do something msg
			ByteBuf buf = (ByteBuf)msg;
			byte[] data = new byte[buf.readableBytes()];
			buf.readBytes(data);
			String request = new String(data, "utf-8");
			System.out.println("Client: " + request);
			//写给客户端
			ChannelFuture cf= ctx.writeAndFlush(Unpooled.copiedBuffer("服务器得到数据并响应OK".getBytes()));
			cf.addListener(ChannelFutureListener.CLOSE);  //加上这句话就是短链接 当客户端请求服务端响应完了后断开链接  如果不加这句话客户端和服务器端是一直长连接的
			
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
