package com.api.base.controll;

import com.alibaba.fastjson.JSON;
import com.api.base.config.auth.AuthUser;
import com.api.base.config.auth.JwtTokenUtil;
import com.api.base.model.User;
import com.api.base.model.UserRole;
import com.api.base.service.UserService;
import com.api.common.wx.WxCommon;
import com.api.core.Ctrl;
import com.api.core.annotation.PowerEnable;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@PowerEnable(name = "账号管理",url = "/user")
@Api(value = "账号管理", tags = {"账号管理"})
@RestController
@RequestMapping(value = "/user")
public class UserController extends Ctrl {
    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = "用户添加", tags = {"账号管理"}, notes = "用户添加")
    @PostMapping(value = "/add", name = "用户添加")
    public Result add(User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "用户列表", tags = {"账号管理"}, notes = "用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "where", value = "条件json", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", dataType = "Integer", paramType = "query"),
    })
    @PostMapping(value = "/list", name = "用户列表")
    @ResponseBody
    public Result list(@RequestParam(defaultValue = "[]") String where,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);

        Condition c = new Condition(User.class);
        Example.Criteria criteria = c.createCriteria();

        buildWhere(where, criteria);

        List<User> list = userService.findByCondition(c);
        PageInfo pageInfo = new PageInfo<>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @ApiOperation(value = "用户添加角色", tags = {"账号管理"}, notes = "用户添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roles", value = "角色json", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", paramType = "query"),
    })
    @PostMapping(value = "/add/role", name = "用户添加角色")
    @Caching(evict = {@CacheEvict(value = "role", key = "#userId"), @CacheEvict(value = "power", key = "#userId")})
    public Result addRole(String roles, Long userId) {
        userService.addRole(JSON.parseArray(roles, UserRole.class), userId);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "获取登录用户信息", tags = {"账号管理"}, notes = "获取登录用户信息")
    @PostMapping(value = "get", name = "获取登录用户信息")
    public Result get(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        authUser.setPassword("");
        return ResultGenerator.genSuccessResult(authUser);
    }

    /**
     * 绑定微信
     *
     * @param openid
     * @param authentication
     * @return
     */
    @ApiOperation(value = "绑定微信", tags = {"账号管理"}, notes = "绑定微信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "wx/bind", name = "绑定微信")
    public Result bindWx(@RequestParam String openid, Authentication authentication) {

        AuthUser authUser = (AuthUser) authentication.getPrincipal();

        User user = userService.findById(authUser.getId());

        if (user.getOpenid() != null) return ResultGenerator.genFailResult("用户以绑定微信");

        user.setOpenid(openid);

        userService.update(user);

        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "根据openid刷新token", tags = {"账号管理"}, notes = "根据open刷新token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "openid", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "refresh/token", name = "根据open刷新token")
    public Result getTokenByOpenId(@RequestParam String openid) {

        User user = userService.findBy("openid", openid);

        if (user == null) return ResultGenerator.genFailResult("用户不存在");
        Map<String, Object> res = new HashMap<>();

        String jwtToken = jwtTokenUtil.generateToken(user.getUsername());

        res.put("token", jwtToken);

        return ResultGenerator.genSuccessResult(res);
    }


    @ApiOperation(value = "获取openid", tags = {"账号管理"}, notes = "获取openid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appid", value = "appid", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "secret", value = "secret", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "jscode", value = "jscode", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "get/openid", name = "获取openId")
    public Result getOpenId(@RequestParam String appid,
                            @RequestParam String secret,
                            @RequestParam String jscode) {

        Map<String, Object> res = WxCommon.getOpenId(appid, secret, jscode);
        return ResultGenerator.genSuccessResult(res);
    }

}
