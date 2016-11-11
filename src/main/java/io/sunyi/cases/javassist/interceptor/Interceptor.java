package io.sunyi.cases.javassist.interceptor;

/**
 * @author sunyi
 */
public interface Interceptor {

	public void before(Object target, Object[] args);

	public void after(Object target, Object[] args, Object result, Throwable exception);
}
