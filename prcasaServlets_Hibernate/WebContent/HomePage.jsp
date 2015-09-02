<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body background="images/bg.png" >
<script>
function createFunction()
{
var x;

var album=prompt("Please enter an album name","");

if (album)
  {
  x="Your album name is : " + album;
  
  document.location="create?albumname="+album;
  
  document.getElementById("createdemo").innerHTML=x;
  }
  
else
	{
	x="No album has been created.";
	}
}
function deleteFunction()
{
var x;

var albumid=prompt("Please enter an album id","");

if (albumid)
  {
  x="Deleted albumid is : " + albumid;
  
  document.location="delete?albumid="+albumid;
  document.getElementById("deletedemo").innerHTML=x;
 	 var numbers = /^[0-9]+$/;  
	if(!albumid.match(numbers))  
	{  
		alert("Album ID must contains digits, Enter Only Digits");
		document.location="HomePage.jsp";
	}  
  
  }
  
else
	{
	x="No album has been created.";
	}
}
</script>

<center>		



<table border="1" cellpadding="1" cellspacing="1" style="width: 500px;" style="background-color:white;" BORDERCOLOR=BLACK>
			<tbody>
				<tr>
					<td>			
						<h1>
							<font color="080908">Home Page</font>
						</h1>
					</td>
				</tr>
				<tr>	
					<td>			
						
							<a href ="logout.jsp"style="float:right">Logout</a>
						
					</td>
				</tr>
					
				<tr>
					<td>
					<p id="createdemo"></p><button name="create" value="Create Album" style="float: right;" onclick="createFunction()">Create Album</button>
					<p id="deletedemo"></p><button name="delete" value="Delete Album" style="float: left;" onclick="deleteFunction()">Delete Album</button>					
					</td>
				</tr>
			</tbody>
</table>

Album that you can view and share
<table border="1" cellpadding="1" cellspacing="1" style="width: 500px;">
			<tbody>
				<tr>
					<td>Albums</td>
					<td>Ownership</td>
					<td>Date<br/>Created</td>
					<td>Sharing</td>
					
				</tr>
			</tbody>
</table>
<table border="1" cellpadding="1" cellspacing="1" style="width: 500px;">
			<tbody>

<%@ page language="java" import="com.highradius.model.Album,java.util.List,java.util.ArrayList"%>
<%@ page language="java" import="java.util.Iterator"%>
<% 
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
	System.out.print("session has expired .Redirected to login page");
	response.sendRedirect("index.jsp");
}
else{
List<Album> albumlist =(ArrayList)session.getAttribute("albumslist");

Iterator<Album> iterator = albumlist.iterator();
while(iterator.hasNext())
{
	Album album = new Album();
	album=iterator.next();
%>	
<tr>

	<td><a href = "viewalbum?albumid=<%= album.getAlbumId()%>"><%= album.getAlbumName()%></a></td>
	<td><font size="2"><%= album.getCreateUserId()%>
	</font></td>
	<td><font size="2"><%= album.getCreatetime()%></font></td>
	<td><input type="button" name="share" value="share" style="float: right;" onclick="document.location='sharepage.jsp?albumid=<%= album.getAlbumId()%>'"></td>
</tr>

<%
}
}
%>
</tbody>
</table>

</body>
</html>