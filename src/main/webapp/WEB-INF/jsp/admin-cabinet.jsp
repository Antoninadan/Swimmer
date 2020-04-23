<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin cabinet</title>
</head>
<body>
<h1>Hello, ${user.name}!</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/country/open-all" method="get">
    <input name="userId" value="${user.id}" hidden>
    <input type="submit" value="Open countries">
</form>
<br>
<form action="/franchise/open-all" method="get">
    <input name="userId" value="${user.id}" hidden>
    <input type="submit" value="Open franchises">
</form>
<br>
<form action="/event/open-all" method="get">
    <input name="userId" value="${user.id}" hidden>
    <input type="submit" value="Open events">
</form>
<br>
<form action="/result/open" method="get">
    <input name="userId" value="${user.id}" hidden>
    <input type="submit" value="Results">
</form>
<br>
<form action="/file/open" method="get">
    <input type="submit" value="File">
</form>

</body>
</html>