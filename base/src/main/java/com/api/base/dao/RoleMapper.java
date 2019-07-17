package com.api.base.dao;

import com.api.base.model.Role;
import com.api.core.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {
    List<Role> getByUser(Long userId);

    void deletePower(Long roleId);
}