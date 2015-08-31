<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


 <script type="text/javascript">
 
function validate(){
	
	var username = document.regform.username.value;
	if (username == "") {
		alert("Please Enter User Name");
		document.regform.username.focus();
		return false;
	}

	var letters = /^[a-zA-Z]+$/;  
	if(!username.match(letters))
	{
		alert("User Name must contains Alphabets");
			document.regform.username.focus();
		return false;
	}
	if (document.regform.pwd.value == ""){
		alert ( "Please enter password" );
		document.regform.pwd.focus();

		return false;
		}
	if (document.regform.rpwd.value == ""){
		alert ( "Please re-enter password" );
		document.regform.rpwd.focus();

		return false;
		}
	if (document.regform.pwd.value != document.regform.rpwd.value){
		alert ( "Entered passwords must same" );
		
		return false;
		}
	var email=document.regform.userid.value;
	if(email=="")
	{
		alert("Please Enter Email");
		document.regform.userid.focus();
		return false;
	}

	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
	if(!email.match(mailformat))
	{
		alert("Enter only Valid Mail, Ex: smith@highradius.com");
		document.regform.userid.focus();
		return false;
	}
	alert ( "Welcome User" );
return true;
}
</script>

<body>
<br/>
<br/>
<br/>
<center><font size="5">Registration</font></center>
<form name="regform" onsubmit="return validate()" action="registration" >
	

        <center>
   	<table border="0">
				<tr>
					<td>UserName </td>
					<td> <input type="text" name="username" value="" /></td>
				</tr>

				<tr>
					<td>Password </td>
					<td> <input type="password" name="pwd" value="" /></td>
				</tr>
				<tr>
					<td>Re-enter password</td>
					<td> <input type="password" name="rpwd" value="" /></td>
				</tr>
			<tr>
				<td>Email-Id</td>
					<td> <input type="text" name="userid" value="" /></td>
			</tr>
			
			<tr>
				<td><input type="submit" value="submit" /></td>
				<td>
				      <input type="reset" value="reset" />
				</td>
			</tr>
	</table>
	<a href = "index.jsp">Go to Login page</a>
		</center>
      </form>
</body>
</html>