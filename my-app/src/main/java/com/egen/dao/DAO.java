/**
 * 
 */
package com.egen.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.egen.beans.User;
import com.egen.constants.MyAppConstants;
import com.egen.helper.Validator;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;

/**
 * It is DAO class which gives CRUD functionalities with MongoDB
 * 
 * @author Ankur
 * @version 0.1
 */
public class DAO {

	/**
	 * This method initializes the connection to MongoDB
	 * 
	 * @return DB
	 */
	public static DB initConnection() {

		MongoClient mongoClient = null;
		DB db = null;
		try {

			// To connect to mongodb server
			mongoClient = new MongoClient(MyAppConstants.HOST_DB_URL, MyAppConstants.HOST_DB_PORT);

			// Now connect to your databases
			db = mongoClient.getDB(MyAppConstants.DB_NAME);
			System.out.println("Connect to database successfully");

		} catch (UnknownHostException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} catch (MongoException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return db;
	}

	/**
	 * Creates User Record to the DB.
	 * 
	 * @return
	 */
	public static String createUser(Map<String, Object> documentMap) {

		String msg = "";
		DB db = initConnection();
		DBCollection collection = db.getCollection(MyAppConstants.COLLECTION_NAME);
		WriteResult result = collection.insert(new BasicDBObject(documentMap));
		if (result.getN() > 0) {
			msg = MyAppConstants.SUCCESS;
		} else {
			msg = MyAppConstants.FAILURE;
		}
		DBCursor cursorDocMap = collection.find();
		while (cursorDocMap.hasNext()) {
			System.out.println(cursorDocMap.next());
		}
		return msg;
	}

	/**
	 * Fetches all Users present in the database.
	 */
	public static ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		String msg = "";
		DB db = initConnection();
		DBCollection collection = db.getCollection(MyAppConstants.COLLECTION_NAME);

		DBCursor cursorDocMap = collection.find();
		while (cursorDocMap.hasNext()) {
			Map<String, Object> rs = cursorDocMap.next().toMap();
			User user = new User();
			System.out.println(rs.get("id"));
			user.setId(Long.parseLong(rs.get(MyAppConstants.FIELD_USER_ID).toString()));
			user.setFname(rs.get(MyAppConstants.FIELD_FIRST_NAME).toString());
			user.setLname(rs.get(MyAppConstants.FIELD_LAST_NAME).toString());
			user.setEmailid(rs.get(MyAppConstants.FIELD_EMAIL_ID).toString());
			Map<String, Object> addressMap = (Map<String, Object>) rs.get(MyAppConstants.FIELD_ADDR);
			user.setStreet(addressMap.get(MyAppConstants.FIELD_STREET).toString());
			user.setCity(addressMap.get(MyAppConstants.FIELD_CITY).toString());
			user.setZip(addressMap.get(MyAppConstants.FIELD_ZIP).toString());
			user.setState(addressMap.get(MyAppConstants.FIELD_STATE).toString());
			user.setCountry(addressMap.get(MyAppConstants.FIELD_COUNTRY).toString());
			Map<String, Object> companyMap = (Map<String, Object>) rs.get(MyAppConstants.FIELD_COMPANY);
			user.setCompanyName(companyMap.get(MyAppConstants.FIELD_COMPANY_NAME).toString());
			user.setCompanyUrl(companyMap.get(MyAppConstants.FIELD_COMPANY_WEBSITE).toString());
			user.setDateCreated(rs.get(MyAppConstants.FIELD_DT_CREATED).toString());
			users.add(user);
		}
		return users;
	}

	/**
	 * Fetches all Users present in the database based on the parameters
	 * 
	 * @param fieldName
	 * @param value
	 * @return ArrayList<User>
	 */
	public static ArrayList<User> getAllUsers(String fieldName, String value) {
		ArrayList<User> users = new ArrayList<>();
		String msg = "";
		DB db = initConnection();
		DBCollection collection = db.getCollection(MyAppConstants.COLLECTION_NAME);
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(fieldName, value);
		DBCursor cursorDocMap = collection.find(whereQuery);
		while (cursorDocMap.hasNext()) {
			Map<String, Object> rs = cursorDocMap.next().toMap();
			User user = new User();
			System.out.println(rs.get("id"));
			user.setId(Long.parseLong(rs.get("id").toString()));
			user.setFname(rs.get("fname").toString());
			user.setLname(rs.get("lname").toString());
			user.setEmailid(rs.get("emailid").toString());
			users.add(user);
		}
		return users;
	}

	/**
	 * Updates the user in the database.
	 * 
	 * @return
	 */
	public static String updateUser(String userId, String fname, String lname, String emailid, String street,
			String zip, String city, String state, String country, String companyName, String companyUrl,
			String dateCreated) {
		String msg = "";
		DB db = initConnection();
		DBCollection collection = db.getCollection(MyAppConstants.COLLECTION_NAME);
		Validator validator = new Validator();
		System.out.println(fname + " " + lname + emailid);
		// Update to Db
		if (userId != null && emailid != null && fname != null && lname != null) {
			if (userId.trim().length() == 0 || emailid.trim().length() == 0 || fname.trim().length() == 0
					|| lname.trim().length() == 0) {
				return MyAppConstants.FAILURE;
			} else {
				if (!validator.validateEmail(emailid) || !validator.validateOnlyAlphabets(fname)
						|| !validator.validateOnlyAlphabets(lname)) {
					return MyAppConstants.FAILURE;
				}
			}
		} else {
			return MyAppConstants.FAILURE;
		}

		BasicDBObject userRecord = new BasicDBObject();
		User user = new User(fname, lname, emailid, street, city, zip, state, country, companyName, companyUrl,
				dateCreated);

		userRecord.put("id", userId);
		userRecord.put("firstName", user.getFname());
		userRecord.put("lastName", user.getLname());
		userRecord.put("email", user.getEmailid());
		// Add Address
		Map<String, Object> addressMap = new HashMap<String, Object>();
		addressMap.put("street", user.getStreet());
		addressMap.put("city", user.getCity());
		addressMap.put("zip", user.getZip());
		addressMap.put("state", user.getState());
		addressMap.put("country", user.getCountry());

		userRecord.put("address", addressMap);

		// Add Company
		Map<String, Object> companyMap = new HashMap<String, Object>();
		companyMap.put("name", user.getCompanyName());
		companyMap.put("website", user.getCompanyUrl());
		userRecord.put("company", companyMap);
		userRecord.put("dateCreated", user.getDateCreated());
		WriteResult result = collection.update(new BasicDBObject().append("id", Long.parseLong(userId)), userRecord);
		System.out.println("result " + result.getN());
		if (result.getN() > 0) {
			msg = MyAppConstants.SUCCESS;
		} else {
			msg = MyAppConstants.FAILURE;
		}
		return msg;
	}

}
