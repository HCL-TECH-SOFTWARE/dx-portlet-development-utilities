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

package com.hcl.dx.sample.util;

import java.util.Locale;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.naming.CompositeName;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.hcl.dx.sample.pages.PageBean;
import com.ibm.portal.CreationContext;
import com.ibm.portal.Locator;
import com.ibm.portal.ModelException;
import com.ibm.portal.Modifiable;
import com.ibm.portal.ModifiableIdentifiable;
import com.ibm.portal.ModifiableLocalized;
import com.ibm.portal.ModifiableMetaData;
import com.ibm.portal.ModifiableMetaDataProvider;
import com.ibm.portal.ModifiableObjectID;
import com.ibm.portal.ObjectID;
import com.ibm.portal.admin.Markup;
import com.ibm.portal.admin.MarkupList;
import com.ibm.portal.admin.ModifiableMarkupCapable;
import com.ibm.portal.content.ContentLabel;
import com.ibm.portal.content.ContentModel;
import com.ibm.portal.content.ContentModelController;
import com.ibm.portal.content.ContentNode;
import com.ibm.portal.content.ContentNodeCopyCreationContext;
import com.ibm.portal.content.ContentNodeCreationContext;
import com.ibm.portal.content.ContentPage;
import com.ibm.portal.content.LayoutContainer;
import com.ibm.portal.content.LayoutControl;
import com.ibm.portal.content.LayoutModelController;
import com.ibm.portal.content.LayoutNode;
import com.ibm.portal.content.ModifiableLayoutControl;
import com.ibm.portal.content.ModifiableLayoutNode;
import com.ibm.portal.content.StaticContentPage;
import com.ibm.portal.model.MarkupListHome;
import com.ibm.portal.model.controller.ContentModelControllerHome;
import com.ibm.portal.model.controller.CreationContextBuilderFactory;
import com.ibm.portal.model.controller.context.TemplatingCreationContext;
import com.ibm.portal.model.controller.context.UniqueNameStrategy;
import com.ibm.portal.portlet.ModifiablePortletEntity;
import com.ibm.portal.portlet.ModifiablePortletPreferences;
import com.ibm.portal.portlet.service.PortletServiceHome;
import com.ibm.portal.portlet.service.PortletServiceUnavailableException;
import com.ibm.portal.portlet.service.model.ContentModelProvider;
import com.ibm.portal.portlet.service.model.NavigationModelProvider;
import com.ibm.portal.portlet.service.model.PortletModelProvider;
import com.ibm.portal.portletmodel.PortletEntity;
import com.ibm.portal.portletmodel.PortletModel;
import com.ibm.portal.portletmodel.PortletWindow;

public class Utility {

	private static final String className = Utility.class.toString();
	private static final Logger logger = LogManager.getLogManager().getLogger(className);

	public static PageBean createSinglePage(ActionRequest request, ActionResponse response, String parentUniqueName,
			String defaultContentUUID, String friendlyUrl, String pageName) {

		PortletServiceHome psh;
		ContentModelController contentModelController = null;
		PageBean pageBean = new PageBean(pageName, new Long(1), friendlyUrl, parentUniqueName);

		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			psh = (PortletServiceHome) ctx
					.lookup("portletservice/com.ibm.portal.portlet.service.model.ContentModelProvider");
			ContentModelProvider contentProvider = (ContentModelProvider) psh
					.getPortletService(ContentModelProvider.class);
			ContentModel contentModel = contentProvider.getContentModel(request, response);
			psh = (PortletServiceHome) ctx
					.lookup("portletservice/com.ibm.portal.portlet.service.model.NavigationModelProvider");
			NavigationModelProvider navProvider = (NavigationModelProvider) psh
					.getPortletService(NavigationModelProvider.class);
			final ContentModelControllerHome home = (ContentModelControllerHome) ctx
					.lookup(ContentModelControllerHome.JNDI_NAME);
			contentModelController = home.getContentModelControllerProvider()
					.createContentModelController(contentModel);

			MarkupListHome markupListHome = (MarkupListHome) ctx.lookup("portal:service/model/MarkupList");

			// parent
			Locator locator = contentModel.getLocator();

			// obtain creation context builder
			final CreationContextBuilderFactory creationContextBuilderFactory = CreationContextBuilderFactory
					.getInstance();

			ContentNodeCreationContext context = creationContextBuilderFactory.newContentNodeCreationContext(false);
			psh = (PortletServiceHome) ctx
					.lookup("portletservice/com.ibm.portal.portlet.service.model.PortletModelProvider");
			PortletModelProvider portletModelProvider = (PortletModelProvider) psh
					.getPortletService(PortletModelProvider.class);
			PortletModel model = portletModelProvider.getPortletModel(request, response);

			// portal:uniquename prefix is required for unique name lookup
			Name uniqueName = new CompositeName("portal:uniquename");

			uniqueName.add("ibm.portal.Web.Content.Viewer.Jsr286");

			ObjectID definition = (ObjectID) ctx.lookup(uniqueName);

			logger.fine("Setup is done for createPages");
			
			ContentNode contentParentPage = (ContentNode) (locator.findByUniqueName(parentUniqueName));
			if (pageBean.getLevel().intValue() > 1) {
				contentParentPage = (ContentNode) (locator.findByUniqueName(pageBean.getParentPage()));

				System.out.println("Inside level 1: " + pageBean.toString());
			}
			
			if (contentParentPage == null) {
				// could not find parent
				logger.fine("createPages could not find parent: " + pageBean.getParentPage());
			}
			// do not overwrite existing pages
			ContentPage pageExists = (ContentPage) (locator.findByUniqueName(pageBean.getPageName()));
			if (pageExists != null)
			{
				return pageBean;
			}

			ContentPage targetPage = (ContentPage) contentModelController.create(ContentPage.class, context);

			// Set page name
			if (targetPage instanceof ModifiableLocalized) {
				((ModifiableLocalized) targetPage).setTitle(Locale.ENGLISH, pageBean.getPageName());
			}

			// Set page markup
			if (targetPage instanceof ModifiableMarkupCapable) {
				MarkupList markupList = null;
				if (markupListHome != null) {
					markupList = markupListHome.getMarkupListProvider().getMarkupList();
				}
				Markup markup = markupList.getByName("html");
				((ModifiableMarkupCapable) targetPage).addMarkup(markup);
			}

			// Set page unique name
			if (targetPage instanceof ModifiableIdentifiable) {

				ModifiableObjectID mOID = ((ModifiableIdentifiable) targetPage).getModifiableObjectID();
				mOID.setUniqueName(pageBean.getPageName());
			}

			// friendly URLs
			String friendlyURL = pageBean.getUrl();
			if (targetPage instanceof ModifiableMetaDataProvider && friendlyURL != null) {

				ModifiableMetaData modifiableMetaData = ((ModifiableMetaDataProvider) targetPage)
						.getModifiableMetaData();
				modifiableMetaData.setValue(com.ibm.portal.resolver.friendly.Constants.FRIENDLY_NAME_KEY, friendlyURL);
			}

			contentModelController.insert(targetPage, contentParentPage, null);
			LayoutModelController<LayoutNode, ModifiableLayoutNode> lmc = contentModelController
					.getLayoutModelController(targetPage);
			final LayoutContainer rootContainer = (LayoutContainer) lmc.create(LayoutContainer.class, null);
			lmc.insert(rootContainer, null, null);
			final LayoutContainer oneCol = (LayoutContainer) lmc.create(LayoutContainer.class, null);
			lmc.insert(oneCol, rootContainer, null);

			// add portlet
			final CreationContext creationCtx = CreationContextBuilderFactory.getInstance()
					.newLayoutControlCreationContext(definition, null);
			final ModifiableLayoutControl newControl = (ModifiableLayoutControl) lmc.create(LayoutControl.class,
					creationCtx);
			lmc.insert(newControl, oneCol, null);
			PortletWindow window = model.getPortletWindow(newControl);
			PortletEntity entity = model.getPortletEntity(window);
			// obtain modifiable instance of a portlet entity
			final Modifiable modifiable = lmc.getPortletModelController().getModifiableNode(entity);

			// obtain modifiable preferences layer
			final ModifiablePortletPreferences preferences = ((ModifiablePortletEntity) modifiable)
					.getModifiablePortletPreferencesLayer();

			// Set a content (optional)
			if(null != defaultContentUUID && !defaultContentUUID.isEmpty()) {
				preferences.setStringValue("WCM_CONTENT_CONTEXT_IDR", defaultContentUUID);

			}
			contentModelController.commit();
			contentModelController.dispose();
			contentModelController = home.getContentModelControllerProvider()
					.createContentModelController(contentModel);

		} catch (NamingException | PortletServiceUnavailableException | ModelException ex) {
			ex.printStackTrace();
			System.out.println(ex);
		} finally {
			contentModelController.dispose();
		}

		return pageBean;

	}
	
	//Sample method to create a page from template instead - works for labels too
	public static PageBean createSinglePageFromTemplate(ActionRequest request, ActionResponse response, String parentUniqueName,
			String friendlyUrl, String pageName, String templatePageUniqueName) {

		PortletServiceHome psh;
		ContentModelController contentModelController = null;
		PageBean pageBean = new PageBean(pageName, new Long(1), friendlyUrl, parentUniqueName);

		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			psh = (PortletServiceHome) ctx
					.lookup("portletservice/com.ibm.portal.portlet.service.model.ContentModelProvider");
			ContentModelProvider contentProvider = (ContentModelProvider) psh
					.getPortletService(ContentModelProvider.class);
			ContentModel contentModel = contentProvider.getContentModel(request, response);
			psh = (PortletServiceHome) ctx
					.lookup("portletservice/com.ibm.portal.portlet.service.model.NavigationModelProvider");
			NavigationModelProvider navProvider = (NavigationModelProvider) psh
					.getPortletService(NavigationModelProvider.class);
			final ContentModelControllerHome home = (ContentModelControllerHome) ctx
					.lookup(ContentModelControllerHome.JNDI_NAME);
			contentModelController = home.getContentModelControllerProvider()
					.createContentModelController(contentModel);

			MarkupListHome markupListHome = (MarkupListHome) ctx.lookup("portal:service/model/MarkupList");

			// parent
			Locator locator = contentModel.getLocator();
			
			ContentNode templatePage = (ContentNode) (locator.findByUniqueName(templatePageUniqueName));

			// obtain creation context builder
			final CreationContextBuilderFactory creationContextBuilderFactory = CreationContextBuilderFactory
					.getInstance();

			psh = (PortletServiceHome) ctx
					.lookup("portletservice/com.ibm.portal.portlet.service.model.PortletModelProvider");
			PortletModelProvider portletModelProvider = (PortletModelProvider) psh
					.getPortletService(PortletModelProvider.class);
			PortletModel model = portletModelProvider.getPortletModel(request, response);

			// portal:uniquename prefix is required for unique name lookup
			Name uniqueName = new CompositeName("portal:uniquename");

			uniqueName.add("ibm.portal.Web.Content.Viewer.Jsr286");

			ObjectID definition = (ObjectID) ctx.lookup(uniqueName);

			logger.fine("Setup is done for createPages");
			
			ContentNode contentParentPage = (ContentNode) (locator.findByUniqueName(parentUniqueName));
			if (pageBean.getLevel().intValue() > 1) {
				contentParentPage = (ContentNode) (locator.findByUniqueName(pageBean.getParentPage()));

				System.out.println("Inside level 1: " + pageBean.toString());
			}
			
			if (contentParentPage == null) {
				// could not find parent
				logger.fine("createPages could not find parent: " + pageBean.getParentPage());
			}
			// do not overwrite existing pages
			ContentPage pageExists = (ContentPage) (locator.findByUniqueName(pageBean.getPageName()));
			if (pageExists != null)
			{
				return pageBean;
			}

			ContentNodeCopyCreationContext<ContentNode> origContext = creationContextBuilderFactory.newContentNodeCopyCreationContext(templatePage, contentModel, model, UniqueNameStrategy.NULL,contentParentPage);
			TemplatingCreationContext templateContext = creationContextBuilderFactory.newTemplatingCreationContext(true);
			CreationContext creationContext= creationContextBuilderFactory.combine(origContext,templateContext);
			
			ContentNode targetPage=null;
			if(templatePage instanceof StaticContentPage)
			{
				targetPage = (ContentNode)contentModelController.create(StaticContentPage.class, creationContext);
			}
			else if (templatePage instanceof ContentLabel)
			{
				targetPage = (ContentNode)contentModelController.create(ContentLabel.class, creationContext);
			}
				
			// Set page name
			if (targetPage instanceof ModifiableLocalized) {
				((ModifiableLocalized) targetPage).setTitle(Locale.ENGLISH, pageBean.getPageName());
			}

			// Set page markup
			if (targetPage instanceof ModifiableMarkupCapable) {
				MarkupList markupList = null;
				if (markupListHome != null) {
					markupList = markupListHome.getMarkupListProvider().getMarkupList();
				}
				Markup markup = markupList.getByName("html");
				((ModifiableMarkupCapable) targetPage).addMarkup(markup);
			}

			// Set page unique name
			if (targetPage instanceof ModifiableIdentifiable) {

				ModifiableObjectID mOID = ((ModifiableIdentifiable) targetPage).getModifiableObjectID();
				mOID.setUniqueName(pageBean.getPageName());
			}

			// friendly URLs
			String friendlyURL = pageBean.getUrl();
			if (targetPage instanceof ModifiableMetaDataProvider && friendlyURL != null) {

				ModifiableMetaData modifiableMetaData = ((ModifiableMetaDataProvider) targetPage)
						.getModifiableMetaData();
				modifiableMetaData.setValue(com.ibm.portal.resolver.friendly.Constants.FRIENDLY_NAME_KEY, friendlyURL);
			}

			contentModelController.insert(targetPage, contentParentPage, null);
			contentModelController.commit();
			contentModelController.dispose();
			contentModelController = home.getContentModelControllerProvider()
					.createContentModelController(contentModel);

		} catch (NamingException | PortletServiceUnavailableException | ModelException ex) {
			ex.printStackTrace();
			System.out.println(ex);
		} finally {
			contentModelController.dispose();
		}

		return pageBean;

	}


}
