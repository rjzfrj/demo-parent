package com.potter;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaClientHanlder extends IoHandlerAdapter{
	
	public void sessionOpened(IoSession session) throws Exception {
		 PlayerAccount_Entity ho = new PlayerAccount_Entity(10,"Potter","potter@qq.com",0);
	     session.write(ho);
    }
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		PlayerAccount_Entity ho = (PlayerAccount_Entity) message;        
		System.out.println("Server Say:name:" + ho.getName());
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		session.close();
	}
}
