package com.example.javaweb.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.User;
import com.example.javaweb.entity.vo.CargoVo;
import com.example.javaweb.entity.vo.UserVo;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IUserService;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Value("${upload.fileAddress}")
    private String fileAddress;

    @Autowired
    private IUserService userService;

    /* 查询用户名 */
    @GetMapping("/selectUname/{uname}")
    public Result<User> selectUname(@PathVariable("uname") String uname) {
        boolean b = userService.queryOneByUname(uname);
        if(b) {
            return Result.build(null, ResultEnum.USER_REPEAT_NAME);
        }
        return Result.build(null, ResultEnum.USER_NOT_REPEAT_NAME);
    }


    @SaCheckLogin
    /* 查询用户列表 */
    @GetMapping("/selectAll")
    public Result<List<User>> selectAll() {
        return Result.ok(userService.list());
    }


    @SaCheckLogin
    /* 查询当前用户信息 */
    @GetMapping("/select")
    public Result<User> select() {
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        return Result.ok(userService.getById(id));
    }

    @SaCheckLogin
    /* 分页查询 */
    @GetMapping("/{current}/{limit}")
    public Result<Page<User>> pageQuery(@PathVariable("current") Integer current,
                                        @PathVariable("limit") Integer limit) {
        return Result.ok(userService.pageQuery(current, limit));
    }


    @SaCheckLogin
    /* 修改用户信息 */
    @PutMapping("/update")
    public Result<User> update(@RequestBody User user) {
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        if (!id.equals(user.getId())) {
            return Result.fail();
        }
        if (user.getNickname() == null) {
            return Result.build(null, ResultEnum.PARAMETERS_NOT);
        }
        System.out.println(user.getPortrait());
        if (StringUtils.hasLength(user.getPortrait())) {
            user.setPortrait(fileAddress + user.getPortrait());
        }
        boolean b = userService.saveOrUpdate(user);
        if (b) {
            return Result.ok();
        }
        return  Result.fail();
    }

    @SaCheckLogin
    /* 添加用户快递信息 */
    @PutMapping("/addCargo")
    public Result<User> addCargo(@RequestBody CargoVo cargoVo) {
        User user = userService.addCargo(cargoVo);
        if (user != null) {
            return Result.ok(user);
        }
        return Result.fail();
    }

    @SaCheckLogin
    /* 永久注销当前用户 */
    @DeleteMapping("/delete/{id}")
    public Result<User> delete(@PathVariable("id") Integer id) {
        User user = userService.deleteUser(id);
        if (user != null) {
            return Result.ok(user);
        }
        return Result.fail();
    }
}
