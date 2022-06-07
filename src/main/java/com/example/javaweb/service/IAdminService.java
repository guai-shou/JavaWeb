package com.example.javaweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.javaweb.entity.Admin;
import com.example.javaweb.entity.vo.AdminVo;

import java.util.List;

public interface IAdminService extends IService<Admin> {
    List<String> getPermissionListById(Object loginId);

    void addAdmin(AdminVo adminVo);

    void deleteAdmin(Integer id);

    AdminVo updateAdmin(AdminVo adminVo);

    List<AdminVo> selectAll();

    AdminVo selectById(Integer id);

    AdminVo login(Admin admin);

    void logout();
}
