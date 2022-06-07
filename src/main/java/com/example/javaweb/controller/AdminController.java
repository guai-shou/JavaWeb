package com.example.javaweb.controller;

import com.example.javaweb.entity.vo.AdminVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.result.Result;
import com.example.javaweb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/31 10:16
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /* 增加管理员 */
    @PostMapping("/add")
    public Result<Void> addAdmin(@RequestBody AdminVo adminVo) {
        try {
            adminService.addAdmin(adminVo);
            return Result.ok();
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 删除管理员 */
    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteAdmin(@PathVariable("id") Integer id) {
        try {
            adminService.deleteAdmin(id);
            return Result.ok();
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 修改管理员 */
    @PutMapping("/update")
    public Result<AdminVo> updateAdmin(@RequestBody AdminVo adminVo) {
        try {
            adminVo = adminService.updateAdmin(adminVo);
            return Result.ok(adminVo);
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 查询管理员 */
    @GetMapping("/select")
    public Result<List<AdminVo>> select() {
        try {
            List<AdminVo> adminVo = adminService.selectAll();
            return Result.ok(adminVo);
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(), e.getMessage());
        }
    }

    /* 根据ID查询管理员信息 */
    @GetMapping("/select/{id}")
    public Result<AdminVo> selectById(@PathVariable("id") Integer id) {
        try {
            AdminVo adminVo = adminService.selectById(id);
            return Result.ok(adminVo);
        }catch (JavaWebException e) {
            e.printStackTrace();
            return Result.build(e.getCode(),  e.getMessage());
        }
    }

}
