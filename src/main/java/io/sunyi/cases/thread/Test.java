package io.sunyi.cases.thread;

import java.io.IOException;
import java.util.concurrent.*;

public class Test {

	private static ThreadPoolExecutor tpe = null;

	public static void main(String[] args) throws IOException {

		tpe = new ThreadPoolExecutor(1,2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1), new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r,"");
			}
		}

		, new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				if (r instanceof OrderSuccHandlerTask) {
					OrderSuccHandlerTask handler = (OrderSuccHandlerTask) r;
					System.out.println("异步处理成功的订单, 提交任务失败, OrderId: " + handler.getOrderId());
				}
			}
		});

		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));
		tpe.execute(new OrderSuccHandlerTask("1"));

		System.in.read();

	}

	private static class OrderSuccHandlerTask extends Thread {
		private String orderId;

		public OrderSuccHandlerTask(String orderId) {
			super(OrderSuccHandlerTask.class.getSimpleName() + "-" + orderId);
			this.orderId = orderId;
		}

		public String getOrderId() {
			return orderId;
		}

		@Override
		public void run() {
			System.out.println(orderId);
		}

	}

}
