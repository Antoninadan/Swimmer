<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Country Page</title>
</head>
<body>
<h2>Country edit form</h2>
<c:if test="${message != null}">
    <h3> ${message} </h3>
</c:if>
<br>
<form action="/country/delete" method="post">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="countryId" value="${country.id}">
    <form action="/Config?pg=FIBiller&amp;cmd=delete">
        <input type="submit" value="Delete this record"
               onclick="return confirm('Are you sure you want to delete?')" />
    </form>
</form>
<br>
<br>
<form action="/country/update" method="post">
    Name: <input type="text" size="30" name="name" required value="${country.name}"/>
    <br>
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="countryId" value="${country.id}">
    <br>
    <input type="submit" value="Update"/>
</form>

<%--Delete--%>
<br>
<br>
<br>
<br>
<!-- Back to countries -->
<form action="/country/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to countries">
</form>
<br>

<!-- Back to cabinet -->
<form action="/user/admin-cabinet" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to cabinet">
</form>


</body>
</html>
