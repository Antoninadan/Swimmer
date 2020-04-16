<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h2><c:out value="${message}" default="Please, input your credentials!"/></h2>
<br>
<a href="/">GO BACK</a>
<br>
<form action="/user/save" method="post">
    <label for="login">Login:</label>
    <input type="text" id="login" size="30" name="login" required/>
    <br>
    <label for="password">Password:</label>
    <input type="password" id="password" size="30" name="password" required/>
    <br>
    <label for="name">Name:</label>
    <input type="text" id="name" size="30" name="name" required/>
    <br>
    <label for="sex">Sex:</label>
    <select name="sex" id="sex">
        <c:forEach items="${sexList}" var="sex">
            <option value="${sex}">${sex}</option>
        </c:forEach>
    </select>
    <br>
    <label for="birthdate">Birth date:</label>
    <input type="date" id="birthdate" name="birth_date" min="1920-01-01" max = "2020-01-01"r equired/>
    <br>
    <input type="submit" value="REGISTER"/>
</form>
</body>
</html>
