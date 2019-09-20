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

        .form-control, .form-control:focus, .input-group-addon {
            border-color: #e1e1e1;
        }

        select {
            width: auto;
            box-shadow: none;
            height: 40px;
            width: 285px;

        }

        .form-control, .btn {
            border-radius: 3px;
        }

        .signup-form {
            width: 390px;
            margin: 0 auto;
            padding: 30px 0;
        }

        .signup-form form {
            color: #999;
            border-radius: 3px;
            margin-bottom: 15px;
            background: #f2f2f2;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }

        .signup-form h2 {
            color: #333;
            font-weight: bold;
            margin-top: 0;
        }

        .signup-form hr {
            margin: 0 -30px 20px;
        }

        .signup-form .form-group {
            margin-bottom: 20px;
        }

        .signup-form label {
            font-weight: normal;
            font-size: 13px;
        }

        .signup-form .form-control {
            min-height: 38px;
            box-shadow: none !important;
        }

        .signup-form .input-group-addon {
            max-width: 42px;
            text-align: center;
        }


        .signup-form a {
            color: #fff;
            text-decoration: underline;
        }

        .signup-form a:hover {
            text-decoration: none;
        }

        .signup-form form a {
            color: #19aa8d;
            text-decoration: none;
        }

        .signup-form form a:hover {
            text-decoration: underline;
        }

        .signup-form .fa {
            font-size: 21px;
        }

        .signup-form .fa-paper-plane {
            font-size: 18px;
        }

        .signup-form .fa-check {
            color: #fff;
            left: 17px;
            top: 18px;
            font-size: 7px;
            position: absolute;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg ">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorHome">Home
                    <span><i class="fa fa-home"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRequest">Requests
                    <span><i class="fa fa-user-plus"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/admin/getRequests">Approval<span> <i
                        class="fa fa-legal"></i>
                &nbsp;<span class="badge badge-light" id="newRequestForAdmin"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="<c:out value="${pageContext.request.contextPath}"/>/user/userRequests">Status<span> <i
                        class="fa fa-bell"></i>&nbsp;<span class="badge badge-light"
                                                           id="newRequestForUser"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/guard/search">Search
                    <span> <i class="fa fa-clock-o"></i></span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Settings <span><i class="fa fa-cog"></i></span>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a style="color:black" class="dropdown-item"
                       href="<c:out value="${pageContext.request.contextPath}"/>/admin/registration"><i
                            class="fa fa-edit"></i> Create Account</a>
                    <a style="color:black" class="dropdown-item"
                       href="<c:out value="${pageContext.request.contextPath}"/>/admin/allUsers"><i
                            class="fa fa-id-badge"></i> Accounts</a>
                    <a style="color:black" class="dropdown-item" href="<c:out value="${pageContext.request.contextPath}"/>/admin/Reports"><i class="fa fa-tasks"></i> Reports</a>                </div>
            </li>
        </ul>
    </div>
    <div class="navbar-collapse collapse w-1 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/logout">Sign Out<span> <i
                        class="fa fa-power-off"></i></span></a>
            </li>

        </ul>
    </div>

</nav>
<div class="container">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">User details</h3>
    <div class="card-body">
        <div class="active-cyan-3 active-cyan-4 mb-4">
            <input class="form-control" id="searchInput" type="text" placeholder="Search" aria-label="Search"
                   onkeyup="search()">
        </div>
        <div id="table" class="table-editable">
            <table id="userInfo" class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <th>User Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>HOD mail</th>
                <th>Department</th>
                <th>Delete</th>
                <th>Edit</th>
                </thead>
                <tbody id="tableBody">
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true"
     id="deleteUserModal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="card-body">
                        <h5 class="modal-title" id="myModalLabel">Do you want to delete the user </h5>
                        <b><p id="request"></p></b>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="modal-btn-si" onclick="deleteYes()">Yes</button>
                <button type="button" class="btn btn-primary" id="modal-btn-no" onclick="deleteNo()">No</button>
            </div>
        </div>
    </div>
</div>
<%--Modal--%>
<div class="modal fade" id="editDetailsModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content -lg">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Update User</h3>
                    <div class="signup-form">
                        <form>
                            <div class="form-group">
                                <div class="input-group">
                                    <p style="width: 100px">User Name :</p>
                                    <br>
                                    <input disabled="disabled" type="text" class="form-control" placeholder="Username" id="userName">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <p style="width: 100px">Email :</p>
                                    <br>
                                    <input type="text" class="form-control" placeholder="Email Address" id="email">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <p style="width: 100px">HOD mail :</p>
                                    <br>
                                    <input type="text" class="form-control" name="email" id="HODEmail"
                                           placeholder="HOD Mail">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <p style="width: 100px">Role :</p>
                                    <select class="mdb-select md-form" id="role">
                                        <option value="POWER_ADMIN">POWER ADMIN</option>
                                        <option value="ADMIN">ADMIN</option>
                                        <option value="USER">USER</option>
                                        <option value="GUARD">GUARD</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <p style="width: 100px">Department :</p>
                                    <br>
                                    <select class="mdb-select md-form" id="department">
                                        <option value="Administration" id="Administration">Administration</option>
                                        <option value="Compliance" id="Compliance">Compliance</option>
                                        <option value="Finance" id="Finance">Finance</option>
                                        <option value="Central-IT" id="Central-IT">Central-IT</option>
                                        <option value="EAG" id="EAG">EAG</option>
                                        <option value="Stores" id="Stores">Stores</option>
                                        <option value="Other" id="Other">Other</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <p style="width: 100px">Change Password :</p>
                                    <br>
                                    <input type="password" class="form-control" name="email" id="changePw"
                                           placeholder="Change Password">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a class="btn btn-outline-success" onclick="editUserDetails()">Save</a>
            </div>
        </div>
    </div>
</div>
<%--Success Modal--%>
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <i class="text-center fa fa-check-circle" style="color:green;font-size: 30px"></i> Successfully Updated
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Close</button>
            </div>
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