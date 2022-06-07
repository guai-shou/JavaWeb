package com.example.javaweb.result;

public enum ResultEnum {
    // 1xx 系统操作
    OPERATE_SUCCESS(100, "操作成功"),
    OPERATE_FAIL(110, "操作失败"),
    PARAMETERS_NOT(111, "参数错误"),
    // 2xx 用户
    USER_LOGIN_SUCCESS(200, "登录成功"),
    USER_LOGOUT_SUCCESS(201, "注销成功"),
    USER_REGISTER_SUCCESS(202, "注册成功"),
    USER_NOT_REPEAT_NAME(203, "没有重复用户名"),
    USER_LOGIN_FAIL(210, "账号或密码错误"),
    USER_NOT_LOGIN(211, "账户没有登录"),
    USER_REGISTER_FAIL(212, "注册失败"),
    USER_NOT_CARGO_INFO(213, "用户无收获信息"),
    USER_REPEAT_NAME(214, "该用户名已存在"),

    // 3xx 书籍
    BOOK_NOT_FOUND(310, "没有找到该书籍"),
    BOOK_INVALID(311, "部分书籍失效"),
    BOOK_STOCK_LACK(312, "部分书籍不足"),

    // 4xx 订单
    ORDER_NOT_FOUND(410, "订单不存在"),

    // 5xx 购物车
    CART_NOT_BOOK(510, "购物车不存在此商品"),
    CART_NOT_BOOKS(511, "购物车不存在这些商品"),

    // 6xx 文件
    FILE_NOT_SELECT(610, "未选择文件"),
    FILE_UPLOAD_FAIL(611, "文件上传失败"),

    // 7xx 管理员
    ADMIN_LOGIN_SUCCESS(701, "管理员登录成功"),
    ADMIN_LOGOUT_SUCCESS(702, "管理员注销成功"),
    ADMIN_NOT_PERMISSION(711, "该管理员没有此权限"),
    ADMIN_NOT_EXIST(712, "该管理员不存在"),
    ADMIN_LOGIN_FAIL(713, "管理员账户或密码错误"),
    ADMIN_NOT_LOGIN(714, "管理员没有登录")
    ;


    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
