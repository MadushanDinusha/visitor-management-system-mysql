<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Madushan
  Date: 8/27/2019
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
</head>
<body>
<div class="container">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Request Approval details</h3>
    <div class="card-body">
        <input type="checkbox" onclick="getAllRequestForUser()" value="Approved" id="approve"> Approved<br>
        <input type="checkbox" onclick="getAllRequestForUser()" value="Rejected" id="reject"> Rejected<br>
        <input type="checkbox" onclick="getAllRequestForUser()" value="Modify" id="modify"> Modify<br>
        <input type="checkbox" onclick="getAllRequestForUser()" value="Pending" id="pending"> Pending<br>
        <div id="tables1" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <th>Request Number</th>
                <th>State</th>
                </thead>
                <tbody id="userTable">
                </tbody>
            </table>
        </div>
    </div>
</div>
<%-- Modal--%>
<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg" style="width: 1200px">
        <div class="modal-content" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modal Header</h4>
            </div>
            <div class="modal-body">
                <div class="container">
                    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">User details</h3>
                    <div class="card-body">
                        <div id="tables" class="table-editable">
                            <table class="table table-bordered table-responsive-md table-striped text-center">
                                <thead>
                                <th>nic</th>
                                <th>name</th>
                                <th>company</th>
                                <th>date</th>
                                <th>purpose</th>
                                <th>responded_emp</th>
                                <th>vehicle_number</th>
                                </thead>
                                <tbody id="visitorTable">
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
<script>
    $(document).ready(function () {
        getAllRequestForUser();
        $('#approve').click(function(){
            getAllRequestForUser();
        });
    });
</script>
</html>
