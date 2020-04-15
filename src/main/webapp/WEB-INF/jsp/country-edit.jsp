<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h2><c:out value="${message}" default="Country edit form!"/></h2>
<h2><c:out value="${country.name}"/></h2>
<br>
<br>
<form action="/country/update" method="post">
    Name: <input type="text" size="30" name="name" required value="${country.name}"/>
    <br>
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="countryId" value="${country.id}">
    <input type="submit" value="Update"/>
</form>

<%--Delete--%>
<br>
<form action="/country/delete" method="post">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="countryId" value="${country.id}">

    <%--&lt;%&ndash;<INPUT TYPE=submit VALUE=" Метод Confirm " LANGUAGE="Javascript" onclick="confirm('Are you sure?')">&ndash;%&gt;--%>
    <%--<script language="javascript">--%>
    <%--if (self==parent) {--%>
        <%--if (confirm('Are you sure?\')) <input type="submit" value="Delete"> };--%>
<%--</script>--%>

    <%--<script language="javascript">--%>
    <%--var isAdmin = confirm("Вы - администратор?");--%>

    <%--alert( isAdmin );--%>
    <%--</script>--%>

    <input type="submit" value="Delete">
</form>
<br>
<br>
<!-- Back to countries -->
<form action="/country/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to countries">
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
