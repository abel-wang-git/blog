package com.api.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "article_list")
public class ArticleList implements Serializable {
    /**
     * 文章id
     */
    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 分类id
     */
    @Column(name = "class_id")
    private Long classId;

    /**
     * 是否置顶（0否1是）
     */
    private Boolean top;

    /**
     * 列表图片URL
     */
    @Column(name = "cover_picture")
    private String coverPicture;

    /**
     * 保存时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章点击数
     */
    private Integer hot;

    /**
     * 文章状态 1,公开 2 私密, 3删除 
     */
    private Byte status;

    /**
     * 文章摘要
     */
    private String digest;

    /**
     * 作者
     */
    private Long uid;

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
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取分类id
     *
     * @return class_id - 分类id
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * 设置分类id
     *
     * @param classId 分类id
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }

    /**
     * 获取是否置顶（0否1是）
     *
     * @return top - 是否置顶（0否1是）
     */
    public Boolean getTop() {
        return top;
    }

    /**
     * 设置是否置顶（0否1是）
     *
     * @param top 是否置顶（0否1是）
     */
    public void setTop(Boolean top) {
        this.top = top;
    }

    /**
     * 获取列表图片URL
     *
     * @return cover_picture - 列表图片URL
     */
    public String getCoverPicture() {
        return coverPicture;
    }

    /**
     * 设置列表图片URL
     *
     * @param coverPicture 列表图片URL
     */
    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
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
     * 获取文章点击数
     *
     * @return hot - 文章点击数
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * 设置文章点击数
     *
     * @param hot 文章点击数
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }

    /**
     * 获取文章状态 1,公开 2 私密, 3删除 
     *
     * @return status - 文章状态 1,公开 2 私密, 3删除 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置文章状态 1,公开 2 私密, 3删除 
     *
     * @param status 文章状态 1,公开 2 私密, 3删除 
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取文章摘要
     *
     * @return digest - 文章摘要
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 设置文章摘要
     *
     * @param digest 文章摘要
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 获取作者
     *
     * @return uid - 作者
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置作者
     *
     * @param uid 作者
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }
}