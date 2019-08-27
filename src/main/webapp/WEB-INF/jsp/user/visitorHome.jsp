<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 8/26/2019
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
</head>
<body>
<a href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRequest">add visitor</a>
<a href="<c:out value="${pageContext.request.contextPath}"/>/admin/adminHome">admin</a>
</body>
</html>
