<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Bootstrap Sign up Form with Icons</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/resources/javascript/javas.js"/>" type="text/javascript"></script>
    <style type="text/css">
        body {
            color: #fff;
            background: #c8c3c3;
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
                    <option value="1">ADMIN</option>
                    <option value="2">USER</option>
                    <option value="3">GUARD</option>
                </select>

            </div>
        </div>

        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-institution"></i></span>
                <select class="mdb-select md-form" id="department" required="required">
                    <option value="" disabled selected>Choose Department</option>
                    <option value="1">Administration</option>
                    <option value="2">Compliance</option>
                    <option value="2">Finance</option>
                    <option value="4">Central-IT</option>
                    <option value="5">EAG</option>
                    <option value="6">Stores</option>
                    <option value="7">Other</option>

                </select>

            </div>
        </div>

    </form>
    <div class="form-group">
        <button onclick="registerUser()" class="btn btn-primary btn-lg">Sign Up</button>
    </div>
</div>
</body>
<script>

</script>
</html>
