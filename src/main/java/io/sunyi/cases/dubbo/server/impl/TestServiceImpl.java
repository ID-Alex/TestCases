package io.sunyi.cases.dubbo.server.impl;

import java.util.concurrent.TimeUnit;

import io.sunyi.cases.dubbo.server.TestService;

public class TestServiceImpl implements TestService
{

	public String test(String message)
	{
		try {
			System.out.println("in test");
//			TimeUnit.SECONDS.sleep(10);
			System.out.println("out test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
