package com.api.base.service;

import com.api.base.model.Role;
import com.api.base.model.User;
import com.api.base.model.UserRole;
import com.api.core.Service;
import com.api.core.response.Result;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
public interface UserService extends Service<User> {

    List<Role> getRole(Long userId);


    Result addRole(List<Long> roles, Long userId);

}
