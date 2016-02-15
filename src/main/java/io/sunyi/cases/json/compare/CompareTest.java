package io.sunyi.cases.json.compare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.json.JSON;
import com.google.gson.Gson;

public class CompareTest {

	static int count = 1;

	public static void main(String[] args) throws IOException {
		long now = 0L, sub;
		Model m = new Model();
		Gson gson = new Gson();
		
		
		// fastjson
		now = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			com.alibaba.dubbo.common.json.JSON.json(m);
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
