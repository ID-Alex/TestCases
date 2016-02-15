package io.sunyi.cases.thrift.test;

import io.sunyi.cases.thrift.test.service.SayService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ClientMainClass {
	public static void main(String[] args) throws TException {
		TTransport transport = new TSocket("localhost", 9090);
		transport.open();

		TProtocol protocol = new TBinaryProtocol(transport);
		SayService.Client client = new SayService.Client(protocol);

		System.out.println(client.sayHelloWorld());

		transport.close();
	}
}
