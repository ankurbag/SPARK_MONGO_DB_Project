package com.egen.testcases;

/**
 * Test Case Executor
 * @author Ankur
 *
 */
import org.junit.Test;

import com.egen.beans.User;
import com.egen.constants.MyAppConstants;
import com.egen.controllers.MessageUtil;
import com.egen.dao.DAO;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestJunit {
	
   
	// Trying to create a new user and check for the JUnit
	User user = new User();
	
	String message = "User Created Successfully";	
   MessageUtil messageUtil = new MessageUtil(message);

   @Test
   public void createNewUser() {
	   
	   Map<String, Object> userRecord = new HashMap<String, Object>();
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   User user = new 
       		User("Sabrish2", "Iyer2", "sabrish2.iyer@gmail.com",
       				"44clearway", "Boston", "02115", "MA", "USA", 
       				"Abc", "abc.com",dateFormat.format(new Date(System.currentTimeMillis())));
	   String id = ""+System.currentTimeMillis();
       userRecord.put("_id", id);
       userRecord.put("firstName", user.getFname());
       userRecord.put("lastName", user.getLname());
       userRecord.put("email", user.getEmailid());
       userRecord.put("email", user.getEmailid());
       userRecord.put("email", user.getEmailid());
       //Add Address
       Map<String, Object> addressMap = new HashMap<String, Object>();
       addressMap.put("street",user.getStreet());
       addressMap.put("city", user.getCity());
       addressMap.put("zip", user.getZip());
       addressMap.put("state", user.getState());
       addressMap.put("country", user.getCountry());
   	
       userRecord.put("address", addressMap);
       
       //Add Company
       Map<String, Object> companyMap = new HashMap<String, Object>();
       companyMap.put("name",user.getCompanyName());
       companyMap.put("website", user.getCompanyUrl());
       userRecord.put("company", companyMap);
       userRecord.put("dateCreated",user.getDateCreated());
      String msg =  DAO.createUser(userRecord);
      System.out.println("Test Case - "+msg);
      assertEquals(MyAppConstants.FAILURE ,msg );
      
   }
   
   
   @Test
   public void updateUser() {
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   String msg = DAO.updateUser("1462218285664", "Ankur", "Bag", "sabrish2.iyer@gmail.com","44clearway", "Boston", "02115", "MA", "US", 
  				"Abc", "abc.com",dateFormat.format(new Date(System.currentTimeMillis())));
	   System.out.println("Test Case - "+msg);
	   assertEquals(MyAppConstants.SUCCESS ,msg );
   }
   
   @Test
   public void getAllUsers() {
	   int listsize = DAO.getAllUsers().size();
	   assertEquals(2 ,listsize );
   }
}