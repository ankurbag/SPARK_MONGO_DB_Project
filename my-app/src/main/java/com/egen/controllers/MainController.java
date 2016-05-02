package com.egen.controllers;

/**
 * Controller class which maps the model to view
 * @author Ankur
 * 
 */
import static spark.Spark.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.egen.beans.User;
import com.egen.constants.MyAppConstants;
import com.egen.dao.DAO;

import spark.ModelAndView;
import spark.Spark;
public class MainController {

	/**
     * Map holding the users
     */
    private static Map<String, Object> userRecord = new HashMap<String, Object>();
    public static void main(String[] args) {
		 final Random random = new Random();
		staticFileLocation("public"); //index.html is served at localhost:4567 (default port)
        //Creates a new User resource, will return the ID to the created resource
        post("/createUser", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
     	   //get current date time with Date()
     	    Date date = new Date();
     	    System.out.println(dateFormat.format(date));
            String fname = request.queryParams("fname");
            String lname = request.queryParams("lname");
            String emailid = request.queryParams("email");
            String street = request.queryParams("street");
            String city = request.queryParams("city");
            String zip = request.queryParams("zip");
            String state = request.queryParams("state");
            String country = request.queryParams("country");
            String companyName = request.queryParams("company_name");
            String companyUrl = request.queryParams("company_url");
            String profilePic = MyAppConstants.PROFILE_URL;
            
            User user = new 
            		User(fname, lname, emailid, street, city, zip, state, country, companyName, companyUrl,dateFormat.format(date));
            
            String id = ""+System.currentTimeMillis();
            userRecord.put("_id", id);
            userRecord.put("firstName", user.getFname());
            userRecord.put("lastName", user.getLname());
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
            userRecord.put("profilePic",profilePic);
            String msg = DAO.createUser(userRecord);
            response.status(201); // 201 Created
            model.put("msg", msg);
            //response.redirect("/index.jsp?id="+id);
            // The vm files are located under the resources directory
            return new ModelAndView(model, "home.vm");
        }, new VelocityTemplateEngine());
        
        //Searches Users
        get("/getAllUsers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<User> users = new ArrayList<>();
            String fname = request.queryParams("fname");
            String lname = request.queryParams("lname");
            String emailid = request.queryParams("email");
            System.out.println("fname :"+fname);
            
            //Retrieves all users
            if(fname.trim().length()==0 && lname.trim().length()==0 && emailid.trim().length()==0){
            	users = DAO.getAllUsers();
            }else{
            	if(fname.trim().length() > 0)  
            		users =DAO.getAllUsers(MyAppConstants.FIELD_FIRST_NAME,fname);
            	if(lname.trim().length() > 0) 
            		users =DAO.getAllUsers(MyAppConstants.FIELD_LAST_NAME,lname);
            	if(emailid.trim().length() > 0) 
            		users =DAO.getAllUsers(MyAppConstants.FIELD_EMAIL_ID,emailid);
            }
                        
            model.put("users", users);
            System.out.println(users);
            return new ModelAndView(model, "searchresult.vm");
        }, new VelocityTemplateEngine());
        
      //Searches Users
        get("/updateUsers", (request, response) -> {
            String msg ="";
        	Map<String, Object> model = new HashMap<>();
            ArrayList<User> users = new ArrayList<>();
            String userId = request.queryParams("userId");
            String fname = request.queryParams("t_fname");
            String lname = request.queryParams("t_lname");
            String emailid = request.queryParams("t_emailid");
            String street = request.queryParams("t_street");
            String city = request.queryParams("t_city");
            String zip = request.queryParams("t_zip");
            String state = request.queryParams("t_state");
            String country = request.queryParams("t_country");
            String companyName = request.queryParams("t_company");
            String companyUrl = request.queryParams("t_company_url");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      	   //get current date time with Date()
      	    Date date = new Date();
      	    System.out.println(dateFormat.format(date));
            String dateCreated = dateFormat.format(date);
            
         
            
            System.out.println("Update userId :"+userId);
            msg = DAO.updateUser(userId, fname, lname, emailid, street, city,zip, state, country, companyName,companyUrl,dateCreated);     
            model.put("users", users);
            System.out.println(users);
            //return new ModelAndView(model, "searchresult.vm");
            System.out.println("Msg ::"+msg);
            return msg;
        });
        
        
    }
	
	

}
