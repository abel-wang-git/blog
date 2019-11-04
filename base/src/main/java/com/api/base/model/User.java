package com.api.base.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    @Column(name = "mobile_number")
    private String mobileNumber;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 最后登录的时间
     */
    @Column(name = "latest_login_time")
    private Date latestLoginTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建时间
     */
    private String nickname;

    /**
     * 创建时间
     */
    private String avatar;

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
     * @return mobile_number - 手机
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 设置手机
     *
     * @param mobileNumber 手机
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public Byte getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Byte gender) {
        this.gender = gender;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取最后登录的时间
     *
     * @return latest_login_time - 最后登录的时间
     */
    public Date getLatestLoginTime() {
        return latestLoginTime;
    }

    /**
     * 设置最后登录的时间
     *
     * @param latestLoginTime 最后登录的时间
     */
    public void setLatestLoginTime(Date latestLoginTime) {
        this.latestLoginTime = latestLoginTime;
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
     * 获取创建时间
     *
     * @return nickname - 创建时间
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置创建时间
     *
     * @param nickname 创建时间
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取创建时间
     *
     * @return avatar - 创建时间
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置创建时间
     *
     * @param avatar 创建时间
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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