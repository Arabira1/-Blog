package com.spring.Util;

import com.spring.Entity.KeyEntity;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.sql.Timestamp;

/**
 * Created by arabira on 17-8-26.
 */
public class RSA {

    //将string类型的私钥转换为私钥类
    private static PrivateKey getPrivateKey(String keyVal) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(keyVal.getBytes("UTF-8"));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
        //使用抽象语法标记编码私钥字符串
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(keySpec);
    }

    //使用私钥解码字符串
    public static String decryptByPrivateKey(String src, String key) throws Exception {
        byte[] bytes = Base64.decodeBase64(src);
        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(key);
        //添加BC类型provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes1 = cipher.doFinal(bytes);
        return new String(bytes1, "UTF-8");
    }

    public static KeyEntity getNewKey() throws Exception {
        //增加BC类型的Provider，否则会报没有Provider错误
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //配置加密强随机生成器
        SecureRandom random = new SecureRandom();
        KeyEntity keyEntity = new KeyEntity();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
        keyPairGenerator.initialize(1024, random);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //提取公钥,进行base64处理
        keyEntity.setKey(new String(Base64.encodeBase64(publicKey.getEncoded()), "UTF-8"));
        //提取私钥，进行base64处理
        keyEntity.setType(new String(Base64.encodeBase64(privateKey.getEncoded()), "UTF-8"));
        keyEntity.setTime(Timestamp.valueOf(LocalDateTime.now()));
        return keyEntity;
    }
}
