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
  <div class="row">
    <div class="col-12">
		<table class="table table-image">
		  <thead>
		    <tr>
		      <th scope="col">Day</th>
		      <th scope="col">Image</th>
		      <th scope="col">Article Name</th>
		      <th scope="col">Author</th>
		      <th scope="col">Words</th>
		      <th scope="col">Shares</th>
		    </tr>
		  </thead>
		  </table>
		  </div>
		  </div>
		  </div>        
      </div>
    </div>
  </section>
</body>
</html>