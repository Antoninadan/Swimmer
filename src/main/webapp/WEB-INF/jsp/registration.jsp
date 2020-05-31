<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width = device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <style>
        <%@include file='main.css' %>
    </style>
</head>
<body>
<main class="form-registration">
    <h2><c:out value="${message}" default="Please, input your credentials!"/></h2>
    <br>
    <a href="/">GO BACK</a>
    <br>
    <form action="/user/save" method="post">
        <p>
            <label class="input-label" for="login">Login:</label>
            <input class="input-content" type="text" id="login" name="login" required/>
        </p>
        <p>
            <label class="input-label" for="password">Password:</label>
            <input class="input-content" type="password" id="password" name="password" required/>
        </p>
        <p>
            <label class="input-label" for="name">Name:</label>
            <input class="input-content" type="text" id="name" name="name" required/>
        </p>
        <p>
            <label class="input-label" for="sex">Sex:</label>
            <select name="sex" id="sex">
                <c:forEach items="${sexList}" var="sex">
                    <option value="${sex}">${sex}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label for="birthdate">Birth date:</label>
            <input type="date" id="birthdate" name="birth_date" min="1920-01-01" max="2020-01-01" required/>
        </p>
        <p>
            <input type="submit" value="REGISTER"/>
        </p>
    </form>
</main>
</body>
</html>
