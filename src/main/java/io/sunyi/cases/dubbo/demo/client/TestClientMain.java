package io.sunyi.cases.dubbo.demo.client;

import io.sunyi.cases.dubbo.demo.server.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClientMain {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"io/sunyi/cases/dubbo/dubbo-client.xml"});
		context.start();

		TestService demoService = (TestService) context.getBean("testService"); // 获取远程服务代理


		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));
		System.out.println(demoService.test("world"));

//		// 当前应用配置
//		ApplicationConfig application = new ApplicationConfig();
//		application.setName("xxx");
//
//		// 连接注册中心配置
//		RegistryConfig registry = new RegistryConfig();
//		registry.setProtocol("zookeeper");
//		registry.setAddress("192.168.1.120:2181");
//		registry.setCheck(false);
//
//		// 服务提供者协议配置
//		ProtocolConfig protocol = new ProtocolConfig();
//		protocol.setName("dubbo");
//		protocol.setPort(10001);
//		protocol.setThreads(200);
//
//		ReferenceConfig<TestService> ref = new ReferenceConfig<TestService>();
//		ref.setApplication(application);
//		ref.setRegistry(registry); // 多个注册中心可以用setRegistries()
//		ref.setInterface(TestService.class);
//		ref.setVersion("1.0.0");
//		ref.setRetries(0);
//		ref.setTimeout(3600000);
//
//		TestService testService = ref.get();
//		String test = testService.test("aaa");
//		System.out.println(test);
//
//		test = testService.test("aaa");
	}
}
