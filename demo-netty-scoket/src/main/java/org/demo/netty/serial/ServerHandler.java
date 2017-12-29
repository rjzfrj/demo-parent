package org.demo.netty.serial;

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
			String request = (String)msg;
			System.out.println("Service: " + request);
			String response="服务器得到数据"+request+"并响应OK$_";
			//写给客户端
			ChannelFuture cf= ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
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
