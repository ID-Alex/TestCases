package io.sunyi.cases.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.concurrent.TimeUnit;

/**
 * Created by sunyi on 15/9/22.
 */
public class EchoClient {

	public static ChannelFuture f;

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

							ch.pipeline().addLast("LengthFieldPrepender", new LengthFieldPrepender(4));
							ch.pipeline().addLast("LengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

							ch.pipeline().addLast("encode", new EchoEncode());
							ch.pipeline().addLast("decode", new EchoDecode());

							ch.pipeline().addLast(new EchoClientHandler());
						}
					});


			f = b.connect(host, port).sync();

			for (int i = 0; i < 100; i++) {
				ChannelFuture sync = f.channel().writeAndFlush("100").sync();
				sync.await(1, TimeUnit.SECONDS);
				if (!sync.isSuccess() && !f.channel().isActive()) {
					b.connect(host, port).sync();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

	private static class EchoClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			System.out.println(msg);
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			System.out.println("exceptionCaught");
			cause.printStackTrace();
			ctx.close();
		}
	}


}
