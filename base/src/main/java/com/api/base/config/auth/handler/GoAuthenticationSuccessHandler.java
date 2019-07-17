package com.api.base.config.auth.handler;

import com.alibaba.fastjson.JSON;
import com.api.base.config.auth.AuthUser;
import com.api.base.config.auth.JwtTokenUtil;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private JwtTokenUtil jwtTokenUtil;

    public GoAuthenticationSuccessHandler(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");

        AuthUser userDetails = (AuthUser) authentication.getPrincipal();
        Map<String, Object> res = new HashMap<>();

        String jwtToken = jwtTokenUtil.generateToken(userDetails.getUsername());
        userDetails.setPassword("");
        res.put("info",userDetails);
        res.put("token", jwtToken);

        Result result = ResultGenerator.genSuccessResult(res, "登录成功");

        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
        httpServletResponse.getWriter().flush();

    }


}
