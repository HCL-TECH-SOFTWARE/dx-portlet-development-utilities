/*
 * Copyright 2024 HCL America, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pers_offers;


import java.util.*;
import java.math.*;

// Personalization imports

import com.ibm.websphere.personalization.RequestContext;
import com.ibm.websphere.personalization.resources.*;



public class Per_Offers_User implements com.ibm.websphere.personalization.resources.Resource
{

	private static final String PROPERTY_COLUMN_MAP[][] = {
		{"birthdate", "PZNDEMO.PZN_USER.BIRTHDATE", java.sql.Timestamp.class.getName()},
		{"customertype", "PZNDEMO.PZN_USER.CUSTOMERTYPE", java.lang.String.class.getName()},
		{"department", "PZNDEMO.PZN_USER.DEPARTMENT", java.lang.String.class.getName()},
		{"email", "PZNDEMO.PZN_USER.EMAIL", java.lang.String.class.getName()},
		{"first_name", "PZNDEMO.PZN_USER.FIRST_NAME", java.lang.String.class.getName()},
		{"gender", "PZNDEMO.PZN_USER.GENDER", java.lang.String.class.getName()},
		{"incomegroup", "PZNDEMO.PZN_USER.INCOMEGROUP", java.lang.Integer.class.getName()},
		{"last_name", "PZNDEMO.PZN_USER.LAST_NAME", java.lang.String.class.getName()},
		{"numlogins", "PZNDEMO.PZN_USER.NUMLOGINS", java.lang.Integer.class.getName()},
		{"office_phone", "PZNDEMO.PZN_USER.OFFICE_PHONE", java.lang.String.class.getName()},
		{"password", "PZNDEMO.PZN_USER.PASSWORD", java.lang.String.class.getName()},
		{"role", "PZNDEMO.PZN_USER.ROLE", java.lang.String.class.getName()},
		{"title", "PZNDEMO.PZN_USER.TITLE", java.lang.String.class.getName()},
		{"username", "PZNDEMO.PZN_USER.USERNAME", java.lang.String.class.getName()}
	};

	/** 
	 * Instance variable for birthdate property
	 */
	protected java.sql.Timestamp birthdate = null;

	/** 
	 * Instance variable for customertype property
	 */
	protected java.lang.String customertype = null;

	/** 
	 * Instance variable for department property
	 */
	protected java.lang.String department = null;

	/** 
	 * Instance variable for email property
	 */
	protected java.lang.String email = null;

	/** 
	 * Instance variable for first_name property
	 */
	protected java.lang.String first_name = null;

	/** 
	 * Instance variable for gender property
	 */
	protected java.lang.String gender = null;

	/** 
	 * Instance variable for incomegroup property
	 */
	protected java.lang.Integer incomegroup = null;

	/** 
	 * Instance variable for last_name property
	 */
	protected java.lang.String last_name = null;

	/** 
	 * Instance variable for numlogins property
	 */
	protected java.lang.Integer numlogins = null;

	/** 
	 * Instance variable for office_phone property
	 */
	protected java.lang.String office_phone = null;

	/** 
	 * Instance variable for password property
	 */
	protected java.lang.String password = null;

	/** 
	 * Instance variable for role property
	 */
	protected java.lang.String role = null;

	/** 
	 * Instance variable for title property
	 */
	protected java.lang.String title = null;

	/** 
	 * Instance variable for username property
	 */
	protected java.lang.String username = null;



		

	/**
	 * Instance of dynamic properties - override
	 */
	protected java.util.Hashtable dynamicProperties;
	


	/**
	 * Default Constructor
	 */
	public Per_Offers_User() {
		super();
	}

	/**
	 * Constructor
	 * @param id The primary key
	 */
	public Per_Offers_User(java.lang.String id) {
		super();
		this.username = id;
	}



	/**
	 * getId action method 
	 * @return the result of the getId action
	 */
	public java.lang.String getId() {
		if(username != null)
			return username.toString();
		return null;
	}



	/**
	 * Get method for the birthdate property.
	 * @return The value of the birthdate property  
	 */
	public java.sql.Timestamp getBirthdate() {	
		return birthdate;
	}
	
	
	/**
	 * Set method for the birthdate property
	 * @param value The new value for the birthdate property  
	 */
	public void setBirthdate(java.sql.Timestamp value) {
		this.birthdate = value;
	}
	

	/**
	 * Get method for the customertype property.
	 * @return The value of the customertype property  
	 */
	public java.lang.String getCustomertype() {	
		return customertype;
	}
	/**
	 * A map of values to descriptions for use by the
	 * getCustomertypeDescription method.
	 */
	private static final Map CUSTOMERTYPE_DESCRIPTION_MAP = new HashMap();
	
	static {
		try {
			CUSTOMERTYPE_DESCRIPTION_MAP.put("Gold", "Gold");
			CUSTOMERTYPE_DESCRIPTION_MAP.put("Titanium", "Titanium");
			CUSTOMERTYPE_DESCRIPTION_MAP.put("Platinum", "Platinum");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get method for a description of the customertype property value.  If more than
	 * one description is mapped to a particular value, this method does not ensure which
	 * description will be returned.
	 * @return The mapped description or the value itself if no description is mapped.
	 */
	public String getCustomertypeDescription() {
		String description = null;
		if (customertype != null) {
			description = (String) CUSTOMERTYPE_DESCRIPTION_MAP.get(customertype);
			if (description == null)
				description = customertype.toString();
		}
		return description;
	}
	
	
	/**
	 * Set method for the customertype property
	 * @param value The new value for the customertype property  
	 */
	public void setCustomertype(java.lang.String value) {
		this.customertype = value;
	}
	

	/**
	 * Get method for the department property.
	 * @return The value of the department property  
	 */
	public java.lang.String getDepartment() {	
		return department;
	}
	
	
	/**
	 * Set method for the department property
	 * @param value The new value for the department property  
	 */
	public void setDepartment(java.lang.String value) {
		this.department = value;
	}
	

	/**
	 * Get method for the email property.
	 * @return The value of the email property  
	 */
	public java.lang.String getEmail() {	
		return email;
	}
	
	
	/**
	 * Set method for the email property
	 * @param value The new value for the email property  
	 */
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	

	/**
	 * Get method for the first_name property.
	 * @return The value of the first_name property  
	 */
	public java.lang.String getFirst_name() {	
		return first_name;
	}
	
	
	/**
	 * Set method for the first_name property
	 * @param value The new value for the first_name property  
	 */
	public void setFirst_name(java.lang.String value) {
		this.first_name = value;
	}
	

	/**
	 * Get method for the gender property.
	 * @return The value of the gender property  
	 */
	public java.lang.String getGender() {	
		return gender;
	}
	
	
	/**
	 * Set method for the gender property
	 * @param value The new value for the gender property  
	 */
	public void setGender(java.lang.String value) {
		this.gender = value;
	}
	

	/**
	 * Get method for the incomegroup property.
	 * @return The value of the incomegroup property  
	 */
	public java.lang.Integer getIncomegroup() {	
		return incomegroup;
	}
	
	
	/**
	 * Set method for the incomegroup property
	 * @param value The new value for the incomegroup property  
	 */
	public void setIncomegroup(java.lang.Integer value) {
		this.incomegroup = value;
	}
	

	/**
	 * Get method for the last_name property.
	 * @return The value of the last_name property  
	 */
	public java.lang.String getLast_name() {	
		return last_name;
	}
	
	
	/**
	 * Set method for the last_name property
	 * @param value The new value for the last_name property  
	 */
	public void setLast_name(java.lang.String value) {
		this.last_name = value;
	}
	

	/**
	 * Get method for the numlogins property.
	 * @return The value of the numlogins property  
	 */
	public java.lang.Integer getNumlogins() {	
		return numlogins;
	}
	
	
	/**
	 * Set method for the numlogins property
	 * @param value The new value for the numlogins property  
	 */
	public void setNumlogins(java.lang.Integer value) {
		this.numlogins = value;
	}
	

	/**
	 * Get method for the office_phone property.
	 * @return The value of the office_phone property  
	 */
	public java.lang.String getOffice_phone() {	
		return office_phone;
	}
	
	
	/**
	 * Set method for the office_phone property
	 * @param value The new value for the office_phone property  
	 */
	public void setOffice_phone(java.lang.String value) {
		this.office_phone = value;
	}
	

	/**
	 * Get method for the password property.
	 * @return The value of the password property  
	 */
	public java.lang.String getPassword() {	
		return password;
	}
	
	
	/**
	 * Set method for the password property
	 * @param value The new value for the password property  
	 */
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	

	/**
	 * Get method for the role property.
	 * @return The value of the role property  
	 */
	public java.lang.String getRole() {	
		return role;
	}
	
	
	/**
	 * Set method for the role property
	 * @param value The new value for the role property  
	 */
	public void setRole(java.lang.String value) {
		this.role = value;
	}
	

	/**
	 * Get method for the title property.
	 * @return The value of the title property  
	 */
	public java.lang.String getTitle() {	
		return title;
	}
	
	
	/**
	 * Set method for the title property
	 * @param value The new value for the title property  
	 */
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	

	/**
	 * Get method for the username property.
	 * @return The value of the username property  
	 */
	public java.lang.String getUsername() {	
		return username;
	}
	
	








	/**
	 * get action method 
	 * @param name Name of dynamic property to be retrieved.
	 * @return the result of the get action
	 */
	public java.lang.Object get(java.lang.String name) {
		if ( dynamicProperties != null )
			return dynamicProperties.get(name);
		else
			return null;
	}
	
	/**
	 * put action method 
	 * @param name Name of dynamic property being defined.
	 * @param value Value of dynamic property.
	 */
	public void put(java.lang.String name, java.lang.Object value) {
		if (dynamicProperties == null)
			dynamicProperties = new Hashtable();
		dynamicProperties.put(name,value);    
	}
	
	/**
	 * remove action method 
	 * @param name Name of dynamic property to be removed.
	 */
	public void remove(java.lang.String name) {
		if (dynamicProperties != null)
			dynamicProperties.remove(name);    
	}

	/**
	 * keys action method 
	 * @return the result of the keys action
	 */
	public java.util.Enumeration keys() {
		if (dynamicProperties != null)
			return dynamicProperties.keys();
		else 
			return (new Vector()).elements();
	}
	/**
	 * getPropertyColumnMap action method 
	 * @return the result of the getPropertyColumnMap action
	 */
	public static java.lang.String[][] getPropertyColumnMap()  
	{
		return PROPERTY_COLUMN_MAP;
	}
	
}
