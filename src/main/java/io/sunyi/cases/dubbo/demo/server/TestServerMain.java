package io.sunyi.cases.dubbo.demo.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author sunyi
 */
public class TestServerMain {

	public static void main(String args[]) throws IOException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"io/sunyi/cases/dubbo/dubbo-service.xml"});
		context.start();

		System.in.read();
	}
}
