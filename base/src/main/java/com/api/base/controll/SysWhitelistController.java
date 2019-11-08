package com.api.base.controll;
import com.api.core.Ctrl;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.api.base.model.SysWhitelist;
import com.api.base.service.SysWhitelistService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheEvict;
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
* Created by CodeGenerator on 2019/11/08.
*/
@PowerEnable(name = "白名单",url = "/sys/whitelist")
@Api(value = "白名单", tags = {"白名单"})
@RestController
@RequestMapping("/sys/whitelist")
public class SysWhitelistController extends Ctrl{
    @Resource
    private SysWhitelistService sysWhitelistService;

    @ApiOperation(value = "白名单添加", tags = {"白名单"}, notes = "白名单添加")
    @PostMapping(value="/add",name="白名单添加")
    @CacheEvict(value = "whiteList",key = "'all'")
    public Result add(@ApiParam SysWhitelist sysWhitelist) {
        sysWhitelistService.save(sysWhitelist);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "白名单删除", tags = {"白名单"}, notes = "白名单删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "白名单id", dataType = "Long", paramType = "query")
    })
    @PostMapping(value="/delete",name="白名单删除")
    @CacheEvict(value = "whiteList",key = "'all'")
    public Result delete(@RequestParam Long id) {
        sysWhitelistService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "白名单修改", tags = {"白名单"}, notes = "白名单修改,对象主键必填")
    @PostMapping(value="/update",name="白名单修改")
    @CacheEvict(value = "whiteList",key = "'all'")
    public Result update(@ApiParam SysWhitelist sysWhitelist) {
        sysWhitelistService.update(sysWhitelist);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "白名单列表信息", tags = {"白名单"}, notes = "白名单列表信息")
    @PostMapping(value="/list",name="白名单列表信息")
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        List<SysWhitelist> list = sysWhitelistService.findAll();
        PageInfo<SysWhitelist> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
