<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add new user</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/navVisitor.css"/>">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <style type="text/css">
        body {

            font-family: 'Roboto', sans-serif;
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

        .signup-form .btn {
            font-size: 16px;
            font-weight: bold;
            background: #19aa8d;
            border: none;
            min-width: 140px;
        }

        .signup-form .btn:hover, .signup-form .btn:focus {
            background: #179b81;
            outline: none;
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
<body id="signup">
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
                        class="fa fa-legal"></i><span class="badge badge-light" id="newRequestForAdmin"></span></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link"
                   href="<c:out value="${pageContext.request.contextPath}"/>/user/userRequests">Status<span> <i
                        class="fa fa-bell"></i>&nbsp;<span class="badge badge-light" id="newRequestForUser"></span></span></a>
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
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Create an account</h3>

    <div class="table-editable">
        <div class="signup-form">
            <form action="" method="post">
                <h2>Sign Up</h2>
                <p>Please fill in this form to create an account!</p>
                <hr>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="username" placeholder="Username" id="userName"
                               required="required">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-paper-plane"></i></span>
                        <input type="email" class="form-control" name="email" placeholder="Email Address" id="email"
                               required="required">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" class="form-control" name="password" placeholder="Password" id="password"
                               required="required">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
				<span class="input-group-addon">
					<i class="fa fa-lock"></i>
					<i class="fa fa-check"></i>
				</span>
                        <input type="text" class="form-control" name="confirm_password" id="confirmPassword"
                               placeholder="Confirm Password" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user-circle-o"></i></span>
                        <input type="email" class="form-control" name="email" id="HODEmail" placeholder="HOD Mail"
                               required="required">
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-address-card-o"></i></span>

                        <select class="mdb-select md-form" id="role" required="required">
                            <option value="" disabled selected>Choose Account type</option>
                            <option value="ADMIN">ADMIN</option>
                            <option value="USER">USER</option>
                            <option value="GUARD">GUARD</option>
                        </select>

                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-institution"></i></span>
                        <select class="mdb-select md-form" id="department" required="required">
                            <option value="" disabled selected>Choose Department</option>
                            <option value="Administration">Administration</option>
                            <option value="Compliance">Compliance</option>
                            <option value="Finance">Finance</option>
                            <option value="Central-IT">Central-IT</option>
                            <option value="EAG">EAG</option>
                            <option value="Stores">Stores</option>
                            <option value="Other">Other</option>

                        </select>

                    </div>
                </div>

            </form>
            <button onclick="registerUser()" class="btn btn-primary btn-lg">Sign Up</button>
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
