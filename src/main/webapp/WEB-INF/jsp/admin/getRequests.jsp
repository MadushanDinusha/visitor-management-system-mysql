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
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/navVisitor.css"/>">
<style>
    #userLink:hover{
        text-decoration: underline;
        cursor: pointer;
    }
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg ">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/userHome/home">Home
                    <span><i class="fa fa-home"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRequest">Requests
                    <span><i class="fa fa-user-plus"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/admin/getRequests">Approval<span> <i
                        class="fa fa-legal"></i>&nbsp;<span class="badge badge-light"
                                                            id="newRequestForAdmin"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="<c:out value="${pageContext.request.contextPath}"/>/user/userRequests">Status<span> <i
                        class="fa fa-bell"></i> &nbsp;<span class="badge badge-light"
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
                       href="<c:out value="${pageContext.request.contextPath}"/>/powerAdmin/allUsers"><i
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
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Pending Requests</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <th colspan="2">Pending requests</th>
                </thead>
                <tbody id="tableBodyRequests">
                </tbody>
            </table>
        </div>
    </div>
</div>
<%-- Modal--%>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg" style="width: 1200px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Request details</h3>
                    <div class="card-body">
                        <div id="tables" class="table-editable">
                            Requested by : &nbsp; <b><a id="userLink" onclick="showUserDetails()"><span id="requestedUser"></span></a></b>
                            <hr>
                            <h6>Visitor Details</h6>
                            <table class="table table-bordered table-responsive-md table-striped text-center">
                                <thead>
                                <th>Nic</th>
                                <th>Name</th>
                                <th>Company</th>
                                <th>Date</th>
                                <th>Time</th>
                                <th>Purpose</th>
                                </thead>
                                <tbody id="visitorTable">
                                </tbody>
                            </table>
                        </div>
                        <hr>
                        <h6>Vehicle Details</h6>
                        Number of Vehicles : <span id="numberOfVehicles"></span>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="rejectBtn" class="btn btn-outline-danger" onclick="rejects()">Reject</button>
                <button type="button" id="modifyBtn" class="btn btn-outline-success" onclick="modifies()">Modify
                </button>
                <button type="button" id="approveBtn" class="btn btn-outline-primary" onclick="approve()">Approve
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true"
     id="mi-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="card-body">
                        <h5 class="modal-title" id="myModalLabel">Do you want to </h5>
                        <b><p id="request"></p></b>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="modal-btn-si" onclick="ModalYes()">Yes</button>
                <button type="button" class="btn btn-primary" id="modal-btn-no" onclick="ModalNo()">No</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true"
     id="modify-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="card-body">
                        <h5 class="text-center modal-title" id="myModalLabels">Do you want to Modify</h5>
                        <b><p id="requests"></p></b>
                        Comment : <input type="text" id="message">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="modal-btn-sis" onclick="ModalYes()">Yes</button>
                <button type="button" class="btn btn-primary" id="modal-btn-nos" onclick="ModalNo()">No</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="userDetails" role="dialog">
    <div class="modal-dialog modal-lg" style="width: 1200px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">User details</h3>
                    <div class="card-body">
                        <div id="UserDetailsTable" class="table-editable">
                            <hr>
                            <table class="table table-bordered table-responsive-md table-striped text-center">
                                <thead>
                                <th>User name</th>
                                <th>Email</th>
                                <th>HOD mail</th>
                                <th>Account type</th>
                                <th>Department</th>
                                </thead>
                                <tbody id="userTable">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var contextPath = "<c:out value="${pageContext.request.contextPath}"/>";
    sessionStorage.setItem('contextPath', contextPath);
</script>
<script>
    $(document).ready(function () {
        getUserRoll();
        getAllRequest();
        getAllRequestForUser();
    });
</script>
</html>
