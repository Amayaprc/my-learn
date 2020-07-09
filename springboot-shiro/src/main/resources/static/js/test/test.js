function testSubmit() {
    var userid = $("#userid").val();
    $.ajax({
        type: "post",
        data: {"userid":userid},
        url: "/test/testQuery",
        success: function (data) {
            $("#username").html(data[0].username);
            $("#password").html(data[0].password);
            $("#sex").html(data[0].sex);
            $("#address").html(data[0].address);
        }
    });
}