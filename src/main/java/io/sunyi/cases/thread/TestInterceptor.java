package io.sunyi.cases.thread;

public class TestInterceptor {

	public static void main(String[] args) {
		TestThread t = new TestThread();
		t.start();
		t.interrupt();
		
	}

	public static class TestThread extends Thread {
		@Override
		public void run() {
			
			System.out.println(1);
			System.out.println(2);
		}
	}

}
