<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 4 Navbar with Search Form</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/styleVisitor.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/navStyle.css"/>">

</head>
<body>
<nav class="navbar navbar-expand-lg ">
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span><i class="fa fa-home"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/visitorRequest">Requests <span><i class="fa fa-user-plus"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/admin/getRequests">Approval<span> <i class="fa fa-legal"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:out value="${pageContext.request.contextPath}"/>/user/userRequests">Status<span> <i class="fa fa-bell"></i></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Search<span> <i class="fa fa-clock-o"></i></span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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


<div class="container" style="margin-top: 3%">
    <h2 style="color: aliceblue">Visitor Requests</h2>
    <hr>
    <form action="">
        <div class="row" >
            <label class="control-label col-sm-2">No of Visitors:</label>
            <div class="col-sm-1">
                <div class="def-number-input number-input safari_only">
                    <input class="quantity" min="0" name="quantity" value="1" type="number">
                </div>
            </div>
            <div class="col-sm-1"><a href="#" class="btn btn-info">Ok</a></div>
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
        <div class="row">
            <label class="control-label col-sm-2">No of Vehicles:</label>
            <div class="col-sm-1">
                <div class="def-number-input number-input safari_only">
                    <input class="quantity" min="0" name="quantity" value="1" type="number">
                </div>
            </div>
            <div class="col-sm-1"><a href="#" id="btnok" class="btn btn-info">Ok</a></div>
        </div>
        <div class="row" id="rw">

            <label class="control-label col-sm-2">Vehicle No : </label>

            <div class="col-sm-3">

            </div>
        </div>


        <div class="row">
            <button type="reset" class="btn">Clear</button>
            <button type="submit" class="btn">Submit</button>
        </div>
    </form>
</div>

</body>
</html>