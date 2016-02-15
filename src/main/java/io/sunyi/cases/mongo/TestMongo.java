package io.sunyi.cases.mongo;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

public class TestMongo
{
	public static void main(String[] args) throws UnknownHostException
	{

		// MongoClientURI mcUri = new MongoClientURI("mongodb://dba:dba@127.0.0.1:27017/mydb");
		MongoClient mc = new MongoClient("localhost", 27017);

		List<String> databaseNames = mc.getDatabaseNames();
		for (String n : databaseNames)
		{
			System.out.print("-");
			System.out.println(n);
			DB db = mc.getDB(n);
			Set<String> colls = db.getCollectionNames();

			for (String s : colls)
			{
				System.out.print("\t-");
				System.out.println(s);

				DBCollection collection = db.getCollection(s);
				DBCursor dbc = collection.find();
				while (dbc.hasNext())
				{
					DBObject dbo = dbc.next();
					System.out.print("\t\t-");
					System.out.println(dbo);
				}
			}
		}
		mc.close();
	}
}
