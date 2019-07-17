package com.api.core.response;


/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultMessage.SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultMessage.SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genSuccessResult(Object data, String message) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    public static Result genSuccessResult(Object data, Long count) {

        return new Result.ResultPage().setCount(count)
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultMessage.SUCCESS_MESSAGE)
                .setData(data);

    }


    public static Result genCreatedSuccessResult() {
        return new Result()
                .setCode(ResultCode.CREATED)
                .setMessage(ResultMessage.CREATED);
    }

    public static Result genCreatedSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.CREATED)
                .setMessage(ResultMessage.CREATED)
                .setData(data);
    }

    public static Result genDeleteSuccessResult() {
        return new Result()
                .setCode(ResultCode.DELETED)
                .setMessage(ResultMessage.DELETED);
    }

    public static Result genUploadSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(ResultMessage.UPLOADED);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    public static Result genFailResult(Object data, String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }

    public static Result genFailResult(ResultMessage resultMessage) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(resultMessage);
    }

    public static Result genUnauthorizedResult() {
        return new Result()
                .setCode(ResultCode.UNAUTHORIZED)
                .setMessage(ResultMessage.UNAUTHORIZED);
    }

    public static Result genForbiddenResult() {
        return new Result()
                .setCode(ResultCode.FORBIDDEN)
                .setMessage(ResultMessage.FORBIDDEN);
    }

    public static Result genExceptionResult(String message) {
        return new Result()
                .setCode(ResultCode.INTERNAL_SERVER_ERROR)
                .setMessage(message);
    }

    public static Result genPaymentErrorResult(String message) {
        return new Result()
                .setCode(ResultCode.PAYMENT_ERROR)
                .setMessage(message);
    }

    public static Result genPaymentErrorResult(ResultMessage resultMessage) {
        return new Result()
                .setCode(ResultCode.PAYMENT_ERROR)
                .setMessage(resultMessage);
    }

    public static Result genResult(ResultCode code,ResultMessage message,Object data) {
        return new Result()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
