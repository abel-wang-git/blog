package com.api.service.impl;

import com.api.dao.ArticleCommentMapper;
import com.api.model.ArticleComment;
import com.api.service.ArticleCommentService;
import com.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/10/30.
 */
@Service
@Transactional
public class ArticleCommentServiceImpl extends AbstractService<ArticleComment> implements ArticleCommentService {
    @Resource
    private ArticleCommentMapper articleCommentMapper;

}
