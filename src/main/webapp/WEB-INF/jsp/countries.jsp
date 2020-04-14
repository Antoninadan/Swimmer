<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Carts</title>
</head>
<body>
	<h2>Countries:</h2>

	<!-- Table of carts  -->
	<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>create date</th>
			<th>modify date</th>
			<th>record status</th>
		</tr>
		<c:forEach items="${countries}" var="country">
			<form action="/country/open" method="post">
			<tr>
				<td><c:out value="${country.id}" /></td>
				<td><c:out value="${country.name}" /></td>
				<td><c:out value="${country.createDate}" /></td>
				<td><c:out value="${country.modifyDate}" /></td>
				<td><c:out value="${country.recordStatus}" /></td>
				<td><input type="submit" value="open"></td>
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


	<!-- Back to cabinet -->
	<form action="/user/admin-cabinet" method="get">
		<input hidden="true" name="userId" value="${user.id}">
		<input type="submit" value="Back to cabinet">
	</form>

</body>
</html>