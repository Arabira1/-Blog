package com.spring.Server;

import com.spring.Dao.KeyDao;
import com.spring.Dao.UserDao;
import com.spring.Entity.KeyEntity;
import com.spring.Entity.UserEntity;
import com.spring.Util.MD5Util;
import com.spring.Util.RSA;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arabira on 17-5-29.
 */
@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private KeyDao keyDao;

    private boolean mailMatch(String mailAddr) {
        Pattern pattern = Pattern.compile("[0-9a-zA-Z]*@[0-9a-z]*.com");
        Matcher matcher = pattern.matcher(mailAddr);
        return matcher.matches();
    }

    private boolean phoneMath(String phoneNum) {
        return Pattern.matches("[0-9]{11}", phoneNum);
    }

    //判断加密密钥是否有效，若超过10分钟密钥失效
    private KeyEntity isAlive(Integer id) {
        KeyEntity key = keyDao.findKey(id);
        if (null == key){
            return null;
        }
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        //检测是否超过10分钟
        if (now.getTime() - key.getTime().getTime() > 10*60*1000) {
            keyDao.delTheKey(key);
            return null;
        }
        return key;
    }

    public KeyEntity getNewKey() throws Exception {
        KeyEntity keyVal = RSA.getNewKey();
        keyVal.setTime(Timestamp.valueOf(LocalDateTime.now()));
        keyDao.saveTheKey(keyVal);
        return keyVal;
    }
    public String login(@NotNull String loginName, @NotNull String password, Integer Id) throws Exception {
        KeyEntity key = isAlive(Id);
        if (null == key) {
            return "登录超时";
        }
        else {
            password = RSA.decryptByPrivateKey(password, key.getType());
            UserEntity user = userDao.findByLoginName(loginName);
            keyDao.delTheKey(key);
            if (null == user) {
                return "用户不存在";
            }
            if (user.getPassword().equals(MD5Util.calc(password, user.getPriSalt()))) {
                return null;
            }
            else {
                return "密码有误";
            }
        }
    }

    public boolean checkLoginName(@NotNull String loginName) {
        UserEntity user = userDao.findByLoginName(loginName);
        if (null != user) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean signIn(@NotNull UserEntity userEntity, Integer id, HttpServletRequest request) throws Exception {
        KeyEntity keyEntity = keyDao.findKey(id);
        if (!checkLoginName(userEntity.getLoginName()) ||
            !mailMatch(userEntity.getMail()) ||
            !phoneMath(userEntity.getPhone()) ||
            null == keyEntity) {
            return false;
        }
        userEntity.setPriSalt(RandomStringUtils.random(8));
        if (null == userEntity.getPassword()) {
            userEntity.setPassword(MD5Util.calc("123456", userEntity.getPriSalt()));
        }
        else {
            //对前端加密进行解密
            userEntity.setPassword(RSA.decryptByPrivateKey(userEntity.getPassword(), keyEntity.getType()));
            //对用户明文密码进行加密处理
            userEntity.setPassword(MD5Util.calc(userEntity.getPassword(), userEntity.getPriSalt()));
            keyDao.delTheKey(keyEntity);
        }
        userDao.addUser(userEntity);
        return true;
    }

    public boolean updatePassword(@NotNull String userId, @NotNull String oldPassword, @NotNull String newPassword, Integer id) throws Exception {
        KeyEntity key = isAlive(id);
        UserEntity user = userDao.findById(userId);
        if (null == key || null == user) {
            return false;
        }
        //对前端加密进行解密
        oldPassword = RSA.decryptByPrivateKey(oldPassword, key.getType());
        newPassword = RSA.decryptByPrivateKey(newPassword, key.getType());
        if (!user.getPassword().equals(MD5Util.calc(oldPassword, user.getPriSalt()))) {
            keyDao.delTheKey(key);
            return false;
        }
        else {
            userDao.updatePassword(userId, MD5Util.calc(newPassword, user.getPriSalt()));
            keyDao.delTheKey(key);
            return true;
        }
    }

    public boolean updateUserInfo(@NotNull UserEntity user) {
        if (null == user.getUserId()) {
            return false;
        }
        user.setPassword(null);
        userDao.updateUserInfo(user);
        return true;
    }
}
