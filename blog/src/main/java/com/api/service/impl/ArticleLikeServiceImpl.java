package com.api.service.impl;

import com.api.dao.ArticleLikeMapper;
import com.api.model.ArticleLike;
import com.api.service.ArticleLikeService;
import com.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/10/30.
 */
@Service
@Transactional
public class ArticleLikeServiceImpl extends AbstractService<ArticleLike> implements ArticleLikeService {
    @Resource
    private ArticleLikeMapper articleLikeMapper;

}
