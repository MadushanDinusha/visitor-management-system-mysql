<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
        <a href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRegistor">register visitor</a>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/guard/parking">parking</a>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/admin/adminHome">admin</a>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/logout">Logout</a>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>


