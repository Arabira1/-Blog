package com.spring.Entity;

/**
 * Created by arabira on 17-5-27.
 *
 * 标签实体将对应一张标签名称表
 * 和一张标签-文章映射表，总共
 * 两张表，并最终通过sql来拼接
 * 为此实体
 */
public class TagEntity {
    private String articleId;
    private String tagName;
    private String type;
    private String theFirstLetter;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTheFirstLetter() {
        return theFirstLetter;
    }

    public void setTheFirstLetter(String theFirstLetter) {
        this.theFirstLetter = theFirstLetter;
    }
}
