<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="<c:out value="${pageContext.request.contextPath}"/>/doLogout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/admin/registration">register</a>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRegistor">register visitor</a>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/guard/parking">parking</a>
        <a href="<c:out value="${pageContext.request.contextPath}"/>/logout">Logout</a>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>


