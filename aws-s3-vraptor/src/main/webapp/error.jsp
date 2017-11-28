<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    isErrorPage="true"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
    <h2>
        Error: ${pageContext.exception}  <br/>
    </h2>
 
    Exception stack trace:<br/>
     
    <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
        ${trace}<br/>
    </c:forEach> 
 
</body>
</html>