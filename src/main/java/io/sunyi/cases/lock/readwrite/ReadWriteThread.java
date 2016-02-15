package io.sunyi.cases.lock.readwrite;

public class ReadWriteThread extends Thread
{
	@Override
	public void run()
	{
		try
		{
			MainClass.r.lock();
			MainClass.r.unlock();
			MainClass.w.lock();
			MainClass.r.lock();

		} finally
		{
			MainClass.r.unlock();
			MainClass.w.unlock();
		}
	}
}
