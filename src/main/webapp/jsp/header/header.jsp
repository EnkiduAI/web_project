<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="${sessionScope.currentLocale}" scope = "session"/>
    <fmt:setBundle basename="${sessionScope.currentBundle}" var="rb"/> 
 <fmt:message key="main.title" bundle="${rb}" var="title"/>
 <fmt:message key="main.login" bundle="${rb}" var="login"/>
 <fmt:message key="main.button" bundle="${rb}" var="main"/>
 <fmt:message key="main.contacts" bundle="${rb}" var="contacts"/>
 <fmt:message key="main.admin" bundle="${rb}" var="admin"/>
 <fmt:message key="main.applicant" bundle="${rb}" var="applicant"/>
 <fmt:message key="main.user" bundle="${rb}" var="user"/>
 <fmt:message key="main.registration" bundle="${rb}" var="registration"/>
<html>
<head>
<title>${title}</title>
<link rel="stylesheet" href="css/bootstrap.css"/>
<script type="text/javascript" src='js/bootstrap.min.js'></script>
</head>
<body>

<nav class="navbar navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand" href="#">
      <img src="image/logo.png" width="70" height="70">
    </a>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
  <button class="btn btn-primary me-md-2" type="button" onclick="${pageContext.request.contextPath}/jsp/mainPage.jsp">${main}</button>
  <button class="btn btn-primary" type="button">${contacts}</button>
  <div class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    ${login}
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=ADMIN_LOGIN">${admin}</a></li>
    <li><a class="dropdown-item" href="#">${user}</a></li>
    <li><a class="dropdown-item" href="#">${applicant}</a></li>
  </ul>
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    ${registration}
  </button>
  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=USER_REGISTRATION">${user}</a></li>
    <li><a class="dropdown-item" href="#">${applicant}</a></li>
  </ul>
  </div>
  <div class="header-right">
    	<form name="header" method="post" id="localeForm" action="${pageContext.request.contextPath}/controller">
		<input type="hidden" name="command" value="LANGUAGE_CHANGE"/>
		<input type="hidden" name="current_page" value="${pageContext.request.requestURL}">
			<select class="form-select form-select-sm" aria-label=".form-select-sm example" name="language" onChange="this.form.submit()">
			<option value="${sessionScope.currentLocale}">${sessionScope.currentLocale}</option>
			<option value="${sessionScope.secondLocale}">${sessionScope.secondLocale}</option>
			</select>
			
		</form>
		</div>
</div>
</div>
</nav>
    
		
</body>
</html>