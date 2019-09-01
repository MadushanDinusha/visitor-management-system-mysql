<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Visitor Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link href="css/bootstrap-switch/bootstrap-switch.css" rel="stylesheet">
    <link rel="stylesheet" href="stylevisitor.css">
</head>
<body id="visitorReg">
<div class="container">
    <h2>Visitor Details</h2>
    <hr>
    <form action="">
        <div class="row" id="rw">
            <label class="control-label col-sm-2">No of Visitors:</label>
            <div class="col-sm-1">
                <div class="def-number-input number-input safari_only">
                    <input class="quantity" min="0" name="quantity" value="1" type="number">
                </div>
            </div>
            <div class="col-sm-1"><a href="#" id="btnok"  class="btn btn-info">Ok</a></div>
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
        <div class="row" id="rw">
            <label class="control-label col-sm-2">No of vehicles</label>
            <div class="def-number-input number-input safari_only">
                <div class="col-sm-1">
                    <input class="quantity" min="0" name="quantity" value="1" type="number">
                </div>
            </div>
            <div class="col-sm-1"><a href="#" class="btn btn-info">Ok</a></div>

        </div>
        <div class="row" id="rw">

            <label class="control-label col-sm-2" >Vehicle No : </label>

            <div class="col-sm-3" id="vehicle">

            </div>
        </div>



        <div class="row">
            <button type="reset" class="btn">Clear</button>
            <button type="submit" class="btn" >Submit</button>
        </div>
    </form>
</div>


</body>

</html>
