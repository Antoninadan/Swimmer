<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Logined Page</title>
</head>
<body>

<c:if test="${message != null}">
    <h3> ${message} </h3>
</c:if>

<h2>Hello, ${user.name} ${user.sex}!!!</h2>
<br>
<br>




</body>
</html>