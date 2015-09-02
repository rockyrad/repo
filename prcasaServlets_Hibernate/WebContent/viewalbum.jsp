<html>
<head>
<title></title>
</head>
<body>


	<input type="button" value="Logout" name="logout" onclick="document.location = 'HomePage.jsp'">
	<a href="logout.jsp" style="float: right">Logout</a>
	<form name="albumform" method="POST" enctype="multipart/form-data"
		action="upload">
		<table align="center" border="1" style="width: 100px; height: 50px;">
		<tr>
		<td>
		Image
		</td>
		<td>
		Name
		</td>
		</tr>
			<tr>
				<%@ page language="java" import="com.highradius.model.Photo,java.util.Iterator,java.util.List"%>

				<%
					String userid = (String) session.getAttribute("userid");
							int albumid = Integer.parseInt(request.getParameter("albumid"));
							session.setAttribute("albumid", albumid);

							if (userid == null) {
								System.out
										.print("session has expired .Redirected to login page");
								response.sendRedirect("index.jsp");
							}
							int number_of_photos = 0;

							List<Photo> photoslist = (List<Photo>) session
									.getAttribute("photos");

							number_of_photos = photoslist.size();

							//System.out.println(photoslist.size());
							Photo photo = new Photo();

							Iterator<Photo> iterator = photoslist.iterator();

							while (iterator.hasNext()) {
								photo = (Photo) iterator.next();
								photo.getPhotoId();
								photo.getAlbumId();
								photo.getPhotoName();
				%>
				<td align="center"><img
					src="/photos/<%=photo.getAlbumId()%>/<%=photo.getPhotoName()%>"
					alt="<%=photo.getPhotoName()%>" border="2" height="80" width="80">
				</td>
				<td>id:&nbsp;&nbsp;&nbsp;&nbsp;<font size="2"><%=photo.getAlbumId()%> <br/><%=photo.getPhotoName()%></font></td>
			</tr>
			<%
				}
			%>


		</table>
		<%
		if(number_of_photos<5)
		{
		out.println("You Can upload some more files");
		
			for (int i = 1; i <= 5 - number_of_photos; i++) {
		%>
		<p align="center">
			<input type="file" name="f1" size="20">
		</p>
		<%
			}
		%>
		<p align="center">
			<input type="submit" value="Upload" name="upload">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

		</p>
<%} %>
<br/><br/>
<br/><br/>
			<center>
			<input type="button" value="HomePage" name="cancel" onclick="document.location = 'HomePage.jsp'">
	</center>
	</form>
</body>
</html>

