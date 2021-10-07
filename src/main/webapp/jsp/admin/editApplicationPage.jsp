<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
  <%@include file="../header/header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<title>Edit application</title>
</head>
<body>
<div class="container">
        <!-- edit form column -->
        <div class="col-lg-12 text-lg-center">
            <h2>Edit Application</h2>
            <br>
            <br>
        </div>
        <div class="col-lg-8 push-lg-4 personal-info">
        
        <div class="col-lg-4 pull-lg-8 text-xs-center">
        <div class="text-center">
	
</div>
                <img src="image/wanted/${currentApplication.getPhoto()}" class="m-x-auto img-fluid img-circle" alt="avatar" width="100px" height="100px"/>
                
        </div>
              <form role="form" method="post" action="controller">
              <input type="hidden" name="command" value="UPDATE_APPLICATION"/>
             
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label" name="">Name</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="name" value="${currentApplication.getName()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Surname</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="surname" value="${currentApplication.getSurname()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Traits</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="traits" value="${currentApplication.getTraits()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Height</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="height" value="${currentApplication.getHeight()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Weight</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="weight" value="${currentApplication.getWeight()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Description</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="description" value="${currentApplication.getDescription()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Reward</label>
                    <div class="col-lg-9">
                        <input class="form-control" type="text" name="reward" value="${currentApplication.getReward()}" />
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label">Expiration date</label>
                    <div class="col-lg-9">
                       <input type="date" id="start" name="expdate" value="${currentApplication.getExpirationDate()}"/>                      
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-lg-3 col-form-label form-control-label"></label>
                    <div class="col-lg-9">
                        <button type="reset" class="btn btn-secondary">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                        </div>
                        </form>
                        <input type="button" class="btn btn-primary" value="Set new Image" onclick="window.location.href='${pageContext.request.contextPath}/controller?command=SET_NEW_IMAGE'"/>
                    
                
            
        </div>
        
</div>
<hr />
</body>
</html>