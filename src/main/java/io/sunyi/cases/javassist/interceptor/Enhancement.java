package io.sunyi.cases.javassist.interceptor;

import io.sunyi.cases.javassist.JavaAssistUtils;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author sunyi
 */
public class Enhancement {

	public Class doEnhancement(String target, String methodName, String[] argsType, String interceptor) throws NotFoundException {

		ClassPool cp = ClassPool.getDefault();
		CtClass targetCtClass = cp.get(target);


		CtMethod ctMethod = getCtMethod(targetCtClass, methodName, argsType);

		StringBuilder builder = new StringBuilder("{");

		// try {
		//     _$PINPOINT$_holder13 = InterceptorRegistry.findInterceptor(13);
		//     (($INTERCEPTOR_TYPE)_$PINPOINT$_holder13.getInterceptor.before($ARGUMENTS);
		// } catch (Throwable t) {
		//     InterceptorInvokerHelper.handleException(t);
		// }

		builder.append("try { ");


		builder.append("} catch (java.lang.Throwable e) { e.printStackTrace(); }");
		builder.append("}");

		return null;


	}

	private static CtMethod getCtMethod(CtClass ctClass, String methodName, String[] parameterTypes) {
		final String jvmSignature = JavaAssistUtils.javaTypeToJvmSignature(parameterTypes);

		for (CtMethod method : ctClass.getDeclaredMethods()) {
			if (!method.getName().equals(methodName)) {
				continue;
			}
			final String descriptor = method.getMethodInfo2().getDescriptor();
			if (descriptor.startsWith(jvmSignature)) {
				return method;
			}
		}

		return null;
	}

	public static void main(String args[]) throws NotFoundException {
		Enhancement e = new Enhancement();
		e.doEnhancement("io.sunyi.cases.javassist.interceptor.SayService", null, null, null);



	}

}
