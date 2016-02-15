package io.sunyi.cases.lock.readwrite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class MainClass
{

	public static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	public static final ReadLock r = rwLock.readLock();
	public static final WriteLock w = rwLock.writeLock();
	
	public static final Map<String,String> map = new  ConcurrentHashMap<String, String>();
	static{
		map.put("1", "1");
	}
	
	

//	public static void main(String[] args) throws InterruptedException
//	{
//		ReadWriteThread t1 = new ReadWriteThread();
//		ReadWriteThread t2 = new ReadWriteThread();
//		
//		t1.start();
//		
//		t2.start();
//		
//		t1.join();
//		t2.join();
//	}
	
	public static void main(String[] args) {
		ReadThread rt = new ReadThread();
		rt.start();
		
		WriteThread wt = new WriteThread();
		wt.start();
		
		
		
	}
}
