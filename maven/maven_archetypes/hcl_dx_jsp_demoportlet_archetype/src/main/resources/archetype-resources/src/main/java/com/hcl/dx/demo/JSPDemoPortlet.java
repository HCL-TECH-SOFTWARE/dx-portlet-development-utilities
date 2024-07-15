/*
 * Copyright 2022 HCL America, Inc.
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
 
 package com.hcl.dx.demo;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletSession;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

/**
 * A sample portlet based on GenericPortlet
 */
public class JSPDemoPortlet extends GenericPortlet {

	public static final String JSP_FOLDER    = "/_JSPDemoPortlet/jsp/";    // JSP folder name

	public static final String VIEW_JSP      = "JSPDemoPortletView";         // JSP file name to be rendered on the view mode
	public static final String EDIT_JSP      = "JSPDemoPortletEdit";         // JSP file name to be rendered on the edit mode
	
	public static final String SESSION_BEAN  = "JSPDemoPortletSessionBean";  // Bean name for the portlet session
	public static final String FORM_SUBMIT   = "JSPDemoPortletFormSubmit";   // Action name for submit form
	public static final String FORM_TEXT     = "JSPDemoPortletFormText";     // Parameter name for the text input
   
	public static final String EDIT_SUBMIT   = "JSPDemoPortletEditSubmit";   // Action name for submit form
	public static final String EDIT_TEXT     = "JSPDemoPortletEditText";     // Parameter name for the text input
	public static final String EDIT_KEY      = ".JSPDemoPortletEditKey";     // Key for the portlet preferences
	public static final String PREF_SET      = "JSPDemoPortletPrefSet";      // Action name for set preference
	public static final String PREF_RESET    = "JSPDemoPortletPrefReset";    // Action name for reset preference
	public static final String PREF_NAME     = "JSPDemoPortletPrefName";     // Parameter name for the preference name
	public static final String PREF_VALUE    = "JSPDemoPortletPrefValue";    // Parameter name for the preference value

    public static final String ERROR_MESSAGE = "JSPDemoPortletErrorMessage"; // Parameter name for the validation error message
	public static final String ERROR_KEYS    = "JSPDemoPortletErrorKeys";    // Parameter name for the validation failed keys




	 
	/**
	 * @see javax.portlet.Portlet#init()
	 */
	public void init() throws PortletException{
		super.init();
	}

	/**
	 * Serve up the <code>view</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doView(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		JSPDemoPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
			response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}

		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, VIEW_JSP));
		rd.include(request,response);
	}

	/**
	 * Serve up the <code>edit</code> mode.
	 * 
	 * @see javax.portlet.GenericPortlet#doEdit(javax.portlet.RenderRequest, javax.portlet.RenderResponse)
	 */
	public void doEdit(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		// Set the MIME type for the render response
		response.setContentType(request.getResponseContentType());

		// Check if portlet session exists
		JSPDemoPortletSessionBean sessionBean = getSessionBean(request);
		if( sessionBean==null ) {
		    response.getWriter().println("<b>NO PORTLET SESSION YET</b>");
			return;
		}


		// Invoke the JSP to render
		PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(getJspFilePath(request, EDIT_JSP));
		rd.include(request,response);
	}
	
	/**
	 * Process an action request.
	 * 
	 * @see javax.portlet.Portlet#processAction(javax.portlet.ActionRequest, javax.portlet.ActionResponse)
	 */
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		if( request.getParameter(FORM_SUBMIT) != null ) {
			// Set form text in the session bean
			JSPDemoPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setFormText(request.getParameter(FORM_TEXT));
		}
		if( request.getParameter(PREF_RESET) != null ) {
			// Reset or remove a portlet preference
			PortletPreferences prefs = request.getPreferences();
			prefs.reset(request.getParameter(PREF_RESET));
			prefs.store();
			// Reset bookmark start position
			JSPDemoPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setStartPosition(0);
		}
		else if( request.getParameter(PREF_SET) != null ) {
			// Set a portlet preference
			PortletPreferences prefs = request.getPreferences();
			String prefName = request.getParameter(PREF_NAME);
			if( "".equals(prefName) ) prefName = "(no name)";
			prefName = "url." + prefName;
			try {
				prefs.setValue(prefName,request.getParameter(PREF_VALUE));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
				response.setRenderParameter(ERROR_MESSAGE,"Read-only portlet preference '"+prefName+"' cannot be changed. Use configure mode to change it.");
			}
			catch( ValidatorException ve ) {
				// Show validation error messages
				response.setRenderParameter(ERROR_MESSAGE,ve.getMessage());
				final Enumeration<?> failedKeys = ve.getFailedKeys();
				if( failedKeys != null && failedKeys.hasMoreElements() ) {
					final Vector<Object> errors = new Vector<Object>();
					while( failedKeys.hasMoreElements() )
						errors.add(failedKeys.nextElement());
					response.setRenderParameter(ERROR_KEYS,(String[])errors.toArray(new String[errors.size()]));
				}
			}
			// Reset bookmark start position
			JSPDemoPortletSessionBean sessionBean = getSessionBean(request);
			if( sessionBean != null )
				sessionBean.setStartPosition(0);
		}
		if( request.getParameter(EDIT_SUBMIT) != null ) {
			PortletPreferences prefs = request.getPreferences();
			try {
				prefs.setValue(EDIT_KEY,request.getParameter(EDIT_TEXT));
				prefs.store();
			}
			catch( ReadOnlyException roe ) {
			}
			catch( ValidatorException ve ) {
			}
		}
	}

	/**
	 * Get SessionBean.
	 * 
	 * @param request PortletRequest
	 * @return JSPDemoPortletSessionBean
	 */
	private static JSPDemoPortletSessionBean getSessionBean(PortletRequest request) {
		PortletSession session = request.getPortletSession();
		if( session == null )
			return null;
		JSPDemoPortletSessionBean sessionBean = (JSPDemoPortletSessionBean)session.getAttribute(SESSION_BEAN);
		if( sessionBean == null ) {
			sessionBean = new JSPDemoPortletSessionBean();
			session.setAttribute(SESSION_BEAN,sessionBean);
		}
		return sessionBean;
	}

	/**
	 * Returns JSP file path.
	 * 
	 * @param request Render request
	 * @param jspFile JSP file name
	 * @return JSP file path
	 */
	private static String getJspFilePath(RenderRequest request, String jspFile) {
		String markup = request.getProperty("wps.markup");
		if( markup == null )
			markup = getMarkup(request.getResponseContentType());
		return JSP_FOLDER + markup + "/" + jspFile + "." + getJspExtension(markup);
	}

	/**
	 * Convert MIME type to markup name.
	 * 
	 * @param contentType MIME type
	 * @return Markup name
	 */
	private static String getMarkup(String contentType) {
		if( "text/vnd.wap.wml".equals(contentType) )
			return "wml";
        else
            return "html";
	}

	/**
	 * Returns the file extension for the JSP file
	 * 
	 * @param markupName Markup name
	 * @return JSP extension
	 */
	private static String getJspExtension(String markupName) {
		return "jsp";
	}

}

