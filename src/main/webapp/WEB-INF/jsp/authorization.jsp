<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width = device-width, initial-scale=1.0">
    <title>Authorization</title>

    <style>
        <%@include file='main.css' %>
    </style>
</head>
<body>
<main class="form-auth">
    <h2><c:out value="${message}" default="Please, input your login-password!"/></h2>
    <br>
    <a href="/registration">Registration</a>
    <br>
    <form action="/user/auth" method="post">
        <p>
            <label class="input-label" for="login">Login:</label>
            <input class="input-content" type="text" id="login" name="login" required/>
        </p>
        <p>
            <label class="input-label" for="password">Password:</label>
            <input class="input-content" type="password" id="password" name="password" required/>
        </p>
        <p>
            <input type="submit" value="LOGIN"/>
        </p>
    </form>
</main>
</body>
</html>
