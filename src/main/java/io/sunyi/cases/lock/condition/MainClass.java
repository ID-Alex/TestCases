package io.sunyi.cases.lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainClass
{
	public static Lock lock = new ReentrantLock();

	public static volatile boolean value = false;

	// true
	public static Condition T = lock.newCondition();
	// false
	public static Condition F = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException
	{
		SetTrue t = new SetTrue();
		SetFalse f = new SetFalse();
		
		Thread tt = new Thread(t);
		tt.start();
		Thread ft = new Thread(f);
		ft.start();
		
		
		tt.join();
		ft.join();
	}
}
