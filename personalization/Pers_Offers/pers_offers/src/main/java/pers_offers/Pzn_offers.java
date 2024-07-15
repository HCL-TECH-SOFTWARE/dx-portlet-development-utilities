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



public class Pzn_offers implements com.ibm.websphere.personalization.resources.Resource
{

	private static final String PROPERTY_COLUMN_MAP[][] = {
		{"customertype", "PZNDEMO.PZN_OFFERS.CUSTOMERTYPE", java.lang.String.class.getName()},
		{"details", "PZNDEMO.PZN_OFFERS.DETAILS", java.lang.String.class.getName()},
		{"offer_id", "PZNDEMO.PZN_OFFERS.OFFER_ID", java.lang.Integer.class.getName()},
		{"title", "PZNDEMO.PZN_OFFERS.TITLE", java.lang.String.class.getName()}
	};

	/** 
	 * Instance variable for customertype property
	 */
	protected java.lang.String customertype = null;

	/** 
	 * Instance variable for details property
	 */
	protected java.lang.String details = null;

	/** 
	 * Instance variable for offer_id property
	 */
	protected java.lang.Integer offer_id = null;

	/** 
	 * Instance variable for title property
	 */
	protected java.lang.String title = null;



		

	/**
	 * Instance of dynamic properties - override
	 */
	protected java.util.Hashtable dynamicProperties;
	


	/**
	 * Default Constructor
	 */
	public Pzn_offers() {
		super();
	}

	/**
	 * Constructor
	 * @param id The primary key
	 */
	public Pzn_offers(java.lang.Integer id) {
		super();
		this.offer_id = id;
	}
	/**
	 * Constructor
	 * @param value The primary key
	 */
	public Pzn_offers(java.lang.String id) {
		super();
		this.offer_id = new Integer(id);
	}



	/**
	 * getId action method 
	 * @return the result of the getId action
	 */
	public java.lang.String getId() {
		if(offer_id != null)
			return offer_id.toString();
		return null;
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
	 * Get method for the details property.
	 * @return The value of the details property  
	 */
	public java.lang.String getDetails() {	
		return details;
	}
	
	
	/**
	 * Set method for the details property
	 * @param value The new value for the details property  
	 */
	public void setDetails(java.lang.String value) {
		this.details = value;
	}
	

	/**
	 * Get method for the offer_id property.
	 * @return The value of the offer_id property  
	 */
	public java.lang.Integer getOffer_id() {	
		return offer_id;
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
