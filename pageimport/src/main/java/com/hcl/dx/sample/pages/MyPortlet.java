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

package com.hcl.dx.sample.pages;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.GenericPortlet;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import com.hcl.dx.sample.util.Utility;

public class MyPortlet extends GenericPortlet {

	@Override
	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
		String method = "processAction";
		logger.entering(MyPortlet.class.getName(), method);
		
		String successMessage = "";
		PageBean pageBean = Utility.createSinglePage(request, response, request.getParameter("parentUniqueName"),
				request.getParameter("contentUUID"), request.getParameter("friendlyUrl"),
				request.getParameter("pageName"));
		successMessage = "Successfully created " + pageBean.getPageName();
		

		request.getPortletSession().setAttribute("Success",successMessage);
		logger.exiting(MyPortlet.class.getName(), method);

	}

	private static final String NORMAL_VIEW = "/normal.jsp";
	private static final String MAXIMIZED_VIEW = "/maximized.jsp";
	private static final String HELP_VIEW = "/help.jsp";


	private PortletRequestDispatcher normalView;
	private PortletRequestDispatcher maximizedView;
	private PortletRequestDispatcher helpView;

	private static final Logger logger = LogManager.getLogManager().getLogger(MyPortlet.class.getName());

	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		String method = "doView";
		logger.entering(MyPortlet.class.getName(), method);

		if (WindowState.MINIMIZED.equals(request.getWindowState())) {
			return;
		}

		String successMessage = (String) request.getPortletSession().getAttribute("Success");
		request.setAttribute("Success", successMessage);
		if (WindowState.NORMAL.equals(request.getWindowState())) {
			normalView.include(request, response);
		} else {
			maximizedView.include(request, response);
		}
		logger.exiting(MyPortlet.class.getName(), method);
	}

	protected void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {

		helpView.include(request, response);

	}

	public void init(PortletConfig config) throws PortletException {
		super.init(config);
		normalView = config.getPortletContext().getRequestDispatcher(NORMAL_VIEW);
		maximizedView = config.getPortletContext().getRequestDispatcher(MAXIMIZED_VIEW);
		helpView = config.getPortletContext().getRequestDispatcher(HELP_VIEW);
	}

	public void destroy() {
		normalView = null;
		maximizedView = null;
		helpView = null;
		super.destroy();
	}

}
