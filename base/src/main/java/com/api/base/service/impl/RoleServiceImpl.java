package com.api.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.api.base.dao.RoleMapper;
import com.api.base.dao.RolePowerMapper;
import com.api.base.model.Role;
import com.api.base.model.RolePower;
import com.api.base.service.RoleService;
import com.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePowerMapper rolePowerMapper;

    @Override
    @Transactional
    public void addPower(String powers,Long roleId) {
        roleMapper.deletePower(roleId);
        List<Long> powerids = JSON.parseArray(powers,Long.class);

        List<RolePower> rolePowers = new ArrayList<>();
        for (Long pid: powerids) {
            RolePower rolePower = new RolePower();
            rolePower.setPowerId(pid);
            rolePower.setRoleId(roleId);
            rolePowers.add(rolePower);
        }
        if(rolePowers.size()>0)
            rolePowerMapper.insertListNoAuto(rolePowers);
    }
}
