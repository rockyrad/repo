<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>


<!--META-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <title>Login Form</title> -->

<!--STYLESHEETS-->
<link href="css/style.css" rel="stylesheet" type="text/css" />

<!--SCRIPTS-->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<!--Slider-in icons-->

 <script type="text/javascript">
 
function validate(){
	var email=document.loginform.userid.value;
	if(email=="")
	{
		alert("Please Enter Email");
		document.loginform.userid.focus();
		return false;
	}

	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
	if(!email.match(mailformat))
	{
		alert("Enter only Valid Mail, Ex: smith@highradius.com");
		document.loginform.userid.focus();
		return false;
	}
	
	if (document.loginform.pwd.value == ""){
		alert ( "Please enter password" );
		document.loginform.pwd.focus();

		return false;
		}
	alert ( "Welcome User" );
return true;
}
</script>


<script type="text/javascript">
$(document).ready(function() {
	$(".username").focus(function() {
		$(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
		$(".user-icon").css("left","0px");
	});
	
	$(".password").focus(function() {
		$(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
		$(".pass-icon").css("left","0px");
	});
});
</script>

</head>
<body>

<!--WRAPPER-->
<div id="wrapper">
<!-- <center><font size=7>HR Casa</font></center> -->

	<!--SLIDE-IN ICONS-->
    <div class="user-icon"></div>
    <div class="pass-icon"></div>
    <!--END SLIDE-IN ICONS-->

<!--LOGIN FORM-->

<form name="loginform" class="login-form" onsubmit="return validate()" action="login" method="post">

	<!--HEADER-->
    <div class="header">
    <!--TITLE-->
    <!-- <h1>Login Form</h1> -->
    <!--END TITLE-->
    <!--DESCRIPTION-->
    <!-- <span>A photo uploading tool</span> -->
    <!--END DESCRIPTION-->
    </div>
    <!--END HEADER-->
	
	
	<!--CONTENT-->
    <div class="content">
    
	<!--USERNAME--><input name="userid" type="text" class="input username" value="Emailid" onfocus="this.value=''" /><!--END USERNAME-->
    <!--PASSWORD--><input name="pwd" type="password" class="input password" value="Password" onfocus="this.value=''" /><!--END PASSWORD-->
    </div>
    <!--END CONTENT-->
    <%
		String message = request.getParameter("message");
		if (message != null && message.equalsIgnoreCase("fail")) 
		{
			out.println("<td colspan='2'><font color='red'>Invalid UserID/Password  </font>   </td>");
		} 
		
		
	%>
    <!--FOOTER-->
    <div class="footer">
    <!--LOGIN BUTTON--><input type="submit" name="submit" value="Login" class="button" /><!--END LOGIN BUTTON-->
    <!--REGISTER BUTTON--><input type="button" name="register" value="Register" class="register" onclick="document.location='registration.jsp'"/><!--END REGISTER BUTTON-->
    </div>
    <!--END FOOTER-->

</form>
<!--END LOGIN FORM-->

</div>
<!--END WRAPPER-->

<!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

</body>
</html>