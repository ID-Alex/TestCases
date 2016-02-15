package io.sunyi.cases.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunyi
 *         Created on 15/10/12
 */
public class LockTest {

	public static final ReentrantLock lock = new ReentrantLock();
	public static final Condition condition = lock.newCondition();

	public static void main(String args[]) throws InterruptedException {

		B b = new B();
		A a = new A();

		b.start();

		TimeUnit.SECONDS.sleep(1);

		a.start();
	}


	static class A extends Thread {

		@Override
		public void run() {
			try {
				lock.lock();

				condition.signalAll();

				System.out.println("A");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	static class B extends Thread {

		@Override
		public void run() {
			try {
				lock.lock();

				condition.await();


				System.out.println("B");


			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
