<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Franchise Page</title>
    <script>
        let objectURL;
    </script>
</head>
<body>
<h1>Franchise edit form</h1>
<c:if test="${message != null}">
    <h3 style="color:red;"> ${message} </h3>
</c:if>
<br>
<br>
<form action="/franchise/delete" method="post">
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="franchiseId" value="${franchise.id}">
    <form action="/Config?pg=FIBiller&amp;cmd=delete">
        <input type="submit" value="Delete this record"
               onclick="return confirm('Are you sure you want to delete?')"/>
    </form>
</form>
<br>


<script>
    function prepareUrlAndView(fileList) {
        let file = null;

        for (let i = 0; i < fileList.length; i++) {
            if (fileList[i].type.match(/^image\//)) {
                file = fileList[i];
                break;
            }
        }
        if (file !== null) {
            objectURL = URL.createObjectURL(file);
            output.src = objectURL;
        }
    }
</script>


<br>
<form action="/franchise/update" method="post" enctype="multipart/form-data">
    Name: <input type="text" size="30" name="name" required value="${franchise.name}"/>
    <br>
    Path: <input type="text" size="30" name="path" readonly value="${franchise.path}"/>
    <br>
    <input type="file" accept="image/*" id="file-input" name="file" >
    <script>
        const fileInput = document.getElementById('file-input');
        fileInput.addEventListener('change', (e) => prepareUrlAndView(e.target.files));
        <%--fileInput.prepareUrlAndView("${logoFile}");--%>
    </script>
    <br>
    <br>
    <img id="output" src="${pageContext.request.contextPath}/file/get/oceanman.jpg">
    <br>
    <br>
    <input hidden="true" name="userId" value="${user.id}">
    <input hidden="true" name="franchiseId" value="${franchise.id}">
    <br>
    <input type="submit" value="Update"/>
</form>

<%--Delete--%>
<br>
<br>
<br>
<br>
<!-- Back to franchises -->
<form action="/franchise/open-all" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to franchises">
</form>
<br>

<!-- Back to cabinet -->
<form action="/user/admin-cabinet" method="get">
    <input hidden="true" name="userId" value="${user.id}">
    <input type="submit" value="Back to cabinet">
</form>

</body>
</html>
