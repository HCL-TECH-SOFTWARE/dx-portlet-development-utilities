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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

/**
 *
 * A sample portlet preferences validator
 * 
 */
public class JSPDemoPortletPreferencesValidator implements PreferencesValidator {

	/**
	 * Validate portlet preferences
	 * 
	 * @see javax.portlet.PreferencesValidator#validate(javax.portlet.PortletPreferences)
	 */
	public void validate(PortletPreferences preferences) throws ValidatorException 	{
		final Collection<String> failedKeys = new ArrayList<String>();
		for( Enumeration<?> names=preferences.getNames(); names.hasMoreElements(); ) {
			String name = names.nextElement().toString();
			if( !name.startsWith("url.") ) continue;
			final String value = preferences.getValue(name, "");
			//validates that the preferences do not start or end with white space
			//validates that the preferences start with http: or https:
			if( !value.equalsIgnoreCase(value.trim()) || !(value.startsWith("http:")||value.startsWith("https:")) ) {
				failedKeys.add(name);
			}
		}
		if( !failedKeys.isEmpty() ) {
			throw new ValidatorException("One or more preferences do not comply with the validation criteria",failedKeys);
		}
	}
}
