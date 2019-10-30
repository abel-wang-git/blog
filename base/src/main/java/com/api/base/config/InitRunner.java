package com.api.base.config;

import com.api.base.model.Power;
import com.api.base.model.Role;
import com.api.base.service.PowerService;
import com.api.base.service.RoleService;
import com.api.core.annotation.PowerEnable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by wanghuiwen on 17-2-12.
 * 服务启动执行
 */
@Component
public class InitRunner implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private PowerService powerService;
    @Resource
    private RoleService roleService;

    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;


    public void run(String... strings) {
        initPower();
        initRole();
    }

    private void initRole() {
        for (Role r : ProjectConstant.initRole) {
            if (roleService.findBy("description", ProjectConstant.ROLE_ADMIN) == null)
                roleService.save(r);
        }
    }

    private void initPower() {
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Power power = new Power();
            Power parent ;

            String parentName = "";
            String parentUrl = "";
            if (m.getValue().getMethod().getDeclaringClass().isAnnotationPresent(PowerEnable.class)) {
                parentName = m.getValue().getMethod().getDeclaringClass().getAnnotation(PowerEnable.class).name(); // 类名
                parentUrl = m.getValue().getMethod().getDeclaringClass().getAnnotation(PowerEnable.class).url(); // 类名

            }
            if (!StringUtils.isEmpty(parentName)) {
                parent = powerService.findBy("url", parentUrl);
                if (parent == null) {
                    parent = new Power();
                    parent.setName(parentName);
                    parent.setUrl(parentUrl);
                    parent.setPid(0);
                    powerService.save(parent);
                }
            }else {
                parent=null;
            }


            PatternsRequestCondition p = m.getKey().getPatternsCondition();
            for (String url : p.getPatterns()) {
                if (StringUtils.isEmpty(url)) continue;
                power.setUrl(url);
            }

            Power old = powerService.findBy("url", power.getUrl());
            power.setName(m.getKey().getName() == null ? power.getUrl() : m.getKey().getName());
            power.setPid(parent==null? -1 :parent.getId());
            if (old == null) {
                powerService.save(power);
            } else {
                old.setPid(parent==null? -1 :parent.getId());
                old.setName(m.getKey().getName());
                powerService.update(old);
            }
        }
    }


}
