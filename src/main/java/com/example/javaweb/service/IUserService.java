package com.example.javaweb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javaweb.entity.vo.CargoVo;
import com.example.javaweb.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
public interface IUserService extends IService<User> {
    boolean queryOneByUname(@Param("uname") String uname);

    User login(UserVo userVo);

    void logout();

    User addCargo(CargoVo cargoVo);

    User deleteUser(Integer id);

    Page<User> pageQuery(Integer current, Integer limit);
}
