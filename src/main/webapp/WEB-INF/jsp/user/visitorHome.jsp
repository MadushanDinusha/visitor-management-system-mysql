<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Madushan
  Date: 8/25/2019
  Time: 10:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Visitor Home</title>
</head>
<body>
<a href="<c:out value="${pageContext.request.contextPath}"/>/logout">logout</a>
<a href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRegistor">add new user</a>
<a href="<c:out value="${pageContext.request.contextPath}"/>/admin/adminHome">admin</a>
</body>
</html>
