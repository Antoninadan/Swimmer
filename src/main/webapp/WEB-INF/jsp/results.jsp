<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h1>Results:</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>

<!-- Find user -->
<form action="/result/get-swimmer" method="get">
    <input type="number" name="swimmerId" min="1" value="${swimmer.id}" required/>
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Find">
</form>

<br>
Id: <c:out value="${swimmer.id}"/>
<br>
Login: <c:out value="${swimmer.login}"/>
<br>
Name: <c:out value="${swimmer.name}"/>
<br>
Sex: <c:out value="${swimmer.sex}"/>
<br>
Birth date: <c:out value="${swimmer.birthDate}"/>
<br>
<br>
<br>

<!-- Table of results  -->
<h2>Table of results:</h2>
<table border="1">
    <tr>
        <th>id</th>
        <th>event</th>
        <th>distance</th>
        <th>time, s</th>
        <th>comment</th>
        <th>create date</th>
        <th>modify date</th>
        <th>record status</th>
    </tr>
    <c:forEach items="${results}" var="result">
        <tr>
            <td><c:out value="${result.id}"/></td>
            <td><c:out value="${result.event}"/></td>
            <td><c:out value="${result.distance}"/></td>
            <td><c:out value="${result.timeInSeconds}"/></td>
            <td><c:out value="${result.comment}"/></td>
            <td><c:out value="${result.createDate}"/></td>
            <td><c:out value="${result.modifyDate}"/></td>
            <td><c:out value="${result.recordStatus}"/></td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<!-- Table of favorites  -->
<h2>Table of favorite events:</h2>
<table border="1">
    <tr>
        <th>id</th>
        <th>event</th>
        <th>create date</th>
        <th>modify date</th>
        <th>record status</th>
    </tr>
    <c:forEach items="${favorites}" var="favorite">
        <tr>
            <td><c:out value="${favorite.id}"/></td>
            <td><c:out value="${favorite.event}"/></td>
            <td><c:out value="${favorite.createDate}"/></td>
            <td><c:out value="${favorite.modifyDate}"/></td>
            <td><c:out value="${favorite.recordStatus}"/></td>
        </tr>
    </c:forEach>
</table>
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