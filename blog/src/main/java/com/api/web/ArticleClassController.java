package com.api.web;
import com.api.core.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.model.ArticleClass;
import com.api.service.ArticleClassService;
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
@PowerEnable(name = "文章类型",url = "/article/class")
@Api(value = "文章类型", tags = {"文章类型"})
@RestController
@RequestMapping("/article/class")
public class ArticleClassController extends Ctrl{
    @Resource
    private ArticleClassService articleClassService;

    @ApiOperation(value = "文章类型添加", tags = {"文章类型"}, notes = "文章类型添加")
    @PostMapping(value="/add",name="文章类型添加")
    public Result add(@ApiParam ArticleClass articleClass) {
        articleClassService.save(articleClass);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章类型删除", tags = {"文章类型"}, notes = "文章类型删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "文章类型id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="文章类型删除")
    public Result delete(@RequestParam Long id) {
        articleClassService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章类型修改", tags = {"文章类型"}, notes = "文章类型修改,对象主键必填")
    @PostMapping(value="/update",name="文章类型修改")
    public Result update(@ApiParam ArticleClass articleClass) {
        articleClassService.update(articleClass);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章类型列表信息", tags = {"文章类型"}, notes = "文章类型列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "where", value = "条件构建", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query",defaultValue="10")
    })
    @PostMapping(value="/list",name="文章类型列表信息")
    public Result list(@RequestParam(defaultValue = "[]") String  where ,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        Condition c= new Condition(ArticleClass.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<ArticleClass> list = articleClassService.findByCondition(c);
        PageInfo<ArticleClass> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
