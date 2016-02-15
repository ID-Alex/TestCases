package io.sunyi.cases.netty;

import org.jboss.netty.channel.*;

import java.net.SocketAddress;
import java.nio.charset.Charset;

public class TestServerHandler extends SimpleChannelHandler
{
	
	public static Charset charset;
	
	static 
	{
		charset = Charset.forName("UTF-8");
	}
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception
	{
		System.out.println("messageReceived");

		MessagePojo msg = (MessagePojo) e.getMessage();
		System.out.println(msg.getMessage());
		
		ctx.getChannel().close();
		
	}

	/** Invoked when a {@link Channel} is open, but not bound nor connected. */
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelOpen");
		super.channelOpen(ctx, e);
	}

	/** Invoked when a {@link Channel} is open and bound to a local address, but not connected. */
	public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelBound");
		super.channelBound(ctx, e);
	}

	/** Invoked when a {@link Channel} is open, bound to a local address, and connected to a remote address. */
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	/** Invoked when a {@link Channel}'s {@link Channel#getInterestOps() interestOps} was changed. */
	public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelInterestChanged");
		super.channelInterestChanged(ctx, e);
	}

	/** Invoked when a {@link Channel} was disconnected from its remote peer. */
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	/** Invoked when a {@link Channel} was unbound from the current local address. */
	public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelUnbound");
		super.channelUnbound(ctx, e);
	}

	/** Invoked when a {@link Channel} was closed and all its related resources were released. */
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}

	/** Invoked when something was written into a {@link Channel}. */
	public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception
	{
		System.out.println("writeComplete");
		super.writeComplete(ctx, e);
	}

	/** Invoked when a child {@link Channel} was open. (e.g. a server channel accepted a connection) */
	public void childChannelOpen(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception
	{
		System.out.println("childChannelOpen");
		super.childChannelOpen(ctx, e);
	}

	/** Invoked when a child {@link Channel} was closed. (e.g. the accepted connection was closed) */
	public void childChannelClosed(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception
	{
		System.out.println("childChannelClosed");
		super.childChannelClosed(ctx, e);
	}

	/** Invoked when {@link Channel#write(Object)} is called. */
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception
	{
		System.out.println("writeRequested");
		super.writeRequested(ctx, e);
	}

	/** Invoked when {@link Channel#bind(SocketAddress)} was called. */
	public void bindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("bindRequested");
		super.bindRequested(ctx, e);		
	}

	/** Invoked when {@link Channel#connect(SocketAddress)} was called. */
	public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("connectRequested");
		super.connectRequested(ctx, e);		
	}

	/** Invoked when {@link Channel#setInterestOps(int)} was called. */
	public void setInterestOpsRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("setInterestOpsRequested");
		super.setInterestOpsRequested(ctx, e);
	}

	/** Invoked when {@link Channel#disconnect()} was called. */
	public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("disconnectRequested");
		super.disconnectRequested(ctx, e);
	}

	/** Invoked when {@link Channel#unbind()} was called. */
	public void unbindRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("unbindRequested");
		super.unbindRequested(ctx, e);
	}

	/** Invoked when {@link Channel#close()} was called. */
	public void closeRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
	{
		System.out.println("closeRequested");
		super.closeRequested(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
	{
		System.out.println("exceptionCaught");
		e.getCause().printStackTrace();

		Channel ch = e.getChannel();
		ch.close();
	}
}
