package com.api.service.impl;

import com.api.dao.ArticleListMapper;
import com.api.model.ArticleList;
import com.api.service.ArticleListService;
import com.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/10/30.
 */
@Service
@Transactional
public class ArticleListServiceImpl extends AbstractService<ArticleList> implements ArticleListService {
    @Resource
    private ArticleListMapper articleListMapper;

}
