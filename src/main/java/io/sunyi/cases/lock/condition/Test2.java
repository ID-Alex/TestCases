package io.sunyi.cases.lock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunyi
 */
public class Test2 {

	static ReentrantLock rel = new ReentrantLock();
	static Condition condition = rel.newCondition();


	public static void main(String args[]) throws InterruptedException {

		T1 t1 = new T1();
		t1.start();

		T2 t2 = new T2();
		t2.start();



	}



	static class T1 extends Thread {

		@Override
		public void run() {


			try {
				rel.lock();

				boolean await = condition.await(1000, TimeUnit.MILLISECONDS);
				System.out.println(await);

				System.out.println(rel.getHoldCount());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	static class T2 extends Thread {

		@Override
		public void run() {

			try {
				TimeUnit.MILLISECONDS.sleep(100);

				rel.lock();

				System.out.println("t2 lock");

				System.out.println(rel.getHoldCount());

				System.out.println(rel.getHoldCount());

				condition.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				rel.unlock();
			}


		}
	}

}