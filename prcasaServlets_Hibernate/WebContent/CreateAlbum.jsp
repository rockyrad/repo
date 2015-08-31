

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Photo Album</title>
</head>

<body>

<%

String userid=(String)session.getAttribute("userid");
int albumid = Integer.parseInt(request.getParameter("albumid"));
session.setAttribute("albumid", albumid);

if(userid==null)
{
	System.out.print("session has expired .Redirected to login page");
	response.sendRedirect("index.jsp");
}

%>

<form name = "albumform" method="POST" enctype="multipart/form-data" action="upload">
<input type="button" value="Logout" style="float:right" name="logout" onclick = "document.location = 'logout.jsp'">
	<p>&nbsp;</p>
	<p align="center">
	<p align="center">Upload your photos</p>
	<p align="center"><input type="file" name="f1" size="20"></p>
	<p align="center"><input type="file" name="f2" size="20"></p>
	<p align="center"><input type="file" name="f3" size="20"></p>
	<p align="center"><input type="file" name="f4" size="20"></p>
	<p align="center"><input type="file" name="f5" size="20"></p>
	<p>&nbsp;</p>
	<p align="center"><input type="submit" value="Upload" name="upload">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="Cancel" name="cancel" onclick = "document.location = 'HomePage.jsp'"></p>
</form>

</body>

</html>

