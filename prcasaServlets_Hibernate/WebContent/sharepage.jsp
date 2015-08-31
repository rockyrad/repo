<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<form name="shareform" action="sharealbum" method = "POST">
<%@ page language="java" import="java.sql.*"%>
<% 
String userid = (String)session.getAttribute("userid");

if(userid==null)
{
	response.sendRedirect("index.jsp");
}


String albumid = request.getParameter("albumid");
System.out.print(albumid);
session.setAttribute("albumid", albumid);

System.out.println("albumid :"+albumid);

Connection con = null;

System.out.println("Registering Driver....");
try {
	String url = "jdbc:mysql://localhost:3306/pradeep";
	//System.out.println(url);
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("Driver has been registered.\n");
	
con = DriverManager.getConnection(url,"root","");

String query = "select user_id from user";

PreparedStatement pstmt = con.prepareStatement(query);

ResultSet rs = pstmt.executeQuery();

%>
<font size="4">Select User id with whom you want to share</font>
<select name="shareduser" >
<%

while(rs.next())
{
%>
<br/>
<br/>
<br/>
<br/>
<center>





<option value="<%=rs.getString("user_id")%>"><%=rs.getString("user_id")%></option>
<%
}
}
catch(Exception e){
System.out.print("problem in sharepage");
}
%>
</select> 


<br/><br/><!-- <input type = "text" name ="shareduser" value="" > -->
<br/><br/>
<input type="submit" value="Submit" name="submit">
</center>
</form>
</body>
</html>




