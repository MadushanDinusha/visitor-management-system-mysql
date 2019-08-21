function getAll() {
    var userName = "hello";
    var data = 'fname='+encodeURIComponent(userName);
    $.ajax({
        url: 'getRoll',
        type : 'POST',
        // contentType: "application/json",
        dataType : 'json',
        data:data,
        success: function (result) {
            alert("s")
        },
        error: function (e) {
            alert("e")
        }
    });
}