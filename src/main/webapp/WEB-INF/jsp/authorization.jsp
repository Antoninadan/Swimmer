<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width = device-width, initial-scale=1.0">
    <title>Authorization</title>
    <%--<link type= "text/css" rel="stylesheet" media="screen" href="main.css" />--%>
    <%--<link rel="stylesheet" href="main.css">--%>

    <style>
        <%@include file='main.css' %>
    </style>
</head>
<body>
<main>
    <h2><c:out value="${message}" default="Please, input your login-password!"/></h2>
    <br>
    <a href="/registration">Registration</a>
    <br>
    <form action="/user/auth" method="post">
        Login: <input type="text" size="30" name="login" required/>
        <br>
        Password: <input type="password" size="30" name="password" required/>
        <br>

        <input type="submit" value="LOGIN"/>
    </form>
</main>
</body>
</html>
