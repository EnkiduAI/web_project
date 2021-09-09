<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <fmt:setLocale value="${sessionScope.currentLocale}" scope = "session"/>
 <fmt:setBundle basename="${sessionScope.currentBundle}" var="rb"/> 
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="pagecontent.title" bundle="${ rb }" /></title>

</head>
<body>
<form name = "loginForm" method = "POST" action = "controller">
<a href="${pageContext.request.contextPath}/controller?command=LANGUAGE_CHANGE&language=${sessionScope.currentLocale}"> ${sessionScope.secondLocale} </a>
<input type = "hidden" name = "command" value = "LOGIN"/>     

<h2 style="text-align: center;"><fmt:message key="pagecontent.loginAdmin" bundle="${ rb }"/></h2>

<p style="text-align: center;"><fmt:message key="pagecontent.name" bundle = "${ rb }"/>&nbsp; &nbsp; &nbsp; &nbsp;<input name="login" type="text" id="username"/></p>

<p style="text-align: center;"><fmt:message key="pagecontent.password" bundle = "${ rb }"/>&nbsp;<input name="password" type="password" id="password"/></p>

<p style="text-align: center;"><input name="submit" type="submit" value="Log In" /></p>
          <%--    <fmt:message key="pagecontent.name" bundle = "${ rb }"/>
             <input type="text" id="username" name="login">
             <br>
             <fmt:message key="pagecontent.password" bundle = "${ rb }"/>
             <input type="password" id="password" name="password">
             <br>
             <fmt:message key="pagecontent.submit" var="buttonValue" bundle = "${ rb }"/>
             <input type="submit" name="submit" value="${buttonValue}">
            --%>
         </form>
${errorLoginPasswordMessage}
<br/>
${wrongAction}
<br/>
${nullPage}

</body>
</html>