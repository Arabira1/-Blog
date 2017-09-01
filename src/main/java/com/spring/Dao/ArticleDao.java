package com.spring.Dao;

import com.spring.Entity.ArticleEntity;
import com.spring.Entity.TagEntity;
import com.spring.Entity.UserEntity;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by arabira on 17-5-27.
 */
@Repository
public interface ArticleDao {
    public void addArticle(ArticleEntity articleEntity);
    public void deleteArticleById(String articleId);
    public void updateArticle(ArticleEntity articleEntity);
    public ArticleEntity findById(String articleId);
    public List<ArticleEntity> findByTitle(String articleTitle);

    public void saveTheArticle(ArticleEntity articleEntity);

    public List<ArticleEntity> findByUserName(String userId);

    public void updateArticleTag(ArticleEntity articleEntity);

    public List<ArticleEntity> findByUserId(String userId);
}
