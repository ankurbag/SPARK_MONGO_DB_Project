/**
 * 
 */
package com.egen.constants;

/**
 * This Interface contains all the constants used throughout the application
 * @author Ankur
 *
 */
public interface MyAppConstants {
	/**
	 * Constants declaration
	 */
	//DB RELATED CONSTANTS
	String HOST_DB_URL = "localhost";
	int HOST_DB_PORT = 27017;
	String DB_NAME = "local";//"my_app_test_db";
	String COLLECTION_NAME = "user_collection";
	//APP CONSTANTS
	String SUCCESS = "success";
	String FAILURE = "failure";
	//FIELDS
	String FIELD_FIRST_NAME = "firstName";
	String FIELD_LAST_NAME = "lastName";
	String FIELD_EMAIL_ID = "email";
	String FIELD_USER_ID = "_id";
	String FIELD_ADDR ="address";
	String FIELD_STREET ="street";
	String FIELD_CITY ="city";
	String FIELD_STATE ="state";
	String FIELD_ZIP ="zip";
	String FIELD_COUNTRY ="country";
	String FIELD_DT_CREATED ="dateCreated";
	String FIELD_COMPANY ="company";
	String FIELD_COMPANY_NAME="name";
	String FIELD_COMPANY_WEBSITE="website";
	String FIELD_PROFILE_PIC="profilePic";
	String PROFILE_URL ="http://icons.iconarchive.com/icons/icons-land/vista-people/128/Office-Customer-Male-Light-icon.png";
	
	
}
