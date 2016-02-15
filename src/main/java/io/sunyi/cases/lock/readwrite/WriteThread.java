package io.sunyi.cases.lock.readwrite;

public class WriteThread extends Thread {

	@Override
	public void run() {
		try {
			MainClass.w.lock();
		
			MainClass.map.clear();
			
		} finally{
			MainClass.w.unlock();
		}
	}
	
}