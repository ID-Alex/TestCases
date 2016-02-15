package io.sunyi.cases.lock.readwrite;

public class ReadThread extends Thread {

	@Override
	public void run() {

		try {
			MainClass.r.lock();

			System.out.println(MainClass.map.get("1"));
		} finally {
			MainClass.r.unlock();
		}

	}

}