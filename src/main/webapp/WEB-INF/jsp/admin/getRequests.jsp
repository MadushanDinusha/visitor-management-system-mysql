<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Madushan
  Date: 8/27/2019
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>

</head>
<body>
<a onclick="getAllRequest()">requests</a>
<div class="container">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">User details</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <th>Request Id</th>
                </thead>
                <tbody id="tableBodyRequests">
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var contextPath = "<c:out value="${pageContext.request.contextPath}"/>";
    sessionStorage.setItem('contextPath', contextPath);
</script>
</html>
