/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited 2022                           */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.hcl.dx.sample.pages;

public class PageBean {

	public PageBean(String pageName, Long level, String url, String parentPage) {
		super();
		this.pageName = pageName;
		this.level = level;
		this.url = url;
		this.parentPage = parentPage;
	}

	@Override
	public String toString() {
		return "pageName: "+pageName+" level: "+level+" url: "+url+" parentPage: "+parentPage;
	}

	private String pageName;
	
	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private Long level;
	
	private String url;
	
	private String parentPage;

	public String getParentPage() {
		return parentPage;
	}

	public void setParentPage(String parentPage) {
		this.parentPage = parentPage;
	}
}
