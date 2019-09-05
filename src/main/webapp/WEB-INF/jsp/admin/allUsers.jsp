<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Madushan
  Date: 8/23/2019
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/navVisitor.css"/>">
    <style>
        .list-group-item {
            cursor: pointer;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg ">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorHome">Home <span><i class="fa fa-home"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRequest">Requests <span><i class="fa fa-user-plus"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/admin/getRequests">Approval<span> <i class="fa fa-legal"></i>
                &nbsp;<span class="badge badge-light" id="newRequestForAdmin"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/userRequests">Status<span> <i class="fa fa-bell"></i>&nbsp;<span class="badge badge-light" id="newRequestForUser"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Search<span> <i class="fa fa-clock-o"></i></span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Settings <span><i class="fa fa-cog"></i></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a style="color:black" class="dropdown-item" href="<c:out value="${pageContext.request.contextPath}"/>/admin/registration"><i class="fa fa-edit"></i> Create Account</a>
                    <a style="color:black" class="dropdown-item" href="<c:out value="${pageContext.request.contextPath}"/>/admin/allUsers"><i class="fa fa-id-badge"></i> Accounts</a>
                    <a style="color:black" class="dropdown-item" href="#"><i class="fa fa-tasks"></i> Reports</a>
                </div>
            </li>
        </ul>
    </div>
    <div class="navbar-collapse collapse w-1 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/logout">Sign Out<span> <i class="fa fa-power-off"></i></span></a>
            </li>

        </ul>
    </div>

</nav>
<div class="container">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">User details</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <th>User Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>HOD mail</th>
                <th>Department</th>
                <th>Delete</th>
                </thead>
                <tbody id="tableBody">
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        getAllUsers();
        getUserRoll();
        getAllRequest();
        getAllRequestForUser();
    });
</script>
</html>