package com.example.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javaweb.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/31 10:14
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select au.permission_name from admin a, admin_authority aa, authority au where a.id = #{loginId} and a.id = aa.a_id and aa.au_id = au.id")
    List<String> getPermissionListById(@Param("loginId") Object loginId);

    void insertPermission(@Param("id") Integer id, @Param("permissionList") List<String> permissionList);

    @Delete("delete from admin_authority where a_id = #{id} and au_id = (select id from authority where permission_name = #{permission})")
    void deletePermission(@Param("id") Integer id, @Param("permission") String permission);

    @Insert("insert into admin_authority (a_id, au_id) value (#{id}, (select id from authority where permission_name = #{permission}))")
    void addPermission(@Param("id") Integer id, @Param("permission") String permission);
}
