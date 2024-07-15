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

import java.beans.*;
import java.sql.Types;

public class Pzn_offersBeanInfo extends SimpleBeanInfo 
{
	public BeanDescriptor getBeanDescriptor()
	{
		BeanDescriptor aDescriptor = new BeanDescriptor(Pzn_offers.class);
		aDescriptor.setDisplayName("Pzn_offers");
		aDescriptor.setName("Pzn_offers");
		aDescriptor.setShortDescription("Pzn_offers");
		aDescriptor.setValue("ibmwcp.defaultCollectionName", "Pzn_offers");
		aDescriptor.setValue("ibmwcp.tableName", "PZN_OFFERS");
		aDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
		aDescriptor.setValue("resourceIdPropertyName", "offer_id");
		return aDescriptor;
	}


	private PropertyDescriptor[] propertyDescriptors;

	public PropertyDescriptor[] getPropertyDescriptors()
	{
		if (propertyDescriptors == null) {
			propertyDescriptors = new PropertyDescriptor[] {
				getDetailsPropertyDescriptor(),
				getCustomertypePropertyDescriptor(),
				getTitlePropertyDescriptor(),
				getOffer_idPropertyDescriptor(),
			};
		}
			
		return propertyDescriptors;
	}
	
	protected PropertyDescriptor getDetailsPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("details", Pzn_offers.class, "getDetails", "setDetails");
			aPropertyDescriptor.setDisplayName("Details");
			aPropertyDescriptor.setName("details");
			aPropertyDescriptor.setShortDescription("Details");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_OFFERS");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "DETAILS"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(1));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getCustomertypePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("customertype", Pzn_offers.class, "getCustomertype", "setCustomertype");
			aPropertyDescriptor.setDisplayName("Customertype");
			aPropertyDescriptor.setName("customertype");
			aPropertyDescriptor.setShortDescription("Customertype");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_OFFERS");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "CUSTOMERTYPE"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("enumerationValues", new Object[] { "Gold", "Gold", "Gold", "Titanium", "Titanium", "Titanium", "Platinum", "Platinum", "Platinum" });
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(2));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getTitlePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("title", Pzn_offers.class, "getTitle", "setTitle");
			aPropertyDescriptor.setDisplayName("Title");
			aPropertyDescriptor.setName("title");
			aPropertyDescriptor.setShortDescription("Title");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_OFFERS");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "TITLE"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(3));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getOffer_idPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("offer_id", Pzn_offers.class, "getOffer_id", null);
			aPropertyDescriptor.setDisplayName("Offer_id");
			aPropertyDescriptor.setName("offer_id");
			aPropertyDescriptor.setShortDescription("Offer_id");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.TRUE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_OFFERS");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "OFFER_ID"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.precision", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.scale", new Integer(0));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.INTEGER));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(4));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.FALSE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}

}