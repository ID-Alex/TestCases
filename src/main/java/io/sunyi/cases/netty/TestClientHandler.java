package io.sunyi.cases.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import java.nio.charset.Charset;

public class TestClientHandler extends SimpleChannelHandler
{


	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		String message = "你好啊啊啊啊啊";
		ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
		buffer.writeBytes(message.getBytes(Charset.forName("UTF-8")));
		ctx.getChannel().write(buffer);
		

		ctx.getChannel().disconnect();
		ctx.getChannel().close();
	}

	@Override
	public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("disconnectRequested");
	}
	
	
	@Override
	public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("closeRequested");
		System.exit(0);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
	{
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}