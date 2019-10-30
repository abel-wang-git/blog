package com.api.service.impl;

import com.api.dao.ArticleContentMapper;
import com.api.model.ArticleContent;
import com.api.service.ArticleContentService;
import com.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/10/30.
 */
@Service
@Transactional
public class ArticleContentServiceImpl extends AbstractService<ArticleContent> implements ArticleContentService {
    @Resource
    private ArticleContentMapper articleContentMapper;

}
