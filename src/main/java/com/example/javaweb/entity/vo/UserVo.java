package com.example.javaweb.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/24 14:55
 * 用户登录信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String uname;
    private String passwd;
}
