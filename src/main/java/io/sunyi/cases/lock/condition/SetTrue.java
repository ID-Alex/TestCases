package io.sunyi.cases.lock.condition;

public class SetTrue implements Runnable
{

	@Override
	public void run()
	{
		try
		{
			MainClass.lock.lock();

			while (MainClass.value)
				// 如果为 True 则一直等待.
				MainClass.T.await();

			MainClass.value = true;
			MainClass.F.signal();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			MainClass.lock.unlock();
		}
	}

}
