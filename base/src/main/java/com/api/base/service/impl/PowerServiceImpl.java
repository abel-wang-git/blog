package com.api.base.service.impl;

import com.api.base.dao.PowerMapper;
import com.api.base.model.Power;
import com.api.base.service.PowerService;
import com.api.common.mybatis.ResultMap;
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
public class PowerServiceImpl extends AbstractService<Power> implements PowerService {
    @Resource
    private PowerMapper powerMapper;

    @Override
    @Cacheable(cacheNames = "power",key = "'all'")
    public List<Power> findAll() {
        return super.findAll();
    }

    @Override
    public List<ResultMap<String, Object>> listAll() {
        return powerMapper.listAll();
    }

    @Override
    @Cacheable(cacheNames = "power",key = "#roleId")
    public List<Power>  getByRole(Long roleId) {
        return powerMapper.getByRole(roleId);
    }
}
