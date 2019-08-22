<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta name="_csrf" th:content="${_csrf.token}"/>
    <title>Home</title>

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/resources/fonts/font-awesome-4.3.0/css/font-awesome.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/common-js.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js" />"></script>
</head>
<body>
<div class="container">
    <h2>Hi <span th:utext="${userName}"></span>, this is the home page.</h2>
    <form th:action="@{/logout}" method="post" >
        <button  type="submit">logout</button>
    </form>
    <a href="<c:out value="${pageContext.request.contextPath}"/>/home/registration">register</a>
    <button onclick="getAll()">send</button>
</div>
<script type="text/javascript">
    contextPath = ;

</script>
</body>
</html>