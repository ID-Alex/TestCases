package io.sunyi.cases.compare;

import com.alibaba.dubbo.common.json.JSON;
import com.google.gson.Gson;

import java.io.IOException;

public class CompareTest {

	static int count = 1;

	public static void main(String[] args) throws IOException {
		long now = 0L, sub;
		Model m = new Model();
		Gson gson = new Gson();
		
		
		// fastjson
		now = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			JSON.json(m);
		}
		sub = System.currentTimeMillis() - now;
		System.out.println(sub);
		
		//gson
		now = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			gson.toJson(m);
		}
		sub = System.currentTimeMillis() - now;
		System.out.println(sub);

	}
}
