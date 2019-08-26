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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link href="css/bootstrap-switch/bootstrap-switch.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <form action="">
        <div class="row">
            <label class="control-label col-sm-2">No of Visitors:</label>
            <div class="col-lg-2">
                <div class="def-number-input number-input safari_only">
                    <input class="quantity" min="0" name="quantity" value="1" type="number">
                </div>
            </div>
            <div class="col-sm-4">
                <button>OK</button>
            </div>
        </div>
        <table class="table table-dark table-hover">
            <thead>
            <tr>
                <th>NIC</th>
                <th>Name</th>
                <th>Company</th>
                <th>Reason</th>
                <th>Date</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="text" class="form-control" id="NIC"></td>
                <td><input type="text" class="form-control" id="name"></td>
                <td><input type="text" class="form-control" id="company"></td>
                <td><input type="text" class="form-control" id="purpose"></td>
                <td><input type="text" class="form-control" id="date"></td>
                <td><input type="text" class="form-control" id="time"></td>
            </tr>
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
            <button type="submit" class="btn btn-default">Submit</button>
        </div>
    </form>
</div>


</body>

</html>
