<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Events</title>
</head>
<body>
<h1>Events:</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>

<!-- New event -->
<form action="/event/open-for-save" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Add event">
</form>

<!-- Table of events  -->
<table border="1">
    <tr>
        <th>id</th>
        <th>franchise</th>
        <th>organizer</th>
        <th>name</th>
        <th>data from</th>
        <th>data to</th>
        <th>country</th>
        <th>venue</th>
        <th>url</th>
        <th>comment</th>
        <th>create date</th>
        <th>modify date</th>
        <th>record status</th>
    </tr>
    <c:forEach items="${events}" var="event">
        <form action="/event/open-for-edit" method="get">

                <tr>
                    <td><c:out value="${event.id}"/></td>
                    <td><c:out value="${event.franchise}"/></td>
                    <td><c:out value="${event.organizer}"/></td>
                    <td><c:out value="${event.name}"/></td>
                    <td><c:out value="${event.dateFrom}"/></td>
                    <td><c:out value="${event.dateTo}"/></td>
                    <td><c:out value="${event.country}"/></td>
                    <td><c:out value="${event.venue}"/></td>
                    <td><c:out value="${event.url}"/></td>
                    <td><c:out value="${event.comment}"/></td>
                    <td><c:out value="${event.createDate}"/></td>
                    <td><c:out value="${event.modifyDate}"/></td>
                    <td><c:out value="${event.recordStatus}"/></td>
                    <td><input hidden="true" name="userId" value="${user.id}">
                    </td>
                    <td><input hidden="true" name="eventId" value="${event.id}">
                    </td>
                    <td><input type="submit" value="Edit"></td>

                </tr>
        </form>
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