<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Franchise Page</title>
</head>
<body>
<h1>Create new franchise</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/franchise/save" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" size="30" name="name" required value="${franchise.name}"/>
    <br>
    <%--<label for="logo">Logo:</label>--%>
    <%--<input type="text" id="logo" size="30" name="name" required value="${franchise.logo}"/>--%>
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="franchiseId" value="${franchise.id}">
    <input type="submit" value="Save"/>
</form>

<br>
<br>
<br>
<!-- Back to franchises -->
<form action="/franchise/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to franchises">
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
