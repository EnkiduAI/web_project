<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Log in</title>
</head>
<body>
<form name = "loginForm" method = "POST" action = "controller">
<input type = "hidden" name = "command" value = "login"/>
Login: <br/>
<input type  = "text" name = "login" value = ""/>
<br/>Password:<br/>
<input type = "password" name = "password" value = ""/>
<br/>
${errorLoginPasswordMessage}
<br/>
${wrongAction}
<br/>
${nullPage}
<br/>
<input type = "submit" value = "Confirm"/>
</form><hr/>
</body>
</html>