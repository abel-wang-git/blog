package com.api.base.service;

import com.api.base.model.Role;
import com.api.core.Service;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
public interface RoleService extends Service<Role> {

    void addPower(String powers, Long roleId);
}
