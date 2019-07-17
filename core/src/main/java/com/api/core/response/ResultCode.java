package com.api.core.response;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//服务器成功返回用户请求的数据
    CREATED(201),//用户新建或修改数据成功
    ACCEPTED(202),//表示一个请求已经进入后台排队（异步任务）
    DELETED(204),//用户删除数据成功
    FAIL(400),//用户发出的请求有错误，服务器没有进行新建或修改数据的操作
    UNAUTHORIZED(401),//表示用户没有权限（令牌、用户名、密码错误）
    ADVISORY(4001),//随访跳转聊天室
    FORBIDDEN(403),//表示用户得到授权（与401错误相对），但是访问是被禁止的
    NOT_FOUND(404),//用户发出的请求针对的是不存在的记录，服务器没有进行操作
    INTERNAL_SERVER_ERROR(500),//服务器内部错误
    PAYMENT_ERROR(433);//支付错误


    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
