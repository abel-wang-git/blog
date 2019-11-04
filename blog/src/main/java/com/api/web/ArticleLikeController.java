package com.api.web;
import com.api.core.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.model.ArticleLike;
import com.api.service.ArticleLikeService;
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
@PowerEnable(name = "文章点赞",url = "/article/like")
@Api(value = "文章点赞", tags = {"文章点赞"})
@RestController
@RequestMapping("/article/like")
public class ArticleLikeController extends Ctrl{
    @Resource
    private ArticleLikeService articleLikeService;

    @ApiOperation(value = "文章点赞添加", tags = {"文章点赞"}, notes = "文章点赞添加")
    @PostMapping(value="/add",name="文章点赞添加")
    public Result add(@ApiParam ArticleLike articleLike) {
        articleLikeService.save(articleLike);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章点赞删除", tags = {"文章点赞"}, notes = "文章点赞删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "文章点赞id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="文章点赞删除")
    public Result delete(@RequestParam Long id) {
        articleLikeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "文章点赞列表信息", tags = {"文章点赞"}, notes = "文章点赞列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "where", value = "条件构建", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query",defaultValue="10")
    })
    @PostMapping(value="/list",name="文章点赞列表信息")
    public Result list(@RequestParam(defaultValue = "[]") String  where ,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        Condition c= new Condition(ArticleLike.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<ArticleLike> list = articleLikeService.findByCondition(c);
        PageInfo<ArticleLike> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
