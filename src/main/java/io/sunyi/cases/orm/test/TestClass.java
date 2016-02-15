package io.sunyi.cases.orm.test;

import io.sunyi.cases.orm.dao.HbmDao;
import io.sunyi.cases.orm.entity.Group;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:io/sunyi/cases/orm/applicationContext.xml")
public class TestClass
{

	@Autowired
	@Qualifier("hbmDao")
	public HbmDao dao;

	@Test
	@Rollback(value = false)
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void test()
	{
		Session session = dao.getSessionFactory().openSession();

		@SuppressWarnings("unused")
		Group g1 = (Group) session.get(Group.class, 1);
		
		@SuppressWarnings("unused")
		Group g2 = (Group) session.get(Group.class, 2);
		session.close();
		
		session = dao.getSessionFactory().openSession();
		
		
		g2 = (Group) session.get(Group.class, 2);
		
		// <cache name="org.alex.cases.orm.entity.Group" maxElementsInMemory="1" ></cache>
		// 会再次查询.
		g1 = (Group) session.get(Group.class, 1);


	}
}
