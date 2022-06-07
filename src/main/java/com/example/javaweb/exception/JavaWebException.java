package com.example.javaweb.exception;

import com.example.javaweb.result.ResultEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/24 12:41
 */
@Getter
@Setter
public class JavaWebException extends RuntimeException{
    private Integer code;

    public JavaWebException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public JavaWebException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    @Override
    public String toString() {
        return "JavaWebException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
