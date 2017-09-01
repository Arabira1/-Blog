package com.spring.Controller;

import com.github.pagehelper.Page;
import com.spring.Entity.ArticleEntity;
import com.spring.Entity.RequestEntity;
import com.spring.Server.ArticleService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by arabira on 17-9-1.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    private Map<String, Object> resultMap;

    private void getThePage(Page<ArticleEntity> articles) {
        if (null != articles) {
            resultMap.put("status", "OK");
            resultMap.put("article", articles.getResult());
            resultMap.put("pageSum", articles.getPages());
            resultMap.put("pageNumber", articles.getPageNum());
        }
        else {
            resultMap.put("status", "OK");
            resultMap.put("article", null);
            resultMap.put("pageSum", 0);
            resultMap.put("pageNumber", 1);
        }
    }


    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public Map<String, Object> addTheArticle(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String userId = requestEntity.getUserInfo().getUserId();
        ArticleEntity articleEntity = requestEntity.getArticle();
        if (articleService.saveTheArticle(userId, articleEntity)) {
            resultMap.put("status", "OK");
        }
        else {
            resultMap.put("status", "error");
        }
        return resultMap;
    }

    @RequestMapping(value = "/userName/list", method = RequestMethod.GET)
    public Map<String, Object> findArticleByUser(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String userName = requestEntity.getUserInfo().getUserName();
        Integer pageNum = requestEntity.getPageNum();
        Integer pageSize = requestEntity.getPageSize();
        Page<ArticleEntity> articles = null;
        articles = articleService.findByUserName(userName, pageNum, pageSize);
        getThePage(articles);
        return resultMap;
    }

    @RequestMapping(value = "/title/list", method = RequestMethod.GET)
    public Map<String, Object> findArticleByTitle(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String title = requestEntity.getArticle().getTitle();
        Integer pageNum = requestEntity.getPageNum();
        Integer pageSize = requestEntity.getPageSize();
        Page<ArticleEntity> articles = articleService.findByTitle(title, pageNum, pageSize);
        getThePage(articles);
        return resultMap;
    }

    @RequestMapping(value = "/userId/list", method = RequestMethod.GET)
    public Map<String, Object> findByUserId(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String userId = requestEntity.getUserInfo().getUserId();
        Integer pageNum = requestEntity.getPageNum();
        Integer pageSize = requestEntity.getPageSize();
        Page<ArticleEntity> page = articleService.findByUserId(userId, pageNum, pageSize);
        getThePage(page);
        return resultMap;
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Map<String, Object> findById(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String id = requestEntity.getArticle().getId();
        ArticleEntity articleEntity = articleService.findById(id);
        resultMap.put("status", "OK");
        resultMap.put("article", articleEntity);
        return resultMap;
    }

    @RequestMapping(value = "/this", method = RequestMethod.PUT)
    public Map<String, Object> updateTheContent(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        ArticleEntity articleEntity = requestEntity.getArticle();
        articleService.updateTheArticle(articleEntity);
        resultMap.put("status", "OK");
        return resultMap;
    }

    @RequestMapping(value = "/own", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTheArticle(@NotNull RequestEntity requestEntity) {
        resultMap = new HashMap<String, Object>();
        String id = requestEntity.getArticle().getId();
        String userId = requestEntity.getUserInfo().getUserId();
        articleService.deleteTheArticle(id, userId);
        resultMap.put("status", "OK");
        return resultMap;
    }
}

