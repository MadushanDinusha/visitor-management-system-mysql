<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Madushan
  Date: 9/12/2019
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reports</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.debug.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/navVisitor.css"/>">
</head>
<body>
<div>
    <p>Date-wise</p>
    <input type="date" id="fromDate" class="form-control">
    <input type="date" id="toDate" class="form-control">
    <a class="btn btn-outline-info" onclick="getVisitorReports()">OK</a>
</div>
<div>
<p>User-wise</p>
    <input type="text" id="userNameForReport" class="form-control">
</div>
<%--Report Modal--%>
<div class="modal fade" id="reportModal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content " style="width:1300px;margin-left:-25%">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body -lg">
                <div class="container" id="tab">
                    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">Visitor Report</h3>
                    <div class="card-body">
                        <p id="fromToDate"></p>
                        <div id="tabless" class="table-editable -lg">
                            <table id="visitorReportTable"
                                   class="table table-bordered table-responsive-md table-striped text-center">
                                <thead>
                                <th>Nic</th>
                                <th>Name</th>
                                <th>Company</th>
                                <th>Purpose</th>
                                <th>Date</th>
                                <th>Check-in-time</th>
                                <th>Check-out-time</th>
                                </thead>
                                <tbody id="visitorReportTableBody">
                                </tbody>
                            </table>
                            <div class="container-fluid" id="vehicles"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a class="btn btn-outline-success" onclick="createPDF()" id="pdfBtn">Save</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>

</script>
</html>
