/**
 * Created by arabira on 17-8-29.
 */
var publicKey;
var encoding;
/*function clickThis() {
    alert;
    $.ajax({
        type:'GET',
        url:'http://127.0.0.1:8080/user/index',
        dataType:'json',
        async:false,
        data:'',
        success: function (resultVal) {
            var keytype = resultVal.keyType;
            var id = resultVal.id;
            var encoding = encrypt("aaaaaaaaaaa", keytype);
            var result = {
                src:encoding,
                id:id
            };

            $.ajax({
                type:"GET",
                url:"http://127.0.0.1:8080/user/test",
                dataType:'json',
                async:false,
                data:result,
                success: function () {
                    alert("ok");
                }
            })
        }
    });
}

function encrypt(content, key) {
    var encryptResult = new JSEncrypt();
    encryptResult.setPublicKey(key);
    var result = encryptResult.encrypt(content);
    return result;
}

/!**
 * [decrypt 解密]
 * @return {[type]} [description]
 *!/
function decrypt(content, key) {
    var bytes = CryptoJS.AES.decrypt(content.toString(), key, {
        iv: key,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.ZeroPadding
    });
    var decryptResult = bytes.toString(CryptoJS.enc.Utf8);
    return decryptResul;
}

function encoding(content, key) {
    var result = CryptoJS.AES.encrypt(content, key, {
        iv:key,
        mode:CryptoJS.mode.CBC,
        padding:CryptoJS.pad.ZeroPadding
    });
    return result.toString();
}
function decoding(src, key) {
    var bytes = CryptoJS.AES.decrypt(src.toString(), key, {
        iv:key,
        mode:CryptoJS.mode.CBC,
        padding:CryptoJS.pad.ZeroPadding
    });
    return bytes.toString(CryptoJS.enc.Utf8);
}*/
$(function () {
    publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4ZsH37rvVP4yXN7p7lF5A9ETQ4iToUTImwc4qRrPKdsZvUsQLTTgjsMFb/TxFGxDUs8K0qyEuSvp9dNd51O55Zy9UvEASd29r9HNLSkWop7+O74jmiIMM4d23h9s9JhmCPhqFUYwJlBmRHbtDSOhrRQkapHBW9ut+Trd5NTkfAwIDAQAB';
    encoding = encrypt("aaaaaaaaaaaaaaaaaaa", publicKey);
    console.log(encoding);
});
function encrypt(content, key) {
    var encryptResult = new JSEncrypt();
    encryptResult.setPublicKey(key);
    var result = encryptResult.encrypt(content);
    return result;
}
