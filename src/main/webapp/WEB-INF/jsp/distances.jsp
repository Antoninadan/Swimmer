<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Distances</title>
</head>
<body>
<h1>Distances:</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
EventId: <c:out value="${eventId}"/>
<br>

<!-- New distance -->
<form action="/distance/open-for-save" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="eventId" value="${eventId}">
    <input type="submit" value="Add distance">
</form>

<!-- Table of distance  -->
<table border="1">
    <tr>
        <th>id</th>
        <th>distance type</th>
        <th>age</th>
        <th>length, m</th>
        <th>date</th>
        <th>comment</th>
        <th>create date</th>
        <th>modify date</th>
        <th>record status</th>
    </tr>
    <c:forEach items="${distances}" var="distance">
        <tr>
            <td><c:out value="${distance.id}"/></td>
            <td><c:out value="${distance.distanceType}"/></td>
            <td><c:out value="${distance.ageDistanceType}"/></td>
            <td><c:out value="${distance.lengthInMeters}"/></td>
            <td><c:out value="${distance.date}"/></td>
            <td><c:out value="${distance.comment}"/></td>
            <td><c:out value="${distance.createDate}"/></td>
            <td><c:out value="${distance.modifyDate}"/></td>
            <td><c:out value="${distance.recordStatus}"/></td>

            <td>
                <form action="/distance/open-for-edit" method="get">
            <input hidden="true" name="userId" value="${user.id}">
            <input hidden="true" name="eventId" value="${eventId}">
            <input hidden="true" name="distanceId" value="${distance.id}">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="/distance/delete" method="post">
                    <form action="/Config?pg=FIBiller&amp;cmd=delete">
                        <input hidden="true" name="userId" value="${user.id}">
                        <input hidden="true" name="eventId" value="${eventId}">
                        <input hidden="true" name="distanceId" value="${distance.id}">
                        <input type="submit" value="Delete"
                               onclick="return confirm('Are you sure you want to PERMANENTLY delete?')"/>
                    </form>
                </form>
            </td>
            <td>
                <form action="/distance/soft-delete" method="post">
                    <form action="/Config?pg=FIBiller&amp;cmd=delete">
                        <input hidden="true" name="userId" value="${user.id}">
                        <input hidden="true" name="eventId" value="${eventId}">
                        <input hidden="true" name="distanceId" value="${distance.id}">
                        <input type="submit" value="SOFT delete"
                               onclick="return confirm('Are you sure you want to SOFT delete?')"/>
                    </form>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<br>
<!-- Back to events -->
<form action="/event/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to events">
</form>
<br>


<!-- Back to cabinet -->
<form action="/user/admin-cabinet" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to cabinet">
</form>

</body>
</html>