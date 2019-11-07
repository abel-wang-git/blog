package com.api.base.controll;


import com.api.base.dto.ElTree;
import com.api.base.model.Power;
import com.api.base.service.PowerService;
import com.api.core.Ctrl;
import com.api.core.annotation.PowerEnable;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@PowerEnable(name = "权限管理",url = "/power")
@Api(value = "权限管理", tags = {"权限管理"})
@RestController
@RequestMapping("/power")
public class PowerController extends Ctrl {
    @Resource
    private PowerService powerService;

    @ApiOperation(value = "权限树列表", tags = {"权限管理"}, notes = "权限树列表")
    @PostMapping(value = "/list", name = "权限树列表")
    public Result list() {
        List<Power> powers = powerService.findAll();

        Map<Integer, List<Power>> res = powers.stream().collect(Collectors.groupingBy(Power::getPid));

        List<Power> parent = res.get(0);


        List<ElTree<Power>> elTrees = new ArrayList<>();

        for (Power p: parent) {
            ElTree<Power> elTree = new ElTree<>();
            elTree.setId(p.getId());
            elTree.setName(p.getName());
            elTree.setChildren(new ArrayList<>());
            elTrees.add(elTree);
        }

        for (Integer key:res.keySet()) {
            if(key!=0){
                for (ElTree<Power> e:elTrees) {
                    if(e.getId().equals(key)){
                       e.getChildren().addAll(res.get(key));
                    }
                }
            }
        }

        return ResultGenerator.genSuccessResult(elTrees);
    }
}
