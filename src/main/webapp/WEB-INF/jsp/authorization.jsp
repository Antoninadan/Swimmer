<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h2><c:out value="${message}" default="Please, input your login-password!"/></h2>
<br>
<a href="/registration">REGISTER NEW USER</a>
<br>
<form action="/user/auth" method="post">
    Login: <input type="text" size="30" name="login" required/>
    <br>
    Password: <input type="password" size="30" name="password" required/>
    <br>



    <input type="submit" value="LOGIN"/>
</form>
</body>
</html>
