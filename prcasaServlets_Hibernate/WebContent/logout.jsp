<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

if(session != null)  
{  
	     session.invalidate();  
 	     response.setHeader("Cache-Control","no-cache");
         response.sendRedirect("index.jsp"); // No logged-in user found, so redirect to login page.
         response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         
}

%>
</body>
</html>