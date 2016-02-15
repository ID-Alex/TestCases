package io.sunyi.cases.spring.framework.task.schedule;

public class SimpleTask extends Thread
{
	@Override
	public void run()
	{
		System.out.println("Simple task executing... -" + super.getId() );
	}
}
