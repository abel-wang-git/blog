package com.api.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "article_class")
public class ArticleClass implements Serializable {
    /**
     * 分类id
     */
    @Id
    @Column(name = "class_id")
    private Long classId;

    /**
     * 分类名称
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 用户id
     */
    private Long uid;

    private static final long serialVersionUID = 1L;

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
     * 获取分类名称
     *
     * @return class_name - 分类名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置分类名称
     *
     * @param className 分类名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

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
}