package org.demo.netty.serializable;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter {

	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channel active...");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			//得到服务器响应数据
			Resp resp = (Resp)msg;
			System.out.println("Client : " + resp);		
			
		} finally {
			//释放资源
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
	
}
