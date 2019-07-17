package com.api.base.service.impl;

import com.api.base.dao.RoleMapper;
import com.api.base.dao.UserMapper;
import com.api.base.dao.UserRoleMapper;
import com.api.base.model.Role;
import com.api.base.model.User;
import com.api.base.model.UserRole;
import com.api.base.service.UserService;
import com.api.core.AbstractService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Cacheable(cacheNames = "role",key = "#userId")
    public List<Role> getRole(Long userId) {
        return roleMapper.getByUser(userId);
    }

    @Override
    public void addRole(List<UserRole> roles, Long userId) {
        userMapper.deleteRoleById(userId);
        userRoleMapper.insertListNoAuto(roles);
    }
}
