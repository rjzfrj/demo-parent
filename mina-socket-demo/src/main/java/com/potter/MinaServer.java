package com.potter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	private static final int bindPort=8899;
	public static void main(String[] args){
		SocketAcceptor acceptor = new NioSocketAcceptor();
		
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		
		ProtocolCodecFilter filter= new ProtocolCodecFilter(new HCoderFactory(Charset.forName("UTF-8"))); 
		//添加编码过滤器 处理乱码问题、编码问题
		chain.addLast("objectFilter",filter);
		//设置核心消息业务处理器
		acceptor.setHandler(new MinaServerHanlder());
		
		try {
			// 绑定端口
			acceptor.bind(new InetSocketAddress(bindPort));
		} catch (IOException e) {
			 System.out.println("Mina Server start for error!"+bindPort);
			e.printStackTrace();
		}
		
		System.out.println("Mina Server run done! on port:"+bindPort);
	}
}
