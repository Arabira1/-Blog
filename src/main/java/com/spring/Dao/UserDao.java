package com.spring.Dao;

import com.spring.Entity.KeyEntity;
import com.spring.Entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by arabira on 17-5-27.
 */
@Repository
public interface UserDao {
    public void addUser(UserEntity userEntity);
    public void updateUser(UserEntity userEntity);
    public void updatePassword(String userId, String password);
    public UserEntity findById(String userId);
    public UserEntity findByLoginName(String loginName);
    public List<String> findIdByUserName(String userName);

    public void updateUserInfo(UserEntity user);
    public List<Map> test(String sqlvalue) ;
}
