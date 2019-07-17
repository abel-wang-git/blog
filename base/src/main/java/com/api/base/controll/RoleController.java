package com.api.base.controll;


import com.api.base.model.Role;
import com.api.base.service.PowerService;
import com.api.base.service.RoleService;
import com.api.core.Ctrl;
import com.api.core.annotation.PowerEnable;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
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


/**
* Created by CodeGenerator on 2019/03/25.
*/
@PowerEnable(name = "角色管理",url = "/role")
@RestController
@RequestMapping("/role")
public class RoleController extends Ctrl {
    @Resource
    private RoleService roleService;
    @Resource
    private PowerService powerService;

    @PostMapping(value = "/add",name = "添加角色")
    public Result add(Role role) {
        roleService.save(role);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping(value = "/delete",name = "删除角色")
    public Result delete(@RequestParam Integer id) {
        roleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping(value = "/list",name = "角色列表")
    public Result list(@RequestParam String  where , @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);

        Condition c= new Condition(Role.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<Role> list = roleService.findByCondition(c);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping(value = "/add/power",name = "角色添加权限")
    @CacheEvict(value = "power",key = "#roleId")
    public Result addPower(String powers, Long roleId){

        roleService.addPower( powers,roleId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping(value = "/get/power",name = "获取角色权限")
    public Result getPowers(Long roleId){
        List powers =  powerService.getByRole(roleId);
        return ResultGenerator.genSuccessResult(powers);
    }
}
