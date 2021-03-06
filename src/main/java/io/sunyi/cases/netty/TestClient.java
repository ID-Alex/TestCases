package io.sunyi.cases.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class TestClient
{
	public static void main(String[] args)
	{
		String host = "localhost";
		int port = 10100;

		ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory()
		{
			public ChannelPipeline getPipeline()
			{
				return Channels.pipeline(new TestClientHandler());
			}
		});

		bootstrap.setOption("tcpNoDelay", true);

		bootstrap.connect(new InetSocketAddress(host, port));
		
		
	}
}
