

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Photo Album</title>
</head>

<body>
<%@ page language = "java" import="java.io.File,com.highradius.HrCasa"%>

<%
int albumid = Integer.parseInt(request.getParameter("albumid"));
String userid=(String)session.getAttribute("userid");
session.setAttribute("albumid", albumid);

if(userid==null)
{
	System.out.print("session has expired .Redirected to login page");
	response.sendRedirect("index.jsp");
}

out.println("<center><font size=\"4\">Delete action is "+request.getParameter("message")+"</font></center>");
%>
		
<center>
<input type="button" name="delete" value="Homepage"  onclick="document.location='homepage'">
</center>