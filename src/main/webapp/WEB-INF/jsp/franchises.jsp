<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Franchises</title>
</head>
<body>

<h1>Franchises:</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<!-- New franchise -->
<form action="/franchise/open-for-save" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Add franchise">
</form>

<img src="${pageContext.request.contextPath}/images/oceanman.jpg"/>

<%--<script>--%>
<%--var img = new Image();--%>
<%--img.src = "/images/oceanman33.png";--%>
<%--document.getElementById('display').appendChild(img);--%>
<%--</script>--%>

<!-- Table of franchises  -->
<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>logo</th>
        <th>create date</th>
        <th>modify date</th>
        <th>record status</th>
    </tr>
    <c:forEach items="${franchises}" var="franchise">
        <form action="/franchise/open-for-edit" method="get">

                <tr>
                    <td><c:out value="${franchise.id}"/></td>
                    <td><c:out value="${franchise.name}"/></td>
                    <td><c:out value="${franchise.logo}"/></td>
                    <td><c:out value="${franchise.createDate}"/></td>
                    <td><c:out value="${franchise.modifyDate}"/></td>
                    <td><c:out value="${franchise.recordStatus}"/></td>
                    <td><input hidden="true" name="userId" value="${user.id}">
                    </td>
                    <td><input hidden="true" name="franchiseId" value="${franchise.id}">
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