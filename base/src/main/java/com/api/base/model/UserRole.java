package com.api.base.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "sys_user_role")
public class UserRole implements Serializable {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}