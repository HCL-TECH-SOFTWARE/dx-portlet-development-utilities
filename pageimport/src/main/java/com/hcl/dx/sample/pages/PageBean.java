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
