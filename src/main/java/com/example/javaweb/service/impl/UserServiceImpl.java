package com.example.javaweb.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javaweb.entity.User;
import com.example.javaweb.entity.vo.CargoVo;
import com.example.javaweb.entity.vo.UserVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.mapper.UserMapper;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public boolean queryOneByUname(String uname) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("uname", uname);
        return baseMapper.selectOne(wrapper) != null;
    }

    @Override
    public User login(UserVo userVo) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        boolean b = Optional.ofNullable(userVo).isEmpty() ||
                    Optional.ofNullable(userVo.getUname()).isEmpty() ||
                    Optional.ofNullable(userVo.getPasswd()).isEmpty();
        if(b) {
            return null;
        }
        wrapper.eq("uname", userVo.getUname())
                .eq("passwd", userVo.getPasswd());
        User user = baseMapper.selectOne(wrapper);
        if (user != null) {
            StpUtil.login(user.getId());
            return user;
        }
        return null;
    }

    @Override
    public void logout() throws JavaWebException {
        try {
            Integer id = Integer.parseInt((String) StpUtil.getLoginId());
            System.out.println(id);
            StpUtil.logout(id);
        }catch (NotLoginException e) {
            throw new JavaWebException(ResultEnum.USER_NOT_LOGIN);
        }
    }

    @Override
    public User addCargo(CargoVo cargoVo) {
        Integer id = Integer.parseInt((String) StpUtil.getLoginId());
        User user = baseMapper.selectById(id);
        user.setCargoName(cargoVo.getCargoName());
        user.setCargoPhone(cargoVo.getCargoPhone());
        user.setCargoAddress(cargoVo.getCargoAddress());
        boolean b = this.saveOrUpdate(user);
        if (b) {
            return user;
        }
        return null;
    }

    @Override
    public User deleteUser(Integer id) {
        User user = baseMapper.selectById(id);
        baseMapper.deleteById(id);
        return user;
    }

    @Override
    public Page<User> pageQuery(Integer current, Integer limit) {
        Page<User> page = new Page<>(current, limit);
        return baseMapper.selectPage(page, null);
    }
}
