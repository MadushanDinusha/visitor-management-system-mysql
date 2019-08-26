<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Visitor Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
</head>
<body>
<div class="container">
    <form>
        <div class="row">
            <label class="control-label col-sm-2">No of Visitors:</label>
                    <input id="tableNumber" min="0" type="number">
                <a onclick="makeTable()">OK</a>
        </div>
        <table class="table table-dark table-hover" >
            <thead>
            <tr>
                <th>NIC</th>
                <th>Name</th>
                <th>Company</th>
                <th>Reason</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody id="tableNumbers">
            </tbody>
        </table>
        <div class="row">
            <label class="control-label col-sm-2">vehicle</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="vehicle">
            </div>
        </div>
        <div class="row">
            <button type="clear" class="btn btn-default">Clear</button>
            <button onclick="addVisitor()" class="btn btn-default">Submit</button>
        </div>
    </form>
</div>
</body>

</html>
