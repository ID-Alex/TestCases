package io.sunyi.cases.thread;

import java.util.concurrent.*;

public class AsyncResult {
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		
		Future<Boolean> result1 = service.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				TimeUnit.SECONDS.sleep(3);
				return true;
			}
		});
		
		Future<Boolean> result2 = service.submit(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				TimeUnit.SECONDS.sleep(3);
				return true;
			}
		});
		
//		Boolean Boolean;
//		try {
//			Boolean = result.get(1, TimeUnit.SECONDS);
//		} catch (TimeoutException e) {
//			Boolean = false;
//		}
//		System.out.println(Boolean);
//		
//		TimeUnit.SECONDS.sleep(3);
//		Boolean = result.get();
//		System.out.println(Boolean);
		
	}
	
}
