<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@include file="header/header.jsp" %>
    <fmt:setLocale value="${sessionScope.currentLocale}" scope = "session"/>
 	 <fmt:setBundle basename="${sessionScope.currentBundle}" var="rb"/> 
 <fmt:message key="main.title" bundle="${rb}" var="title"/>
 <fmt:message key="main.wanted" bundle="${rb}" var="wanted"/>
 <fmt:message key="main.description" bundle="${rb}" var="description"/>

 <!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.css"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">${wanted}</h1>
        <p class="lead text-muted">${description}</p>
<div class="container">
  <div class="row justify-content-center">
    <div class="col-12" >
		<table class="table table-borderless-xl">
		  <thead>
		    <tr>
		      <th scope="col">Photo</th>
		      <th scope="col">Name</th>
		      <th scope="col">Surname</th>
		      <th scope="col">Traits</th>
		      <th scope="col">Weight</th>
		      <th scope="col">Height</th>
		      <th scope="col">Description</th>
		      <th scope="col">Reward</th>
		      <th scope="col">Expiration date</th>
		    </tr>
		  </thead>
		  <c:forEach items="${applicationList}" var="unposted">
                        <tr>
                            <td><img id="wanted_image" src="image/wanted/${unposted.getPhoto()}" width="70" height="70"/></td>
                            <td>${unposted.getName()}</td>
                            <td>${unposted.getSurname()}</td>
                            <td>${unposted.getTraits()}</td>
                            <td>${unposted.getWeight()}</td>
                            <td>${unposted.getHeight()}</td>
                            <td>${unposted.getDescription()}</td>
                            <td>${unposted.getReward()}</td>
                            <td>${unposted.getExpirationDate()}</td>
                        </tr>
                    </c:forEach>
		  </table>
		  </div>
		  </div>
		  </div>        
      </div>
    </div>
  </section>
</body>
</html>