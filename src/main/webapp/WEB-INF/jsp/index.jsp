<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Bootstrap Sign up Form with Icons</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <style>
        .col-sm-6 {
            background-color: lemonchiffon;
            border: 1px solid black;
            padding: 20px;
            margin-top: 20px;
        }

        #home {
            background-color: black;
        }

        .vsm {
            width: 140px;
        }

        span {
            color: black;
            font-size: 25px;
        }

        #first {
            margin-right: 5%;
            float: left;
            width: 45%;
            margin-left: 3%;

        }

        #second {
            float: left;
            width: 45%;
        }

        #second:hover {
            background-color: chocolate;
        }

        #first:hover {
            background-color: chocolate;
        }
    </style>
</head>
<body id="home">

<div class="row" style="background-color: #4A4A4A; height:75px;overflow:hidden;width:100%;margin-left:.01%">

    <img src="<c:url value="/resources/images/brandixx.jpg"/>" style="height: 75px">
</div>


<div class="card">
    <div id="demo" class="carousel slide" data-ride="carousel">

        <!-- Indicators -->
        <ul class="carousel-indicators">
            <li data-target="#demo" data-slide-to="0" class="active"></li>
            <li data-target="#demo" data-slide-to="1"></li>
            <li data-target="#demo" data-slide-to="2"></li>
        </ul>

        <!-- The slideshow -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="<c:url value="/resources/images/la.jpg"/>" alt="Los Angeles" width="1650px" height="475px">
            </div>
            <div class="carousel-item">
                <img src="<c:url value="/resources/images/chicago.jpg"/>" alt="Chicago" width="100%" height="475px">
            </div>
            <div class="carousel-item">
                <img src="<c:url value="/resources/images/ny.jpg"/>" alt="New York" width="1550px" height="475px">
            </div>
        </div>
        <!-- Left and right controls -->
        <a class="carousel-control-prev" href="#demo" data-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#demo" data-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
</div>
<div class="container center">
    <a href="#">
        <div class="col-sm-6" id="first">
            <img class="vsm" src="<c:url value="/resources/images/visitor.png"/>">
            <span>VMS</span>
        </div>"
    </a>
    <a href="#">
        <div class="col-sm-6" id="second">
            <img class="vsm" src="<c:url value="/resources/images/park.png"/>">
            <span>PSM</span>
        </div>
    </a>
</div>
</div>
</body>
</html>