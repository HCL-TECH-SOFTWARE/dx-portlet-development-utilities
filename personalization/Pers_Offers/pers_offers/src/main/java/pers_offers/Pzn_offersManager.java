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
 
public class Pzn_offersManager implements Serializable, ResourceManager3, ResourceDomain3 {

	protected final static String DEFAULT_PROTOCOL = "SQL";
	protected final static String OS_NAME = System.getProperty("os.name");

	/**
	 * Variable to hold the resource collection name
	 */
	protected String resourceCollectionName = "Pzn_offers";

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
	protected static final int CUSTOMERTYPE_COLUMN = 1; 
	protected static final int DETAILS_COLUMN = 2; 
	protected static final int OFFER_ID_COLUMN = 3; 
	protected static final int TITLE_COLUMN = 4; 

	/**
	 * initSQLs property
	 */
	protected final String[] initSQLs = new String[] {
		"CREATE TABLE PZNDEMO.PZN_OFFERS (DETAILS VARCHAR(32), CUSTOMERTYPE VARCHAR(32), TITLE VARCHAR(32), OFFER_ID INTEGER NOT NULL, PRIMARY KEY ( OFFER_ID ) )",
	};
	
	public String[] getInitSQLs() {
		return initSQLs;
	}

	/** 
	 * SELECT_SQL property
	 */
	protected static final String SELECT_SQL = "SELECT PZNDEMO.PZN_OFFERS.CUSTOMERTYPE, PZNDEMO.PZN_OFFERS.DETAILS, PZNDEMO.PZN_OFFERS.OFFER_ID, PZNDEMO.PZN_OFFERS.TITLE FROM PZNDEMO.PZN_OFFERS ";

	public String getSelectSQL() {
		return SELECT_SQL;
	}

	/** 
	 * DELETE_SQL property
	 */
	protected static final String DELETE_SQL = "DELETE FROM PZNDEMO.PZN_OFFERS ";

	public String getDeleteSQL() {
		return DELETE_SQL;
	}

	/** 
	 * INSERT_SQL property
	 */
	protected static final String INSERT_SQL = "INSERT INTO PZNDEMO.PZN_OFFERS ( CUSTOMERTYPE, DETAILS, OFFER_ID, TITLE ) VALUES ( ?,  ?,  ?,  ? ) ";

	public String getInsertSQL() {
		return INSERT_SQL;
	}

	/** 
	 * UPDATE_SQL property
	 */
	protected static final String UPDATE_SQL = 
	"UPDATE PZNDEMO.PZN_OFFERS SET CUSTOMERTYPE = ?, DETAILS = ?, TITLE = ? WHERE ( OFFER_ID = ? ) ";

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

		PROPERTY_COLUMN_MAP.put("pzn.id", "PZNDEMO.PZN_OFFERS.OFFER_ID");
		PROPERTY_TYPE_MAP.put("pzn.id", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_INTEGER));

		PROPERTY_COLUMN_MAP.put("customertype", "PZNDEMO.PZN_OFFERS.CUSTOMERTYPE");
		PROPERTY_TYPE_MAP.put("customertype", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("details", "PZNDEMO.PZN_OFFERS.DETAILS");
		PROPERTY_TYPE_MAP.put("details", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
		PROPERTY_COLUMN_MAP.put("offer_id", "PZNDEMO.PZN_OFFERS.OFFER_ID");
		PROPERTY_TYPE_MAP.put("offer_id", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_INTEGER));
		PROPERTY_COLUMN_MAP.put("title", "PZNDEMO.PZN_OFFERS.TITLE");
		PROPERTY_TYPE_MAP.put("title", new Integer(com.ibm.websphere.query.base.Constants.DATATYPE_VARCHAR));
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
	public Pzn_offersManager() {
		super();
		setDatabaseAccessProperties();
	}

	/**
	 * Initializing constructor
	 */
	public Pzn_offersManager(Map propertiesMap) {
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
		traceEntry(pers_offers.Pzn_offersManager.class, "add", null);
		resource.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());
		Pzn_offers res = (Pzn_offers) resource;

		Connection conn = getConnection(context);
		if (conn != null) {
			PreparedStatement statement= null;
			try {
				statement = conn.prepareStatement(getInsertSQL());
				if (res.getCustomertype() != null)
					statement.setObject(1, res.getCustomertype(), java.sql.Types.VARCHAR);
				else
					statement.setNull(1, java.sql.Types.VARCHAR);
				if (res.getDetails() != null)
					statement.setObject(2, res.getDetails(), java.sql.Types.VARCHAR);
				else
					statement.setNull(2, java.sql.Types.VARCHAR);
				if (res.getOffer_id() != null)
					statement.setObject(3, res.getOffer_id(), java.sql.Types.INTEGER);
				else
					statement.setNull(3, java.sql.Types.INTEGER);
				if (res.getTitle() != null)
					statement.setObject(4, res.getTitle(), java.sql.Types.VARCHAR);
				else
					statement.setNull(4, java.sql.Types.VARCHAR);
	
				int rc = statement.executeUpdate();
				if (rc != 1) {
					String msg = "executeUpdate row count not equal to 1 (" + rc + ")";
					traceDebug(pers_offers.Pzn_offersManager.class, "add", msg);
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
			traceDebug(pers_offers.Pzn_offersManager.class, "add", msg);
			throw new AddResourceException(msg);
		}
		traceExit(pers_offers.Pzn_offersManager.class, "add", null);
	}

	/**
	 * Delete the specified resource from the resource collection
	 * @param resource Resource to delete from the table of resource records
	 * @param context Context object used to get personalization context.
	 * @exception DeleteResourceException
	 */
	public void delete(Resource resource, RequestContext context) throws DeleteResourceException {
		traceEntry(pers_offers.Pzn_offersManager.class, "delete", null);
		resource.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());

		Connection conn = getConnection(context);
		if (conn != null) {
			Statement statement = null;
			try {
				Pzn_offers res = (Pzn_offers) resource;
				statement = conn.createStatement();
				
				String deleteString = getDeleteSQL() + "WHERE PZNDEMO.PZN_OFFERS.OFFER_ID = " + res.getOffer_id().toString();
				traceDebug(pers_offers.Pzn_offersManager.class, "delete", deleteString);
				
				int rc = statement.executeUpdate(deleteString);
				if (rc != 1) {
					String msg = "executeUpdate row count not equal to 1 (" + rc + ")";
					traceDebug(pers_offers.Pzn_offersManager.class, "delete", msg);
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
			String msg = "connection is null in pers_offers.Pzn_offersManager#delete";
			traceDebug(pers_offers.Pzn_offersManager.class, "delete", msg);
			throw new DeleteResourceException(msg);
		}

		traceExit(pers_offers.Pzn_offersManager.class, "delete", null);
	}

	/**
	 * Update the resource in the repository with the data in the given
	 * Resource object
	 * @param resource Resurce used to update the record.
	 * @param context Context object used to get personalization context.
	 * @exception ResourceUpdateException
	 */
	public void sync(Resource resource, RequestContext context) throws ResourceUpdateException {
		traceEntry(pers_offers.Pzn_offersManager.class, "sync", null);
		resource.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());
		Pzn_offers res = (Pzn_offers) resource;

		Connection conn = getConnection(context);
		if (conn != null) {

			PreparedStatement statement = null;

			try {
				statement = conn.prepareStatement(getUpdateSQL());
				if (res.getCustomertype() != null)
					statement.setObject(1, res.getCustomertype(), java.sql.Types.VARCHAR);
				else
					statement.setNull(1, java.sql.Types.VARCHAR);
				if (res.getDetails() != null)
					statement.setObject(2, res.getDetails(), java.sql.Types.VARCHAR);
				else
					statement.setNull(2, java.sql.Types.VARCHAR);
				if (res.getTitle() != null)
					statement.setObject(3, res.getTitle(), java.sql.Types.VARCHAR);
				else
					statement.setNull(3, java.sql.Types.VARCHAR);
				statement.setObject(4, res.getOffer_id(), java.sql.Types.INTEGER);

				int rc = statement.executeUpdate();
				if (rc != 1) {
					String msg = "executeUpdate row count not equal to 1 (" + rc + ")";
					traceDebug(pers_offers.Pzn_offersManager.class, "sync", msg);
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
			traceDebug(pers_offers.Pzn_offersManager.class, "sync", msg);
			throw new ResourceUpdateException(msg);
		}	
		traceExit(pers_offers.Pzn_offersManager.class, "sync", null);
	}


	/**
	 * findById action method 
	 * @param value Primary key value of resource to be retrieved.
	 * @param context Context object used to get personalization context.
	 * @return the result of the findById action
	 */
	public Resource findById(String id, RequestContext context) {
		if (isTraceEntryEnabled())
			traceEntry(pers_offers.Pzn_offersManager.class, "findById", new Object[] { id });

		Resource res = null;
		try {
			Enumeration resources = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.OFFER_ID = " + id, DEFAULT_PROTOCOL, context, true);
			if (resources != null && resources.hasMoreElements()) {
				res = (Resource)resources.nextElement();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (isTraceEntryEnabled())
			traceExit(pers_offers.Pzn_offersManager.class, "findById", res);
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
			traceEntry(pers_offers.Pzn_offersManager.class, "findResourcesByProperty", new Object[] { name, value });

		Enumeration results = null;

		String queryString = " ";

		try {
			if (value == null) {
				if (name.equals("customertype"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.CUSTOMERTYPE is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("details"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.DETAILS is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("offer_id"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.OFFER_ID is null" + queryString, DEFAULT_PROTOCOL, context, true);
				else if (name.equals("title"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.TITLE is null" + queryString, DEFAULT_PROTOCOL, context, true);
			} else {
				if (name.equals("customertype"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.CUSTOMERTYPE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("details"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.DETAILS = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("offer_id"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.OFFER_ID = " + value + queryString, DEFAULT_PROTOCOL, context);
				else if (name.equals("title"))
					results = findResourcesByQueryString(" WHERE PZNDEMO.PZN_OFFERS.TITLE = '" + value+ "'" + queryString, DEFAULT_PROTOCOL, context);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		traceExit(pers_offers.Pzn_offersManager.class, "findResourcesByProperty", results);
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
			traceEntry(pers_offers.Pzn_offersManager.class, "findResourcesByQueryString", new Object[] { queryString, language });
		
		if (!language.equalsIgnoreCase(DEFAULT_PROTOCOL)) {
			if (isTraceDebugEnabled())
				traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQueryString", "query language is not supported - " + language);
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
				traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQueryString", fullQueryString);
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
						traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQueryString", "Table does not exist error code caught during preview - " + e.getLocalizedMessage());
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
			traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQueryString", "connection is null");
		}
	
		traceExit(pers_offers.Pzn_offersManager.class, "findResourcesByQueryString", elements);
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
		traceEntry(pers_offers.Pzn_offersManager.class, "findResourcesByQuery", null);

		Vector resources = new Vector();	

		Connection conn = getConnection(context);
		if (conn != null) {
			HashSet resourceIds = new HashSet();
	
			// Build SQL query string
			ISelectQueryCallback callback = getCallback();

			String s = query.buildString(callback);
			if (isTraceDebugEnabled())
				traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQuery", "predicate=" + s);

			String queryString = getSelectSQL() + s;
			if (isTraceDebugEnabled())
				traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQuery", "queryString=" + queryString);


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
						traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQuery", "Table does not exist error code caught during preview - " + e.getLocalizedMessage());
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
			traceDebug(pers_offers.Pzn_offersManager.class, "findResourcesByQuery", msg);
			throw new QueryException(msg);
		}

		traceExit(pers_offers.Pzn_offersManager.class, "findResourcesByQuery", resources);
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
		traceEntry(pers_offers.Pzn_offersManager.class, "getPooledConnection", null);
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
			traceDebug(pers_offers.Pzn_offersManager.class, "getPooledConnection", "connection not found from datasource on manager instance - attempting to get a new datasource instance...");
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
					traceDebug(pers_offers.Pzn_offersManager.class, "getPooledConnection", "datasource could not be found or created with name=" + getDataSourceName() + "; consult your system admin to create the datasource manually");
			}
		}
		
		if (conn != null) {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		traceExit(pers_offers.Pzn_offersManager.class, "getPooledConnection", conn);
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
			traceDebug(pers_offers.Pzn_offersManager.class, "closeConnection", "error occured closing connection");
			e.printStackTrace();
		}
	}



	/**
	 * Converts a ResultSet row into a Resource of the type returned from this Domain.
	 * @return resource created from the current row of the ResultSet
	 */
	protected Resource convertResultRowToResource(ResultSet result, Set resourceIds, RequestContext context) {
		traceEntry(pers_offers.Pzn_offersManager.class, "convertResultRowToResource", null);
		Pzn_offers res = null;
		try {		

			java.lang.Integer key = null;
			key = new java.lang.Integer(result.getInt(OFFER_ID_COLUMN));
			if (result.wasNull())
				key = null;

			if (!resourceIds.contains(key)) {
				if (isTraceDebugEnabled())
					traceDebug(pers_offers.Pzn_offersManager.class, "convertResultRowToResource", "found new resource with id='" + key + "'");
				
				res = createResource(key);
				res.put(WCPConstants.COLLECTION_NAME_PROPERTY_KEY, getResourceCollectionName());		
		


				res.setCustomertype(result.getString(CUSTOMERTYPE_COLUMN));
				res.setDetails(result.getString(DETAILS_COLUMN));
				res.setTitle(result.getString(TITLE_COLUMN));

				resourceIds.add(key);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			res = null;
		}

		traceExit(pers_offers.Pzn_offersManager.class, "convertResultRowToResource", null);
		return res;
	}
	
	protected Pzn_offers createResource(java.lang.Integer offer_id) {
		return new Pzn_offers(offer_id);
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
			Pzn_offers res = (Pzn_offers)XMLUtility.createResourceFromXML(inputStream, getClass().getClassLoader());
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
					Pzn_offers res = (Pzn_offers) resources[i];
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
						traceDebug(pers_offers.Pzn_offersManager.class, "init", "create table sql = " + initSqls[i]);
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
			String msg = "connection is null in pers_offers.Pzn_offersManager#init";
			traceDebug(pers_offers.Pzn_offersManager.class, "init", msg);
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
		{"customertype", "PZNDEMO.PZN_OFFERS.CUSTOMERTYPE", java.lang.String.class.getName()},
		{"details", "PZNDEMO.PZN_OFFERS.DETAILS", java.lang.String.class.getName()},
		{"offer_id", "PZNDEMO.PZN_OFFERS.OFFER_ID", java.lang.Integer.class.getName()},
		{"title", "PZNDEMO.PZN_OFFERS.TITLE", java.lang.String.class.getName()}
	};

	private static final TraceComponent TC = Tr.register(pers_offers.Pzn_offersManager.class, WCPConstants.RESOURCES_TRACE_LABEL, null);

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
