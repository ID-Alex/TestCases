package io.sunyi.cases.dubbo.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;

/**
 * @author sunyi
 *         Created on 15/10/17
 */
public class TestServer2 {

	public static void main(String args[]) throws IOException {

		ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:dubbo/server2.xml");

		TestService service = ac.getBean("testServiceImpl", TestService.class);

		System.out.println(TestServer2.class.getSimpleName() + " is started");
		System.in.read();

	}

}
