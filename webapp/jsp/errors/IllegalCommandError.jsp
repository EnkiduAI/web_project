<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>
<h1>Opps...</h1>
        <table border="1">
        <tr valign="top">
            <td width="40%"><strong>Error:</strong></td>
   <td><p>${ pageContext.exception }</p></td></tr> </table>
</body>
</html>