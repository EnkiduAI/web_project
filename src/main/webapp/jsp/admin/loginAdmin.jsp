<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  <%@include file="../header/header.jsp" %> 
 <fmt:setLocale value="${sessionScope.currentLocale}" scope = "session"/>
 <fmt:setBundle basename="${sessionScope.currentBundle}" var="rb"/> 
 <fmt:message key="pagecontent.loginAdmin" bundle="${rb}" var="loginPage"/>
 <fmt:message key="pagecontent.name" bundle="${rb}" var="name"/>
 <fmt:message key="pagecontent.password" bundle="${rb}" var="password"/>
 <fmt:message key="pagecontent.submit" bundle="${rb}" var="submit"/>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="pagecontent.title" bundle="${ rb }" /></title>
<link rel="stylesheet" href="css/bootstrap.min.css"/>

</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="mb-3">
                <h3>${loginPage}</h3>
            </div>
            <form class="shadow p-4" method="post" action="controller">
            <input type="hidden" name="command" value="LOGIN"/>                  
                <div class="mb-3">
                    <label for="username">${name}</label>
                    <input type="text" class="form-control" name="login" id="username" placeholder="${name}">
                </div>
                <div class="mb-3">
                    <label for="Password">${password}</label>
                    <input type="password" class="form-control" name="password" id="${password}" placeholder="${password}">
                </div>
                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">${submit}</button>
                </div>
                <hr>
            </form>
        </div>
    </div>
</div>
</body>
</html>