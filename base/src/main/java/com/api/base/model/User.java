package com.api.base.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    /**
     * 手机
     */
    @Column(name = "info_mobile_number")
    private String infoMobileNumber;

    /**
     * 性别
     */
    @Column(name = "info_gender")
    private Byte infoGender;

    /**
     * 邮箱
     */
    @Column(name = "info_email")
    private String infoEmail;

    /**
     * 最后登录的时间
     */
    @Column(name = "info_latest_login_time")
    private Date infoLatestLoginTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private Byte enable;

    private Byte type;

    private String openid;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机
     *
     * @return info_mobile_number - 手机
     */
    public String getInfoMobileNumber() {
        return infoMobileNumber;
    }

    /**
     * 设置手机
     *
     * @param infoMobileNumber 手机
     */
    public void setInfoMobileNumber(String infoMobileNumber) {
        this.infoMobileNumber = infoMobileNumber;
    }

    /**
     * 获取性别
     *
     * @return info_gender - 性别
     */
    public Byte getInfoGender() {
        return infoGender;
    }

    /**
     * 设置性别
     *
     * @param infoGender 性别
     */
    public void setInfoGender(Byte infoGender) {
        this.infoGender = infoGender;
    }

    /**
     * 获取邮箱
     *
     * @return info_email - 邮箱
     */
    public String getInfoEmail() {
        return infoEmail;
    }

    /**
     * 设置邮箱
     *
     * @param infoEmail 邮箱
     */
    public void setInfoEmail(String infoEmail) {
        this.infoEmail = infoEmail;
    }

    /**
     * 获取最后登录的时间
     *
     * @return info_latest_login_time - 最后登录的时间
     */
    public Date getInfoLatestLoginTime() {
        return infoLatestLoginTime;
    }

    /**
     * 设置最后登录的时间
     *
     * @param infoLatestLoginTime 最后登录的时间
     */
    public void setInfoLatestLoginTime(Date infoLatestLoginTime) {
        this.infoLatestLoginTime = infoLatestLoginTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return enable
     */
    public Byte getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(Byte enable) {
        this.enable = enable;
    }

    /**
     * @return type
     */
    public Byte getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * @return openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }
}