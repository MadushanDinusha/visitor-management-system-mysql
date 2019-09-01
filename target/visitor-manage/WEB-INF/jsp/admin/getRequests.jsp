<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Madushan
  Date: 8/27/2019
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/javascript/javas.js"/>"></script>

</head>
<body>
<a onclick="getAllRequest()">requests</a>
<div class="container">
    <h3 class="card-header text-center font-weight-bold text-uppercase py-4">User details</h3>
    <div class="card-body">
        <div id="table" class="table-editable">
            <table class="table table-bordered table-responsive-md table-striped text-center">
                <thead>
                <th>Request Id</th>
                </thead>
                <tbody id="tableBodyRequests">
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
                <button type="button" id="rejectBtn" class="btn btn-danger" onclick="rejects()">Reject</button>
                <button type="button" id="modifyBtn" class="btn btn-success" onclick="modifies()" >Modify</button>
                <button type="button" id="approveBtn" class="btn btn-primary" onclick="approve()">Approve</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="mi-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Do you want to </h4><b><p id="request"></p></b>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="modal-btn-si" onclick="ModalYes()">Yes</button>
                <button type="button" class="btn btn-primary" id="modal-btn-no" onclick="ModalNo()">No</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" id="modify-modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabels">Do you want to </h4><b><p id="requests"></p></b>
                Any comment : <input type="text" id="message">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="modal-btn-sis" onclick="ModalYes()">Yes</button>
                <button type="button" class="btn btn-primary" id="modal-btn-nos" onclick="ModalNo()">No</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var contextPath = "<c:out value="${pageContext.request.contextPath}"/>";
    sessionStorage.setItem('contextPath', contextPath);
    $(document).ready(function () {
        getAllRequest();
    });
</script>
</html>
