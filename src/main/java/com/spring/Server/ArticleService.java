package com.spring.Server;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spring.Dao.ArticleDao;
import com.spring.Dao.TagDao;
import com.spring.Dao.UserDao;
import com.spring.Entity.ArticleEntity;
import com.spring.Entity.TagEntity;
import com.spring.Entity.UserEntity;
import com.sun.istack.internal.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by arabira on 17-8-26.
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    private TagDao tagDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ArticleDao articleDao;

    @Nullable
    private Page<ArticleEntity> checkPageResult(Page<ArticleEntity> page) {
        if (null == page.getResult()) {
            return null;
        }
        else {
            return page;
        }
    }


    public boolean saveTheArticle(@NotNull String author, @NotNull ArticleEntity article) {
        UserEntity check = userDao.findById(author);
        if (null == check) {
            return false;
        }
        article.setAuthor(author);
        articleDao.saveTheArticle(article);
        if (null != article.getTags()) {
            //之后保存文章Id和标签Id
            for (TagEntity tag: article.getTags()) {
                tagDao.saveArticleInfo(tag.getTagName(), article.getId());
            }
        }
        return true;
    }

    public Page<ArticleEntity> findByUserName(final String userName, Integer pageNum, Integer pageSize) {
        if (null == userName) {
            return null;
        }
        Page<ArticleEntity> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> articleDao.findByUserName(userName));
        return checkPageResult(page);
    }

    public Page<ArticleEntity> findByTitle(String title, Integer pageNum, Integer pageSize) {
        if (null == title) {
            return null;
        }
        Page<ArticleEntity> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> articleDao.findByTitle(title));
        return checkPageResult(page);
    }

    public Page<ArticleEntity> findByUserId(String userId, Integer pageNum, Integer pageSize) {
        if (null == userId) {
            return null;
        }
        Page<ArticleEntity> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> articleDao.findByUserId(userId));
        return checkPageResult(page);
    }

    public void updateTheArticle(ArticleEntity articleEntity) {
        ArticleEntity check = articleDao.findById(articleEntity.getId());
        articleDao.updateArticle(articleEntity);
        tagDao.updateTheTag(articleEntity);
    }

    public void deleteTheArticle(String id, String userId) {
        ArticleEntity check = articleDao.findById(id);
        if (null != check || check.getAuthor().equals(userId)) {
            articleDao.deleteArticleById(id);
        }
    }


    public ArticleEntity findById(String id) {
        if (null == id) {
            return null;
        }
        return articleDao.findById(id);
    }
}
