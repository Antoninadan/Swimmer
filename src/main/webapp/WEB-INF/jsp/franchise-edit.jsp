<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Franchise Page</title>
</head>
<body>
<h1>Franchise edit form</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/franchise/delete" method="post">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="franchiseId" value="${franchise.id}">
    <form action="/Config?pg=FIBiller&amp;cmd=delete">
        <input type="submit" value="Delete this record"
               onclick="return confirm('Are you sure you want to delete?')" />
    </form>
</form>
<br>
<br>
<form action="/franchise/update" method="post">
    Name: <input type="text" size="30" name="name" required value="${franchise.name}"/>
    <br>
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="franchiseId" value="${franchise.id}">
    <br>
    <input type="submit" value="Update"/>
</form>

<%--Delete--%>
<br>
<br>
<br>
<br>
<!-- Back to franchises -->
<form action="/franchise/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to franchises">
</form>
<br>

<!-- Back to cabinet -->
<form action="/user/admin-cabinet" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to cabinet">
</form>


</body>
</html>
