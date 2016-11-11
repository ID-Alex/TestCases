package io.sunyi.cases.thrift.test.service.impl;

import io.sunyi.cases.thrift.test.service.SayService;
import org.apache.thrift.TException;

public class SayServiceImpl implements SayService.Iface {

	@Override
	public String say(String para) throws TException {
		return "SayService say: " + para;
	}

	@Override
	public String sayHelloWorld() throws TException {
		return "Hello World";
	}

}
