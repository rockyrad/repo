<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
			
			String userid = (String) session.getAttribute("userid");
			int albumid = Integer.parseInt(request.getParameter("albumid"));
			session.setAttribute("albumid", albumid);

			if (userid == null) {
				System.out
						.print("session has expired .Redirected to login page");
				response.sendRedirect("index.jsp");
			}
			
			
			
			%>
			<br/><br/><br/><br/>

			<center>
			<font size="4">Uploading file should not exceed 500kb
			Please upload files which are less than 500kb</font></br></br></br></br>
			<input type="button" value="View Album" name="viewalbum" onclick = document.location="homepage">
			</center>
			
</body>
</html>