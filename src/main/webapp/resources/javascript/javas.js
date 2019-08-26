function getAll() {
    var inputText = "hello";
    $.ajax({
        url: 'getUserName/' + inputText,
        dataType: 'html',
        type: 'get',
        contentType: 'application/json',
        success: function () {
            alert("successful");
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
    for (var j = 0; j < numberOfTables; j++) {
        var visitor = new Object();
        visitor.nic = $("#" + '' + j + '' + "nic").val();
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
                alert("successfully saved");
            },
            error: function (err) {
                alert(err.responseText);
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