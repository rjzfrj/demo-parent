package com.zf.nio.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyHeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

       IdleStateEvent event= (IdleStateEvent) evt;
       String str="";
       if(event instanceof IdleStateEvent){
           switch (event.state()){
               case READER_IDLE:
                   str="读空闲";
                   break;
               case WRITER_IDLE:
                  str="写空闲";
                  break;
               case ALL_IDLE:
                   str="读写空闲";
                   break;
           }
           System.out.println(ctx.channel().remoteAddress()+"发生"+str);
       }

    }
}
