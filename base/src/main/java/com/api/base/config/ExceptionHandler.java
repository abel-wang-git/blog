package com.api.base.config;

import com.api.core.ServiceException;
import com.api.core.response.Result;
import com.api.core.response.ResultCode;
import com.api.core.response.ResultGenerator;
import io.lettuce.core.RedisCommandTimeoutException;
import io.lettuce.core.RedisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

@ControllerAdvice
public class ExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler()
    public @ResponseBody
    Result defaultErrorHandler(HttpServletRequest req, Exception e) {
        logger.error(req.getRequestURI(),e);
        if (e instanceof RedisConnectionFailureException) return new Result().setMessage("权限服务器未开启").setCode(ResultCode.FAIL);
        if (e instanceof MaxUploadSizeExceededException) return new Result().setMessage("文件太大无法上传").setCode(ResultCode.FAIL);
        if( e instanceof IllegalArgumentException) return new Result().setMessage(e.getMessage()).setCode(ResultCode.FAIL);
        if (e instanceof MissingServletRequestParameterException) return new Result().setMessage(e.getMessage()).setCode(ResultCode.FAIL);
        if (e instanceof RedisConnectionException) return new Result().setMessage("缓存服务器未开启").setCode(ResultCode.FAIL);
        if (e instanceof RedisCommandTimeoutException) return new Result().setMessage("无法链接缓存服务器").setCode(ResultCode.FAIL);
        if (e instanceof ConnectException) return new Result().setMessage("拒接链接").setCode(ResultCode.FAIL);
        if (e instanceof HttpRequestMethodNotSupportedException) return new Result().setMessage(e.getMessage()).setCode(ResultCode.FAIL);
        if (e instanceof ServiceException) return new Result().setMessage(e.getMessage()).setCode(ResultCode.INTERNAL_SERVER_ERROR);
        if (e instanceof RequestRejectedException) return new Result().setMessage(e.getMessage()).setCode(ResultCode.INTERNAL_SERVER_ERROR);
        return ResultGenerator.genExceptionResult("系统繁忙，请稍后再试");
    }
}
