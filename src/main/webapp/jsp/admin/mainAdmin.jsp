<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@include file="../header/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>

<table class="table table-borderless-xl">
		  <thead>
		    <tr>
		    <th scope="col">Id</th>
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
		  <c:forEach items="${unpostedList}" var="application">
                        <tr >
                        	<td>${application.getApplicationId()}</td>
                            <td><img id="wanted_image" src="image/wanted/${application.getPhoto()}" width="70" height="70"/></td>
                            <td id="name">${application.getName()}</td>
                            <td id="surname">${application.getSurname()}</td>
                            <td id="traits">${application.getTraits()}</td>
                            <td id="weight">${application.getWeight()}</td>
                            <td id="height">${application.getHeight()}</td>
                            <td id="description">${application.getDescription()}</td>
                            <td id="reward">${application.getReward()}</td>
                            <td id="exdate">${application.getExpirationDate()}</td>
                            <td ><button type="button" class="btn btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/controller?command=EDIT_APPLICATION&applicationId=${application.getApplicationId()}'">Edit</button>   
                            
                            <button type="submit" class="btn btn-primary">Delete</button>
                                                        
                            </td>
                        </tr>
                    </c:forEach>
		  </table>
</body>
</html>