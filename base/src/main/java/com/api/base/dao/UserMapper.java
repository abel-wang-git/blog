package com.api.base.dao;

import com.api.base.model.User;
import com.api.core.Mapper;

public interface UserMapper extends Mapper<User> {

    void deleteRoleById(Long userId);
}