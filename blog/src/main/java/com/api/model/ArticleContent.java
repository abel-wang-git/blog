package com.api.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "article_content")
public class ArticleContent implements Serializable {
    /**
     * 文章id
     */
    @Id
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 文章内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 获取文章id
     *
     * @return article_id - 文章id
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置文章id
     *
     * @param articleId 文章id
     */
    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}