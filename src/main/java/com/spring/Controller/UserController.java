package com.spring.Controller;

import com.spring.Entity.KeyEntity;
import com.spring.Entity.RequestEntity;
import com.spring.Entity.UserEntity;
import com.spring.Server.UserService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arabira on 17-8-5.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    private Map<String, Object> resultMap;

    @RequestMapping(value = "/key", method = RequestMethod.GET)
    public Map<String, Object> prelogin() throws Exception {
        KeyEntity key = userService.getNewKey();
        resultMap.put("status", "OK");
        resultMap.put("keyType", key.getKey());
        resultMap.put("id", key.getId());
        return resultMap;
    }

    @RequestMapping(value = "/name/check", method = RequestMethod.GET)
    public Map<String, Object> checkLoginName(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String loginName = requestEntity.getContent();
        if (userService.checkLoginName(loginName)) {
            resultMap.put("check", true);
        }
        else {
            resultMap.put("check", false);
        }
        return resultMap;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<String, Object> signIn(@NotNull RequestEntity requestEntity, HttpServletRequest httpServletRequest) {
        resultMap = new HashMap<String, Object>();
        UserEntity userEntity = requestEntity.getUserInfo();
        Integer id = requestEntity.getId();
        if (userService.checkLoginName(userEntity.getLoginName())) {
            try {
                if (userService.signIn(userEntity, id, httpServletRequest)) {
                    resultMap.put("status", "OK");
                }
                else {
                    resultMap.put("status", "error");
                    resultMap.put("message", "填写参数非法");
                }
            } catch (Exception e) {
                resultMap.put("status", "error");
                resultMap.put("description", e.getMessage());
            }
        }
        else {
            resultMap.put("status", "error");
            resultMap.put("description", "账户已存在");
        }
        return resultMap;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@NotNull RequestEntity requestEntity, HttpServletRequest httpServletRequest) {
        resultMap = new HashMap<String, Object>();
        UserEntity userEntity = requestEntity.getUserInfo();
        Integer id = requestEntity.getId();
        try {
            String userId = userService.login(userEntity.getLoginName(), userEntity.getPassword(), id);
            resultMap.put("status", "OK");
            resultMap.put("description", "登录成功");
            resultMap.put("nextUrl", "");
            httpServletRequest.setAttribute("user", userId);
        } catch (Exception e) {
            resultMap.put("status", "error");
            resultMap.put("description", e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/password/change", method = RequestMethod.PUT)
    public Map<String, Object> updateThePassword(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        try {
            UserEntity userEntity = requestEntity.getUserInfo();
            String newPassword = requestEntity.getContent();
            Integer id = requestEntity.getId();
            userService.updatePassword(userEntity.getUserId(), userEntity.getPassword(), newPassword, id);
            resultMap.put("status", "OK");
        } catch (Exception e) {
            resultMap.put("status", "error");
            resultMap.put("message",e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "/info/change", method = RequestMethod.PUT)
    public Map<String,Object> updateTheInfo(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        UserEntity userEntity = requestEntity.getUserInfo();
        userService.updateUserInfo(userEntity);
        resultMap.put("status", "OK");
        return resultMap;
    }
}
