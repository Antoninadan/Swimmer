<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin cabinet</title>
</head>
<body>

<c:if test="${message != null}">
    <h3> ${message} </h3>
</c:if>

<h2>Hello, ${user.name}!</h2>
<br>
<br>


<form action="/country/open" method="get">
    <input name="userId" value="${user.id}" hidden>
    <input type="submit" value="Open countries">
</form>

<br>
<a href="/franchises">Franchises</a>
<br>
<a href="/events">Counties</a>
<br>
<a href="/distances">Counties</a>

</body>
</html>