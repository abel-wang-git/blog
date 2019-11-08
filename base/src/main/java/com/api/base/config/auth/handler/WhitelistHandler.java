package com.api.base.config.auth.handler;

import com.api.base.model.SysWhitelist;
import com.api.base.service.SysWhitelistService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WhitelistHandler {
    @Resource
    private SysWhitelistService sysWhitelistService;

    public HttpSecurity handle(HttpSecurity http) throws Exception {
        List<SysWhitelist> whitelists=sysWhitelistService.findAll();
        List<String> w= whitelists.stream()
                .map(SysWhitelist::getUrl)
                .collect(Collectors.toList());
        String[] liString = w.toArray(new String[0]);
        return http
                .authorizeRequests()
                .regexMatchers(liString)
                .permitAll()
                .and();
    }
}
