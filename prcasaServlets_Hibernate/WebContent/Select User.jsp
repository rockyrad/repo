<%-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type = "text/javascript">
function setUser(objDropDown)
{
    var objHidden = document.getElementById("pwd");
    objHidden.value = objDropDown.value; 
}
</script>
</head>
<body>
<%@ page language="java" import="java.sql.*"%>
<%

Connection con = null;

System.out.println("Registering Driver....");
try {
	String url = "jdbc:mysql://localhost:3306/hrcasa";
	//System.out.println(url);
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("Driver has been registered.\n");
	
	con = DriverManager.getConnection(url,"root","root");

	String query = "select create_user_id,password from user";

PreparedStatement pstmt = con.prepareStatement(query);

ResultSet rs = pstmt.executeQuery();


%>

<select name="users" class="input username" onchange ="setUser(this);" onfocus="this.value=''>

<%
while(rs.next())
{
%>
<option value="<%=rs.getString("password")%>" class="input username"><%=rs.getString("create_user_id")%></option>
<%	
}
%>
</select> 

<br/>
<br/>
password is
<input name="pwd" type="text" class="input username" value="">
	
	
<%
	
} catch (ClassNotFoundException e) {

	e.printStackTrace();
} catch (SQLException e) {

	e.printStackTrace();
} catch (Exception e) {

	e.printStackTrace();
}

finally {
    if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
    if (pstmt != null) try { pstmt.close(); } catch (SQLException ignore) {}
    if (con!= null) try { con.close(); } catch (SQLException ignore) {}
}

%>
</body>
</body>
</html> --%>