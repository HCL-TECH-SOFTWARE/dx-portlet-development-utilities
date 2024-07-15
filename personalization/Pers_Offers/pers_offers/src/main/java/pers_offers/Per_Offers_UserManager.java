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


import java.math.*;
import java.io.*;
import java.util.*;

// Personalization imports
import com.ibm.websphere.personalization.RequestContext;
import com.ibm.websphere.personalization.resources.*;

// Query framework
import com.ibm.websphere.query.callbacks.*;
import com.ibm.websphere.query.base.*;

// Default read/write stream implementation import
import com.ibm.websphere.personalization.utils.XMLUtility;

// Constants for configuration property access
import com.ibm.wcp.runtime.WCPConstants;

// Runtime database configuration utility
import com.ibm.wcp.runtime.util.RuntimePropFileUtils;

// Tracing
import com.ibm.ejs.ras.Tr;
import com.ibm.ejs.ras.TraceComponent;

// Password utility import
import com.ibm.wcp.runtime.util.PasswordUtil;


// Database imports
import com.ibm.websphere.personalization.util.db.DBPortability;
import javax.sql.DataSource;
import java.sql.*;
 
public class Per_Offers_UserManager implements Serializable, ResourceManager3, ResourceDomain3 {

	protected final static String DEFAULT_PROTOCOL = "SQL";
	protected final static String OS_NAME = System.getProperty("os.name");

	/**
	 * Variable to hold the resource collection name
	 */
	protected String resourceCollectionName = "Per_Offers_User";

	/** 
	 * Instance variable for the userID
	 */
	protected String userID = "";

	/** 
	 * Instance variable for the password
	 */
	private String password = PasswordUtil.decode("null");

	/**
	 * Instance variable for the URL
	 */
	protected String URL = "jdbc:derby:C:\\HCL\\wp_profile\\PortalServer";
	/**
	 * Instance variable for the DataSource class name
	 */
	protected String dataSourceClassName= "";

	/**
	 * Variable to hold the DataSource name
	 */
	protected String dataSourceName = "jdbc/pzndemo";
	
	/**
	 * Instance variable to hold DataSource
	 */
	protected transient DataSource ds = null;
	
	/**
	 * DataSource vendor specific properties
	 */
	protected final Properties dataSourceProperties = new Properties();



	// Property column index in selectSQL ResultSet
	protected static final int BIRTHDATE_COLUMN = 1; 
	protected static final int CUSTOMERTYPE_COLUMN = 2; 
	protected static final int DEPARTMENT_COLUMN = 3; 
	protected static final int EMAIL_COLUMN = 4; 
	protected static final int FIRST_NAME_COLUMN = 5; 
	protected static final int GENDER_COLUMN = 6; 
	protected static final int INCOMEGROUP_COLUMN = 7; 
	protected static final int LAST_NAME_COLUMN = 8; 
	protected static final int NUMLOGINS_COLUMN = 9; 
	protected static final int OFFICE_PHONE_COLUMN = 10; 
	protected static final int PASSWORD_COLUMN = 11; 
	protected static final int ROLE_COLUMN = 12; 
	protected static final int TITLE_COLUMN = 13; 
	protected static final int USERNAME_COLUMN = 14; 

	/**
	 * initSQLs property
	 */
	protected final String[] initSQLs = new String[] {
		"CREATE TABLE PZNDEMO.PZN_USER (FIRST_NAME VARCHAR(32), INCOMEGROUP INTEGER, ROLE VARCHAR(32), EMAIL VARCHAR(32), NUMLOGINS INTEGER, TITLE VARCHAR(32), GENDER VARCHAR(32), BIRTHDATE TIMESTAMP, DEPARTMENT VARCHAR(32), CUSTOMERTYPE VARCHAR(32), OFFICE_PHONE VARCHAR(32), LAST_NAME VARCHAR(32), USERNAME VARCHAR(32) NOT NULL, PASSWORD VARCHAR(32), PRIMARY KEY ( USERNAME ) )",
	};
	
	public String[] getInitSQLs() {
		return initSQLs;
	}

	/** 
	 * SELECT_SQL property
	 */
	protected static final String SELECT_SQL = "SELECT PZNDEMO.PZN_USER.BIRTHDATE, PZNDEMO.PZN_USER.CUSTOMERTYPE, PZNDEMO.PZN_USER.DEPARTMENT, PZNDEMO.PZN_USER.EMAIL, PZNDEMO.PZN_USER.FIRST_NAME, PZNDEMO.PZN_USER.GENDER, PZNDEMO.PZN_USER.INCOMEGROUP, PZNDEMO.PZN_USER.LAST_NAME, PZNDEMO.PZN_USER.NUMLOGINS, PZNDEMO.PZN_USER.OFFICE_PHONE, PZNDEMO.PZN_USER.PASSWORD, PZNDEMO.PZN_USER.ROLE, PZNDEMO.PZN_USER.TITLE, PZNDEMO.PZN_USER.USERNAME FROM PZNDEMO.PZN_USER ";

	public String getSelectSQL() {
		return SELECT_SQL;
	}

	/** 
	 * DELETE_SQL property
	 */
	protected static final String DELETE_SQL = "DELETE FROM PZNDEMO.PZN_USER ";

	public String getDeleteSQL() {
		return DELETE_SQL;
	}

	/** 
	 * INSERT_SQL property
	 */
	protected static final String INSERT_SQL = "INSERT INTO PZNDEMO.PZN_USER ( BIRTHDATE, CUSTOMERTYPE, DEPARTMENT, EMAIL, FIRST_NAME, GENDER, INCOMEGROUP, LAST_NAME, NUMLOGINS, OFFICE_PHONE, PASSWORD, ROLE, TITLE, USERNAME ) VALUES ( ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?,  ? ) ";

	public String getInsertSQL() {
		return INSERT_SQL;
	}

	/** 
	 * UPDATE_SQL property
	 */
	protected static final String UPDATE_SQL = 
	"UPDATE PZNDEMO.PZN_USER SET BIRTHDATE = ?, CUSTOMERTYPE = ?, DEPARTMENT = ?, EMAIL = ?, FIRST_NAME = ?, GENDER = ?, INCOMEGROUP = ?, LAST_NAME = ?, NUMLOGINS = ?, OFFICE_PHONE = ?, PASSWORD = ?, ROLE = ?, TITLE = ? WHERE ( USERNAME = ? ) ";

	public String getUpdateSQL() {
		return UPDATE_SQL;
	}

	



	/**
	 * A map of property names to column names.
	 */
	protected static final Map PROPERTY_COLUMN_MAP = new HashMap();

	/**
	 * A map of property names to Query framework types.
	 */
	protected static final Map PROPERTY_TYPE_MAP = new HashMap();

	// initialize the property to column and property to type maps
	static {

		PROPERTY_COLUMN_MAP.put("pzn.id", "PZNDEMO.PZN_USER.USERNAME");
		PROPERTY_TYPE_MAP.put("pzn.id", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));

		PROPERTY_COLUMN_MAP.put("birthdate", "PZNDEMO.PZN_USER.BIRTHDATE");
		PROPERTY_TYPE_MAP.put("birthdate", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_TIMESTAMP));
		PROPERTY_COLUMN_MAP.put("customertype", "PZNDEMO.PZN_USER.CUSTOMERTYPE");
		PROPERTY_TYPE_MAP.put("customertype", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("department", "PZNDEMO.PZN_USER.DEPARTMENT");
		PROPERTY_TYPE_MAP.put("department", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("email", "PZNDEMO.PZN_USER.EMAIL");
		PROPERTY_TYPE_MAP.put("email", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("first_name", "PZNDEMO.PZN_USER.FIRST_NAME");
		PROPERTY_TYPE_MAP.put("first_name", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("gender", "PZNDEMO.PZN_USER.GENDER");
		PROPERTY_TYPE_MAP.put("gender", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("incomegroup", "PZNDEMO.PZN_USER.INCOMEGROUP");
		PROPERTY_TYPE_MAP.put("incomegroup", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_INTEGER));
		PROPERTY_COLUMN_MAP.put("last_name", "PZNDEMO.PZN_USER.LAST_NAME");
		PROPERTY_TYPE_MAP.put("last_name", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("numlogins", "PZNDEMO.PZN_USER.NUMLOGINS");
		PROPERTY_TYPE_MAP.put("numlogins", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_INTEGER));
		PROPERTY_COLUMN_MAP.put("office_phone", "PZNDEMO.PZN_USER.OFFICE_PHONE");
		PROPERTY_TYPE_MAP.put("office_phone", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("password", "PZNDEMO.PZN_USER.PASSWORD");
		PROPERTY_TYPE_MAP.put("password", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("role", "PZNDEMO.PZN_USER.ROLE");
		PROPERTY_TYPE_MAP.put("role", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("title", "PZNDEMO.PZN_USER.TITLE");
		PROPERTY_TYPE_MAP.put("title", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("username", "PZNDEMO.PZN_USER.USERNAME");
		PROPERTY_TYPE_MAP.put("username", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
	}

	public static Map getPropertyColumnMap() {
		return PROPERTY_COLUMN_MAP;
	}

	public static Map getPropertyTypeMap() {
		return PROPERTY_TYPE_MAP;
	}


	/**
	 * Default constructor
	 */
	public Per_Offers_UserManager() {
		super();
		setDatabaseAccessProperties();
	}

	/**
	 * Initializing constructor
	 */
	public Per_Offers_UserManager(Map propertiesMap) {
		super();
		String newResourceCollectionName = (String) propertiesMap.get(WCPConstants.COLLECTION_NAME_PROPERTY_KEY);
		if (newResourceCollectionName != null && newResourceCollectionName.length() > 0)
			setResourceCollectionName(newResourceCollectionName);
		setDatabaseAccessProperties();
	}


	/**
	 * Get method for the resource collection name
	 */
	public String getResourceCollectionName() {
		return resourceCollectionName;
	}
	
	/**
	 * Set method for the resource collection name
	 */
	protected void setResourceCollectionName(String resourceCollectionName) {
		this.resourceCollectionName = resourceCollectionName;
	}
	
	/**
	 * Set method for all the database access properties
	 */
	protected void setDatabaseAccessProperties() {
		setDatabaseAccessProperties(RuntimePropFileUtils.getDefaultAccessProperties(this.getClass()));
	}
	
	/**
	 * Set method for all the database access properties
	 */
	protected void setDatabaseAccessProperties(Properties dbAccessProperties) {

		if (dbAccessProperties != null) {
			String currentCollection = getResourceCollectionName();
			
			String id = dbAccessProperties.getProperty(currentCollection + ".userid");
			if (id == null || id.length() == 0){
				id = dbAccessProperties.getProperty("userid");
				if (id != null && id.length() > 0)
					userID = id;
			} else 
				userID = id;

			String pw = dbAccessProperties.getProperty(currentCollection + ".password");
			if (pw == null || pw.length() == 0){
				pw = dbAccessProperties.getProperty("password");
				if (pw != null && pw.length() > 0)
					password = pw;
			} else 
				password = pw;

			String dSource = dbAccessProperties.getProperty(currentCollection + ".datasource");
			if (dSource == null || dSource.length() == 0){
				dSource = dbAccessProperties.getProperty("datasource");
				if (dSource != null && dSource.length() > 0)
					dataSourceName = dSource;
			} else 
				dataSourceName = dSource;

			String dataSourceClassNameProperty = dbAccessProperties.getProperty(currentCollection + ".driver");
			if (dataSourceClassNameProperty == null || dataSourceClassNameProperty.length() == 0){
				dataSourceClassNameProperty = dbAccessProperties.getProperty("driverName");
				if (dataSourceClassNameProperty != null && dataSourceClassNameProperty.length() > 0)
					dataSourceClassName = dataSourceClassNameProperty;
			} else 
				dataSourceClassName = dataSourceClassNameProperty;

			String u = dbAccessProperties.getProperty(currentCollection + ".url");
			if (u == null || u.length() == 0){
				u = dbAccessProperties.getProperty("url");
				if (u != null && u.length() > 0)
					URL = u;
			} else 
				URL = u;
		}
	}

	/**
	 * Get method for the userID property
	 * @return the value of the userID property
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Set method for the userID property
	 * @param value the new value for the userID property
	 */
	public void setUserID(String value) {
		userID = value;
	}

	/**
	 * Set method for the password property
	 * @param value the new value for the password property
	 */
	public void setPassword(String value) {
		password = value;
	}
	
	/**
	 * Get method for the URL property
	 * @return the value of the URL property
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * Set method for the URL property
	 * @param value the new value for the URL property
	 */
	public void setURL(String value) {
		URL = value;
	}

	/**
	 * Get method for the dataSourceClassName property
	 * @return the value of the dataSourceClassName property
	 */
	public String getDataSourceClassName() {
		return dataSourceClassName;
	}
	
	/**
	 * Set method for the dataSourceClassName property
	 * @param value the new value for the driver property
	 */
	public void setDataSourceClassName(String value) {
		dataSourceClassName = value;
	}

	/**
	 * Get method for the dataSourceName property
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * Set method for the dataSourceName property
	 */
	public void setDataSourceName(String value) {
		dataSourceName = value;
	}

	public ISelectQueryCallback getCallback() {
		return new SqlSelectQueryCallback(getPropertyColumnMap(), getPropertyTypeMap());
	}

	/**
	 * Add the specified resource to the resource collection
	 * @param resource Resource to be added to the resource collection.
	 * @param context RequestContext object used to get personalization context.
	 * @exception AddResourceException
	 * @exception DuplicateResourceException
	 */
	public void add(Resource resource, RequestContext context) throws AddResourceException, DuplicateResourceException {
		traceEntry(pers_offers.Per_Offers_UserManager.class, "add", null);
		resource.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());
		Per_Offers_User res = (Per_Offers_User) resource;

		Connection conn = getConnection(context);
		if (conn != null) {
			PreparedStatement statement= null;
			try {
				statement = conn.prepareStatement(getInsertSQL());
				if (res.getBirthdate() != null)
					statement.setObject(1, res.getBirthdate(), java.sql.Types.TIMESTAMP);
				else
					statement.setNull(1, java.sql.Types.TIMESTAMP);
				if (res.getCustomertype() != null)
					statement.setObject(2, res.getCustomertype(), java.sql.Types.VARCHAR);
				else
					statement.setNull(2, java.sql.Types.VARCHAR);
				if (res.getDepartment() != null)
					statement.setObject(3, res.getDepartment(), java.sql.Types.VARCHAR);
				else
					statement.setNull(3, java.sql.Types.VARCHAR);
				if (res.getEmail() != null)
					statement.setObject(4, res.getEmail(), java.sql.Types.VARCHAR);
				else
					statement.setNull(4, java.sql.Types.VARCHAR);
				if (res.getFirst_name() != null)
					statement.setObject(5, res.getFirst_name(), java.sql.Types.VARCHAR);
				else
					statement.setNull(5, java.sql.Types.VARCHAR);
				if (res.getGender() != null)
					statement.setObject(6, res.getGender(), java.sql.Types.VARCHAR);
				else
					statement.setNull(6, java.sql.Types.VARCHAR);
				if (res.getIncomegroup() != null)
					statement.setObject(7, res.getIncomegroup(), java.sql.Types.INTEGER);
				else
					statement.setNull(7, java.sql.Types.INTEGER);
				if (res.getLast_name() != null)
					statement.setObject(8, res.getLast_name(), java.sql.Types.VARCHAR);
				else
					statement.setNull(8, java.sql.Types.VARCHAR);
				if (res.getNumlogins() != null)
					statement.setObject(9, res.getNumlogins(), java.sql.Types.INTEGER);
				else
					statement.setNull(9, java.sql.Types.INTEGER);
				if (res.getOffice_phone() != null)
					statement.setObject(10, res.getOffice_phone(), java.sql.Types.VARCHAR);
				else
					statement.setNull(10, java.sql.Types.VARCHAR);
				if (res.getPassword() != null)
					statement.setObject(11, res.getPassword(), java.sql.Types.VARCHAR);
				else
					statement.setNull(11, java.sql.Types.VARCHAR);
				if (res.getRole() != null)
					statement.setObject(12, res.getRole(), java.sql.Types.VARCHAR);
				else
					statement.setNull(12, java.sql.Types.VARCHAR);
				if (res.getTitle() != null)
					statement.setObject(13, res.getTitle(), java.sql.Types.VARCHAR);
				else
					statement.setNull(13, java.sql.Types.VARCHAR);
				if (res.getUsername() != null)
					statement.setObject(14, res.getUsername(), java.sql.Types.VARCHAR);
				else
					statement.setNull(14, java.sql.Types.VARCHAR);
	
				int rc = statement.executeUpdate();
				if (rc != 1) {
					String msg = "executeUpdate row count not equal to 1 (" + rc + ")";
					traceDebug(pers_offers.Per_Offers_UserManager.class, "add", msg);
					throw new AddResourceException(msg);
				}


			} catch (SQLException e) {
				if (DBPortability.normalizeErrorCode(e, getDBPortabilityVendorCode(conn)) == DBPortability.DUPLICATE_KEY_ERROR_CODE) {
					DuplicateResourceException t = new DuplicateResourceException(res.getId() + ":" + e.getLocalizedMessage());
					t.initCause(e);
					throw t;
				}
				e.printStackTrace();
				AddResourceException t = new AddResourceException(e.getLocalizedMessage());
				t.initCause(e);
				throw t;
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch(SQLException e) {
					e.printStackTrace();
				}
				closeConnection(conn, context);
			}
		
			
		} else {
			String msg = "connection is null";
			traceDebug(pers_offers.Per_Offers_UserManager.class, "add", msg);
			throw new AddResourceException(msg);
		}
		traceExit(pers_offers.Per_Offers_UserManager.class, "add", null);
	}

	/**
	 * Delete the specified resource from the resource collection
	 * @param resource Resource to delete from the table of resource records
	 * @param context Context object used to get personalization context.
	 * @exception DeleteResourceException
	 */
	public void delete(Resource resource, RequestContext context) throws DeleteResourceException {
		traceEntry(pers_offers.Per_Offers_UserManager.class, "delete", null);
		resource.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());

		Connection conn = getConnection(context);
		if (conn != null) {
			Statement statement = null;
			try {
				Per_Offers_User res = (Per_Offers_User) resource;
				statement = conn.createStatement();
				
				String deleteString = getDeleteSQL() + "WHERE PZNDEMO.PZN_USER.USERNAME = '" + res.getUsername().toString() + "'";
				traceDebug(pers_offers.Per_Offers_UserManager.class, "delete", deleteString);
				
				int rc = statement.executeUpdate(deleteString);
				if (rc != 1) {
					String msg = "executeUpdate row count not equal to 1 (" + rc + ")";
					traceDebug(pers_offers.Per_Offers_UserManager.class, "delete", msg);
					throw new DeleteResourceException(msg);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				DeleteResourceException t = new DeleteResourceException(e.getLocalizedMessage());
				t.initCause(e);
				throw t;
			} finally {
				try {
					if (statement != null )
						statement.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
				closeConnection(conn, context);
			} 
		} else {
			String msg = "connection is null in pers_offers.Per_Offers_UserManager#delete";
			traceDebug(pers_offers.Per_Offers_UserManager.class, "delete", msg);
			throw new DeleteResourceException(msg);
		}

		traceExit(pers_offers.Per_Offers_UserManager.class, "delete", null);
	}

	/**
	 * Update the resource in the repository with the data in the given
	 * Resource object
	 * @param resource Resurce used to update the record.
	 * @param context Context object used to get personalization context.
	 * @exception ResourceUpdateException
	 */
	public void sync(Resource resource, RequestContext context) throws ResourceUpdateException {
		traceEntry(pers_offers.Per_Offers_UserManager.class, "sync", null);
		resource.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());
		Per_Offers_User res = (Per_Offers_User) resource;

		Connection conn = getConnection(context);
		if (conn != null) {

			PreparedStatement statement = null;

			try {
				statement = conn.prepareStatement(getUpdateSQL());
				if (res.getBirthdate() != null)
					statement.setObject(1, res.getBirthdate(), java.sql.Types.TIMESTAMP);
				else
					statement.setNull(1, java.sql.Types.TIMESTAMP);
				if (res.getCustomertype() != null)
					statement.setObject(2, res.getCustomertype(), java.sql.Types.VARCHAR);
				else
					statement.setNull(2, java.sql.Types.VARCHAR);
				if (res.getDepartment() != null)
					statement.setObject(3, res.getDepartment(), java.sql.Types.VARCHAR);
				else
					statement.setNull(3, java.sql.Types.VARCHAR);
				if (res.getEmail() != null)
					statement.setObject(4, res.getEmail(), java.sql.Types.VARCHAR);
				else
					statement.setNull(4, java.sql.Types.VARCHAR);
				if (res.getFirst_name() != null)
					statement.setObject(5, res.getFirst_name(), java.sql.Types.VARCHAR);
				else
					statement.setNull(5, java.sql.Types.VARCHAR);
				if (res.getGender() != null)
					statement.setObject(6, res.getGender(), java.sql.Types.VARCHAR);
				else
					statement.setNull(6, java.sql.Types.VARCHAR);
				if (res.getIncomegroup() != null)
					statement.setObject(7, res.getIncomegroup(), java.sql.Types.INTEGER);
				else
					statement.setNull(7, java.sql.Types.INTEGER);
				if (res.getLast_name() != null)
					statement.setObject(8, res.getLast_name(), java.sql.Types.VARCHAR);
				else
					statement.setNull(8, java.sql.Types.VARCHAR);
				if (res.getNumlogins() != null)
					statement.setObject(9, res.getNumlogins(), java.sql.Types.INTEGER);
				else
					statement.setNull(9, java.sql.Types.INTEGER);
				if (res.getOffice_phone() != null)
					statement.setObject(10, res.getOffice_phone(), java.sql.Types.VARCHAR);
				else
					statement.setNull(10, java.sql.Types.VARCHAR);
				if (res.getPassword() != null)
					statement.setObject(11, res.getPassword(), java.sql.Types.VARCHAR);
				else
					statement.setNull(11, java.sql.Types.VARCHAR);
				if (res.getRole() != null)
					statement.setObject(12, res.getRole(), java.sql.Types.VARCHAR);
				else
					statement.setNull(12, java.sql.Types.VARCHAR);
				if (res.getTitle() != null)
					statement.setObject(13, res.getTitle(), java.sql.Types.VARCHAR);
				else
					statement.setNull(13, java.sql.Types.VARCHAR);
				statement.setObject(14, res.getUsername(), java.sql.Types.VARCHAR);

				int rc = statement.executeUpdate();
				if (rc != 1) {
					String msg = "executeUpdate row count not equal to 1 (" + rc + ")";
					traceDebug(pers_offers.Per_Offers_UserManager.class, "sync", msg);
					throw new ResourceUpdateException(msg);
				}

		
			} catch (Exception e) {
				e.printStackTrace();
				ResourceUpdateException t = new ResourceUpdateException(e.getLocalizedMessage());
				t.initCause(e);
				throw t;
			} finally {
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				closeConnection(conn, context);
			}
			
			
		} else {
			String msg = "connection is null";
			traceDebug(pers_offers.Per_Offers_UserManager.class, "sync", msg);
			throw new ResourceUpdateException(msg);
		}	
		traceExit(pers_offers.Per_Offers_UserManager.class, "sync", null);
	}


	/**
	 * findById action method 
	 * @param value Primary key value of resource to be retrieved.
	 * @param context Context object used to get personalization context.
	 * @return the result of the findById action
	 */
	public Resource findById(String id, RequestContext context) {
		if (isTraceEntryEnabled())
			traceEntry(pers_offers.Per_Offers_UserManager.class, "findById", new Object[] { id });

		Resource res = null;
		try {
			Enumeration resources = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.USERNAME = '" + id + "'", DEFAULT_PROTOCOL, context, true);
			if (resources != null && resources.hasMoreElements()) {
				res = (Resource)resources.nextElement();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isTraceEntryEnabled())
			traceExit(pers_offers.Per_Offers_UserManager.class, "findById", res);
		return res;
	}

	/**
	 * findResourcesByProperty action method 
	 * @param name Name of property in record to be retrieved.
	 * @param value Value of property in record to be retrieved.
	 * @param context Context object used to get personalization context.
	 * @return the result of the erty action
	 */
	public Enumeration findResourcesByProperty(String name, String value, RequestContext context) {
		if(isTraceEntryEnabled())
			traceEntry(pers_offers.Per_Offers_UserManager.class, "findResourcesByProperty", new Object[] { name, value });

		Enumeration results = null;

		String queryString = " ";

		try {
			if (value == null) {
				if (name.equals("birthdate"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.BIRTHDATE is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("customertype"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.CUSTOMERTYPE is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("department"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.DEPARTMENT is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("email"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.EMAIL is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("first_name"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.FIRST_NAME is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("gender"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.GENDER is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("incomegroup"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.INCOMEGROUP is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("last_name"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.LAST_NAME is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("numlogins"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.NUMLOGINS is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("office_phone"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.OFFICE_PHONE is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("password"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.PASSWORD is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("role"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.ROLE is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("title"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.TITLE is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("username"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.USERNAME is null" + queryString, DEFAULT_PROTOCOL, context, true);
			} else {
				if (name.equals("birthdate"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.BIRTHDATE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("customertype"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.CUSTOMERTYPE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("department"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.DEPARTMENT = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("email"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.EMAIL = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("first_name"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.FIRST_NAME = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("gender"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.GENDER = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("incomegroup"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.INCOMEGROUP = " + value + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("last_name"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.LAST_NAME = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("numlogins"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.NUMLOGINS = " + value + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("office_phone"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.OFFICE_PHONE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("password"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.PASSWORD = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("role"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.ROLE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("title"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.TITLE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("username"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_USER.USERNAME = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		traceExit(pers_offers.Per_Offers_UserManager.class, "findResourcesByProperty", results);
		if (results != null)
			return results;
		else
			return new Vector().elements();
	}
	


	/**
	 * findResourcesByQueryString action method 
	 * @param query Where clause used to find resource record.
	 * @param language Language used to retrieve record.
	 * @param context a RequestContext
	 * @return the result of the findResourcesByQueryString action
	 * @exception QueryLanguageNotSupportedException
	 */
	public Enumeration findResourcesByQueryString(String queryString, String language, RequestContext context) throws QueryLanguageNotSupportedException {
		return findResourcesByQueryString(queryString, language, context, false);
	}

	/**
	 * findResourcesByQueryString action method 
	 * @param query Where clause used to find resource record.
	 * @param language Language used to retrieve record.
	 * @param context a RequestContext
	 * @param simplifiedQuery whether omit nonessential joins
	 * @return the result of the findResourcesByQueryString action
	 * @exception QueryLanguageNotSupportedException
	 */
	public Enumeration findResourcesByQueryString(String queryString, String language, RequestContext context, boolean simplifiedQuery) throws QueryLanguageNotSupportedException {
		if (isTraceEntryEnabled())
			traceEntry(pers_offers.Per_Offers_UserManager.class, "findResourcesByQueryString", new Object[] { queryString, language });
		
		if (!language.equalsIgnoreCase(DEFAULT_PROTOCOL)) {
			if (isTraceDebugEnabled())
				traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQueryString", "query language is not supported - " + language);
			throw new QueryLanguageNotSupportedException("Query language is not supported - " + language);
		}
		
		Vector resources = null;
		Enumeration elements = null;
		HashSet resourceIds = new HashSet();
		String fullQueryString = queryString;
		
		Connection conn = getConnection(context);
		if (conn != null) {
			// Add join clause if needed
			fullQueryString = getSelectSQL() + fullQueryString;

			resources = new Vector();
			ResultSet result = null;
			Statement statement = null;
			try {
				traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQueryString", fullQueryString);
				statement = conn.createStatement();
				result = statement.executeQuery(fullQueryString);
				try {				

					while (result.next()) {
						Resource res = convertResultRowToResource(result, resourceIds, context);
						if (res != null) {
							resources.addElement(res);
						}
					} 
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} catch (SQLException e) {
				if (context == null || !context.isPreviewMode() || DBPortability.normalizeErrorCode(e, getDBPortabilityVendorCode(conn)) != DBPortability.TABLE_DOES_NOT_EXIST_ERROR_CODE) {
					e.printStackTrace();
				} else {
					if (isTraceDebugEnabled())
						traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQueryString", "Table does not exist error code caught during preview - " + e.getLocalizedMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (result != null)
						result.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				closeConnection(conn, context);
			}	

			elements = resources.elements();
		} else {
			traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQueryString", "connection is null");
		}
	
		traceExit(pers_offers.Per_Offers_UserManager.class, "findResourcesByQueryString", elements);
		if (elements != null)
			return elements;
		else
			return new Vector().elements();
	}

	/**
	 * isQueryLanguageSupported action method 
	 * @param language Description of Language being checked to see if it is supported.
	 * @return the result of the isQueryLanguageSupported action
	 */
	public boolean isQueryLanguageSupported(String language) {
		return language.equalsIgnoreCase(DEFAULT_PROTOCOL);
	}

	/**
	 * findResourcesByQuery action method 
	 * @param query Query object used to find resource record.
	 * @param context a RequestContext
	 * @return the result of the findResourcesByQuery action
	 * @exception QueryException
	 */
	public Enumeration findResourcesByQuery(Query query, RequestContext context) throws QueryException {
		traceEntry(pers_offers.Per_Offers_UserManager.class, "findResourcesByQuery", null);

		Vector resources = new Vector();	

		Connection conn = getConnection(context);
		if (conn != null) {
			HashSet resourceIds = new HashSet();
	
			// Build SQL query string
			ISelectQueryCallback callback = getCallback();

			String s = query.buildString(callback);
			if (isTraceDebugEnabled())
				traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQuery", "predicate=" + s);

			String queryString = getSelectSQL() + s;
			if (isTraceDebugEnabled())
				traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQuery", "queryString=" + queryString);


			ResultSet result = null;
			Statement statement = null;

			try {
				statement = conn.createStatement();

				result = statement.executeQuery(queryString);
				try {		

					while (result.next()) {
						Resource res = convertResultRowToResource(result, resourceIds, context);
						if (res != null) {
							resources.addElement(res); 
						}
					} 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				if (context == null || !context.isPreviewMode() || DBPortability.normalizeErrorCode(e, getDBPortabilityVendorCode(conn)) != DBPortability.TABLE_DOES_NOT_EXIST_ERROR_CODE) {
					e.printStackTrace();
				} else {
					if (isTraceDebugEnabled())
						traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQuery", "Table does not exist error code caught during preview - " + e.getLocalizedMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (result != null)
						result.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				closeConnection(conn, context);
			}
		} else {
			String msg = "connection is null";
			traceDebug(pers_offers.Per_Offers_UserManager.class, "findResourcesByQuery", msg);
			throw new QueryException(msg);
		}

		traceExit(pers_offers.Per_Offers_UserManager.class, "findResourcesByQuery", resources);
		return resources.elements();
	}


	/**
	 * Attempts to get a connection from a AppServer V4 connection pool.
	 * 
	 * @param userID the userid to use for the database connection
	 * @param password the password to use for the connection
	 * @return a pooled JDBC connection or null
	 */
	protected Connection getPooledConnection(String userID, String password) {
		traceEntry(pers_offers.Per_Offers_UserManager.class, "getPooledConnection", null);
		Connection conn = null;
		
		if (ds != null) {
			try {
				if (userID != null && userID.length() > 0)
					conn = ds.getConnection(userID, password);
				else
					conn = ds.getConnection();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}

		if (conn == null) {
			traceDebug(pers_offers.Per_Offers_UserManager.class, "getPooledConnection", "connection not found from datasource on manager instance - attempting to get a new datasource instance...");
			String dsName = getDataSourceName();
			try {
				//Create the Initial Context
				javax.naming.Context ctx = new javax.naming.InitialContext();
				ds = (javax.sql.DataSource) ctx.lookup(dsName);
			} catch (Throwable t) {
				System.err.println("datasource not found - please create a valid datasource with the name " + dsName);
				t.printStackTrace();
			}

			if (ds != null) {			
				try {
					if (userID != null && userID.length() > 0)
						conn = ds.getConnection(userID, password);
					else
						conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				if (isTraceDebugEnabled())
					traceDebug(pers_offers.Per_Offers_UserManager.class, "getPooledConnection", "datasource could not be found or created with name=" + getDataSourceName() + "; consult your system admin to create the datasource manually");
			}
		}
		
		if (conn != null) {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		traceExit(pers_offers.Per_Offers_UserManager.class, "getPooledConnection", conn);
		return conn;
	}


	/**
	 * Return a database connection
	 */
	protected Connection getConnection(RequestContext context) {
		return getPooledConnection(getUserID(), password);
	}

	/**
	 * closeConnection action method 
	 * @param conn The connection to close
	 * @param context A RequestContext
	 */
	public void closeConnection(Connection conn, RequestContext context) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e){
			traceDebug(pers_offers.Per_Offers_UserManager.class, "closeConnection", "error occured closing connection");
			e.printStackTrace();
		}
	}



	/**
	 * Converts a ResultSet row into a Resource of the type returned from this Domain.
	 * @return resource created from the current row of the ResultSet
	 */
	protected Resource convertResultRowToResource(ResultSet result, Set resourceIds, RequestContext context) {
		traceEntry(pers_offers.Per_Offers_UserManager.class, "convertResultRowToResource", null);
		Per_Offers_User res = null;
		try {		

			java.lang.String key = null;
			key = result.getString(USERNAME_COLUMN);

			if (!resourceIds.contains(key)) {
				if (isTraceDebugEnabled())
					traceDebug(pers_offers.Per_Offers_UserManager.class, "convertResultRowToResource", "found new resource with id='" + key + "'");
				
				res = createResource(key);
				res.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());		
		


				res.setBirthdate(result.getTimestamp(BIRTHDATE_COLUMN));
				res.setCustomertype(result.getString(CUSTOMERTYPE_COLUMN));
				res.setDepartment(result.getString(DEPARTMENT_COLUMN));
				res.setEmail(result.getString(EMAIL_COLUMN));
				res.setFirst_name(result.getString(FIRST_NAME_COLUMN));
				res.setGender(result.getString(GENDER_COLUMN));
				res.setIncomegroup(new java.lang.Integer(result.getInt(INCOMEGROUP_COLUMN)));
				if (result.wasNull())
					res.setIncomegroup(null);
				res.setLast_name(result.getString(LAST_NAME_COLUMN));
				res.setNumlogins(new java.lang.Integer(result.getInt(NUMLOGINS_COLUMN)));
				if (result.wasNull())
					res.setNumlogins(null);
				res.setOffice_phone(result.getString(OFFICE_PHONE_COLUMN));
				res.setPassword(result.getString(PASSWORD_COLUMN));
				res.setRole(result.getString(ROLE_COLUMN));
				res.setTitle(result.getString(TITLE_COLUMN));

				resourceIds.add(key);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res = null;
		}

		traceExit(pers_offers.Per_Offers_UserManager.class, "convertResultRowToResource", null);
		return res;
	}
	
	protected Per_Offers_User createResource(java.lang.String username) {
		return new Per_Offers_User(username);
	}



	/**
	 * areDynamicAttributesSupported action method 
	 * @return the result of the areDynamicAttributesSupported action
	 */
	public boolean areDynamicAttributesSupported() {
		return true;
	}


	private static final String IBM_PZN_STREAM_FORMAT = "IBMPZN";

	/**
	 * Creates a resource from a stream, such as an XML stream.
	 * 
	 * @param inputStream
	 *                The stream containing the data from which
	 *                the resource should be created.
	 * @param context The context
	 * @param format  The format (e.g. "IBMPZN")
	 * @return
	 * @exception CreateResourceFromStreamException
	 */ 
	public Resource createResourceFromStream(InputStreamReader inputStream, RequestContext context, String format) throws CreateResourceFromStreamException {
		if (format.equals(IBM_PZN_STREAM_FORMAT)) {
			Per_Offers_User res = (Per_Offers_User)XMLUtility.createResourceFromXML(inputStream, getClass().getClassLoader());
			if (res != null) {
				res.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());
			}
			return res;
		}
		throw new CreateResourceFromStreamException("Resource stream format not supported by this manager - " + format);
	}
        
	/**
	 * Creates a set resources from a single stream, such as an XML stream.
	 *
	 * @param inputStream
	 *                The stream containing the data from which
	 *                the resource should be created.
	 * @param context The context
	 * @param format  The format (e.g. "IBMPZN")
	 * @return
	 * @exception CreateResourceFromStreamException
	 */
	public Enumeration createResourcesFromStream(InputStreamReader inputStream, RequestContext context, String format) throws CreateResourceFromStreamException {
		if (format.equals(IBM_PZN_STREAM_FORMAT)) {
			Object[] resources = (Object[])XMLUtility.createResourcesFromXML(inputStream, getClass().getClassLoader());
			Vector resObjs = new Vector();
			if (resources != null) {
				for (int i = 0; i < resources.length; i++) {
					Per_Offers_User res = (Per_Offers_User) resources[i];
					if (res != null) {
						res.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());
					}
					resObjs.addElement(res);
				}
			}
			return resObjs.elements();
		}
		throw new CreateResourceFromStreamException("Resource stream format not supported by this manager - " + format);
	}
    
	/**
	 * Creates a stream from a resource.
	 * 
	 * @param outputStream
	 *                 The stream to which the generated form
	 *                 will be written.
	 * @param resource The resource from which the stream should be
	 *                 created.
	 * @param context  The context.
	 * @param format   The desired fornat (e.g. "IBMPZN")
	 * @exception WriteResourceToStreamException
	 */  
	public void writeResourceToStream(OutputStreamWriter outputStream, Resource resource, RequestContext context, String format) throws WriteResourceToStreamException {
		if (format.equalsIgnoreCase(IBM_PZN_STREAM_FORMAT)) {
			XMLUtility.createXMLFromObject(resource, outputStream);
		} else {
			throw new WriteResourceToStreamException("Resource stream format not supported by this manager - " + format);
		}
	}
  
	/**
	 * Creates a stream from a resource.
	 *
	 * @param outputStream
	 *                  The stream to which the generated form
	 *                  will be written.
	 * @param resources The resources from which the stream should be created.
	 * @param context   The context.
	 * @param format    The desired fornat (e.g. "IBMPZN")
	 * @exception WriteResourceToStreamException
	 */
	public void writeResourcesToStream(OutputStreamWriter outputStream, Enumeration resources, RequestContext context, String format) throws WriteResourceToStreamException {
		if (format.equals(IBM_PZN_STREAM_FORMAT)) {
			Vector objList = new Vector();
			while (resources.hasMoreElements())
				objList.addElement(resources.nextElement());
				XMLUtility.createXMLFromObjects(objList, outputStream);
		} else {
			throw new WriteResourceToStreamException("Resource stream format not supported by this manager - " + format);
		}
	}
     
	/**
	 * Answers the list of supported formats for 
	 * createResourceFromStream and writeResourceToStream.
	 * @return a list of supported formats.
	 */
	public String[] getSupportedStreamFormats() {
		return new String[] { IBM_PZN_STREAM_FORMAT };
	}


	/**
	 * Perform any initialization required before a resource can be accessed using this
	 * manager.  For example, create any database table required to hold resource instances.
	 * 
	 * It is safe to call this method more than once.
	 */
	public void init(RequestContext context) throws InitException {
		Connection conn = getConnection(context);

		String errors = "";
		if (conn != null) {
		
			Statement stmt = null;
			try {
				stmt = conn.createStatement();

				String[] initSqls = getInitSQLs();
		
				for (int i=0; i < initSqls.length; i++) {
					if (isTraceDebugEnabled())
						traceDebug(pers_offers.Per_Offers_UserManager.class, "init", "create table sql = " + initSqls[i]);
					try {
						stmt.execute(initSqls[i]);
					} catch (SQLException e) {
						if (DBPortability.normalizeErrorCode(e, getDBPortabilityVendorCode(conn)) != DBPortability.TABLE_ALREADY_EXISTS_ERROR_CODE) {
							e.printStackTrace();
							errors += e.getLocalizedMessage() + ";";
						}
					}
				}

			
			} catch (Throwable t) {
				t.printStackTrace();
				errors += t.getLocalizedMessage();
			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				closeConnection(conn, null);
				if (errors != null && errors.length() > 0)
					throw new InitException(errors);
			}
		} else {
			String msg = "connection is null in pers_offers.Per_Offers_UserManager#init";
			traceDebug(pers_offers.Per_Offers_UserManager.class, "init", msg);
			throw new InitException(msg);
		}	
	}
	
	public int getDBPortabilityVendorCode(Connection conn) {
		return DBPortability.UNKNOWN;
	}



	/**
	 * getAssociationPropertyColumnMap action method 
	 */
	public static String[][] getAssociationPropertyColumnMap() {
		return ASSOCIATION_PROPERTY_COLUMN_MAP;
	}
	
	private static final String ASSOCIATION_PROPERTY_COLUMN_MAP[][] = {
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

	private static final TraceComponent TC = Tr.register(pers_offers.Per_Offers_UserManager.class, WCPConstants.RESOURCES_TRACE_LABEL, null);

	protected boolean isTraceEntryEnabled() {
		return TC.isEntryEnabled();
	}
	
	protected void traceEntry(Class classObj, String methodName, Object[] params) {
		if (TC.isEntryEnabled()) {
			String msg = classObj.getName() + "#" + methodName;
			if (params != null) {
				msg += " - ";
				for (int i = 0; i < params.length; i++)
					msg += params[i].toString() + ", ";
			}
			Tr.entry(TC, msg);
		}
	}
	
	protected void traceExit(Class classObj, String methodName, Object returnValue) {
		if (TC.isEntryEnabled())
			Tr.exit(TC, classObj.getName() + "#" + methodName + " - " + returnValue);
	}
	
	protected boolean isTraceDebugEnabled() {
		return TC.isDebugEnabled();
	}
	
	protected void traceDebug(Class classObj, String methodName, String msg) {
		if (TC.isDebugEnabled())
			Tr.debug(TC, classObj.getName() + "#" + methodName + " - " + msg);
	}

}
