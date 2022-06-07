package com.example.javaweb.controller;

import com.example.javaweb.entity.Admin;
import com.example.javaweb.entity.vo.AdminVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/6/1 12:20
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminLoginController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    public Result<AdminVo> login(@RequestBody Admin admin) {
        try {
            AdminVo adminVo = adminService.login(admin);
            return Result.build(adminVo, ResultEnum.ADMIN_LOGIN_SUCCESS);
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 管理员注销 */
    @GetMapping("/logout")
    public Result<AdminVo> logout() {
        try {
            adminService.logout();
            return Result.build(null, ResultEnum.ADMIN_LOGOUT_SUCCESS);
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(null, ResultEnum.ADMIN_NOT_LOGIN);
        }
    }
}
