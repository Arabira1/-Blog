package com.spring.Dao;

import com.spring.Entity.ArticleEntity;
import com.spring.Entity.TagEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by arabira on 17-5-29.
 */
@Repository
public interface TagDao {
    public void addTag(TagEntity tagEntity);

    public void saveArticleInfo(String tagName, String articleId);

    public void deleteTag(String tagName);

    public void updateTag(TagEntity tagEntity);

    public List<String> getTheListById(String articleId);

    public void updateTheTag(ArticleEntity articleEntity);

    public void deleteTheTag(String articleId);

    public void insertTheTag(String tagName, String articleId);

}
