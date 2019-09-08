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
    var group_id = makeUniqueId();
    var foundEmpty = false;
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            var k = i - 1;
            var nic = $("#" + '' + k + '' + "nic").val();
            var names = $("#" + '' + k + '' + "name").val();
            var company = $("#" + '' + k + '' + "purpose").val();
            var dates = $("#" + '' + k + '' + "company").val();
            var purpose = $("#" + '' + k + '' + "date").val();
            if (nic === '' || nic == null || names === '' || names == null || company === '' || company == null || dates === '' || dates == null || purpose === '' || purpose == null) {
                foundEmpty = true;
            }
        }
    }
    if (foundEmpty) {
        $("#successModal").modal('show');
    } else if ($("#0nic").val() != undefined && okButtonClicked == true) {
        for (var j = 0; j < numberOfTables; j++) {
            var visitor = new Object();
            visitor.nic = $("#" + '' + j + '' + "nic").val();
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
                    okButtonClicked = false;
                    setInterval(reloadAddVisitor, 1000);
                },
                error: function (err) {
                    alert(err.responseText);
                }
            });
        }
    }

    var numberOfVehicles = $("#numberOfVehicles").val();
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
            '<td><input type="text" class="date" id="' + i + 'date" placeholder="YYYY-MM-DD HH:MM"></td></tr>');
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
                if (!groupIds.includes(requestList[i].group_id) && requestList[i].adminState === "UnRead" && userRole === "ADMIN") {
                    newApprovalRequestCount++;
                    document.getElementById("newRequestForAdmin").innerHTML = newApprovalRequestCount.toString();
                }
                if (userRole != "ADMIN") {
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
                    '<td>' + visitorList[i].company + '</td><td>' + visitorList[i].date + '</td><td>' + visitorList[i].purpose + '</td></tr>');
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
                            '<td><i class="fa fa-edit " style="font-size:25px;color:blue"></i></td><td>' + requestList[i].state + ' -<span style="color: red;">' + requestList[i].comment + '</span></td></tr>');
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
                                '<td><i class="fa fa-edit " style="font-size:25px;color:blue"></i></td><td>' + requestList[i].state + ' -<span style="color: red;">' + requestList[i].comment + '</span></td></tr>');
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
    setInterval(getAllRequest, 1000);
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
                    '<td><input type="text" class="form-control" id="' + i + 'nic"></td>' +
                    '<td><input type="text" class="form-control" id="' + i + 'name"></td>' +
                    '<td><input type="text" class="form-control" id="' + i + 'company"></td>' +
                    '<td><input type="text" class="form-control" id="' + i + 'purpose"></td>' +
                    '<td><input type="text" class="form-control" id="' + i + 'date" ></td>' +
                    '<td style="visibility: hidden;width: 0px"><input style="visibility: hidden;width: 0px" id="' + i + 'id"></td>' +
                    '</tr>');
                $("#" + '' + i + '' + "id").val(visitorList[i].id);
                $("#" + '' + i + '' + "nic").val(visitorList[i].nic);
                $("#" + '' + i + '' + "name").val(visitorList[i].name);
                $("#" + '' + i + '' + "company").val(visitorList[i].company);
                $("#" + '' + i + '' + "purpose").val(visitorList[i].purpose);
                $("#" + '' + i + '' + "date").val(visitorList[i].date);
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

function reloadAddVisitor() {
    window.location.href = "/visitor-manage/user/userRequests";
}

function search() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("table");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
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

function changePassword() {
    var newPassword = $("#newPassword").val();
    $.ajax({
        url: "updatePassword",
        dataType: 'text',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify({
            "newPassword": newPassword
        }),
        success: function (data) {
            alert("successfully changed");
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}

function updateUserRole() {
    $.ajax({
        url: "updateRole",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "ba": "aa"
        }),
        success: function (data) {
            alert("successfully changed");
        },
        error: function (err) {
            alert(err.responseText);
        }
    })
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
    $.ajax({
        url: "updateUserDetails",
        type: "post",
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify({
            "userName":$("#userName").val(),
            "email": $("#email").val(),
            "hodMail": $("#HODEmail").val(),
            "role": $("#role").val(),
            "department": $("#department").val(),
        }),
        success: function (data) {
            $("#editDetailsModal").modal('hide');
            $("#successModal").modal('show');
            setInterval(getAllUsers,1000);
        },
        error: function (err) {
            alert(err.responseText);
        }
    });
}