<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <fmt:setLocale value="${sessionScope.currentLocale}" scope = "session"/>
 <fmt:setBundle basename="${sessionScope.currentBundle}" var="rb"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<title>Login</title>
</head>
<body>
<form name="loginSelect" method="post" id="localeForm" action="<c:url value="/controller"/>">
<input type="hidden" name="command" value="LANGUAGE_CHANGE"/>
<select name="language" onChange="this.form.submit()">
<option value="${sessionScope.currentLocale}">${sessionScope.currentLocale}</option>
<option value="${sessionScope.secondLocale}">${sessionScope.secondLocale}</option>
</select>

</form>
 
<p>&nbsp;</p>
<p>&nbsp;</p>

<p style="text-align: center;"><fmt:message key="loginSelect.message" bundle="${ rb }"/></p>

<form name="adminSelect" method="post" action="controller">
<input type="hidden" name="command" value="GO_TO_ADMIN_MAIN_PAGE"/>
<p style="text-align: center;"><input name="submit" type="submit" value="<fmt:message key="loginSelect.adminButton" bundle="${ rb }"/>"/></p>
</form>


<p style="text-align: center;"><input name="submit" type="submit" value="<fmt:message key="loginSelect.applicantButton" bundle="${ rb }"/>" /></p>
<p style="text-align: center;"><input name="submit" type="submit" value="<fmt:message key="loginSelect.userButton" bundle="${ rb }"/>"/></p>
</body>
</html>