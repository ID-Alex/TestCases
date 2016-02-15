package io.sunyi.cases.lock.reentrant;

import java.util.concurrent.locks.ReentrantLock;


public class MainClass
{
	public static final ReentrantLock reentrantLock = new ReentrantLock();
	
	
	public static void main(String[] args)
	{
		ThreadClass t1 = new ThreadClass();
		ThreadClass t2 = new ThreadClass();
	
		new Thread(t1).start();
		new Thread(t2).start();
	}
}
