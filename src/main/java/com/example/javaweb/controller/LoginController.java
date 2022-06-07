package com.example.javaweb.controller;

import com.example.javaweb.entity.User;
import com.example.javaweb.entity.vo.UserVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/25 20:45
 */
@RestController
public class LoginController {

    @Value("${upload.fileAddress}")
    private String fileAddress;


    @Autowired
    private IUserService userService;

    /* 注册用户 */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        if (user == null || !StringUtils.hasLength(user.getUname())) {
            return Result.build(null, ResultEnum.PARAMETERS_NOT);
        }
        boolean b = userService.queryOneByUname(user.getUname());
        if(b) {
            return Result.build(null, ResultEnum.USER_REPEAT_NAME);
        }
        if (StringUtils.hasLength(user.getPortrait())){
            user.setPortrait(fileAddress + user.getPortrait());
        }
        b = userService.save(user);
        if(b) {
            return Result.build(null, ResultEnum.USER_REGISTER_SUCCESS);
        }
        return Result.build(null, ResultEnum.USER_REGISTER_FAIL);
    }

    /* 用户登录 */
    @PostMapping("/login")
    public Result<User> login(@RequestBody UserVo userVo) {
        User user = userService.login(userVo);
        if (user != null) {
            return Result.build(user, ResultEnum.USER_LOGIN_SUCCESS);
        }
        return Result.build(null, ResultEnum.USER_LOGIN_FAIL);
    }

    /* 用户注销 */
    @GetMapping("/logout")
    public Result<User> logout() {
        try {
            userService.logout();
            return Result.build(null, ResultEnum.USER_LOGOUT_SUCCESS);
        }catch (JavaWebException e) {
            return Result.build(null, ResultEnum.USER_NOT_LOGIN);
        }
    }
}
