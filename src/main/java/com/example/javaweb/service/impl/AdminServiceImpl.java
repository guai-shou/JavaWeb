package com.example.javaweb.service.impl;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javaweb.entity.Admin;
import com.example.javaweb.entity.Notice;
import com.example.javaweb.entity.vo.AdminVo;
import com.example.javaweb.exception.JavaWebException;
import com.example.javaweb.mapper.AdminMapper;
import com.example.javaweb.mapper.NoticeMapper;
import com.example.javaweb.result.Result;
import com.example.javaweb.result.ResultEnum;
import com.example.javaweb.service.IAdminService;
import com.example.javaweb.service.INoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/26 21:19
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    /* 获取权限列表 */
    @Override
    public List<String> getPermissionListById(Object loginId) {
        return baseMapper.getPermissionListById(loginId);
    }

    @Transactional
    /* 添加管理员 */
    @Override
    public void addAdmin(AdminVo adminVo) {
        if (adminVo == null || !StringUtils.hasLength(adminVo.getName()) ||
                !StringUtils.hasLength(adminVo.getPasswd())) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminVo, admin, Admin.class);
        baseMapper.insert(admin);
        // 将插入成功后的id拿出来进行权限增加
        Admin one = baseMapper.selectOne(new QueryWrapper<Admin>().eq("name", adminVo.getName()));
        baseMapper.insertPermission(one.getId(), adminVo.getPermissionList());
    }

    /* 删除管理员 */
    @Override
    public void deleteAdmin(Integer id) {
        Admin admin = baseMapper.selectById(id);
        if (admin != null) {
            baseMapper.deleteById(id);
            return ;
        }
        throw new JavaWebException(ResultEnum.ADMIN_NOT_EXIST);
    }

    /* 更新管理员 */
    @Transactional
    @Override
    public AdminVo updateAdmin(AdminVo adminVo) {
        if (adminVo == null || adminVo.getId() == null ||
                !StringUtils.hasLength(adminVo.getName()) ||
                !StringUtils.hasLength(adminVo.getPasswd())) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminVo, admin, Admin.class);
        baseMapper.updateById(admin);

        List<String> permissionList = getPermissionListById(admin.getId());
        List<String> adminVoPermissionList = adminVo.getPermissionList();
        // 删除权限
        for (String permission : permissionList) {
            if (!adminVoPermissionList.contains(permission)) {
                baseMapper.deletePermission(adminVo.getId(), permission);
            }
        }
        // 增加权限
        for (String permission : adminVoPermissionList) {
            if (!permissionList.contains(permission)) {
                baseMapper.addPermission(adminVo.getId(), permission);
            }
        }
        return adminVo;
    }

    /* 查询全部管理员 */
    @Override
    public List<AdminVo> selectAll() {
        List<Admin> adminList = baseMapper.selectList(null);
        List<AdminVo> adminVoList = new ArrayList<>();
        for (Admin admin : adminList) {
            AdminVo adminVo = new AdminVo();
            List<String> permissionList = getPermissionListById(admin.getId());
            BeanUtils.copyProperties(admin, adminVo, AdminVo.class);
            adminVo.setPermissionList(permissionList);
            adminVoList.add(adminVo);
        }
        return adminVoList;
    }

    /* 根据ID查询管理员 */
    @Override
    public AdminVo selectById(Integer id) {
        Admin admin = baseMapper.selectById(id);
        AdminVo adminVo = new AdminVo();
        List<String> permissionList = getPermissionListById(admin.getId());
        adminVo.setPermissionList(permissionList);
        BeanUtils.copyProperties(admin, adminVo, AdminVo.class);
        return adminVo;
    }

    @Override
    public AdminVo login(Admin admin) {
        if (admin == null || !StringUtils.hasLength(admin.getName())
                || !StringUtils.hasLength(admin.getPasswd())) {
            throw new JavaWebException(ResultEnum.PARAMETERS_NOT);
        }
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name", admin.getName());
        wrapper.eq("passwd", admin.getPasswd());
        Admin one = baseMapper.selectOne(wrapper);
        if (one == null) {
            throw new JavaWebException(ResultEnum.ADMIN_LOGIN_FAIL);
        }
        List<String> permissionList = baseMapper.getPermissionListById(one.getId());
        StpUtil.login(one.getId());
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(one, adminVo, AdminVo.class);
        adminVo.setPermissionList(permissionList);
        return adminVo;
    }

    @Override
    public void logout() {
        try {
            Integer id = Integer.parseInt((String) StpUtil.getLoginId());
            System.out.println(id);
            StpUtil.logout(id);
        }catch (NotLoginException e) {
            throw new JavaWebException(ResultEnum.USER_NOT_LOGIN);
        }
    }
}
