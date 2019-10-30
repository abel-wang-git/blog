package com.api.service.impl;

import com.api.dao.ArticleClassMapper;
import com.api.model.ArticleClass;
import com.api.service.ArticleClassService;
import com.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/10/30.
 */
@Service
@Transactional
public class ArticleClassServiceImpl extends AbstractService<ArticleClass> implements ArticleClassService {
    @Resource
    private ArticleClassMapper articleClassMapper;

}
