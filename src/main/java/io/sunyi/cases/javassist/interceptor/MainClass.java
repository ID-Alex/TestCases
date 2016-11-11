package io.sunyi.cases.javassist.interceptor;

/**
 * @author sunyi
 */
public class MainClass {

	public static void main(String args[]) {
		SayService service = new SayService();
		service.say("123");
	}

}
