package com.api.base.service.impl;

import com.api.base.dao.SysWhitelistMapper;
import com.api.base.model.SysWhitelist;
import com.api.base.service.SysWhitelistService;
import com.api.core.AbstractService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/11/08.
 */
@Service
@Transactional
public class SysWhitelistServiceImpl extends AbstractService<SysWhitelist> implements SysWhitelistService {
    @Resource
    private SysWhitelistMapper sysWhitelistMapper;

    @Override
    @Cacheable(cacheNames = "whiteList",key = "'all'")
    public List<SysWhitelist> selectAll() {
        return sysWhitelistMapper.selectAll();
    }
}
