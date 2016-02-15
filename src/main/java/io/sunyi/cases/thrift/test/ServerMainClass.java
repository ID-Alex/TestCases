package io.sunyi.cases.thrift.test;

import io.sunyi.cases.thrift.test.service.SayService;
import io.sunyi.cases.thrift.test.service.impl.SayServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class ServerMainClass {
	public static void main(String[] args) throws TTransportException {

		SayServiceImpl serviceImpl = new SayServiceImpl();

		SayService.Processor<SayService.Iface> processor = new SayService.Processor<SayService.Iface>(serviceImpl);

//		InetSocketAddress inetSocketAddress = new InetSocketAddress("qa.backup.alipay.net", 9090);
		TServerTransport serverTransport = new TServerSocket(9090);
		TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

		System.out.println("Starting server ...");

		server.serve();

	}
}
