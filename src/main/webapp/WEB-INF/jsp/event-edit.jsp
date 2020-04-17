<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Event Page</title>
</head>
<body>
<h1>Event edit form</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/event/delete" method="post">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="eventId" value="${event.id}">
    <form action="/Config?pg=FIBiller&amp;cmd=delete">
        <input type="submit" value="PERMANENTLY delete this record"
               onclick="return confirm('Are you sure you want to PERMANENTLY delete?')"/>
    </form>
</form>
<form action="/event/soft-delete" method="post">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="eventId" value="${event.id}">
    <form action="/Config?pg=FIBiller&amp;cmd=delete">
        <input type="submit" value="SOFT delete this record"
               onclick="return confirm('Are you sure you want to SOFT delete?')"/>
    </form>
</form>
<br>
<br>
<form action="/event/update" method="post">
    ID:  <c:out value="${event.id}"/>
    <br>
    <label for="franchise">Franchise:</label>
    <select name="franchise" id="franchise">
        <c:forEach items="${franchiseList}" var="franchise">
            <option value="${franchise.id}"
                    <c:if test="${event.franchiseId eq franchise.id}">selected="${event.franchiseId}"</c:if>
            >
                    ${franchise.name}---record ${franchise.recordStatus}
            </option>
        </c:forEach>
    </select>
    <br>
    <label for="organizer">Organizer:</label>
    <input type="text" id="organizer" size="30" name="organizer" value="${event.organizer}" required/>
    <br>
    <label for="name">Name:</label>
    <input type="text" id="name" size="30" name="name" value="${event.name}" required/>
    <br>
    <label for="datefrom">Date from:</label>
    <input type="date" id="datefrom" name="date_from" min="2020-01-01" value="${event.dateFrom}" required/>
    <br>
    <label for="dateto">Date to:</label>
    <input type="date" id="dateto" name="date_to" min="2020-01-01" value="${event.dateTo}" required/>
    <br>
    <label for="country">Country:</label>
    <select name="country" id="country">
        <c:forEach items="${countryList}" var="country">
            <option value="${country.id}"
                    <c:if test="${event.countryId eq country.id}">selected="${event.countryId}"</c:if>
            >
                    ${country.name}---record ${country.recordStatus}
            </option>
        </c:forEach>
    </select>
    <br>
    <label for="venue">Venue:</label>
    <input type="text" id="venue" size="30" name="venue" value="${event.venue}" required/>
    <br>
    <label for="url">Url:</label>
    <input type="text" id="url" size="30" name="url" value="${event.url}"/>
    <br>
    <label for="comment">Comment:</label>
    <input type="text" id="comment" size="30" name="comment" value="${event.comment}"/>

    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="eventId" value="${event.id}">
    <br>
    <input type="submit" value="Update"/>
</form>
<br>
<br>
<!-- Table of distance  -->
<!-- New distance -->
<form action="/distance/open-for-save" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="eventId" value="${event.id}">
    <input type="submit" value="Add distance">
</form>
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
        <form action="/event/open-for-edit" method="get">

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
                <td><input hidden="true" name="userId" value="${user.id}">
                </td>
                <td><input hidden="true" name="distanceId" value="${distance.id}">
                </td>
                <td><input type="submit" value="Edit"></td>

            </tr>
        </form>
    </c:forEach>
</table>

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
