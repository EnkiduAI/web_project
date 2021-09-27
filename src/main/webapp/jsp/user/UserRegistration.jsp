<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="../header/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="mb-3">
                <h3>Registration form</h3>
            </div>
            <form accept="" class="shadow p-4">                  
                <div class="mb-3">
                    <label for="username">Email</label>
                    <input type="email" class="form-control" name="email" id="email" placeholder="email">
                </div>
                
                <div class="mb-3">
                    <label for="username">Name</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="Name">
                </div>
                
                <div class="mb-3">
                    <label for="username">Surname</label>
                    <input type="text" class="form-control" name="surname" id="surname" placeholder="Surname">
                </div>
                
                <div class="mb-3">
                    <label for="username">Login</label>
                    <input type="text" class="form-control" name="login" id="login" placeholder="Login">
                </div>

                <div class="mb-3">
                    <label for="Password">Password</label>
                    <input type="password" class="form-control" name="password" id="Password" placeholder="Password">
                </div>
                
                <div class="mb-3">
                    <label for="username">Phone</label>
                    <input type="text" class="form-control" name="phone" id="phone" placeholder="Phone">
                </div>

                <div class="mb-3">
                    <button type="submit" class="btn btn-primary">SignUp</button>
                </div>

                <hr>
                
            </form>
        </div>
    </div>
</div>
</body>
</html>