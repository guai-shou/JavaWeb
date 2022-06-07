package com.example.javaweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/5/31 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("authority")
public class Authority {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("permission_name")
    private String permissionName;
}
