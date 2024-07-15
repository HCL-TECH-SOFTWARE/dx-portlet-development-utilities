<%@page session="false" contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.portlet.*,com.hcl.dx.demo.*"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>                 
<%@taglib uri="http://www.ibm.com/xmlns/prod/websphere/portal/v8.0/portlet-client-model" prefix="portlet-client-model" %>        
<portlet:defineObjects/>
<portlet-client-model:init>
      <portlet-client-model:require module="ibm.portal.xml.*"/>
      <portlet-client-model:require module="ibm.portal.portlet.*"/>   
</portlet-client-model:init>         
        

<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">Welcome!</H3>
This is a sample edit mode page.  You have to edit this page to customize it for your own use.<BR>
<H3 style="margin-bottom: 3px">Available bookmarks</H3>
This is a sample form to edit portlet preferences.
<DIV style="margin: 12px; margin-bottom: 36px">
<% /******** Start of sample code ********/ %>
<FORM ACTION="<portlet:actionURL/>" METHOD="POST">
	<TABLE CELLPADDING=0 CELLSPACING=4>
	<TBODY>
		<TR>
			<TH><B>Name</B></TH>
			<TH><B>URL</B></TH>
			<TH></TH>
		</TR>
		<%
		PortletPreferences prefs = renderRequest.getPreferences();
		for( Enumeration prefNames=prefs.getNames(); prefNames.hasMoreElements(); ) {
			String name = prefNames.nextElement().toString();
			if( !name.startsWith("url.") ) continue;
			%>
			<TR>
				<TD><%=name.substring(4)%></TD>
				<TD><%=prefs.getValue(name,"<undefined>")%></TD>
				<TD><A HREF ='<portlet:actionURL><portlet:param name="<%=com.hcl.dx.demo.JSPDemoPortlet.PREF_RESET%>" value="<%=name%>"/></portlet:actionURL>'>reset</A></TD>
			</TR>
			<%
		}
		%>
		<TR>
			<TD><INPUT NAME="<%=com.hcl.dx.demo.JSPDemoPortlet.PREF_NAME%>" TYPE="text"></TD>
			<TD><INPUT NAME="<%=com.hcl.dx.demo.JSPDemoPortlet.PREF_VALUE%>" TYPE="text"></TD>
			<TD><INPUT NAME="<%=com.hcl.dx.demo.JSPDemoPortlet.PREF_SET%>" TYPE="submit" value="Set"></TD>
		</TR>
	</TBODY>
	</TABLE>
</FORM>

<%
	String errorMessage = renderRequest.getParameter(com.hcl.dx.demo.JSPDemoPortlet.ERROR_MESSAGE);
	if( errorMessage != null ) {
		%>
		<B><%=errorMessage%></B><BR>
		<%
		String errorKeys[] = renderRequest.getParameterValues(com.hcl.dx.demo.JSPDemoPortlet.ERROR_KEYS);
		if( errorKeys != null && errorKeys.length > 0 ) {
			%>
			<UL>
			<%
			for( int i=0; i<errorKeys.length; i++ ) {
				%>
				<LI><%=errorKeys[i]%></LI>
				<%
			}
			%>
			</UL>
			<%
		}
	}
%>

<% /******** End of sample code *********/ %>
</DIV>

<FORM ACTION='<portlet:renderURL portletMode="view"/>' METHOD="POST">
	<INPUT NAME="back" TYPE="submit" VALUE="Back to view mode">
</FORM>
</DIV>
