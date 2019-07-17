package com.api.base.service;

import com.api.base.model.Power;
import com.api.common.mybatis.ResultMap;
import com.api.core.Service;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
public interface PowerService extends Service<Power> {

    @Override

    List<Power> findAll();

    List<ResultMap<String,Object>> listAll();

    List<Power>  getByRole(Long roleId);
}
