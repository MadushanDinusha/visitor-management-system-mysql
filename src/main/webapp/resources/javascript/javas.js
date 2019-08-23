function getAll() {
    var inputText = "hello";
    $.ajax({
        url: 'getUserName/'+inputText,
        dataType: 'html',
        type: 'get',
        contentType: 'application/json',
        success: function (data) {
            alert("suc");
        },
        error: function (err) {
            alert("er" +err.responseText);
        }
    });
}

function registerUser() {
    $.ajax({
        url: 'registerUser',
        dataType: 'text',
        type: 'post',
        contentType: 'application/json',
        data:JSON.stringify({
            "userName":$("#userName").val(),
            "firstName":$("#firstName").val(),
            "lastName":$("#lastName").val(),
            "password":$("#password").val(),
            "email":$("#email").val(),
            "role":$("#role").val()
        }),
        processData: false,
        success: function () {
            alert("successfully saved")
        },
        error: function (err) {
          alert(err.responseText);
        }
    });
}

