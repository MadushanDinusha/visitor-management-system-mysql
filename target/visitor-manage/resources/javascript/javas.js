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