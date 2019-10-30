package com.api.web;
import com.api.core.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.model.ArticleList;
import com.api.service.ArticleListService;
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
@PowerEnable(name = "文章列表信息",url = "/article/list")
@Api(value = "文章列表信息", tags = {"文章列表信息"})
@RestController
@RequestMapping("/article/list")
public class ArticleListController extends Ctrl{
    @Resource
    private ArticleListService articleListService;

    @ApiOperation(value = "文章列表信息添加", tags = {"文章列表信息"}, notes = "文章列表信息添加")
    @PostMapping(value="/add",name="文章列表信息添加")
    public Result add(@ApiParam ArticleList articleList) {
        articleListService.save(articleList);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章列表信息删除", tags = {"文章列表信息"}, notes = "文章列表信息删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "文章列表信息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="文章列表信息删除")
    public Result delete(@RequestParam Long id) {
        articleListService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章列表信息修改", tags = {"文章列表信息"}, notes = "文章列表信息修改,对象主键必填")
    @PostMapping(value="/update",name="文章列表信息修改")
    public Result update(@ApiParam ArticleList articleList) {
        articleListService.update(articleList);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章列表信息详细信息", tags = {"文章列表信息"}, notes = "文章列表信息详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "文章列表信息id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/detail",name="文章列表信息详细信息")
    public Result detail(@RequestParam Integer id) {
        ArticleList articleList = articleListService.findById(id);
        return ResultGenerator.genSuccessResult(articleList);
    }

    @ApiOperation(value = "文章列表信息列表信息", tags = {"文章列表信息"}, notes = "文章列表信息列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "where", value = "条件构建", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query",defaultValue="10")
    })
    @PostMapping(value="/list",name="文章列表信息列表信息")
    public Result list(@RequestParam(defaultValue = "[]") String  where ,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        Condition c= new Condition(ArticleList.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<ArticleList> list = articleListService.findByCondition(c);
        PageInfo<ArticleList> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
