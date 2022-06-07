package com.example.javaweb.result;

import lombok.Data;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/24 9:41
 */
// 返回前端数据的结果集类
@Data
public class Result<T> {
    // 状态码
    private Integer code;
    // 携带的数据
    private T data;
    // 返回信息
    private String message;

    private Result() {}

    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> Result<T> build(T data, ResultEnum resultEnum) {
        Result<T> result = build(data);
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public static <T> Result<T> build(Integer code, String message) {
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return build(data, ResultEnum.OPERATE_SUCCESS);
    }

    public static <T> Result<T> fail() {
        return fail(null);
    }
    public static <T> Result<T> fail(T data) {
        return build(data, ResultEnum.OPERATE_FAIL);
    }

    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    public boolean isOK() {
        return this.getCode().intValue() == ResultEnum.OPERATE_SUCCESS.getCode().intValue();
    }

}
