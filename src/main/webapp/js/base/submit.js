/**
 * Created by arabira on 17-9-4.
 */
function get(async, url, data, success, fail) {
    $.ajax ({
        type:"GET",
        async:async,
        url:url,
        dataType:"json",
        contentType:'application/json;charset=UTF-8',
        data:data,
        success:function (data) {
            if (data.status == "OK") {
                success(data);
            }
            else {
                fail(data);
            }
        },
        error:fail
    });
}

function post(async, url, data, success, fail) {
    $.ajax({
        type:"POST",
        async:async,
        url:url,
        dataType:"json",
        contentType:'application/json;charset=UTF-8',
        data:data,
        success:function (data) {
            if (data.status == "OK") {
                success(data);
            }
            else {
                fail(data);
            }
        },
        error:fail
    });
}