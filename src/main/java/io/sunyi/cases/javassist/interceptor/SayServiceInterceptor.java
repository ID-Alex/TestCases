package io.sunyi.cases.javassist.interceptor;

/**
 * @author sunyi
 */
public class SayServiceInterceptor implements  Interceptor {



	@Override
	public void before(Object target, Object[] args) {
		System.out.println("in SayServiceInterceptor before ");
	}

	@Override
	public void after(Object target, Object[] args, Object result, Throwable exception) {
		System.out.println("in SayServiceInterceptor after ");
	}
}
