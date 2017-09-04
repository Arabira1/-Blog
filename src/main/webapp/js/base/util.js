/**
 * Created by arabira on 17-9-4.
 */
function encrypt(content, key) {
    if (key == undefined || key == null || key == '' ) {
        alert("未获得加密参数");
        return false;
    }
    var encryptResult = new JSEncrypt();
    encryptResult.setPublicKey(key);
    var result = encryptResult.encrypt(content);
    return result;
}
