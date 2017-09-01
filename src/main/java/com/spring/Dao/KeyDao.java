package com.spring.Dao;

import com.spring.Entity.KeyEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * Created by arabira on 17-8-26.
 */
@Repository
public interface KeyDao {
    public void saveTheKey(KeyEntity keyEntity);
    public void delTheKey(KeyEntity key);
    public KeyEntity findKey(Integer Id);
}
