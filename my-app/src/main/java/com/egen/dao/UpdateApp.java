package com.egen.dao;

import java.net.UnknownHostException;
import java.util.Date;

import com.egen.constants.MyAppConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 * Java MongoDB update document
 * 
 * @author mkyong
 * 
 */

public class UpdateApp {

	public static void printAllDocuments(DBCollection collection) {
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}

	public static void removeAllDocuments(DBCollection collection) {
		collection.remove(new BasicDBObject());
	}

	public static void insertDummyDocuments(DBCollection collection) {
		BasicDBObject document = new BasicDBObject();
		document.put("_id", "123");
		document.put("hosting", "hostA");
		document.put("type", "vps");
		document.put("clients", 1000);

		BasicDBObject document2 = new BasicDBObject();
		document2.put("hosting", "hostB");
		document2.put("type", "dedicated server");
		document2.put("clients", 100);

		BasicDBObject document3 = new BasicDBObject();
		document3.put("hosting", "hostC");
		document3.put("type", "vps");
		document3.put("clients", 900);

		collection.insert(document);
		collection.insert(document2);
		collection.insert(document3);
	}

	public static void main(String[] args) {

	try {

	  Mongo mongo = new Mongo("localhost", 27017);
	  DB db = mongo.getDB("local");

	  // get a single collection
	  //DBCollection collection = db.getCollection("dummyColl");
	  //DAO.updateUser("5727858095e0146f1dad022d", "Ankur", "Bag", "sabrish2.iyer@gmail.com","44clearway", "Boston", "02115", "MA", "USA", 
		//		"Abc", "abc.com",dateFormat.format(new Date(System.currentTimeMillis())));
	  
	  
	  DBCollection collection = db.getCollection("dummyColl");

	  System.out.println("Testing 1...no $set");

	  insertDummyDocuments(collection);
	  System.out.println("Before");
	  printAllDocuments(collection);
	  // find hosting = hostB, and update the clients to 110
	  /*BasicDBObject newDocument = new BasicDBObject();
	  newDocument.put("clients", 110);*/

	  //BasicDBObject searchQuery = new BasicDBObject().append("_id", "57278fd795e0e849f8be2ef8");
	  /*BasicDBObject searchQuery = new BasicDBObject().append("hosting", "hostB");
	  collection.update(searchQuery, newDocument);*/
	  System.out.println("After");
	  //printAllDocuments(collection);
	  //removeAllDocuments(collection);

	  System.out.println("\nTesting 1...with $set");

	  //insertDummyDocuments(collection);

	  BasicDBObject updateDocument = new BasicDBObject();
	  updateDocument.append("$set", new BasicDBObject().append("clients", 110));

	  BasicDBObject searchQuery2 = new BasicDBObject().append("_id", "123");

	 // collection.update(searchQuery2, updateDocument);
	  //collection.save(new BasicDBObject("clients", 343));
	  //collection.save (new BasicDBObject("_id", "57278fd795e0e849f8be2ef8"),
      //        new BasicDBObject("$set", new BasicDBObject("clients", 343)));

	 // printAllDocuments(collection);
	  /*
	  removeAllDocuments(collection);

	  System.out.println("\nTesting 2... with $inc");
	  insertDummyDocuments(collection);
	  // find hosting = hostB and increase it's "clients" value by 99
	  BasicDBObject newDocument2 = new BasicDBObject().append("$inc",
		new BasicDBObject().append("clients", 99));

	  collection.update(new BasicDBObject().append("hosting", "hostB"), newDocument2);

	  printAllDocuments(collection);
	  removeAllDocuments(collection);

	  System.out.println("\nTesting 3... with $multi");

	  insertDummyDocuments(collection);
	  // find type = vps , update all matched documents , clients value to 888
	  BasicDBObject updateQuery = new BasicDBObject();
	  updateQuery.append("$set", new BasicDBObject().append("clients", "888"));

	  BasicDBObject searchQuery3 = new BasicDBObject();
	  searchQuery3.append("type", "vps");

	  collection.updateMulti(searchQuery3, updateQuery);
	  // collection.update(searchQuery3, updateQuery, false, true);

	  printAllDocuments(collection);
	  removeAllDocuments(collection);*/

	  System.out.println("Done");

	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (MongoException e) {
		e.printStackTrace();
	}

    }
}