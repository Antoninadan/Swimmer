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
    Login: <input type="text" size="30" name="login" required/>
    <br>
    Password: <input type="password" size="30" name="password" required/>
    <br>
    Name: <input type="text" size="30" name="name" required/>
    <br>
    Sex:
    <select name="sex">
        <c:forEach items="${sexList}" var="sex">
            <option value="${sex}">${sex}</option>
        </c:forEach>
    </select>
    <br>
    Birth date: <input type="text" size="30" name="birth_date" required/>
    <br>
    <input type="submit" value="REGISTER"/>
</form>
</body>
</html>
