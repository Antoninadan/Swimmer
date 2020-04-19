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
    <input type="number" name="swimmerId" min="1"/>
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
    <c:forEach items="${events}" var="event">
        <tr>
            <td><c:out value="${result.id}"/></td>
            <td><c:out value="${result.event}"/></td>
            <td><c:out value="${result.distance}"/></td>
            <td><c:out value="${result.timeInSecond}"/></td>
            <td><c:out value="${result.comment}"/></td>
            <td><c:out value="${result.createDate}"/></td>
            <td><c:out value="${result.modifyDate}"/></td>
            <td><c:out value="${result.recordStatus}"/></td>
            <%--<td><input hidden="true" name="userId" value="${user.id}">--%>
            <%--</td>--%>
            <%--<td><input hidden="true" name="eventId" value="${event.id}">--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<form action="/event/open-for-edit" method="get">--%>
                    <%--<input hidden="true" name="userId" value="${user.id}">--%>
                    <%--<input hidden="true" name="eventId" value="${event.id}">--%>
                    <%--<input type="submit" value="Edit">--%>
                <%--</form>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<form action="/event/delete" method="post">--%>
                    <%--<input hidden="true" name="userId" value="${user.id}">--%>
                    <%--<input hidden="true" name="eventId" value="${event.id}">--%>
                    <%--<form action="/Config?pg=FIBiller&amp;cmd=delete">--%>
                        <%--<input type="submit" value="Delete"--%>
                               <%--onclick="return confirm('Are you sure you want to PERMANENTLY delete?')"/>--%>
                    <%--</form>--%>
                <%--</form>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<form action="/event/soft-delete" method="post">--%>
                    <%--<input hidden="true" name="userId" value="${user.id}">--%>
                    <%--<input hidden="true" name="eventId" value="${event.id}">--%>
                    <%--<form action="/Config?pg=FIBiller&amp;cmd=delete">--%>
                        <%--<input type="submit" value="SOFT delete"--%>
                               <%--onclick="return confirm('Are you sure you want to SOFT delete?')"/>--%>
                    <%--</form>--%>
                <%--</form>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<form action="/distance/open-all" method="get">--%>
                    <%--<input hidden="true" name="userId" value="${user.id}">--%>
                    <%--<input hidden="true" name="eventId" value="${event.id}">--%>
                    <%--<input type="submit" value="Distances">--%>
                <%--</form>--%>
            <%--</td>--%>
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