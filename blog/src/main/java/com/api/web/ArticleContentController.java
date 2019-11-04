package com.api.web;

import com.api.core.Ctrl;
import com.api.core.annotation.PowerEnable;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.model.ArticleContent;
import com.api.service.ArticleContentService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



/**
* Created by CodeGenerator on 2019/10/30.
*/
@PowerEnable(name = "文章内容",url = "/article/content")
@Api(value = "文章内容", tags = {"文章内容"})
@RestController
@RequestMapping("/article/content")
public class ArticleContentController extends Ctrl{
    @Resource
    private ArticleContentService articleContentService;

    @ApiOperation(value = "文章内容修改", tags = {"文章内容"}, notes = "文章内容修改,对象主键必填")
    @PostMapping(value="/update",name="文章内容修改")
    public Result update(@ApiParam ArticleContent articleContent) {
        articleContentService.update(articleContent);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章内容详细信息", tags = {"文章内容"}, notes = "文章内容详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "文章内容id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="文章内容详细信息")
    public Result detail(@RequestParam Integer id) {
        ArticleContent articleContent = articleContentService.findById(id);
        return ResultGenerator.genSuccessResult(articleContent);
    }
}
