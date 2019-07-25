package ${basePackage}.web;
import ${baseCore}.Ctrl;
import ${baseCore}.response.Result;
import ${baseCore}.response.ResultGenerator;
import ${basePackage}.model.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
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
* Created by ${author} on ${date}.
*/
@PowerEnable(name = "${businessName}",url = "${baseRequestMapping}")
@Api(value = "${businessName}", tags = {"${businessName}"})
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller extends Ctrl{
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation(value = "${businessName}添加", tags = {"${businessName}"}, notes = "${businessName}添加")
    @PostMapping("/add")
    public Result add(@ApiParam ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "${businessName}删除", tags = {"${businessName}"}, notes = "${businessName}删除")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "id",required=true, value = "${businessName}id", dataType = "Long", paramType = "query")
    })
    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "${businessName}修改", tags = {"${businessName}"}, notes = "${businessName}修改,对象主键必填")
    @PostMapping("/update")
    public Result update(@ApiParam ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "${businessName}详细信息", tags = {"${businessName}"}, notes = "${businessName}详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required=true, value = "${businessName}id", dataType = "Long", paramType = "query")
    })
    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
    }

    @ApiOperation(value = "${businessName}列表信息", tags = {"${businessName}"}, notes = "${businessName}列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "where", value = "条件构建", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "page", value = "页码", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "size", value = "每页显示的条数", dataType = "String", paramType = "query",defaultValue="10")
    })
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "[]") String  where ,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        Condition c= new Condition(${modelNameUpperCamel}.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findByCondition(c);
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
