package com.api.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "article_like")
public class ArticleLike implements Serializable {
    /**
     * 用户id
     */
    @Id
    private Long uid;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 保存时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

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
     * 获取保存时间
     *
     * @return create_time - 保存时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置保存时间
     *
     * @param createTime 保存时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}