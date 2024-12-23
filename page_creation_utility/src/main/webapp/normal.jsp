<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ page session="false" contentType="text/html" import="java.util.*,javax.portlet.*" %>
<portletAPI:defineObjects/>

<form action="<portlet:actionURL/>" method="post">
	<table>
	  <tr>
	  	<td>Parent unique name</td>
	    <td><input type="text" id="parentUniqueName" name="parentUniqueName"></td>
	  </tr>
	  <tr>
	    <td>Content uuid*</td>
	    <td><input type="text" id="contentUUID" name="contentUUID"></td>
	  </tr>
	  <tr>
	    <td>Page name</td>
	    <td><input type="text" id="pageName" name="pageName"></td>
	  </tr>
	  <tr>
	    <td>Friendly URL</td>
	    <td><input type="text" id="friendlyUrl" name="friendlyUrl"></td>
	  </tr>
	  <tr>
	    <td>Page Template Unique name*</td>
	    <td><input type="text" id="templateUniqueName" name="templateUniqueName"></td>
	  </tr>
	</table>
	<input type="hidden" name="actionType" id="actionType" value="single-page">
	<input type="submit" type="submit" class="button" value="Click to generate a page">
</form>

<div>* denotes optional items.</div>

<%

String resultMessage = (String)request.getAttribute("Message");
if(resultMessage!=null)
{
%>
<div><h5><%=resultMessage%></h5></div>
<%
}
%>


<style>
.button {
    background-color: #1c87c9;
        border: none;
        color: white;
        padding: 20px 34px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 20px;
        margin: 4px 2px;
        cursor: pointer;
}

input[type="text"] {
	font-size: 12px;
	width: 200%;
	height: 25px;

}
</style>
