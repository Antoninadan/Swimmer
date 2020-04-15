<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h1>Create new country</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/country/save" method="post">
    Name: <input type="text" size="30" name="name" required value="${country.name}"/>
    <br>
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="countryId" value="${country.id}">
    <input type="submit" value="Save"/>
</form>

<br>
<br>
<br>
<!-- Back to countries -->
<form action="/country/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to countries">
</form>
<br>
<br>
<br>
<!-- Back to cabinet -->
<form action="/user/admin-cabinet" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to cabinet">
</form>


</body>
</html>
