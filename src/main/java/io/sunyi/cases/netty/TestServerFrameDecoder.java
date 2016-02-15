package io.sunyi.cases.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class TestServerFrameDecoder extends FrameDecoder
{

	public TestServerFrameDecoder()
	{
		System.out.println("new TestServerFrameDecoder");
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception
	{
		System.out.println("decode");

		if (buffer.readableBytes() < 10)
			return null;

		ChannelBuffer readBytes = buffer.readBytes(buffer.readableBytes());
		
		MessagePojo msg = new MessagePojo();
		msg.setMessage(readBytes.toString(TestServerHandler.charset));

		return msg;
	}
}
