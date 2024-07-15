<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.portlet.*,com.hcl.dx.demo.*" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>        
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v8.0/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init> 
<%
	com.hcl.dx.demo.JSPDemoPortletSessionBean sessionBean = (com.hcl.dx.demo.JSPDemoPortletSessionBean)renderRequest.getPortletSession().getAttribute(com.hcl.dx.demo.JSPDemoPortlet.SESSION_BEAN);
%>
 

<DIV style="margin: 6px">

<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample view mode page.  You have to edit this page to customize it for your own use.<BR>
<H3 style="margin-bottom: 3px">Order entry</H3>
This is a sample form to test action handling.

<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<%
	String formText = sessionBean.getFormText();
	if( formText.length()>0 ) {
		%>
		Order details for order id "<%=formText%>" should be displayed here.
		<%
	}
	%>
	<FORM method="POST" action="<portlet:actionURL/>">
		<LABEL  for="<%=com.hcl.dx.demo.JSPDemoPortlet.FORM_TEXT%>">Enter Order id:</LABEL><BR>
		<INPUT name="<%=com.hcl.dx.demo.JSPDemoPortlet.FORM_TEXT%>" type="text"/>
		<INPUT name="<%=com.hcl.dx.demo.JSPDemoPortlet.FORM_SUBMIT%>" type="submit" value="Submit"/>
	</FORM>
<% /******** End of sample code *********/ %>
</DIV>


<H3 style="margin-bottom: 3px">Available bookmarks</H3>
This is a sample to show portlet preferences.

<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<%
	PortletPreferences prefs = renderRequest.getPreferences();
	Enumeration prefNames = prefs.getNames();
	if (!prefNames.hasMoreElements()) { // no bookmarks
		%>
		No bookmarks available. Please use edit mode to add bookmarks.<BR>
		<%
	}
	else {
		int startPos = sessionBean.getStartPosition();
		int nextPos = 0;
		String _startPos = renderRequest.getParameter("startPos");
		if( _startPos != null ) {
			try {
				startPos = Integer.parseInt(_startPos);
			} catch (NumberFormatException nfe) {}
			sessionBean.setStartPosition(startPos);
		}
		while( prefNames.hasMoreElements() && nextPos < startPos+5 ) {
			String name = prefNames.nextElement().toString();
			if( !name.startsWith("url.") ) continue;
			if( nextPos >= startPos ) {
				%>
				<A HREF='<%=prefs.getValue(name,"<undefined>")%>'><%=name.substring(4)%></A><BR>
				<%
			}
			nextPos++;
		}
		%>
		<BR>
		<%
		if( startPos > 0 ) {
			%>
			<A HREF='<portlet:renderURL><portlet:param name="startPos" value="<%=String.valueOf(Math.max(0,startPos-5))%>"/></portlet:renderURL>'>[back]</A>&nbsp;
			<%
		}
		else {
			%>
			[back]&nbsp;
			<%
		}
		if( prefNames.hasMoreElements() ) {
			%>
			&nbsp;<A HREF='<portlet:renderURL><portlet:param name="startPos" value="<%=String.valueOf(nextPos)%>"/></portlet:renderURL>'>[next]</A>
			<%
		}
		else {
			%>
			&nbsp;[next]
			<%
		}
	}
%>
<% /******** End of sample code *********/ %>
</DIV>

</DIV>
