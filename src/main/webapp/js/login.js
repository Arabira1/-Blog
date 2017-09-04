/**
 * Created by arabira on 17-9-4.
 */
var key, id;
$(function () {
    $(".modal").animate({
        top:'30%'
    });
    get(false,  url.getKey, '',getKey,function (data) {
        alert(data.message);
    });
});

function getKey(data) {
    key = data.keyType;
    id = data.id;
}

function loginSuccess(data) {
    window.location.href="/html/main.html?userName=" + data.userName;
}

function loginFail(data) {
    alert(data.message);
    document.location.reload();
}

function submitVal() {
    var loginName = $("#loginName").val();
    var password = $("#password").val();
    password = encrypt(password, key);
    if (password == false) {
        return ;
    }
    var user = new Object();
    user.loginName = loginName;
    user.password = password;
    var data = {
        id:id,
        userInfo: user
    };
    post(false, url.login, JSON.stringify(data), loginSuccess, loginFail);
}