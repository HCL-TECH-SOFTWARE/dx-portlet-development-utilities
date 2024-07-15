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

import com.ibm.websphere.personalization.*;
import pers_offers.Per_Offers_User;

public class Per_Offers_UserSpot extends RuleTrigger {

	public Per_Offers_UserSpot() {
		super();
	}
	
	public String getDisplayName() {
		return "Per_Offers_User spot";
	}
	
	public Per_Offers_User[] getRuleContent() throws Throwable {
		Object[] objArray = super.getRuleResults();
		if ((objArray != null) && (objArray.length > 0)) {
			Per_Offers_User[] tempArray = new Per_Offers_User[objArray.length];
			for (int i = 0; i < objArray.length; i++)
				tempArray[i] = (Per_Offers_User)objArray[i];
			return tempArray;
		}
 		return new Per_Offers_User[0];
	}
	
	public Per_Offers_User getRuleContent(int which) throws ArrayIndexOutOfBoundsException, Throwable {
		return (Per_Offers_User)super.getRuleResults(which);
	}
	
}
