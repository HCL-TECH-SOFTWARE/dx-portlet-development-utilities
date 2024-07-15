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

/**
 *
 * A sample Java bean that stores portlet instance data in portlet session.
 *
 */
public class JSPDemoPortletSessionBean {
	
	/**
	 * Last text for the text form
	 */
	private String formText = "";

	/**
	 * Set last text for the text form.
	 * 
	 * @param formText last text for the text form.
	 */
	public void setFormText(String formText) {
		this.formText = formText;
	}

	/**
	 * Get last text for the text form.
	 * 
	 * @return last text for the text form
	 */
	public String getFormText() {
		return this.formText;
	}

	/**
	 * Start position
	 */
	private int startPosition = 0;

	/**
	 * Get start position
	 * 
	 * @return Start position
	 */
	public int getStartPosition() {
		return startPosition;
	}

	/**
	 * Set start position
	 * 
	 * @param startPosition Start position
	 */
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

}
