package com.api.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "article_comment")
public class ArticleComment implements Serializable {
    /**
     * 评论id
     */
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    /**
     * 被评论id
     */
    @Column(name = "p_id")
    private Long pId;

    /**
     * 被评论id
     */
    @Column(name = "article_id")
    private Long articleId;

    /**
     * 保存时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论人
     */
    private Long uid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取评论id
     *
     * @return comment_id - 评论id
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 设置评论id
     *
     * @param commentId 评论id
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取被评论id
     *
     * @return p_id - 被评论id
     */
    public Long getpId() {
        return pId;
    }

    /**
     * 设置被评论id
     *
     * @param pId 被评论id
     */
    public void setpId(Long pId) {
        this.pId = pId;
    }

    /**
     * 获取被评论id
     *
     * @return article_id - 被评论id
     */
    public Long getArticleId() {
        return articleId;
    }

    /**
     * 设置被评论id
     *
     * @param articleId 被评论id
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

    /**
     * 获取评论内容
     *
     * @return comment - 评论内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置评论内容
     *
     * @param comment 评论内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取评论人
     *
     * @return uid - 评论人
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置评论人
     *
     * @param uid 评论人
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }
}