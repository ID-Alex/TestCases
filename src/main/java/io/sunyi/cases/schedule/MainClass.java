package io.sunyi.cases.schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainClass
{
	public static ApplicationContext appCtx = new ClassPathXmlApplicationContext(new String[]
	{ "io/sunyi/cases/spring/spring.xml" });
	public static ThreadPoolTaskScheduler test = (ThreadPoolTaskScheduler) appCtx.getBean("testScheduler");

	public static void main(String[] args) throws IOException
	{
		cron();
		periodic();
		simple();

		System.in.read();
	}

	public static void cron() throws IOException
	{

		Trigger trg = new CronTrigger("0/1 *  * * * ?");
		SimpleTask task = new SimpleTask();
		test.schedule(task, trg);

	}

	public static void periodic() throws IOException
	{

		Trigger trg = new PeriodicTrigger(1L, TimeUnit.SECONDS);
		SimpleTask task = new SimpleTask();
		test.schedule(task, trg);

	}

	public static void simple() throws IOException
	{

		SimpleTask task = new SimpleTask();
		test.schedule(task, new Date());

	}
}
