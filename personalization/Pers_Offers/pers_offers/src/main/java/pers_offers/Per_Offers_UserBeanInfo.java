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

public class Per_Offers_UserBeanInfo extends SimpleBeanInfo 
{
	public BeanDescriptor getBeanDescriptor()
	{
		BeanDescriptor aDescriptor = new BeanDescriptor(Per_Offers_User.class);
		aDescriptor.setDisplayName("Per_Offers_User");
		aDescriptor.setName("Per_Offers_User");
		aDescriptor.setShortDescription("Per_Offers_User");
		aDescriptor.setValue("ibmwcp.defaultCollectionName", "Per_Offers_User");
		aDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
		aDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
		aDescriptor.setValue("resourceIdPropertyName", "username");
		return aDescriptor;
	}


	private PropertyDescriptor[] propertyDescriptors;

	public PropertyDescriptor[] getPropertyDescriptors()
	{
		if (propertyDescriptors == null) {
			propertyDescriptors = new PropertyDescriptor[] {
				getOffice_phonePropertyDescriptor(),
				getDepartmentPropertyDescriptor(),
				getFirst_namePropertyDescriptor(),
				getRolePropertyDescriptor(),
				getEmailPropertyDescriptor(),
				getCustomertypePropertyDescriptor(),
				getUsernamePropertyDescriptor(),
				getGenderPropertyDescriptor(),
				getLast_namePropertyDescriptor(),
				getTitlePropertyDescriptor(),
				getPasswordPropertyDescriptor(),
				getBirthdatePropertyDescriptor(),
				getIncomegroupPropertyDescriptor(),
				getNumloginsPropertyDescriptor(),
			};
		}
			
		return propertyDescriptors;
	}
	
	protected PropertyDescriptor getOffice_phonePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("office_phone", Per_Offers_User.class, "getOffice_phone", "setOffice_phone");
			aPropertyDescriptor.setDisplayName("Office_phone");
			aPropertyDescriptor.setName("office_phone");
			aPropertyDescriptor.setShortDescription("Office_phone");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "OFFICE_PHONE"); 
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
	protected PropertyDescriptor getDepartmentPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("department", Per_Offers_User.class, "getDepartment", "setDepartment");
			aPropertyDescriptor.setDisplayName("Department");
			aPropertyDescriptor.setName("department");
			aPropertyDescriptor.setShortDescription("Department");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "DEPARTMENT"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(2));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getFirst_namePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("first_name", Per_Offers_User.class, "getFirst_name", "setFirst_name");
			aPropertyDescriptor.setDisplayName("First_name");
			aPropertyDescriptor.setName("first_name");
			aPropertyDescriptor.setShortDescription("First_name");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "FIRST_NAME"); 
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
	protected PropertyDescriptor getRolePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("role", Per_Offers_User.class, "getRole", "setRole");
			aPropertyDescriptor.setDisplayName("Role");
			aPropertyDescriptor.setName("role");
			aPropertyDescriptor.setShortDescription("Role");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "ROLE"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(4));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getEmailPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("email", Per_Offers_User.class, "getEmail", "setEmail");
			aPropertyDescriptor.setDisplayName("Email");
			aPropertyDescriptor.setName("email");
			aPropertyDescriptor.setShortDescription("Email");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "EMAIL"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(5));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getCustomertypePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("customertype", Per_Offers_User.class, "getCustomertype", "setCustomertype");
			aPropertyDescriptor.setDisplayName("Customertype");
			aPropertyDescriptor.setName("customertype");
			aPropertyDescriptor.setShortDescription("Customertype");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "CUSTOMERTYPE"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("enumerationValues", new Object[] { "Gold", "Gold", "Gold", "Titanium", "Titanium", "Titanium", "Platinum", "Platinum", "Platinum" });
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(6));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getUsernamePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("username", Per_Offers_User.class, "getUsername", null);
			aPropertyDescriptor.setDisplayName("Username");
			aPropertyDescriptor.setName("username");
			aPropertyDescriptor.setShortDescription("Username");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.TRUE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "USERNAME"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(7));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.FALSE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getGenderPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("gender", Per_Offers_User.class, "getGender", "setGender");
			aPropertyDescriptor.setDisplayName("Gender");
			aPropertyDescriptor.setName("gender");
			aPropertyDescriptor.setShortDescription("Gender");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "GENDER"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(8));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getLast_namePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("last_name", Per_Offers_User.class, "getLast_name", "setLast_name");
			aPropertyDescriptor.setDisplayName("Last_name");
			aPropertyDescriptor.setName("last_name");
			aPropertyDescriptor.setShortDescription("Last_name");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "LAST_NAME"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(9));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getTitlePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("title", Per_Offers_User.class, "getTitle", "setTitle");
			aPropertyDescriptor.setDisplayName("Title");
			aPropertyDescriptor.setName("title");
			aPropertyDescriptor.setShortDescription("Title");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "TITLE"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(10));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getPasswordPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("password", Per_Offers_User.class, "getPassword", "setPassword");
			aPropertyDescriptor.setDisplayName("Password");
			aPropertyDescriptor.setName("password");
			aPropertyDescriptor.setShortDescription("Password");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "PASSWORD"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(32));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.VARCHAR));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(11));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getBirthdatePropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("birthdate", Per_Offers_User.class, "getBirthdate", "setBirthdate");
			aPropertyDescriptor.setDisplayName("Birthdate");
			aPropertyDescriptor.setName("birthdate");
			aPropertyDescriptor.setShortDescription("Birthdate");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "BIRTHDATE"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.precision", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(0));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.TIMESTAMP));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(12));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getIncomegroupPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("incomegroup", Per_Offers_User.class, "getIncomegroup", "setIncomegroup");
			aPropertyDescriptor.setDisplayName("Incomegroup");
			aPropertyDescriptor.setName("incomegroup");
			aPropertyDescriptor.setShortDescription("Incomegroup");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "INCOMEGROUP"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.precision", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.scale", new Integer(0));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.INTEGER));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(13));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}	
	protected PropertyDescriptor getNumloginsPropertyDescriptor() {
		PropertyDescriptor aPropertyDescriptor = null;
		try {
			aPropertyDescriptor = new PropertyDescriptor("numlogins", Per_Offers_User.class, "getNumlogins", "setNumlogins");
			aPropertyDescriptor.setDisplayName("Numlogins");
			aPropertyDescriptor.setName("numlogins");
			aPropertyDescriptor.setShortDescription("Numlogins");
			aPropertyDescriptor.setValue("ibm-ws-studio-primary-key", Boolean.FALSE);
			aPropertyDescriptor.setValue("isSupportedInWhereClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.comparable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.schemaName", "PZNDEMO");
			aPropertyDescriptor.setValue("ibmwcp.tableName", "PZN_USER");
			aPropertyDescriptor.setValue("ibmwcp.columnName", "NUMLOGINS"); 
			aPropertyDescriptor.setValue("ibmwcp.isSupportedInOrderByClause", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.precision", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.length", new Integer(0));
			aPropertyDescriptor.setValue("ibmwcp.scale", new Integer(0));		
			aPropertyDescriptor.setValue("ibmwcp.dbtype", new Integer(java.sql.Types.INTEGER));
			aPropertyDescriptor.setValue("ibmwcp.searchable", Boolean.TRUE);
			aPropertyDescriptor.setValue("ibmwcp.columnOrder", new Integer(14));
			aPropertyDescriptor.setValue("ibmwcp.nullable", Boolean.TRUE);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return aPropertyDescriptor;
	}

}