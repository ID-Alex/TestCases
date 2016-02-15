package io.sunyi.cases.random;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomTest {
	
	public static Random r = new Random();
	
	public static void main(String[] args) {

		Set<String> s = new HashSet<String>(100000);

		for (int i = 0; i < 100000; i++) {

			String time = DateFormatUtils.format(new Date(), "yyMMddHHmmssSSS");
			String random3 = getRandom3();
//			String random2 = getRandom2();
			s.add(time + random3 );
		}
		
		System.out.println(s.size());

	}

	private static String getRandom3() {
		StringBuilder ret = new StringBuilder("");
		String randomNum = String.valueOf(r.nextInt(99999));
		for (int i = randomNum.length(); i < 5; i++) {
			ret.append("0");
		}
		ret.append(randomNum);
		return ret.toString();
	}
	
	private static String getRandom2() {
		StringBuilder ret = new StringBuilder("");
		String randomNum = String.valueOf(r.nextInt(64));
		for (int i = randomNum.length(); i < 2; i++) {
			ret.append("0");
		}
		ret.append(randomNum);
		return ret.toString();
	}
}
