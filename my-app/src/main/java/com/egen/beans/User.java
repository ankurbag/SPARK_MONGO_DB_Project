
package com.egen.beans;

/**
 * User Bean mapped against the User Record. It is the part of Model
 * @author Ankur
 *
 */
public class User {
	
	public User(){
		
	}
	
	/**
	 * @param fname
	 * @param lname
	 * @param emailid
	 */
	

	/**Attributes **/
	private String id;
	/**
	 * @param fname
	 * @param lname
	 * @param emailid
	 * @param street
	 * @param city
	 * @param zip
	 * @param state
	 * @param country
	 */
	
	private String fname;
	/**
	 * @param fname
	 * @param lname
	 * @param emailid
	 * @param street
	 * @param city
	 * @param zip
	 * @param state
	 * @param country
	 * @param companyName
	 * @param companyUrl
	 */
	public User(String fname, String lname, String emailid, String street, String city, String zip, String state,
			String country, String companyName, String companyUrl,String dateCreated) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.emailid = emailid;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
		this.companyName = companyName;
		this.companyUrl = companyUrl;
		this.dateCreated =dateCreated;
	}

	private String lname;
	private String emailid;
	private String street;
	private String city;
	private String zip;
	private String state;
	private String country;
	private String companyName;
	private String companyUrl;
	private String dateCreated;
	private String profilePic;
	
	
	/**
	 * @return the profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}

	/**
	 * @param profilePic the profilePic to set
	 */
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	/**
	 * @return the dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyUrl
	 */
	public String getCompanyUrl() {
		return companyUrl;
	}

	/**
	 * @param companyUrl the companyUrl to set
	 */
	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the emailid
	 */
	public String getEmailid() {
		return emailid;
	}
	/**
	 * @param emailid the emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
}
