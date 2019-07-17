package com.api.core.response;

public enum ResultMessage {
    SUCCESS_MESSAGE("请求成功"),//服务器成功返回用户请求的数据
    CREATED("新建成功"),//用户新建或修改数据成功
    UPLOADED("上传文件成功"),
    MODIFIED("修改成功"),//用户新建或修改数据成功
    NO_CONTENT("没有找到相关数据"),
    NO_CONTENT_TYPE("没有相关类型"),
    NO_RELATED_USER("没有找到相关用户数据"),
    NO_MATCH_USER("用户数据与相应请求数据不匹配"),
    NO_MATCH_PAYMENT("没有匹配的支付信息"),
    DELETED("删除成功"),//用户删除数据成功
    FAIL("请求失败"),//用户发出的请求有错误，服务器没有进行新建或修改数据的操作
    UNAUTHORIZED("用户没有权限"),//表示用户没有权限（令牌、用户名、密码错误）
    FORBIDDEN("用户禁止访问该内容"),//表示用户得到授权（与401错误相对），但是访问是被禁止的
    NOT_FOUND("服务器没有相应操作"),//用户发出的请求针对的是不存在的记录，服务器没有进行操作
    INTERNAL_SERVER_ERROR("服务器内部错误"),//服务器内部错误
    REGISTER_FAIL("注册失败，请检查用户名与密码等信息是否符合规则,或者是账户名已被注册"),
    LOGIN_FAIL("登录失败，请检查用户名和密码(验证码)是否正确"),
    CREATE_FAIL("新建数据失败"),
    DELETE_FAIL("无法删除数据，因为数据在其它表中仍被使用，请先删除子表中数据"),
    NO_IMAGE_PARAM("参数中无图片输入"),
    IMG_UPLOAD_FAIL("上传图片失败,请再次上传图片"),
    UPDATE_FAIL("更新信息失败"),
    WRONG_PASSWORD("密码错误"),
    DATA_ALREADY_EXISTS("数据已存在"),
    EXTERNAL_USER_MISSING("没有该用户或改用户不可用"),
    DATE_ENTRY_ERROR("输入数据有误"),
    QTY_SHORTAGE("库存不足"),
    NO_UNDELIVERED_MESSAGE("所有信息已经传递完毕"),
    ORDER_STATUS_UNAVAILABLE("订单的状态不可被取消"),
    CANCEL_SUCCESS("取消成功"),
    CODE_ERROR("验证码输入错误"),
    UNPAID("未付款"),
    ORDER_FAIL("订单创建失败"),
    PAYOUT_AMOUNT_ERROR("提现金额需大于1元，小于50000元"),
    PAYOUT_ACCOUNT_NO_CONTENT("未绑定支付账户"),
    NO_BILL_ACCOUNT("没有支付账号或支付账号不正确"),
    MOBILE_ALREADY_EXTSTS("该手机号已注册"),
    NEED_UPDATE("请求失败，需要升级客户端版本"),
    NEED_BIND_MOBILE("用户需要绑定手机号"),
    EXIST_NICKNAME("该昵称已存在"),
    NULL("数据不能为空"),
    BAN("账号已封禁"),
    PARAM_ILLEGAL("非法参数")
    ;






    private final String message;

    ResultMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
