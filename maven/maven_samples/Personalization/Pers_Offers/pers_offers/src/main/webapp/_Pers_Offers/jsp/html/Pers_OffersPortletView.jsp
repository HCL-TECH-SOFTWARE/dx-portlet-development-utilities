<%@ page session="false" contentType="text/html" import="java.util.*, pers_offers.*"%>
<%@ taglib uri="/WEB-INF/tld/portlet.tld" prefix="portletAPI" %>
<portletAPI:init/>
<jsp:useBean class="pers_offers.Pzn_offersSpot" id="offersSpot"></jsp:useBean>
<jsp:useBean class="pers_offers.Per_Offers_UserSpot" id="userSpot"></jsp:useBean>

<%
offersSpot.setRequest(request);
userSpot.setRequest(request);
%>

<DIV style="margin: 6px">
<H3 style="margin-bottom: 3px">Welcome to Personalized Offers!</H3></DIV>
<BR>
Here is a personalized offer:
<BR>
<%
String title = new String();
String details = new String();
try{
		if (offersSpot.getRuleContent()!=null && offersSpot.getRuleContent().length > 0)
		{
		   title = offersSpot.getRuleContent()[0].getTitle();
		   details = offersSpot.getRuleContent()[0].getDetails();
		}
		
	}catch(Throwable e)
	{
		e.printStackTrace();
	}
 %>
<%=title%>

<br>

<%=details%>

<HR>  
Here are all your personalized offers:    
<br>    
<%    
try {    
		pers_offers.Pzn_offers[] items = offersSpot.getRuleContent();    
        pers_offers.Pzn_offers item = items[0]; // throws an exception if empty. %>    
        <TABLE WIDTH="100%">    
        <TBODY>    
        <%    
			for (int i = 0, c = 0; ; ) 
            {    
            	if (c == 0) { %>    
					<TR bgcolor="e7e7e7"><%        
				}    
                else { %>    
                <TR><%        
            } %>    
         <TD>
         <%= item.getTitle() %>: <%= item.getDetails() %>             
         </TD>    
         </TR>
         <%    
         c = c == 0 ? 1 : 0;
         i++;    
         try {    
                 item = items[i];    
             }    
         	 catch (java.lang.ArrayIndexOutOfBoundsException _e0) 
             {    
            	 break;    
        	 }     	
         } 
         %>    
     	</TBODY>
     	</TABLE><%    
    }    
    catch (Throwable _e0) { _e0.printStackTrace();%>     
    <FONT>No rule content found. </FONT>  
<%    
}%>