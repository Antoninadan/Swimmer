<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Distance create Page</title>
</head>
<body>
<h1>Create new distance</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/distance/save" method="post">
    <label for="distance_type">Distance type:</label>
    <select name="distanceType" id="distance_type">
        <c:forEach items="${distanceTypeList}" var="distanceType">
            <option value="${distanceType}">${distanceType}</option>
        </c:forEach>
    </select>
    <br>
    <label for="age_distance_type">Age:</label>
    <select name="ageDistanceType" id="age_distance_type">
        <c:forEach items="${ageDistanceTypeList}" var="ageDistanceType">
            <option value="${ageDistanceType}">${ageDistanceType}</option>
        </c:forEach>
    </select>
    <label for="length_in_meters">Length, m:</label>
    <input type="number" id="length_in_meters" size="30" name="lengthInMeters" min="1" value="${distance.lengthInMeters}" required/>
    <br>
    <label for="date">Date:</label>
    <input type="date" id="date" name="date" min="2020-01-01" value="${distance.date}" required/>
    <br>
    <label for="comment">Comment:</label>
    <input type="text" id="comment" size="30" name="comment" value="${distance.comment}"/>

    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="eventId" value="${event.id}">
    <input type="submit" value="Save"/>
</form>
<br>
<br>
<br>
<!-- Back to event edit -->
<form action="/event/event-edit" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to distances">
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
