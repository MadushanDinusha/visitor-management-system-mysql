var group_ids = '';
var buttonState = '';

function makeUniqueId() {
    return 'visitor_' + Math.random().toString(36).substr(2, 16);
}

function getAll() {
    var inputText = "hello";
    $.ajax({
        url: 'getUserName/' + inputText,
        dataType: 'html',
        type: 'get',
        contentType: 'application/json',
        success: function () {
        },
        error: function (err) {
            alert("er" + err.responseText);
        }
    });
}

function registerUser() {
    $.ajax({
        url: 'registerUser',
        dataType: 'text',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({
            "userName": $("#userName").val(),
            "password": $("#password").val(),
            "email": $("#email").val(),
            "department": $("#department").val(),
            "hodEmail": $("#HODEmail").val(),
            "role": $("#role").val()
        }),
        processData: false,
        success: function () {
            alert("successfully saved");
            window.location.href = "/visitor-manage/admin/allUsers";
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function deleteUser(userName) {
    $.ajax({
        url: 'deleteUser',
        dataType: 'text',
        type: 'delete',
        contentType: 'application/json',
        data: JSON.stringify({
            "userName": userName
        }),
        processData: false,
        success: function () {
            getAllUsers();
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function getAllUsers() {
    $.ajax({
        url: 'getAllUsers',
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            var userList = data;
            console.log("user details " + Object.values(userList));
            var numberOfUsers = userList.length;
            $("#tableBody").html("");
            for (var i = 0; i < numberOfUsers; i++) {
                $("#tableBody").append('<tr><td>' + userList[i].id + '</td><td>' + userList[i].username + '</td>' +
                    '<td>' + userList[i].email + '</td><td>' + userList[i].roles[0].role + '</td><td>' + userList[i].hodMail + '</td>' +
                    '<td>' + userList[i].department + '</td><td><p data-placement="top" data-toggle="tooltip" title="Delete">' +
                    '<button class="btn btn-danger btn-xs" onclick="deleteUser(\'' + userList[i].username + '\')">' +
                    '<span class="glyphicon glyphicon-trash"></span></button></p></td>');
            }
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function addVisitor() {
    var numberOfTables = $("#tableNumber").val();
    var group_id = makeUniqueId();
    for (var j = 0; j < numberOfTables; j++) {
        var visitor = new Object();
        var nicId = $("#" + '' + j + '' + "nic").val();
        visitor.id = parseFloat(nicId);
        visitor.groupId = group_id;
        visitor.name = $("#" + '' + j + '' + "name").val();
        visitor.company = $("#" + '' + j + '' + "company").val();
        visitor.purpose = $("#" + '' + j + '' + "purpose").val();
        visitor.date = $("#" + '' + j + '' + "date").val();
        visitor.vehicleNumber = $("#vehicle").val();
        console.log("visitor " + visitor);
        $.ajax({
            url: "addVisitor",
            dataType: 'text',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(visitor),
            processData: false,
            success: function () {
                console.log("saved items " + j);
            },
            error: function (err) {
                console.log(err.responseText);
            }
        });
    }
}

function makeTable() {
    var numberOfTables = $("#tableNumber").val();
    $("#tableNumbers").html("");
    for (var i = 0; i < numberOfTables; i++) {
        $("#tableNumbers").append('' +
            '<tr><td><input type="text" class="form-control" id="' + i + 'nic"></td>' +
            '<td><input type="text" class="form-control" id="' + i + 'name"></td>' +
            '<td><input type="text" class="form-control" id="' + i + 'company"></td>' +
            '<td><input type="text" class="form-control" id="' + i + 'purpose"></td>' +
            '<td><input type="text" class="form-control" id="' + i + 'date"></td></tr>');
    }
}

function getAllRequest() {
    $.ajax({
        url: 'newRequest',
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            var requestList = data;
            console.log("user details " + Object.values(requestList));
            var numberOfRequests = requestList.length;
            $("#tableBodyRequests").html("");
            var groupIds = [];
            for (var i = 0; i < numberOfRequests; i++) {
                if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Pending") {

                    $("#tableBodyRequests").append('<tr><td><a  data-toggle="modal" data-target="#myModal" ' +
                        'onclick="getRequest(\'' + requestList[i].group_id + '\')">' +
                        requestList[i].group_id + '</a></td></tr>');
                }
                groupIds.push(requestList[i].group_id);
            }
        },
        error: function (err) {
            alert("er" + err.responseText);
        }
    });
}

function getRequest(group_id) {
    group_ids = group_id;
    $.ajax({
        url: 'newRequest/' + group_id,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            var visitorList = data;
            console.log("user details " + Object.values(visitorList));
            var numberOfVisitors = visitorList.length;
            $("#visitorTable").html("");
            for (var i = 0; i < numberOfVisitors; i++) {
                $("#visitorTable").append('<tr><td>' + visitorList[i].id + '</td><td>' + visitorList[i].company + '</td>' +
                    '<td>' + visitorList[i].date + '</td><td>' + visitorList[i].name + '</td><td>' + visitorList[i].purpose + '</td>' +
                    '<td>' + visitorList[i].responded_emp + '</td><td>' + visitorList[i].vehicle_number + '</td></tr>');
            }
        },
        error: function (err) {
            alert("er" + err.responseText);
        }
    });
}


function updateVisitorState(state) {

    $.ajax({
        url: 'updateState',
        dataType: 'text',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({
            "group_id": group_ids,
            "state": state,
            "message": $("#message").val()
        }),
        processData: false,
        success: function () {
            $("#message").html("")
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function getAllRequestForUser() {
    $.ajax({
        url: 'allRequests',
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            var requestList = data;
            console.log("request details " + Object.values(requestList));
            var numberOfVisitors = requestList.length;
            var groupIds = [];
            $("#userTable").html("");
            for (var i = 0; i < numberOfVisitors; i++) {
                if ($("#approve").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Approved") {
                        $("#userTable").append('<tr><td><a  data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + requestList[i].state + '</td></tr>');
                    }
                }
                if ($("#reject").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Rejected") {
                        $("#userTable").append('<tr><td><a  data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + requestList[i].state + '</td></tr>');
                    }
                }
                if ($("#modify").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Modify") {
                        $("#userTable").append('<tr><td><a  data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + requestList[i].state + '<br>' + requestList[i].comment + '</td></tr>');
                    }
                }
                if ($("#pending").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Pending") {
                        $("#userTable").append('<tr><td><a  data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + requestList[i].state + '</td></tr>');
                    }
                }
                if ($("#pending").prop("checked") == false && $("#modify").prop("checked") == false &&
                    $("#reject").prop("checked") == false && $("#approve").prop("checked") == false) {
                    if (!groupIds.includes(requestList[i].group_id)) {
                        $("#userTable").append('<tr><td><a  data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + requestList[i].state + ' <br>' +requestList[i].comment + '</td></tr>');
                    }
                }
                groupIds.push(requestList[i].group_id);
            }
        },
        error: function (err) {
            alert("er" + err.responseText);
        }
    });
}

function approve() {
    buttonState = "approve";
    $("#mi-modal").modal('show');
    $("#request").html("Approve")
}

function rejects() {
    buttonState = "reject";
    $("#mi-modal").modal('show');
    $("#request").html("Reject")
}

function modifies() {
    buttonState = "modify";
    $("#modify-modal").modal('show');
    $("#request").html("Modify")
}

function ModalYes() {
    if (buttonState === "approve") {
        updateVisitorState('Approved');
    } else if (buttonState === "reject") {
        updateVisitorState('Rejected');
    } else {
        updateVisitorState('Modify')
    }
    $("#mi-modal").modal('hide');
    $("#myModal").modal('hide');
    $("#modify-modal").modal('hide');
    getAllRequest();
}

function ModalNo() {
    $("#mi-modal").modal('hide');
    $("#modify-modal").modal('hide');
}





