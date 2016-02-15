package io.sunyi.cases.javassist;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestJavassist
{
	public static void main(String[] args) throws NotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, CannotCompileException,
			IOException, NoSuchMethodException, SecurityException
	{
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("org.alex.cases.javassist.TestClass");
		CtMethod setNameMethod = CtMethod.make("public void setName(String name){this.name = $1;}", cc);
		cc.addMethod(setNameMethod);

		cc.writeFile();

		@SuppressWarnings("unchecked")
		Class<TestClass> c = cc.toClass();
		TestClass instance = (TestClass) c.newInstance();

		Method method = c.getMethod("setName", String.class);
		method.invoke(instance, "a");

		System.out.println(instance.getName());
	}
}
