var group_ids = '';
var buttonState = '';
var groupIdForUpdate = '';
var userRole = '';
var userName = '';
var deleteUserName = '';
var okButtonClicked = false;

function makeUniqueId() {
    return 'visitor_' + Math.random().toString(36).substr(2, 16);
}

function validateDate(dtValue) {
    var dtRegex = new RegExp(/\b\d{1,2}[\/]\d{1,2}[\/]\d{4}\b/);
    return dtRegex.test(dtValue);
}

function increaseDate(dates) {
    var date = new Date(dates);
    date.setHours(date.getHours() + 5);
    date.setMinutes(date.getMinutes() + 30);
    var index = date.toString().lastIndexOf(':') + 3;
    return date.toString().substring(0, index)
}

function getUserRoll() {
    $.ajax({
        url: 'getUserRole',
        dataType: 'text',
        type: 'get',
        contentType: 'application/json',
        success: function (data) {
            userRole = data;
        },
        error: function (err) {
            alert("er" + err.responseText);
        }
    });
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
    var userName = $("#userName").val();
    var HODEmail = $("#HODEmail").val();
    var password = $("#password").val();
    var email = $("#email").val();
    var accountType = $("#role").val();
    var department = $("#department").val();

    if (userName === '') {
        document.getElementById("message").innerHTML = "Please Enter User name";
        $("#successModalForRegistration").modal('show');
    } else if (email === '') {
        document.getElementById("message").innerHTML = "Please Enter email";
        $("#successModalForRegistration").modal('show');
    } else if (!email.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
        document.getElementById("message").innerHTML = "Please Enter a valid email";
        $("#successModalForRegistration").modal('show');
    } else if (password === '') {
        document.getElementById("message").innerHTML = "Please Enter password";
        $("#successModalForRegistration").modal('show');
    } else if (HODEmail === '') {
        document.getElementById("message").innerHTML = "Please Enter HOD mail";
        $("#successModalForRegistration").modal('show');
    } else if (!HODEmail.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
        document.getElementById("message").innerHTML = "Please Enter a valid email";
        $("#successModalForRegistration").modal('show');
    } else if (accountType === null) {
        document.getElementById("message").innerHTML = "Please Choose an account type";
        $("#successModalForRegistration").modal('show');
    } else if (department === null) {
        document.getElementById("message").innerHTML = "Please Choose a department";
        $("#successModalForRegistration").modal('show');
    } else {
        if ($("#password").val() != $("#confirmPassword").val() || $("#password").val() == null) {
            $("#passwordNotMatch").modal('show');
        } else if ($("#password").val().length < 6) {
            document.getElementById("message").innerHTML = "Password should contain at least 6 characters";
            $("#successModalForRegistration").modal('show');
        } else {
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
                success: function (data) {
                    window.location.href = "/visitor-manage/admin/allUsers";
                },
                error: function (err) {
                    document.getElementById("message").innerHTML = err.responseText;
                    $("#successModalForRegistration").modal('show');
                }
            });
        }
    }
}

function deleteUser(userName) {
    $("#deleteUserModal").modal("show");
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
                deleteUserName = userList[i].username;
                $("#tableBody").append('<tr><td>' + userList[i].username + '</td>' +
                    '<td><a style="color: black" href="mailto:' + userList[i].email + '">' + userList[i].email + '</a></td><td>' + userList[i].roles[0].role + '</td><td>' + userList[i].hodMail + '</td>' +
                    '<td>' + userList[i].department + '</td><td><p data-placement="top" data-toggle="tooltip" title="Delete">' +
                    '<button class="btn btn-danger btn-xs" onclick="deleteUser(\'' + userList[i].username + '\')">' +
                    '<span class="fa fa-trash-o"></span></button></p></td>' +
                    '<td><button onclick="getUserByUserName(\'' + userList[i].username + '\')"><span class="fa fa-edit"></span></button></td></tr>');
            }
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function addVisitor() {
    var table, tr, td, i;
    table = document.getElementById("tb1");
    tr = table.getElementsByTagName("tr");
    var numberOfTables = $("#tableNumber").val();
    var numberOfVehicles = $("#numberOfVehicles").val();
    var group_id = makeUniqueId();
    var foundEmpty = false;
    var foundEmptyVehicle = false;
    var successOfLastRecord = false;
    var incorrectDateAndTime = false;
    for (var vehicle = 0; vehicle < numberOfVehicles; vehicle++) {
        var vehicleValue = $("#" + '' + vehicle + '' + "vehicle").val();
        if (vehicleValue === null || vehicleValue === '') {
            foundEmptyVehicle = true;
        }
    }
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            var k = i - 1;
            var nic = $("#" + '' + k + '' + "nic").val();
            var names = $("#" + '' + k + '' + "name").val();
            var company = $("#" + '' + k + '' + "company").val();
            var dates = $("#" + '' + k + '' + "date").val();
            var time = $("#" + '' + k + '' + "time").val();
            var purpose = $("#" + '' + k + '' + "purpose").val();
            if (time === '' || time == null || nic === '' || nic == null || names === '' || names == null || company === '' || company == null || dates === '' || dates == null || purpose === '' || purpose == null) {
                foundEmpty = true;
            }
        }
    }
    if (foundEmpty || foundEmptyVehicle) {
        $("#successModal").modal('show');
    } else if (incorrectDateAndTime) {
        $("#errorInDateAndTime").modal('show');
    } else if ($("#0nic").val() != undefined && okButtonClicked == true) {
        for (var j = 0; j < numberOfTables; j++) {
            var visitor = new Object();
            visitor.nic = $("#" + '' + j + '' + "nic").val();
            visitor.groupId = group_id;
            visitor.name = $("#" + '' + j + '' + "name").val();
            visitor.company = $("#" + '' + j + '' + "company").val();
            visitor.purpose = $("#" + '' + j + '' + "purpose").val();
            visitor.date = $("#" + '' + j + '' + "date").val();
            visitor.time = $("#" + '' + j + '' + "time").val();
            visitor.vehicleNumber = $("#vehicle").val();
            if (j === numberOfTables - 1) {
                $.ajax({
                    url: "addVisitor",
                    dataType: 'text',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(visitor),
                    processData: false,
                    success: function () {
                        okButtonClicked = false;
                        $("#sendingEmailModal").modal("show");
                        sendMail()
                    },
                    error: function (err) {
                        alert(err.responseText);
                    }
                });
            } else {
                $.ajax({
                    url: "addVisitor",
                    dataType: 'text',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(visitor),
                    processData: false,
                    success: function () {
                        okButtonClicked = false;
                    },
                    error: function (err) {
                        alert(err.responseText);
                    }
                });
            }


        }
    }
    var firstVehicle = $("#0vehicle").val();
    if (numberOfVehicles > 0 && firstVehicle != undefined) {
        for (var v = 0; v < numberOfVehicles; v++) {
            var vehicleNumber = $("#" + '' + v + '' + "vehicle").val();
            $.ajax({
                url: "addVehicle",
                dataType: 'text',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "group_id": group_id,
                    "vehicle_number": vehicleNumber,
                    "contextPath": sessionStorage.getItem('contextPath')
                }),
                processData: false,
                success: function () {
                },
                error: function (err) {
                    console.log(err.responseText);
                }
            });
        }
    }
}

function makeTable() {
    okButtonClicked = true;
    var numberOfTables = $("#tableNumber").val();
    $("#tableNumbers").html("");
    for (var i = 0; i < numberOfTables; i++) {
        $("#tableNumbers").append('' +
            '<tr><td><input type="text" class="nic" id="' + i + 'nic"></td>' +
            '<td><input type="text" class="names" id="' + i + 'name"></td>' +
            '<td><input type="text" class="company" id="' + i + 'company"></td>' +
            '<td><input type="text" class="purpose" id="' + i + 'purpose"></td>' +
            '<td><input type="date" class="date" id="' + i + 'date"></td>' +
            '<td><input type="time" id="' + i + 'time"></td></tr>');
    }
}

function makeVehicleInputs() {
    $("#inputVehicle").html("");
    var numberOfVehicles = $("#numberOfVehicles").val();
    for (var i = 0; i < numberOfVehicles; i++) {
        $("#inputVehicle").append('<div class="text-center"><label class="control-label col-sm-2" style="color: black">Vehicle Number:</label>' +
            '<input class="input-lg" id="' + i + 'vehicle" type="text"></div>')
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
            var newApprovalRequestCount = 0;
            for (var i = 0; i < numberOfRequests; i++) {
                if (!groupIds.includes(requestList[i].group_id) && requestList[i].adminState === "UnRead" && (userRole === "ADMIN" || userRole === "POWER_ADMIN")) {
                    newApprovalRequestCount++;
                    document.getElementById("newRequestForAdmin").innerHTML = newApprovalRequestCount.toString();
                }
                if (userRole === "USER" || userRole === "GUARD") {
                    document.getElementById("newRequestForAdmin").style.display = "none";
                }
                if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Pending") {
                    var date = new Date(requestList[i].lastUpdatedTime);
                    date.setHours(date.getHours() + 5);
                    date.setMinutes(date.getMinutes() + 30);
                    var index = date.toString().lastIndexOf(':') + 3;
                    $("#tableBodyRequests").append('<tr><td><a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                        'onclick="getRequest(\'' + requestList[i].group_id + '\')">' +
                        requestList[i].group_id + '</a></td>' +
                        '<td>' + date.toString().substring(0, index) + '</td></tr>');
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
            document.getElementById("requestedUser").innerHTML = visitorList[0].userName;
            userName = visitorList[0].userName;
            for (var i = 0; i < numberOfVisitors; i++) {
                $("#visitorTable").append('<tr><td>' + visitorList[i].nic + '</td><td>' + visitorList[i].name + '</td>' +
                    '<td>' + visitorList[i].company + '</td><td>' + visitorList[i].date + '</td><td>' + visitorList[i].time + '</td><td>' + visitorList[i].purpose + '</td></tr>');
            }
        },
        error: function (err) {
            alert("er" + err.responseText);
        }
    });
    $.ajax({
        url: 'getVehiclesByGroupId/' + group_id,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            var visitorList = data;
            console.log("user details " + Object.values(visitorList));
            var numberOfVehicles = visitorList.length;
            document.getElementById("numberOfVehicles").innerHTML = numberOfVehicles;
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
            var newStatusCount = 0;
            for (var i = 0; i < numberOfVisitors; i++) {
                if (!groupIds.includes(requestList[i].group_id) && requestList[i].employeeState === "UnRead") {
                    newStatusCount++;
                    document.getElementById("newRequestForUser").innerHTML = newStatusCount.toString();
                }
                var date = new Date(requestList[i].lastUpdatedTime);
                date.setHours(date.getHours() + 5);
                date.setMinutes(date.getMinutes() + 30);
                var index = date.toString().lastIndexOf(':') + 3;
                if ($("#approve").prop("checked") == true) {

                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Approved") {
                        $("#userTable").append('<tr><td><a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + date.toString().substring(0, index) + '</td>' +
                            '<td><span><i class="fa fa-check-circle " style="font-size:30px;color: green"></i></span>' +
                            '</td><td>' + requestList[i].state + '</td></tr>');
                    }
                }
                if ($("#reject").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Rejected") {
                        $("#userTable").append('<tr><td><a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                            '<td>' + date.toString().substring(0, index) + '</td>' +
                            '<td><i class="fa fa-times-circle" style="font-size:30px;color: red"></i></td><td>' + requestList[i].state + '</td></tr>');
                    }
                }
                if ($("#modify").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Modify") {
                        $("#userTable").append('<tr><td style="text-align: left"> ' +
                            '<a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a><a onclick="modifyRequest(\'' + requestList[i].group_id + '\')" style="margin-left: 2%"  class="btn btn-outline-info">Edit</a></td>' +
                            '<td>' + date.toString().substring(0, index) + '</td>' +
                            '<td><i class="fa fa-edit " style="font-size:25px;color:blue"></i></td><td>' + requestList[i].state + ' &nbsp; <a class="btn btn-outline-info" onclick="getCommentModal(\'' + requestList[i].comment + '\')">info</a></td></tr>');
                    }
                }
                if ($("#pending").prop("checked") == true) {
                    if (!groupIds.includes(requestList[i].group_id) && requestList[i].state === "Pending") {
                        $("#userTable").append('<tr><td style="text-align: left">' +
                            '<a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                            'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '' +
                            '</a></td>' +
                            '<td>' + date.toString().substring(0, index) + '</td>' +
                            '<td><i class="fa fa-exclamation-circle" style="font-size:30px;color: yellow"></i></td><td>' + requestList[i].state + '</td></tr>');
                    }
                }
                if ($("#pending").prop("checked") == false && $("#modify").prop("checked") == false &&
                    $("#reject").prop("checked") == false && $("#approve").prop("checked") == false) {
                    if (!groupIds.includes(requestList[i].group_id)) {
                        if (requestList[i].state === "Approved") {
                            $("#userTable").append('<tr><td style="text-align: left"> ' +
                                '<a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                                'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                                '<td>' + date.toString().substring(0, index) + '</td>' +
                                '<td><span><i class="fa fa-check-circle " style="font-size:30px;color: green"></i></span></td><td>' + requestList[i].state + '</td></tr>');

                        }
                        if (requestList[i].state === "Rejected") {
                            $("#userTable").append('<tr><td style="text-align: left">' +
                                '<a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                                'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                                '<td>' + date.toString().substring(0, index) + '</td>' +
                                '<td><i class="fa fa-times-circle" style="font-size:30px;color: red"></i></td><td>' + requestList[i].state + '</td></tr>');
                        }
                        if (requestList[i].state === "Modify") {
                            $("#userTable").append('<tr><td style="text-align: left"> ' +
                                '<a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                                'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a><a onclick="modifyRequest(\'' + requestList[i].group_id + '\')" style="margin-left: 2%"  class="btn btn-outline-info">Edit</a></td>' +
                                '<td>' + date.toString().substring(0, index) + '</td>' +
                                '<td><i class="fa fa-edit " style="font-size:25px;color:blue"></i></td><td>' + requestList[i].state + ' &nbsp; <a class="btn btn-outline-info" onclick="getCommentModal(\'' + requestList[i].comment + '\')">info</a></td></tr>');
                        }
                        if (requestList[i].state === "Pending") {
                            $("#userTable").append('<tr><td style="text-align: left">' +
                                '<a class="btn btn-outline-info" data-toggle="modal" data-target="#myModal" ' +
                                'onclick="getRequest(\'' + requestList[i].group_id + '\')">' + requestList[i].group_id + '</a></td>' +
                                '<td>' + date.toString().substring(0, index) + '</td><td><i class="fa fa-exclamation-circle" style="font-size:30px;color: yellow"></i></td><td>' + requestList[i].state + '</td></tr>');
                        }
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

function getCommentModal(comment) {
    $("#comment").html(comment);
    $("#commentModal").modal('show');
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
    setTimeout(getAllRequest, 1000);
}

function ModalNo() {
    $("#mi-modal").modal('hide');
    $("#modify-modal").modal('hide');
}

function modifyRequest(groupsId) {
    $('#myModals').modal('show');
    groupIdForUpdate = groupsId;
    $.ajax({
        url: 'newRequest/' + groupsId,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            var visitorList = data
            var listSize = visitorList.length;
            $("#visitorTables").html("");
            for (var i = 0; i < listSize; i++) {
                $("#visitorTables").append('<tr>' +
                    '<td><input type="text"  id="' + i + 'nic"></td>' +
                    '<td><input type="text"  id="' + i + 'name"></td>' +
                    '<td><input type="text"  id="' + i + 'company"></td>' +
                    '<td><input type="text"  id="' + i + 'purpose"></td>' +
                    '<td><input type="date"  id="' + i + 'date" ></td>' +
                    '<td><input type="time"  id="' + i + 'time" ></td>' +
                    '<td style="visibility: hidden;width: 0px"><input style="visibility: hidden;width: 0px" id="' + i + 'id"></td>' +
                    '</tr>');
                $("#" + '' + i + '' + "id").val(visitorList[i].id);
                $("#" + '' + i + '' + "nic").val(visitorList[i].nic);
                $("#" + '' + i + '' + "name").val(visitorList[i].name);
                $("#" + '' + i + '' + "company").val(visitorList[i].company);
                $("#" + '' + i + '' + "purpose").val(visitorList[i].purpose);
                $("#" + '' + i + '' + "date").val(visitorList[i].date);
                $("#" + '' + i + '' + "time").val(visitorList[i].time);
            }
        },
        error: function (err) {
            alert(err.responseText)
        }
    });
}

function saveModify() {
    var rowCount = $('#modifyTable >tbody >tr').length;
    for (var i = 0; i < rowCount; i++) {
        var visitorModify = new Object();
        visitorModify.id = parseFloat($("#" + '' + i + '' + "id").val());
        visitorModify.nic = $("#" + '' + i + '' + "nic").val();
        visitorModify.name = $("#" + '' + i + '' + "name").val();
        visitorModify.company = $("#" + '' + i + '' + "company").val();
        visitorModify.purpose = $("#" + '' + i + '' + "purpose").val();
        visitorModify.date = $("#" + '' + i + '' + "date").val();
        visitorModify.time = $("#" + '' + i + '' + "time").val();
        visitorModify.groupId = groupIdForUpdate;
        $.ajax({
            url: "updateVisitor",
            dataType: 'text',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(visitorModify),
            success: function (data) {
                $('#myModals').modal('hide');
                getAllRequestForUser();
            }
        });
    }
}

function updateEmpState() {
    $.ajax({
        url: "updateEmpState",
        dataType: 'text',
        type: 'post',
        contentType: 'application/json',
    });
}

function showUserDetails() {
    $("#userDetails").modal('show');
    $.ajax({
        url: 'getUserDetails/' + userName,
        dataType: 'json',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            $("#userTable").html("");
            var userDetails = data;
            $("#userTable").append('<tr><td>' + userDetails.username + '</td><td><a style="color: black" href="mailto:' + userDetails.email + '">' + userDetails.email + '</a></td><td>' + userDetails.hodMail + '</td>' +
                '<td>' + userDetails.roles[0].role + '</td><td>' + userDetails.department + '</td></tr>')
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function deleteYes() {
    $.ajax({
        url: 'deleteUser',
        dataType: 'text',
        type: 'delete',
        contentType: 'application/json',
        data: JSON.stringify({
            "userName": deleteUserName
        }),
        processData: false,
        success: function () {
            $("#deleteUserModal").modal("hide");
            getAllUsers();
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function deleteNo() {
    $("#deleteUserModal").modal("hide");
}

function reloadUserRequests() {
    window.location.href = "/visitor-manage/user/userRequests";
}

function search() {
    var input, filter, table, tr, td, td1, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        td1 = tr[i].getElementsByTagName("td")[4];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


function showChangePasswordModal() {
    $("#modalChangePassword").modal('show');
}

function changePassword() {
    var newPassword = $("#changePassword").val();
    var confirmChange = $("#confirmChangePassword").val();
    if (newPassword.length < 5) {
        $("#errorModalForCharacterChangePassword").modal('show');
    } else {
        if (newPassword === confirmChange) {
            $.ajax({
                url: "updatePassword",
                dataType: 'text',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify({
                    "newPassword": newPassword
                }),
                success: function () {
                    $("#modalChangePassword").modal('hide');
                    $("#successModalForChangePassword").modal('show');
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        } else {
            $("#errorModalForChangePassword").modal('show');
        }
    }
}

function getUserByUserName(userName) {
    $.ajax({
        url: "getUserDetails",
        type: "post",
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "userName": userName
        }),
        success: function (data) {
            $("#userName").val(data.username);
            $("#email").val(data.email);
            $("#HODEmail").val(data.hodMail);
            $("#role").val(data.roles[0].role);
            $("#department").val(data.department);
            $("#editDetailsModal").modal('show');
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function editUserDetails() {
    var changePw = $("#changePw").val();
    if (changePw === null || changePw === "" || changePw === undefined) {
        $.ajax({
            url: "updateUserDetails",
            type: "post",
            dataType: 'text',
            contentType: 'application/json',
            data: JSON.stringify({
                "userName": $("#userName").val(),
                "email": $("#email").val(),
                "hodMail": $("#HODEmail").val(),
                "role": $("#role").val(),
                "department": $("#department").val(),
            }),
            success: function (data) {
                $("#editDetailsModal").modal('hide');
                $("#successModal").modal('show');
                setTimeout(getAllUsers, 1000);
            },
            error: function (err) {
                alert(err.responseText);
            }
        });
    } else {
        $.ajax({
            url: "updateUserDetails",
            type: "post",
            dataType: 'text',
            contentType: 'application/json',
            data: JSON.stringify({
                "userName": $("#userName").val(),
                "email": $("#email").val(),
                "hodMail": $("#HODEmail").val(),
                "role": $("#role").val(),
                "department": $("#department").val(),
                "password": changePw
            }),
            success: function (data) {
                $("#editDetailsModal").modal('hide');
                $("#successModal").modal('show');
                setTimeout(getAllUsers, 1000);
            },
            error: function (err) {
                alert(err.responseText);
            }
        });
    }
}

function getUserName() {
    $.ajax({
        url: 'getUserName',
        dataType: 'text',
        type: 'get',
        contentType: 'application/json',
        processData: false,
        success: function (data) {
            document.getElementById("welcomeUserName").innerHTML = data;
        },
        error: function (err) {
            alert(err.responseText)
        }
    });
}


function updateVisitorCheckIn(index, visitorId, checkIn) {
    $.ajax({
        url: "updateVisitorCheckIn",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "checkIn": checkIn,
            "visitorId": visitorId
        }),
        success: function (data) {
            $("#passIdCheckInModal").modal("hide");
            $("#sureModalPassIdCheckIn").modal("hide");
            getVisitorDetailsForCheckInAndCheckOut();
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function updateVisitorCheckOut(index, visitorId, checkOut) {
    $.ajax({
        url: "updateVisitorCheckOut",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "checkOut": checkOut,
            "visitorId": visitorId
        }),
        success: function (data) {
            $("#checkoutModal").modal("hide");
            $("#sureModalCheckOut").modal("hide");
            getVisitorDetailsForCheckInAndCheckOut()
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function updateVisitorPassId(index, visitorId, passId) {
    $.ajax({
        url: "updateVisitorPassId",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "passId": passId,
            "visitorId": visitorId
        }),
        success: function (data) {
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}


function getVehiclesDetails(groupId) {
    $.ajax({
        url: 'getVehiclesDetails/' + groupId,
        dataType: 'json',
        contentType: 'application/json',
        type: "get",
        processData: false,
        success: function (vehicleList) {
            if (vehicleList[0] == null || vehicleList[0] === "") {
                $("#noVehiclesModal").modal('show')
            } else {
                $("#vehicleTable").html("");
                for (var i = 0; i < vehicleList.length; i++) {
                    $("#vehicleTable").append('<tr><td id="' + i + '"><input type="text" id="' + i + 'vehicle" class="form-control" value="' + vehicleList[i].vehicleNumber + '"></td>' +
                        '<td><a class="btn btn-outline-info" onclick="updateVehicleDetails(\'' + vehicleList[i].id + '\',\'' + i + '\')">Update</a></td></tr>');
                }
                $("#vehicleDetailsModal").modal('show');
            }
        },
        error: function (err) {
            alert(err.responseText)
        }
    });
}

function updateVehicleDetails(id, index) {
    var vehicleNumber = $("#" + '' + index + '' + "vehicle").val();
    $.ajax({
        url: "updateVehicleNumber",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "vehicleId": id,
            "vehicleNumber": vehicleNumber
        }),
        success: function (data) {
            $("#successModalForUpdatingVehicleNumber").modal("show");
            $("#vehicleDetailsModal").modal("hide");
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function sendMail() {
    $.ajax({
        url: "sendMail",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "contextPath": sessionStorage.getItem('contextPath')
        }),
        success: function () {
            setTimeout(reloadUserRequests, 1000);
        }
    });
}

function getVisitorReports() {
    $.ajax({
        url: 'generateReport',
        dataType: 'json',
        contentType: 'application/json',
        type: "post",
        processData: false,
        data: JSON.stringify({
            "fromDate": $("#fromDate").val(),
            "toDate": $("#toDate").val()
        }),
        success: function (visitorList) {
            $("#visitorReportTableBody").html("");
            var fromDate = $("#fromDate").val()
            var toDate = $("#toDate").val()
            $("#fromToDate").html('From ' + fromDate + ' To ' + toDate);
            for (var i = 0; i < visitorList.length; i++) {
                $("#visitorReportTableBody").append('<tr>' +
                    '<td>' + visitorList[i].nic + '</td>' +
                    '<td>' + visitorList[i].name + '</td>' +
                    '<td>' + visitorList[i].company + '</td>' +
                    '<td>' + visitorList[i].purpose + '</td>' +
                    '<td>' + visitorList[i].date + '</td>' +
                    '<td>' + visitorList[i].checkIn + '</td>' +
                    '<td>' + visitorList[i].checkOut + '</td>' +
                    '<td>' + visitorList[i].userName + '</td></tr>');
            }
            $("#reportModal").modal('show');
        },
        error: function (err) {
            alert(err.responseText)
        }
    });
}

function getVisitorDetailsForCheckInAndCheckOut() {
    $.ajax({
        url: 'getVisitorDetailsForCheckInAndCheckOut',
        dataType: 'json',
        contentType: 'application/json',
        type: "get",
        processData: false,
        success: function (visitorList) {
            $("#visitorList").html("");
            var visitorListSize = visitorList.length;
            for (var i = 0; i < visitorListSize; i++) {
                if (visitorList[i].passId != null && visitorList[i].checkIn != null) {
                    if (visitorList[i].checkOut != null) {
                    } else {
                        $("#visitorList").append('<tr>' +
                            '<td>' + visitorList[i].nic + '</td>' +
                            '<td>' + visitorList[i].name + '</td>' +
                            '<td>' + visitorList[i].company + '</td>' +
                            '<td>' + visitorList[i].date + '</td>' +
                            '<td><a class="btn btn-outline-info" onclick="getVehiclesDetails(\'' + visitorList[i].groupId + '\')" >info</a></td>' +
                            '<td><a class="btn btn-outline-info" onclick="getCheckOutModal(\'' + i + '\',\'' + visitorList[i].id + '\')">' + visitorList[i].passId + '</a></td>' +
                            '<td>' + visitorList[i].checkIn + '</td></tr>')
                    }
                } else {
                    $("#visitorList").append('<tr>' +
                        '<td><a class="btn btn-outline-info" onclick="getPassIdCheckInModal(\'' + i + '\',\'' + visitorList[i].id + '\')" >' + visitorList[i].nic + '</a></td>' +
                        '<td>' + visitorList[i].name + '</td>' +
                        '<td>' + visitorList[i].company + '</td>' +
                        '<td>' + visitorList[i].date + '</td>' +
                        '<td><a class="btn btn-outline-info" onclick="getVehiclesDetails(\'' + visitorList[i].groupId + '\')" >info</a></td></tr>')
                }
            }
        },
        error: function (err) {
            alert(err.responseText)
        }
    });
}

function getPassIdCheckInModal(index, visitorId) {
    var d = new Date(),
        h = d.getHours(),
        m = d.getMinutes();
    if (h < 10) h = '0' + h;
    if (m < 10) m = '0' + m;
    $('#checkInInput').val(h + ':' + m);
    $("#passIdCheckInModal").modal("show");
    $("#updatePassIdCheckIn").on('click', function () {
        var passId = $("#passIdInput").val();
        var checkIn = $("#checkInInput").val();
        if ((passId == null || passId === "") || (checkIn == null || checkIn === "")) {
            $("#errorInPassIdOrCheckIn").modal('show');
        } else {
            $("#sureModalPassIdCheckIn").modal("show");
        }
        $("#yseButton").on('click', function () {
            updateVisitorPassId(index, visitorId, passId);
            updateVisitorCheckIn(index, visitorId, checkIn);
        });
    });
}

function getCheckOutModal(index, visitorId) {
    var d = new Date(),
        h = d.getHours(),
        m = d.getMinutes();
    if (h < 10) h = '0' + h;
    if (m < 10) m = '0' + m;
    $('#checkOutInput').val(h + ':' + m);
    $("#checkoutModal").modal("show");
    $("#updateCheckOut").on('click', function () {
        var checkOut = $("#checkOutInput").val();
        if (checkOut == null || checkOut == "") {
            $("#errorInPassIdOrCheckIn").modal('show');
        } else {
            $("#sureModalCheckOut").modal("show");
        }
        $("#yseButtonCheckout").on('click', function () {
            updateVisitorCheckOut(index, visitorId, checkOut);
        });
    });
}

function createPDF() {
    var sTable = document.getElementById('tab').innerHTML;

    var style = "<style>";
    style = style + "table {width: 100%;font: 17px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
    style = style + "padding: 2px 3px;text-align: center;}";
    style = style + "</style>";
    var win = window.open('', '', 'height=700,width=700');
    win.document.write('<html><head>');
    win.document.write(style);
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(sTable);
    win.document.write('</body></html>');
    win.document.close();
    win.print();
}

function getVisitorReportsByUserName() {
    $.ajax({
        url: 'generateReportAsUserWise',
        dataType: 'json',
        contentType: 'application/json',
        type: "post",
        processData: false,
        data: JSON.stringify({
            "userName": $("#userNameForReport").val(),
        }),

        success: function (visitorList) {
            $("#visitorReportTableBody").html("");
            var userName = $("#userNameForReport").val();
            $("#fromToDate").html('User Name - ' + userName);
            for (var i = 0; i < visitorList.length; i++) {
                $("#visitorReportTableBody").append('<tr>' +
                    '<td>' + visitorList[i].nic + '</td>' +
                    '<td>' + visitorList[i].name + '</td>' +
                    '<td>' + visitorList[i].company + '</td>' +
                    '<td>' + visitorList[i].purpose + '</td>' +
                    '<td>' + visitorList[i].date + '</td>' +
                    '<td>' + visitorList[i].checkIn + '</td>' +
                    '<td>' + visitorList[i].checkOut + '</td>' +
                    '<td>' + visitorList[i].userName + '</td></tr>');
            }
            $("#reportModal").modal('show');
        },
        error: function (err) {
            alert(err.responseText)
        }
    });
}

function exportToExcel(tableID, filename) {
    var downloadurl;
    var dataFileType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTMLData = tableSelect.outerHTML.replace(/ /g, '%20');

    filename = filename ? filename + '.xls' : 'export_excel_data.xls';
    downloadurl = document.createElement("a");
    document.body.appendChild(downloadurl);

    if (navigator.msSaveOrOpenBlob) {
        var blob = new Blob(['\ufeff', tableHTMLData], {
            type: dataFileType
        });
        navigator.msSaveOrOpenBlob(blob, filename);
    } else {
        downloadurl.href = 'data:' + dataFileType + ', ' + tableHTMLData;
        downloadurl.download = filename;
        downloadurl.click();
    }
}

function download_csv(csv, filename) {
    var csvFile;
    var downloadLink;
    csvFile = new Blob([csv], {type: "text/csv"});
    downloadLink = document.createElement("a");
    downloadLink.download = filename;
    downloadLink.href = window.URL.createObjectURL(csvFile);
    downloadLink.style.display = "none";
    document.body.appendChild(downloadLink);
    downloadLink.click();
}

function export_table_to_csv(html, filename) {
    var csv = [];
    var rows = document.querySelectorAll("table tr");

    for (var i = 0; i < rows.length; i++) {
        var row = [], cols = rows[i].querySelectorAll("td, th");

        for (var j = 0; j < cols.length; j++)
            row.push(cols[j].innerText);
        csv.push(row.join(","));
    }
    download_csv(csv.join("\n"), filename);
}

function downloadAs() {
    var selectBox = document.getElementById("saveAs");
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    if (selectedValue === "PDF") {
        createPDF()
    } else if (selectedValue === "Excel") {
        exportToExcel('visitorReportTable', 'visitor-report')
    } else if (selectedValue === "CSV") {
        export_table_to_csv('visitorReportTable', 'visitor-report')
    }
}