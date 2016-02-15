package io.sunyi.cases.lock.condition;

public class SetFalse implements Runnable
{

	@Override
	public void run()
	{
		try
		{
			MainClass.lock.lock();

			while (!MainClass.value)
				//如果为 False 则一直等待.
				MainClass.F.await();
			
			MainClass.value = false;
			MainClass.T.signal();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			MainClass.lock.unlock();
		}
	}

}
