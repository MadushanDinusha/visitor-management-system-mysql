<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Request</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/navVisitor.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/styleVisitor.css"/>">

    <style type="text/css">
        a {
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
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRequest">Requests
                    <span><i class="fa fa-user-plus"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/admin/getRequests">Approval<span> <i
                        class="fa fa-legal"></i>&nbsp;<span class="badge badge-light" id="newRequestForAdmin"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="<c:out value="${pageContext.request.contextPath}"/>/user/userRequests">Status<span> <i
                        class="fa fa-bell"></i>&nbsp;<span class="badge badge-light" id="newRequestForUser"></span></span></a>
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
                    <a style="color:black" class="dropdown-item" href="#"><i class="fa fa-tasks"></i> Reports</a>
                </div>
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
    <h3 style="margin-bottom: 5%" class="card-header text-center font-weight-bold text-uppercase py-4">Request Approval details</h3>

    <form action="">
        <div class="text-center">
            <h5>Visitor Details</h5>
            <hr>
            <label class="control-label col-sm-2" style="color: black">No of Visitors:</label>
            <div style="display:inline-block">
                <div class="def-number-input number-input safari_only">
                    <input class="quantity" min="0" id="tableNumber" name="quantity" value="1" type="number">
                </div>
            </div>
            <div style="display:inline-block;"><a onclick="makeTable()" class="btn btn-outline-info">Ok</a></div>
        </div>
        <table class="table table-dark table-hover" id="tb1">
            <thead>
            <tr>
                <th>NIC</th>
                <th>Name</th>
                <th>Company</th>
                <th>Reason</th>
                <th>Date & Time</th>

            </tr>
            </thead>
            <tbody id="tableNumbers">

            </tbody>
        </table>

        <div class="text-center">
            <h5>Vehicle Details</h5>
            <hr>
            <label class="control-label col-sm-2" style="color: black">No of Vehicles:</label>
            <div style="display:inline-block">
                <div class="def-number-input number-input safari_only">
                    <input class="quantity" min="0" id="numberOfVehicles" name="quantity" value="0" type="number">
                </div>
            </div>
            <div style="display:inline-block;"><a onclick="makeVehicleInputs()" class="btn btn-outline-info">Ok</a></div>
        </div>
        <div class="row">
            <div class="container" id="inputVehicle">
            </div>
        </div>
        <div class="row">
            <button type="reset" class="btn btn-outline-dark">Clear</button>
            <a  onclick="addVisitor()" class="btn btn-outline-success">Submit</a>
        </div>
    </form>
</div>
<%--Success Modal--%>
<div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
             <i class="fa fa-times-circle" style="color:red;font-size: 30px"></i>  Please fill Correctly
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
        getUserRoll();
        getAllRequest();
        getAllRequestForUser();
    });
</script>

</html>