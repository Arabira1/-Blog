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
    publicKey = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCMlyuBvDx7Xp/gynVXG8R5Ee9pD34sv0bY1Q17QdXNmp2nLthep/6G1yo7t346BZzXxM2kYN8Rf2zzumal0dTdqwqdJzOHBQdVBN0Jm7awOWLT+0SH2d6fpT5H+aYqkn3Y4EfvJ5ahEwJJKqDZDTdmwVxvYK+Q+Gsk8CgrdrNKbQIDAQAB';
    encoding = encrypt("aaaaaaaaaaaaaaaaaaa", publicKey);
    console.log(encoding);
});
function encrypt(content, key) {
    var encryptResult = new JSEncrypt();
    encryptResult.setPublicKey(key);
    var result = encryptResult.encrypt(content);
    return result;
}
