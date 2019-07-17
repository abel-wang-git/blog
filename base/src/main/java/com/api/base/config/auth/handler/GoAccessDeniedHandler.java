package com.api.base.config.auth.handler;

import com.alibaba.fastjson.JSON;
import com.api.core.response.Result;
import com.api.core.response.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class GoAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        Result result = new Result().setMessage("权限不足,无法访问该页面").setCode(ResultCode.FORBIDDEN);
        httpServletResponse.setHeader("Content-Type", "application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
        httpServletResponse.getWriter().flush();
    }
}
