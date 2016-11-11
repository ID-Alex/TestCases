package io.sunyi.cases.hashmap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class HashMapTest {

	private static Logger logger = LoggerFactory.getLogger(HashMapTest.class);

	List list;

	@RequestMapping("/mobile/api/...")


	public void doSomething(String param1, String param2) {

		try {

			// do something

		} catch (Exception e) {
			logger.error("XX业务，参数1:{},参数2:{}...", new Object[]{param1, param2}, e);
		}

	}


}
