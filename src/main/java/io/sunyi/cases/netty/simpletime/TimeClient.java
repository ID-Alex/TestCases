package io.sunyi.cases.netty.simpletime;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;

/**
 * Created by sunyi on 15/9/22.
 */
public class TimeClient {

	public static void main(String args[]) {
		connect("localhost", 10001);
	}

	public static void connect(String host, int port) {

		EventLoopGroup group = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(group)
					.channel(NioSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new TimeClientHandler());
						}

					});

			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

	private static class TimeClientHandler extends ChannelInboundHandlerAdapter {
		private ByteBuf firstMessage;

		public TimeClientHandler() throws UnsupportedEncodingException {
			byte[] bytes = "QUERY TIME ORDER".getBytes("UTF-8");
			firstMessage = Unpooled.buffer(bytes.length);
			firstMessage.writeBytes(bytes);
		}

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(firstMessage);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf res = (ByteBuf) msg;
			byte[] bytes = new byte[res.readableBytes()];
			res.readBytes(bytes);

			String resBody = new String(bytes, "UTF-8");
			System.out.println(resBody);

		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			cause.printStackTrace();
			ctx.close();
		}
	}


}
