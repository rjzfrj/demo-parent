package com.potter;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaServerHanlder extends IoHandlerAdapter {
	private int count = 0;

	public void sessionCreated(IoSession session) {
		System.out.println("新客户端连接");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		count++;
		System.out.println("第 " + count + " 个 client 登陆！address： : "
				+ session.getRemoteAddress());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		session.close();
		System.out.println("I'm Client &&  I closed!");
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		PlayerAccount_Entity ho = (PlayerAccount_Entity) message;
		System.out.println("Client message="+ho.getId()+ho.getName()+ho.getEmailAdress()+ho.getSex()); 
        
//     	session.write(ho);
	}
}
