package com.api.service;
import com.api.core.response.Result;
import com.api.model.ArticleContent;
import com.api.model.ArticleList;
import com.api.core.Service;


/**
 * Created by CodeGenerator on 2019/10/30.
 */
public interface ArticleListService extends Service<ArticleList> {

    Result add(ArticleList list, ArticleContent content);
}
