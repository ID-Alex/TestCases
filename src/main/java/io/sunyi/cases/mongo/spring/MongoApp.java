package io.sunyi.cases.mongo.spring;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

public class MongoApp {

	public static void main(String[] args) throws Exception {

		MongoClient mc = new MongoClient("localhost", 27017);
		MongoTemplate t = new MongoTemplate(mc, "test");
		
		DBObject o = new BasicDBObject();

		Criteria c = new Criteria("name");
		c.is("Joe");
		DBObject dbo = new BasicDBList();
		

	}
}