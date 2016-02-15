package io.sunyi.cases.lock.reentrant;

public class ThreadClass implements Runnable
{
	@Override
	public void run()
	{
		try{
			System.out.println("lock");
			MainClass.reentrantLock.lock();
			System.out.println("do something");
			MainClass.reentrantLock.lock();
		}finally
		{
			MainClass.reentrantLock.unlock();
			MainClass.reentrantLock.unlock();
		}
	}
}
