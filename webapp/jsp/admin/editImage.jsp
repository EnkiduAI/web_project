<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../header/header.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Image</title>
</head>
<body>

<div class="section-1-container section-container">
    <div class="container">
        <div class="row">
            <div class="col section-1 section-description">              
        </div>
        <div class="row">
        
            <div class="col-10 offset-1 col-lg-8 offset-lg-2 div-wrapper d-flex justify-content-center align-items-center">
            <form action="upload" method="POST" enctype="multipart/form-data">
                <div class="div-to-align">
                   <img src="image/wanted/${currentApplication.getPhoto()}" class="m-x-auto img-fluid img-circle" alt="avatar" width="100px" height="100px"/>
                <h6 class="m-t-2">Upload a different photo</h6>
                <div class="custom-file">
  					<input type="file" class="custom-file-input" name="fileToUpload" id="customFileLang" lang="es">
  					<label class="custom-file-label" for="customFileLang">Choose file</label>
                </div>
                </div>
 				<button type="submit" class="btn btn-primary">Save</button>
 				   </form>  
            </div>            
        </div>
    </div>
</div>
</div>


           
                
                
</body>
</html>