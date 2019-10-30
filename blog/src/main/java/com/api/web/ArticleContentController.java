package com.api.web;
import com.api.core.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.model.ArticleContent;
import com.api.service.ArticleContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import javax.annotation.Resource;
import java.util.List;
import com.api.core.annotation.PowerEnable;
import io.swagger.annotations.*;



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

    @ApiOperation(value = "文章内容添加", tags = {"文章内容"}, notes = "文章内容添加")
    @PostMapping(value="/add",name="文章内容添加")
    public Result add(@ApiParam ArticleContent articleContent) {
        articleContentService.save(articleContent);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章内容删除", tags = {"文章内容"}, notes = "文章内容删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "文章内容id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="文章内容删除")
    public Result delete(@RequestParam Long id) {
        articleContentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

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

    @ApiOperation(value = "文章内容列表信息", tags = {"文章内容"}, notes = "文章内容列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "where", value = "条件构建", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query",defaultValue="10")
    })
    @PostMapping(value="/list",name="文章内容列表信息")
    public Result list(@RequestParam(defaultValue = "[]") String  where ,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        Condition c= new Condition(ArticleContent.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<ArticleContent> list = articleContentService.findByCondition(c);
        PageInfo<ArticleContent> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
